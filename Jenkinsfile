node {

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

    stage('Build Docker image') {
        echo 'Building docker image....'

        image = docker.build("jee7demo", 'wasliberty')
    }

    stage('Push Docker image') {
        echo 'Pushing docker image....'

        withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'PASSWORD', usernameVariable: 'USER')]) {
            // Workaround - see issue https://issues.jenkins-ci.org/browse/JENKINS-38018
            sh "docker login -u $env.USER -p $env.PASSWORD https://localhost:18500/"

            // Tagging images
            sh "docker tag ${image.imageName()} localhost:18500/${image.imageName()}:1.0-SNAPSHOT"
            sh "docker tag ${image.imageName()} localhost:18500/${image.imageName()}:latest"

            // Actually pushing the images
            sh "docker push localhost:18500/${image.imageName()}:1.0-SNAPSHOT"
            sh "docker push localhost:18500/${image.imageName()}:1.0-SNAPSHOT"

            sh 'docker logout https://localhost:18500/'
        }
    }
}
