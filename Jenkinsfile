pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'apimonedas'
        CONTAINER_NAME = 'dockerapimonedas'
        DOCKER_NETWORK = 'dockerdivisionpolitica_red'
        DOCKER_BUILD_DIR = 'presentacion'
        HOST_PORT = '9081'
        CONTAINER_PORT = '8081'
    }

    stages {
        stage('Build Maven') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                dir("${DOCKER_BUILD_DIR}") {
                    bat "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Limpiar contenedor existente') {
            steps {
                script {
                    catchError(buildResult: 'SUCCESS', stageResult: 'UNSTABLE') {
                        bat """
                        docker container inspect ${CONTAINER_NAME} >nul 2>&1 && (
                            docker container stop ${CONTAINER_NAME}
                            docker container rm ${CONTAINER_NAME}
                        ) || echo "No existe el contenedor '${CONTAINER_NAME}'."
                        """
                    }
                }
            }
        }

        stage('Desplegar Contenedor Docker') {
            steps {
                script {
                    bat "docker container run --network ${DOCKER_NETWORK} --name ${CONTAINER_NAME} -p ${HOST_PORT}:${CONTAINER_PORT} -d ${DOCKER_IMAGE}"
                }
            }
        }
    }
}