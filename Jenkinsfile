pipeline {
    agent {
        docker {
            image 'maven:3.9.11-eclipse-temurin-24'
            
            args '-v $HOME/.m2:/root/.m2'
        }
    }

    triggers {
        upstream('build_deploy')    
        pollSCM('H/2 * * * *')      
    }

    stages {
        stage('Clean and Compile') {
            steps {
                sh 'mvn clean compile -B'
            }
        }
        stage('Run Tests') {
            steps {
                sh 'mvn test -B'
            }
        }
    }

    post {
        always {
            echo " Archivage des résultats de tests..."
            junit 'target/surefire-reports/*.xml'
        }
        success {
            echo " Build et tests passés avec succès"
        }
        failure {
            echo "Échec du build ou des tests"
        }
    }
}
