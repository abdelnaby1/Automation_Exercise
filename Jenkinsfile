pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                sh "/opt/homebrew/bin/mvn clean test -Pcu"
            }

            post {

                success {
                   publishHTML([
                       allowMissing: false,
                       alwaysLinkToLastBuild: false,
                       keepAll: false,
                       reportDir: 'test-output//HtmlReport',
                       reportFiles: 'ExtentHtml.html',
                       reportName: 'Extent Report',
                       reportTitles: '',
                       useWrapperFileDirectly: true])
                }
            }
        }
    }
}
// node {
//     def mvnHome
//     stage('Preparation') { // for display purposes
//         // Get some code from a GitHub repository
//         git 'https://github.com/abdelnaby1/Automation_Exercise.git'
//         mvnHome = tool 'MAVEN_HOME'
//     }
//     stage('Build') {
//         // Run the maven build
//         withEnv(["MVN_HOME=$mvnHome"]) {
//             if (isUnix()) {
//                 sh '"$MVN_HOME/bin/mvn" test -Pcu'
//             } else {
//                bat(/"%MVN_HOME%\bin\mvn" test -Pcu/)
//             }
//         }
//     }
//     stage('Results') {
//         junit '**/target/surefire-reports/TEST-*.xml'
//         archiveArtifacts 'target/*.jar'
//     }
// }
