FROM ghcr.io/ls1-courses/container:latest
RUN apk add git 
COPY . /
RUN ./flag
RUN pip3 install -r requirements.txt
CMD python3 app.py
