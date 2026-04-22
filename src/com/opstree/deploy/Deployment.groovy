package com.opstree.deploy

class Deployment implements Serializable {

    def steps

    // 🔒 Centralized Config INSIDE class (as requested)
    def REPO_URL = "https://github.com/opstree/OT-Microservices.git"
    def BRANCH   = "master"

    Deployment(steps) {
        this.steps = steps
    }

    // ✅ Validate Environment
    def validate(String env) {
        steps.echo "🔍 Validating environment: ${env}"

        if (!(env in ['dev', 'staging', 'prod'])) {
            steps.error("❌ Invalid environment: ${env}")
        }

        steps.echo "✅ Validation successful for ${env}"
    }

    // 🚀 Deploy Application
    def deploy(String env) {
        steps.echo "🚀 Starting deployment"
        steps.echo "Environment : ${env}"
        steps.echo "Repository  : ${REPO_URL}"
        steps.echo "Branch      : ${BRANCH}"

        // Clean workspace
        steps.cleanWs()

        // Use directory block (BEST PRACTICE)
        steps.dir("app") {

            // Clone repo
            steps.git url: REPO_URL, branch: BRANCH

            steps.sh """
                echo "📂 Current Directory:"
                pwd

                echo "📁 Project Files:"
                ls -la

                echo "⚙️ Building application..."
                # Example: docker build / mvn build / npm install

                echo "🚀 Deploying application to ${env}..."
                # Example: docker-compose up -d

                echo "✅ Deployment successful for ${env}"
            """
        }
    }

    // 🔄 Rollback Logic
    def rollback(String env) {
        steps.echo "⚠️ Deployment failed! Starting rollback for ${env}"

        steps.sh """
            echo "🔄 Rolling back ${env} environment..."
            echo "Reverting to last stable version..."
            echo "✅ Rollback completed for ${env}"
        """
    }
}
