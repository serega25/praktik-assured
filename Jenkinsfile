pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                git 'http://github.com/serega25/praktik-assured'
                sh './mvnw clean compile'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }

            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
    }
}