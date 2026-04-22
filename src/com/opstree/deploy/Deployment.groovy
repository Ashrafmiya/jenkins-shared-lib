package com.opstree.deploy

class Deployment implements Serializable {

    def steps

    Deployment(steps) {
        this.steps = steps
    }

    // ✅ Validate Environment
    def validate(String env) {
        steps.echo "Validating deployment for ${env}..."

        if (!(env in ['dev', 'staging', 'prod'])) {
            steps.error("Invalid environment: ${env}")
        }

        steps.sh "echo Validation successful for ${env}"
    }

    // 🚀 Deploy Application
    def deploy(String env) {
        steps.echo "Deploying application to ${env}..."

        // Clean workspace (best practice)
        steps.cleanWs()

        // Clone repo using Jenkins git step
        steps.git url: 'https://github.com/opstree/OT-Microservices.git', branch: 'master'

        // Deployment logic (customize as needed)
        steps.sh """
            cd OT-Microservices
            echo "Starting deployment for ${env} environment..."

            # Example commands
            echo "Building application..."
            echo "Deploying services..."

            # Simulate success
            echo "Deployment successful for ${env}"
        """
    }

    // 🔄 Rollback
    def rollback(String env) {
        steps.echo "Rolling back deployment for ${env}..."

        steps.sh """
            echo "Rollback started for ${env}..."
            echo "Reverting to previous stable version..."
            echo "Rollback completed for ${env}"
        """
    }
}
