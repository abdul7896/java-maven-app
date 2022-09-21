#!/usr/bin/env groovy

/* groovylint-disable-next-line CompileStatic */
pipeline {
    agent none
    stages {
        stage('build') {
            when {
                expression {
                    BRANCH_NAME == 'main'
                }
            }
            steps {
                script {
                    echo 'Building the application...'
                    echo "Executing pipeline for ${BRANCH_NAME}"
                }
            }
        }
        stage('test') {
            steps {
                script {
                    echo 'Testing the application...'
                }
            }
        }
        stage('deploy') {
             when {
                expression {
                    BRANCH_NAME == 'main'
                }
            }
            steps {
                script {
                    echo 'Deploying the application...'
                }
            }
        }
    }
}




// #!/usr/bin/env groovy

// library identifier: 'jenkins-shared-library@master', retriever: modernSCM(
//     [$class: 'GitSCMSource',
//      remote: 'https://gitlab.com/nanuchi/jenkins-shared-library.git',
//      credentialsId: 'gitlab-credentials'
//     ]
// )

// pipeline {
//     agent any
//     tools {
//         maven "mvn-3.8.6"
//     }
//     environment {
//         IMAGE_NAME = 'nanajanashia/demo-app:java-maven-2.0'
//     }
//     stages {
//         stage('build app') {
//             steps {
//                script {
//                   echo 'building application jar...'
//                   buildJar()
//                }
//             }
//         }
//         stage('build image') {
//             steps {
//                 script {
//                    echo 'building docker image...'
//                    buildImage(env.IMAGE_NAME)
//                    dockerLogin()
//                    dockerPush(env.IMAGE_NAME)
//                 }
//             }
//         }

//         stage('provision server') {
//             // provision server
            

//         }


//         stage('deploy') {
//             steps {
//                 script {
//                    echo 'deploying docker image to EC2...'

//                    def shellCmd = "bash ./server-cmds .sh ${IMAGE_NAME}"
//                    def ec2Instance = "ec2-user@35.180.251.121"

//                    sshagent(['ec2-server-key']) {
//                        sh "scp -o StrictHostKeyChecking=no server-cmds.sh ${ec2Instance}:/home/ec2-user"
//                        sh "scp -o StrictHostKeyChecking=no docker-compose.yaml ${ec2Instance}:/home/ec2-user"
//                        sh "ssh -o StrictHostKeyChecking=no ${ec2Instance} ${shellCmd}"
//                    }
//                 }
//             }
//         }
//     }
// }
