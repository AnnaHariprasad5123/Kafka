pipeline {
    agent any

    stages {
        stage("continuous cloning"){
            steps {
            git branch: 'FinalAssignment', credentialsId: 'githubtoken', url: 'https://github.com/AnnaHariprasad5123/Kafka.git/'
            }
        }
        stage("build"){
            steps {
                  sh """
                    cd /var/lib/jenkins/workspace/webhook_test/FinalAssignment/order &&
                    mvn clean package
                """
            }
        }
    }
}
