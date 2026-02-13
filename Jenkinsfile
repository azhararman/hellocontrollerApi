pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/azhararman/hellocontrollerApi.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Run Test Command') {
            steps {
                sh 'echo Build Successful'
            }
        }
    }
}
