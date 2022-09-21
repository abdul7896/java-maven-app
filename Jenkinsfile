pipeline{
    agent any
    tools {
        maven "mvn-3.8.6"
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
                    gv.buildApp()
                }
            }
        }

          stage("build image"){
            steps{
                script {
                    gv.buildApp()
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
