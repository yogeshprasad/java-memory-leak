# Spring Boot Application With Memory Leak 
Spring boot application which keeps adding the objects and threads in the memory. This also reports the health metrics to the Wavefront.

### How to Run
- Get the code
    ```
    git clone https://github.com/yogeshprasad/java-memory-leak.git
    ```
- Change directory to java-memory-leak
    ```
    cd java-memory-leak
    ```
- Update `application.properties` under src/main/resources/
- Build the project
    ```
    mvn clean install
    ```
- Copy the jar
    ```
    cp target/memory.leak-0.0.1-SNAPSHOT.jar run
    ```
- Change directory to run
    ```
    cd run
    ```
- Start the application by providing the 1GB of heap size
    ```
    java -Xmx1g -jar memory.leak-0.0.1-SNAPSHOT.jar
    ```
- Start the load
    ```
    sh do_api_call.sh
    ```
    Note: You can modify the script to control the load
