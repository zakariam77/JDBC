pipeline{

        agent any

        tools{
                jdk 'JDK21'
                maven '3.6.3'
        }

        parameters {
            string(name: 'profile', defaultValue: 'SqlTest', description: 'select profile')
        }
        stages{
        stage('build'){
        steps{

        sh 'mvn test -P${params.profile}'

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