pipeline {
    agent any

    triggers {
        githubPush()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building IQPuzzlerPro...'
            }
        }

        stage('Run') {
            steps {
                echo 'Running program...'
            }
        }
    }
}
