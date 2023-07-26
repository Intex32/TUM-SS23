CALL ./gradlew clean build

CALL docker build -t eist-ngrok

CALL docker compose up -d
