pipeline {
    agent any
    stages {
        stage('Build Maven') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }
        stage('Docker Build') {
            steps {
                dir('presentacion') {
                    bat 'docker build -t apimonedas .'
                }
            }
        }
        stage('Deploy') {
            steps {
                bat 'docker rm -f dockerapimonedas || exit 0'
                bat 'docker run -d --name dockerapimonedas -p 8081:8081 apimonedas'
            }
        }
    }
}
