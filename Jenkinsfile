pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "demo-api"
        CONTAINER_NAME = "demo-api"
        APP_PORT = "8080"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'v2', url: 'https://github.com/azhararman/hellocontrollerApi.git'
            }
        }

        stage('Build with Maven (Docker)') {
            steps {
                script {
                    docker.image('maven:3.9.1-eclipse-temurin-17').inside('-v $HOME/.m2:/root/.m2') {
                        sh 'mvn clean package -DskipTests'
                    }
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t $DOCKER_IMAGE ."
            }
        }

        stage('Stop Existing Container') {
            steps {
                sh "docker stop $CONTAINER_NAME || true"
                sh "docker rm $CONTAINER_NAME || true"
            }
        }

        stage('Run Docker Container') {
            steps {
                sh "docker run -d -p $APP_PORT:8080 --name $CONTAINER_NAME $DOCKER_IMAGE"
            }
        }

        stage('Verify') {
            steps {
                echo "Pipeline finished. Visit: http://localhost:$APP_PORT/hello"
            }
        }
    }

    post {
        failure {
            echo "❌ Pipeline failed! Check console logs."
        }
        success {
            echo "✅ Pipeline completed successfully!"
        }
    }
}
