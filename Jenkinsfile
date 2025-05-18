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
        stage('Limpiar contenedor existente') {
            steps {
                script {
                    catchError(buildResult: 'SUCCESS', stageResult: 'UNSTABLE') {
                        bat """
                        docker container inspect dockerapimonedas >nul 2>&1 && (
                            docker container stop dockerapimonedas
                            docker container rm dockerapimonedas
                        ) || echo "No existe el contenedor 'dockerapimonedas'."
                        """
                    }
                }
            }
        }


        stage('Desplegar Contenedor Docker') {
            steps {
                script {
                    bat 'docker container run --network dockerbddivisionpolitica_reddivisionpolitica --name dockerapimonedas -p 8081:8081 -d apimonedas'
                    }
            }
        }
    }
}

