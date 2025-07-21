
pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/niharika2807/TestNg_Project.git'
            }
        }
        
        stage('Test') {
         
            steps {
                 // Run Maven on a Unix agent.
                //sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                bat "mvn clean test -DBrowser=%BrowserName%"
                
            }
            
                
            post {
                always {
                    testNG()
                }
            }

        }
               
    }
}
