pipeline{

        agent any

        tools{
                jdk 'JDK21'
                maven '3.6.31'
        }

        stages{

        stage('clean'){
        steps{

        sh 'mvn clean'

        }
        }
        stage('build'){
        steps{

        sh 'mvn test -PSqlTest'

        }
        }
        }

        post{
        always {
            junit '**/target/surefire-reports/TEST-*.xml'
            allure results [[path: 'target/allure-results']]
        }

        success{
        echo 'build success'
        }
        failure{
        echo 'build fail'
        }

        }


}