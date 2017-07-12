node {
    // Variable for the Docker image to build
    def image

    stage('Checkout') {

        echo 'Checking out ...'
        git branch: 'wasline', url: 'http://gitlab/root/jee-7-demo-app.git'
    }

    stage('Build') {
        echo 'Building ...'

        withMaven(globalMavenSettingsConfig: 'mavenGlobalSettings', maven: 'Maven 3.5.0') {
            sh 'mvn -B -V clean compile'
        }
    }

    stage('Test') {
        echo 'Testing ...'

        withMaven(globalMavenSettingsConfig: 'mavenGlobalSettings', maven: 'Maven 3.5.0') {
            sh 'mvn -B verify'
        }
    }

    stage('Sonar') {
        echo 'Code Analysis ...'

        withMaven(globalMavenSettingsConfig: 'mavenGlobalSettings', maven: 'Maven 3.5.0') {
            sh 'mvn -B sonar:sonar'
        }
    }

    stage('Build Docker image') {
        echo 'Building docker image....'

        image = docker.build("jee7demo", 'wasliberty')
    }

    stage('Push Docker image') {
        echo 'Pushing docker image to private registry ....'

        withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'PASSWORD', usernameVariable: 'USER')]) {
            // Workaround - see issue https://issues.jenkins-ci.org/browse/JENKINS-38018
            sh "docker login -u $env.USER -p $env.PASSWORD https://localhost:18500/"

            // Tagging images
            sh "docker tag ${image.imageName()} localhost:18500/${image.imageName()}:1.0-SNAPSHOT"
            sh "docker tag ${image.imageName()} localhost:18500/${image.imageName()}:latest"

            // Actually pushing the images
            sh "docker push localhost:18500/${image.imageName()}:latest"
            sh "docker push localhost:18500/${image.imageName()}:1.0-SNAPSHOT"

            sh 'docker logout https://localhost:18500/'
        }

        echo 'Pushing docker image to Docker Hub ....'

        withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'PASSWORD', usernameVariable: 'USER')]) {
            // Workaround - see issue https://issues.jenkins-ci.org/browse/JENKINS-38018
            sh "docker login -u $env.USER -p $env.PASSWORD"

            // Tagging images
            sh "docker tag ${image.imageName()} $env.USER/${image.imageName()}:1.0-SNAPSHOT"
            sh "docker tag ${image.imageName()} $env.USER/${image.imageName()}:latest"

            // Actually pushing the images
            sh "docker push $env.USER/${image.imageName()}:latest"
            sh "docker push $env.USER/${image.imageName()}:1.0-SNAPSHOT"

            sh 'docker logout'
        }
    }

    stage('Deploy') {
        echo 'Uploading Maven artifact(s) ...'

        withMaven(globalMavenSettingsConfig: 'mavenGlobalSettings', maven: 'Maven 3.5.0') {
            sh "mvn -B deploy"
        }
    }

    stage('To k8s') {
        sh "curl http://127.0.0.1:8080/apis/extensions/v1beta1/namespaces/default/deployments -X POST -H'Content-Type: application/json' -d @jeedemo-deployment.json"
    }
}
