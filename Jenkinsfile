pipeline {
  agent any
  stages {
    stage('Build Maven') {
      steps {
        sh 'mvn clean package -DskipTests'
      }
    }
    stage('Docker Build') {
      steps {
        dir('presentacion') {
          sh 'docker build -t apimonedas .'
        }
      }
    }
    stage('Deploy') {
      steps {
        sh 'docker rm -f dockerapimonedas || true'
        sh 'docker run -d --name dockerapimonedas -p 8080:8080 apimonedas'
      }
    }
  }
}
