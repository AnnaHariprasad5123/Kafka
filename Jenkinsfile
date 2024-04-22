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
            post {
                success {
                    emailext(
                        subject: "Build Successful",
                        body: "The build for FinalAssignment/order was successful.",
                        to: "hariprasad.anna@zemosolabs.com",
                        from: "annahari.ram97@gmail.com"
                    )
                }
                failure {
                    emailext(
                        subject: "Build Failed",
                        body: "The build for FinalAssignment/order has failed.",
                        to: "hariprasad.anna@zemosolabs.com",
                        from: "annahari.ram97@gmail.com"
                    )
                }
            }
        }
    }
}
