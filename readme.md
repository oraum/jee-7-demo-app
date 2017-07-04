## Run the Application

1. Install Docker CLI on your machine
1. start the Docker daemon
1. mvn package
1. docker container run 
1. goto [http://localhost:9080/jee7demo/](http://localhost:9080/jee7demo/)


## Configure the CI

1. Start the docker CI stack with `docker-compose up`
1. Install maven plugin on Jenkins if not exist
1. Import this git repo to GitLab
1. Create a new job, configure like this
    - Maven project name: jee7demo-mvn
    - Source Code Management --> Git 
        - Repository URL: `http://root@dockercitoolstack_gitlab_1/root/jee7demo.git` 
        - Credential: root, rootroot
        - Branch Specifier: */arquillian
    - Build
        - Root Pom: pom.xml
        - Goals and Options: clean verify
        - click **Advanced**
        - check "Use private Maven repository", strategy: default
        - Settings File: choose "Settings file in file system"
        - File path: `mvn-settings.xml`
    - Post Steps: choose "Run regardless of build result"
    - Build Settings
        - Email notification: oliver.raum@bundesbank.de, 
        ning.zhao@externe-mitarbeiter.bundesbank.de, 
        stefen.elste@bundesbank.de
    - Post Build Actions --> Deploy artifacts to Maven Repository
        - Repo URL: [http://nexus:8081/repository/maven-snapshots/](http://nexus:8081/repository/maven-snapshots/)
        - Repo ID: `maven-snapshot-internal`
        - check "Assign unique versions to snapshots"
        - check "Deploy even if the build is unstable"
1. In the newly created "jee7demo-mvn" job, click "Build Now".
1. You should see the binary artifact in the Nexus Repository Manager, under Maven
        
    