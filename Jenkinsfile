pipeline{

        agent any

        tools{
                jdk 'JDK21'
                maven '3.6.3'
        }
        /*
        environment {
        // Defines the path to your compose file if it is not in the root directory
        COMPOSE_FILE = 'docker-compose.yml'
        }
        */

        parameters {
            string(name: 'PROFILE', defaultValue: 'SqlTest', description: 'select_profile')
            string(name: 'BROWSER', defaultValue: 'Chrome', description: 'Browser_Type')
        }
        stages{
        stage('docker compose down') {
        steps {
            echo 'stopping docker compose to avoid conflicts'
            sh 'sudo docker compose -f down'
        }
        }
        stage('docker compse up') {
        steps {
            echo 'starting up docker compose'
            sh 'docker compose -f up'
        }
        }
        stage('Verify Deployment') {
        steps {
            echo 'verifying docker is up'
            sh 'sudo docker compose ps'
        }
        }
        stage('build cleaup') {
        steps{
              sh 'mvn clean'

        }
        }
        stage('build'){
        steps{

        sh "mvn test -P${params.PROFILE} -Dbrowser=${params.BROWSER}"

        }
        }
        }

        post{
        always {
            junit '**/target/surefire-reports/TEST-*.xml'
            allure results: [[path: 'target/allure-results']]
            emailext (
               to: '$DEFAULT_RECIPIENTS',
               subject: '$DEFAULT_SUBJECT',
               body: '$DEFAULT_CONTENT',
               attachLog: true
            )
        }

        success{
        echo 'build success'
        }
        failure{
        echo 'build fail'
        }

        }


}