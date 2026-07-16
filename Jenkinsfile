pipeline{

        agent any

        tools{
                jdk 'JDK21'
                maven '3.6.3'
        }

        parameters {
            string(name: 'PROFILE', defaultValue: 'SqlTest', description: 'select_profile')
        }
        stages{
        stage('cleaup') {
        steps{
                    sh 'mvn clean'

        }
        }
        stage('build'){
        steps{

        sh "mvn test -P${params.PROFILE}"

        }
        }
        }

        post{
        always {
            junit '**/target/surefire-reports/TEST-*.xml'
            allure results: [[path: 'target/allure-results']]
        }

        success{
        echo 'build success'
        }
        failure{
        echo 'build fail'
        }

        }


}