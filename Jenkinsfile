pipeline {
    agent any

    stages {
        stage('Checkout') {
            echo 'Checking out ...'
            git branch: 'master', url: 'https://github.com/oraum/jee-7-demo-app.git'
        }

        stage('Build') {
            echo 'Building ...'
            withMaven {
                sh 'mvn -B -V clean compile'
            }
        }

        stage('Test') {
            echo 'Testing ...'
        }
    }
}