node {
  
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
    
    
    stage('package') {
        echo 'packaging ear file ...'
        withMaven(globalMavenSettingsConfig: 'mavenGlobalSettings', maven: 'Maven 3.5.0') {
          sh 'mvn -B package'
        }
    }

    stage('docker build/push') {

        echo 'building docker image....'
          docker.withRegistry('http://nexus:18500/', 'nexus') {
          def app = docker.build("jee7demo:1.0-SNAPSHOT", 'wasliberty').push()
        }
  
    }
}