pipeline {
    agent any
    tools {
       /*  maven 'Maven 3.9.9'
        jdk 'JDK21' */
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/manishsewlikar/SeleniumCucumberDemoPlaywright.git', credentialsId: 'github-token'
            }
        }
        stage('Debug Environment') {
            steps {
                bat 'echo JAVA_HOME=%JAVA_HOME%'
                bat 'echo PATH=%PATH%'
                bat 'java -version'
                bat 'mvn --version'
                bat 'chromedriver --version'
                bat 'dir %USERPROFILE%\\.cache\\ms-playwright'
                bat '"C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe" --version'
            }
        }
        stage('Install Playwright Browsers') {
            steps {
                bat 'mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Reports') {
            steps {
                cucumber buildStatus: 'null',
                         fileIncludePattern: 'target/cucumber.json',
                         sortingMethod: 'ALPHABETICAL'
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false,
                             reportDir: 'target', reportFiles: 'cucumber-report.html', reportName: 'Cucumber HTML'])
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/*.json,target/*.html', allowEmptyArchive: true
        }
        failure {
            echo 'Tests failed - Check reports in target/surefire-reports!'
        }
    }
}