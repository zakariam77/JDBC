pipeline{

        agent any

        tools{
                jdk 'JDK21'
                maven '3.6.3'
        }

        environment {
        // Defines the path to your compose file if it is not in the root directory
        COMPOSE_FILE = 'docker-compose.yaml'
        }


        parameters {
            string(name: 'PROFILE', defaultValue: 'ultimate', description: 'ultimate | regression')
            string(name: 'BROWSER', defaultValue: 'Chrome', description: 'Default chrome | firefox')
        }
        stages{

        stage('Stop Existing Grid') {
            steps {
                echo 'Stopping docker compose to avoid conflicts'
                sh "docker compose -f ${env.COMPOSE_FILE} down"
        }
        }
        stage('Launch Selenium Grid') {
            steps {
                echo 'Starting up ephemeral Selenium Grid'
                sh "docker compose -f ${env.COMPOSE_FILE} up -d"
                sh 'docker compose ps'
        }
        }

        stage('build'){
            steps{
                echo 'Cleanup Existing Build'
                sh 'mvn clean'
                echo 'Running parallel TestNG suite'
                sh "mvn test -P${params.PROFILE} -Dbrowser=${params.BROWSER}"

        }
        }
        }

        post{
            always {
                echo 'Tearing down Selenium Grid to free system resources...'
                sh 'docker compose -f docker-compose.yaml down'
                echo 'Processing test results...'
                junit '**/target/surefire-reports/TEST-*.xml'
                allure results: [[path: 'target/allure-results']]
        }

            success{
                echo 'Build Success'

                // not configured yet
                emailext (
                           to: '$DEFAULT_RECIPIENTS',
                           subject: "Success: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]",
                           body: 'Pipeline completed successfully. View reports in Jenkins.',
                           attachLog: true
                        )
        }
            failure{
                echo 'Build Failed'

                // not configured yet
                emailext (
                            to: '$DEFAULT_RECIPIENTS',
                            subject: "Failure: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]",
                            body: 'Tests failed or pipeline crashed. Check console log.',
                            attachLog: true
                          )
        }

        }


}