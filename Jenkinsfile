pipeline {
    triggers {
        upstream('build_deploy')
        pollSCM('H/2 * * * *')
    }
    agent {
        docker {
            image 'maven:3.9.11-eclipse-temurin-24'
        }
    }
    stages {
        stage('clean and compile') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }
    }
    post {
        always {
            junit 'build/reports/**/*.xml'
        }
    }
}
