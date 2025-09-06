pipeline {
    agent any
    tools {
        maven 'Maven 3.9.9'
        jdk 'JDK21'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/manishsewlikar/SeleniumCucumberDemoPlayright.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean compile'  // Use 'sh' for Linux
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Reports') {
            steps {
                // Cucumber Report
                cucumber buildStatus: 'null',
                         fileIncludePattern: 'target/cucumber.json',
                         sortingMethod: 'ALPHABETICAL'
                // Optional: HTML Publisher for Extent/Cucumber HTML
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false,
                             reportDir: 'target', reportFiles: 'cucumber-report.html', reportName: 'Cucumber HTML'])
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/*.json,target/*.html', allowEmptyArchive: true
            // Email/Slack notifications if needed
        }
        failure {
            echo 'Tests failed - Check reports!'
        }
    }
}