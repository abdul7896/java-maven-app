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
    environment {
        IMAGE_NAME = 'abz7896/prod-repo:jma-3.0'
    }
    stages{

        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }

        }

        stage("build jar"){
            steps{
                script {
                    buildJar()
                }
            }
        }

          stage("build and push image"){
            steps{
                script {
                    buildImage(env.IMAGE_NAME)
                    dockerLogin()
                    dockerPush(env.IMAGE_NAME)
                }
            }
    }
        stage ("deploy"){
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
}
}