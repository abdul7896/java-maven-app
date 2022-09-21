def buildApp() {
    echo 'building the application'
    sh 'mvn package'
}

def buildImage() {
    echo 'building the docker image'
    withCredentials([usernamePassword(
        credentialsId: '71ef5aa5-53bc-40e9-bdf6-4cfd27c1aa7e',
        passwordVariable: 'PASS',
        usernameVariable: 'USER'
        )]) {
            sh 'docker build -t abz7896/prod-repo:jma-2.0 .'
            sh "echo $PASS | docker login -u $USER --password-stdin"
            sh 'docker push abz7896/prod-repo:jma-2.0'
        }
}

def deployApp() {
    echo 'deploying the application'
}

return this
