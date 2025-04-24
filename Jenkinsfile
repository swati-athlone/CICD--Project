pipeline {
    agent any

    environment {
        SONARQUBE_URL = 'http://sonarqube_server:9000'
        SONARQUBE_TOKEN = credentials('sonarqube-token') // Store in Jenkins credentials
        DOCKER_IMAGE = 'my-app:latest'
        DOCKER_REGISTRY = 'my-dockerhub-username/my-app'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/YOUR_GITHUB_USERNAME/YOUR_REPO.git'
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn clean package -DskipTests' // Build without running tests initially
            }
        }

        stage('Run Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Run Integration Tests') {
            steps {
                sh 'mvn verify'
            }
        }

        stage('SonarQube Code Analysis') {
            steps {
                script {
                    withSonarQubeEnv('SonarQube') {
                        sh 'mvn sonar:sonar -Dsonar.projectKey=my-project -Dsonar.host.url=$SONARQUBE_URL -Dsonar.login=$SONARQUBE_TOKEN'
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }

        stage('Push Docker Image to Registry') {
            steps {
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    sh 'docker tag $DOCKER_IMAGE $DOCKER_REGISTRY'
                    sh 'docker push $DOCKER_REGISTRY'
                }
            }
        }

        stage('Deploy with Ansible') {
            steps {
                sh 'ansible-playbook -i inventory deploy.yml'
            }
        }
    }
}
