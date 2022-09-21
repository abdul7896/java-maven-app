pipeline{
    agent any
    tools {
        maven "mvn-3.8.6"
    }
    stages{
        stage("build jar"){
            steps{
                script {
                    echo "building the application"
                    sh "mvn package"
                }
            }
        }

          stage("build image"){
            steps{
                script {
                    echo "building the docker image"
                    withCredentials([usernamePassword(
                        credentialsId: '71ef5aa5-53bc-40e9-bdf6-4cfd27c1aa7e',
                        passwordVariable: 'PASS',
                        usernameVariable: 'USER'
                        )]){
                            sh 'docker build -t jma:2.0 .'
                            sh "echo $PASS | docker login -u $USER --password-stdin"
                            sh 'docker push'
                        }
                }
            }
    }
        stage ("deploy"){
            steps {
                script {
                    echo "deploying the application"
                }
            }
        }
}
}
