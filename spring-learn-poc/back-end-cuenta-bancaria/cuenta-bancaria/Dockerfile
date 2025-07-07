FROM maven:3-jdk-11

WORKDIR /cuenta-bancaria
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run