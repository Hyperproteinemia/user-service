pipeline {
    agent none
    stages {
        stage('Clean workspace') {
            agent {
                docker {
                    image 'maven:3.6.3-jdk-8-slim'
                }
            }
            steps {
                sh 'mvn clean'
                sh 'mv src/main/resources/application_example.yml src/main/resources/application.yml'
                sh 'sed -i "s/password:.*/password: ${userpwd}/" src/main/resources/application.yml'
            }
        }
        stage('Build application') {
            agent {
                docker {
                    image 'maven:3.6.3-jdk-8-slim'
                }
            }
            steps {
                sh 'mvn install'
            }
        }
        stage('Build docker image') {
            agent any
            steps {
                sh 'docker image rm miraclewisp/hperproteinaemia-users || true'
                sh 'docker build -t miraclewisp/hperproteinaemia-users:${BUILD_NUMBER} -t miraclewisp/hperproteinaemia-users:latest .'
            }

        }
        stage('Push docker image') {
            agent any
            steps {
                withDockerRegistry([credentialsId: "dockerhub", url: ""]) {
                    sh 'docker push miraclewisp/hperproteinaemia-users:${BUILD_NUMBER}'
                    sh 'docker push miraclewisp/hperproteinaemia-users:latest'
                }
            }

        }
        stage('Deploy') {
            agent any
            steps {
                sh 'ssh Rinslet docker stop users || true'
                sh 'ssh Rinslet docker image rm miraclewisp/hperproteinaemia-users || true'
                sh 'ssh Rinslet docker pull miraclewisp/hperproteinaemia-users'
                sh 'ssh Rinslet docker run --rm --name users -d -p 8081:8081 miraclewisp/hperproteinaemia-users'
            }
        }
    }
}