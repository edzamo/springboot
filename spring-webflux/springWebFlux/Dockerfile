FROM openjdk:11

EXPOSE 9000

WORKDIR /opt/build
COPY ./build/libs/examplewebflux-0.0.1.jar /opt/build/
# Copy the entire local directory into the container at /app
COPY ./build/reports/tests  /opt/build/




#Execute the program
ENTRYPOINT ["java", "-jar", "/opt/build/examplewebflux-0.0.1.jar"]