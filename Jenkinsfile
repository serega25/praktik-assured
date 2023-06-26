pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                git 'http://github.com/serega25/praktik-assured'
                bat 'mvn clean'

            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }

            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
    }
}