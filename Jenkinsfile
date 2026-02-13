pipeline {
    agent any

    environment {
        IMAGE_NAME = "demo-api"
        CONTAINER_NAME = "demo-api"
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
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Docker Run') {
            steps {
                sh 'docker stop $CONTAINER_NAME || true'
                sh 'docker rm $CONTAINER_NAME || true'
                sh 'docker run -d -p 8080:8080 --name $CONTAINER_NAME $IMAGE_NAME'
            }
        }

        stage('Verify') {
            steps {
                sh 'echo "Pipeline completed. Access your app at http://localhost:8080/hello"'
            }
        }
    }
}
