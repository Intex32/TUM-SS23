FROM openjdk:17-bullseye
WORKDIR /app
COPY /build/libs/H10E01-Containers-1.0.0.jar /app/app.jar
COPY start.sh .
CMD chmod 770 start.sh
CMD ./start.sh