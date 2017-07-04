pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out ...'
                git branch: 'wasline', url: 'http://gitlab/root/jee-7-demo-app.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building ...'
                withMaven(maven: 'Maven 3.5.0') {
                    sh 'mvn -B -V clean compile'
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Testing ...'
                withMaven(globalMavenSettingsConfig: 'mavenGlobalSettings', maven: 'Maven 3.5.0') {
                    sh 'mvn -B verify'
                }
            }
        }
    }
}