from flask import Flask
from lib import check
import os

app = Flask(__name__)


@app.get("/")
def index():
    return "<p>Hello, EIST student! click <a href='/getflag'> here </a> to get your flag.</p>"


@app.get("/getflag")
def getflag():
    with open("flag.txt", "r") as f:
        flag = f.read()
    return flag


if __name__ == "__main__":
    if not check():
        print("ERROR :: you need to run the exercise in the eist docker image")
        os._exit(1)
    app.run(host="0.0.0.0", port=8000)
