pipeline {
    agent {
        docker {
            image 'maven:3.9.1-eclipse-temurin-17'
            args '-v /root/.m2:/root/.m2'
        }
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/azhararman/hellocontrollerApi.git'
            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t demo-api .'
            }
        }

        stage('Docker Run') {
            steps {
                sh 'docker stop demo-api || true'
                sh 'docker rm demo-api || true'
                sh 'docker run -d -p 8080:8080 --name demo-api demo-api'
            }
        }

        stage('Verify') {
            steps {
                sh 'echo "Pipeline finished. Visit http://localhost:8080/hello"'
            }
        }
    }
}
