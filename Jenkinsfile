pipeline {
    agent any
    tools {
        maven "MAVEN_HOME"
    }
    parameters {
        string(name: 'email', defaultValue: '', description: 'Nhập email nhận báo cáo')
        choice(name: "environment", choices:["default", "sit", "uat", "product"])
        choice(name: "platformName", choices:["iOS", "Android"])
        string(name: 'udid', defaultValue: '00008120-001241260A03C01E')
        string(name: 'tags', defaultValue: '')
        string(name: 'username', defaultValue: 'xuanthu81')
        password(name: 'password', defaultValue: '12121212')
        string(name: 'defaultAccountForTransfer', defaultValue: '107881480823')
        string(name: 'providerId', defaultValue: 'Y3Test')
        string(name: 'merchantId', defaultValue: 'Y3Test')
    }
    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'develop', credentialsId: 'taind4', url: 'https://github.com/nguyendinhtai1997/automation-vietinbank.git'
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    sh 'mvn clean verify -Denvironment=${environment} -DplatformName=${platformName} -Dudid=${udid} -Dtags=@${tags} -Dusername=${username} -Dpassword=${password} -DdefaultAccountForTransfer=${defaultAccountForTransfer} -DproviderId=${providerId} -DmerchantId=${merchantId}'
                    sh 'pkill -9 -f appium'
                }
            }
        }
    }
    post {
        always {
            publishHTML([allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: false,
                    reportDir: 'target/site/serenity',
                    reportFiles: 'index.html',
                    reportName: 'HTML Report',
                    reportTitles: '',
                    useWrapperFileDirectly: true])
        }
        failure {
            echo "Email sent for Jenkins Build Failed"
            emailext body: 'Jenkins Build Failed', subject: 'Jenkins Build Failed', to: '${email}'
        }
    }
}
