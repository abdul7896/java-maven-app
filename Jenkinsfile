#!/usr/bin/env groovy

library identifier: 'jenkins-shared-library@master', retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'https://github.com/abdul7896/jenkins-shared-library.git',
    credentialsId: '503628bf-7dc0-4b99-aa98-8bdcbaa82818'])

def gv 

pipeline{
    agent any
    tools {
        maven "mvn-3.8.6"
    }

    stages{

        stage {
            steps {
                script {
                    echo "Incrementing app version ..."
                    sh "mvn build-helper:parse-version versions:set \
                        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.nextMinorVersion}.\\\${parsedVersion.incrementalVersion} \
                        versions:commit"
                    def matcher = readFile("pom.xml") =~ '<version>(.*)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }
        }

        stage("build app"){
            steps{
                script {
                    echo "buiding the application for branch ${BRANCH_NAME}"
                    sh 'mvn clean package'
                }
            }
        }

          stage("build image"){
            steps{
                script {
                    echo "building the docker image ..."
                    withCredentials([usernamePassword(
                    credentialsId: '71ef5aa5-53bc-40e9-bdf6-4cfd27c1aa7e',
                    passwordVariable: 'PASS',
                    usernameVariable: 'USER'
            )]) {
                sh "docker build -t abz7896/prod-repo:$IMAGE_NAME"
                sh "echo $PASS | docker login -u $USER --password-stdin"
                sh "docker push abz7896/prod-repo:$IMAGE_NAME"
                }
            }
        }
    }
        stage ("deploy"){
            steps {
                script {
                    echo "deploying ..."
                }
            }
        }
}
}