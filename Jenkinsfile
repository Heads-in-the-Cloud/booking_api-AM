pipeline {
    agent any

    stages {
        stage('System Information') {
            steps {
                echo 'Printing Useful Info'
                sh 'ls'
                sh 'pwd'
            }
        }
        stage('Build') {
            steps {
                echo 'Attempting to build Docker image'
                sh 'docker build -t amattsonsm/users-api .'
            }
        }
        stage('Test') {
            steps {
                echo 'Run container and test - not implemented'
            }
        }
    }
}
