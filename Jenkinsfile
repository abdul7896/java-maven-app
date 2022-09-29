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
        stage("increment version") {
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
                sh "docker build -t abz7896/prod-repo:$IMAGE_NAME ."
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

        stage("commit version update"){
            steps {
                script {
                            withCredentials([usernamePassword(
                            credentialsId: '503628bf-7dc0-4b99-aa98-8bdcbaa82818',
                            passwordVariable: 'PASS',
                            usernameVariable: 'USER'
                    )])
                    {
                        sh 'git config --global  user.email "jenkins@example.com"'
                        sh 'git config --global  user.name "jenkins"'
                        sh "git status"
                        sh "git branch"
                        sh "git config --list"
                        sh "git remote set-url origin https://${USER}:${PASS}@github.com/abdul7896/java-maven-app.git"
                        sh "git add ."
                        sh 'git commit -m "ci: incremented version"'
                        sh "git push origin HEAD:jenkinsfile-sshagent"
                    }
                }
            }
        }
}
}