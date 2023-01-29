def mvn = "/var/lib/jenkins/tools/hudson.tasks.Maven_MavenInstallation/maven_3.6.3/bin/mvn"

pipeline {
    agent any
    parameters {
        string(name: 'BRANCH', defaultValue: 'master', description: '')
    }
    stages {
        stage('Build') {
            steps {
                sh "${mvn} clean compile"
            }
        }
        stage('Run Tests') {
            steps {
                sh "${mvn} clean test -Dbrowser=${BROWSER}"
            }
        }
        stage('Allure Report Generation') {
            steps {
                allure includeProperties: false,
                        jdk: '',
                        results: [[path: 'target/reports/allure-results']]
            }
        }
    }
    post {
        always {
            cleanWs notFailBuild: true
        }
    }
}