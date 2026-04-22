package com.opstree.deploy

class Deployment implements Serializable {

    def steps

    Deployment(steps) {
        this.steps = steps
    }

    // ✅ Validation
    def validate(String env) {
        steps.echo "Validating deployment for ${env}..."

        if (!(env in ['dev', 'staging', 'prod'])) {
            steps.error("Invalid environment: ${env}")
        }

        steps.sh "echo Validation successful for ${env}"
    }

    // 🚀 Deployment
    def deploy(String env) {
        steps.echo "Deploying application to ${env}..."

        if (env == "dev") {
            steps.sh "echo Deploying to DEV environment"
        } else if (env == "staging") {
            steps.sh "echo Deploying to STAGING environment"
        } else if (env == "prod") {
            steps.sh "echo Deploying to PRODUCTION environment"
        }

        // Example using your repo
        steps.sh """
            git clone https://github.com/opstree/OT-Microservices.git
            cd OT-Microservices
            echo "Running deployment scripts..."
        """
    }

    // 🔄 Rollback
    def rollback(String env) {
        steps.echo "Rolling back deployment for ${env}..."

        steps.sh "echo Rollback executed for ${env}"
    }
}
