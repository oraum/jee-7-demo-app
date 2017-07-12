## Run the Application

1. Install Docker CLI on your machine
1. start the Docker daemon
1. `mvn clean verify`
1. `cd wasliberty`, `docker build .`
1. (optional) tag the newly created image
1. `docker container run -p 9080:9080 <image id or name>` 
1. goto [http://localhost:9080/jee7demo/](http://localhost:9080/jee7demo/)



### Jenkins Pipeline Build

The *Jenkinsfile* describes a Build pipeline which consists of the stages:
1. Checkout - checkout of the code; preconfigured is the URL of GitLab repository, the branch *wasline* is used
1. Run 'mvn clean compile' to compile the code
1. Run 'mvn verify' to run Unit- and Integrationtests
1. Run Code Analysis with Sonar, this requires a SonarQube server to be configured
1. Build Docker Image
1. Push the image to a private (Nexus) Docker Registry and to Docker Hub. The credentials for accessing Docker Hub
have to be set up in Jenkins, the id has to set to 'dockerhub' (or the Jenkinsfile modified accordingly)
1. Deploy the Maven artifacts. This requires a Maven Repository Proxy to be set up, with appropriate credentials in Jenkins

### Kubernetes deployment a la kubectl and yaml

The *wasliberty* directory contains YAML files to create a deployment plus corresponding service
in Kubernetes.
To use the Docker image that has been pushed to Docker Hub during the Jenkins Build it will
be necessary to modify the *image* element in the *jeedemo-deployment.yaml*.
The prefix should be replaced and set to the name of the user that is configured in Jenkins.


### Kubernetes deployment a la curl and json

´´´
curl http://<your-k8s-master>/apis/extensions/v1beta1/namespaces/default/deployments \
    -X POST -H'Content-Type: application/json' -d @jeedemo-deployment.json
´´´