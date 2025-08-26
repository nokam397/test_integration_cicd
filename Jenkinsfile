pipeline{
    triggers {
        upstream 'build_deploy, '
    }
    agent{
        docker{
            image 'maven:3.9.11-eclipse-temurin-24'
        }
    }
    stages{
        stage('clean and compile'){
            steps{
                sh 'mvn clean compile'
            }
        }
        stage('Run Tests'){
            steps{
                sh 'mvn test'
            }
        }
    }
   
}