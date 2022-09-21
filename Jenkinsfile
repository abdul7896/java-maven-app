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
