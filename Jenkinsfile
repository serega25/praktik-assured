pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                git 'http://github.com/serega25/praktik-assured'
                bat 'mvn -Dmaven.test.failure.ignore=true install'
            }
        }
        post {
            success {
                 junit 'target/surefire-reports/**/*.xml'
            }
        }
    }
}