pipeline {
    agent {
        docker {
            image 'maven:3.9.1-eclipse-temurin-17'
            args '-v $HOME/.m2:/root/.m2 -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }

    environment {
        DOCKER_IMAGE = "demo-api"
        CONTAINER_NAME = "demo-api"
        APP_PORT = "8080"
    }

    stages {

        stage('Checkout') {
            steps {
                // Pull the project from Git
                git branch: 'main', url: 'https://github.com/azhararman/hellocontrollerApi.git'
            }
        }

        stage('Build Maven') {
            steps {
                echo "Building Spring Boot project with Maven..."
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo "Building Docker image..."
                sh "docker build -t $DOCKER_IMAGE ."
            }
        }

        stage('Stop Existing Container') {
            steps {
                echo "Stopping any running container..."
                sh "docker stop $CONTAINER_NAME || true"
                sh "docker rm $CONTAINER_NAME || true"
            }
        }

        stage('Run Docker Container') {
            steps {
                echo "Running Docker container..."
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
