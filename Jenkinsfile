pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                git("https://github.com/serega25/praktik-assured") {
                sh 'mvn -B -DskipTests clean package'
                }
            }
        }
     }
    post {
       always {
          junit(
        allowEmptyResults: true,
        testResults: '*/test-reports/.xml'
      )
      }
   }
}