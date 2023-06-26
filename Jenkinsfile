pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                git 'http://github.com/serega25/praktik-assured'
                bat 'mvn clean compile'

            }
        }
        stage('Test') {
            steps {
                bat './mvnw test'
            }

            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
    }
}