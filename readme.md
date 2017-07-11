## Run the Application

1. Install Docker CLI on your machine
1. start the Docker daemon
1. `mvn clean verify`
1. `cd wasliberty`, `docker build .`
1. (optional) tag the newly created image
1. `docker container run -p 9080:9080 <image id or name>` 
1. goto [http://localhost:9080/jee7demo/](http://localhost:9080/jee7demo/)



        
    