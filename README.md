**This is a prototype of virtualization solution.** 


Controller is built on Java and uses Docker, Docker-compose and Spring Boot as well as Libvirt library under the hood. Docker image is based on Tomcat:9.0 and debian OS.

To build Dockerimage please use the following commands:
1. Build project: execute `mvn clean install` in directory that contains pom.xml
2. Create docker image: execute `docker build -t prototype --build-arg debug_port=1043 .` in directory that contains Dockerfile

To deploy application you can execute following commands:
1. Deploy docker container: execute `docker run -p 8080:8080 -p 1043:1043 prototype`
2. **Or use docker-compose command:** execute `docker-compose up -d libvirt-connection-app` (This command can be executed even without building docker image)
3. If you want to deploy this application on cluster docker environment please use: `docker stack deploy -c docker-compose.yml prototype`. 
Before execute this command please make sure that docker image with prototype name is existed. Note that this type of deployment aware about container memory consumption.
