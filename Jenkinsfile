pipeline {
    agent any

    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_PROJECT_KEY = 'MyProject'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/your-repo.git'
            }
        }

        stage('Build Process') {
            steps {
                script {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Code Analysis') {
            steps {
                script {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=${SONAR_PROJECT_KEY} -Dsonar.host.url=${SONAR_HOST_URL}'
                }
            }
        }

        stage('Testing') {
            steps {
                script {
                    sh 'mvn test'
                }
            }
        }

        stage('Containerization') {
            steps {
                script {
                    sh 'docker build -t my-app .'
                }
            }
        }

        stage('Deployment') {
            steps {
                script {
                    sh 'ansible-playbook -i inventory deploy.yml'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}

