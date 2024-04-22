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
                always {
                    script {
                        def jobName = env.JOB_NAME
                        def buildNumber = env.BUILD_NUMBER
                        def pipelineStatus = currentBuild.result ?: 'UNKNOWN'
                        def bannerColor = pipelineStatus.toUpperCase() == 'SUCCESS' ? 'green' : 'red'
                        def body = """<html>
                        <body>
                        <div style="border: 4px solid ${bannerColor}; padding: 10px;">
                        <h2>${jobName} - Build ${buildNumber}</h2>
                        <div style="background-color: ${bannerColor}; padding: 10px;">
                        <h3 style="color: white;">Pipeline Status: ${pipelineStatus.toUpperCase()}</h3>
                        </div>
                        <p>Check the <a href="${BUILD_URL}">console output</a>.</p>
                        </div>
                        </body>
                        </html>"""
                        emailext (
                            subject: "${jobName} - Build ${buildNumber} - ${pipelineStatus.toUpperCase()}",
                            body: body,
                            to: 'hariprasad.anna@zemosolabs.com',
                            from: 'annahari.ram97@gmail.com',
                            replyTo: 'annahari.ram97@gmail.com',
                            mimeType: 'text/html',
                        )
                    }
                }
            }
        }
    }
}
