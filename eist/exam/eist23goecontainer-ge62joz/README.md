# EIST SoSe23 Final Exam Containers Exercise

In this exercise you receive:
+ `app.py` ~> a flask application very similar to what we see during lecture, we run a flask application using `python3 app.py`
+ `requirements.txt` ~> the file listing dependencies for our application
+ `Dockerfile` ~> a simple Dockerfile you need to complete (DO NOT EDIT ABOVE LINE 5)
+ `setup.py` ~> a python script you SHOULD run as soon as you clone the repository

## Your task
+ run the setup script (`python3 setup.py`)
+ check the `SOLUTION.json` looks something like this:  

    ```json
        {
            "Repo": "<SOMETHING>.git",
            "Flag": "insert_your_flag_here"
        }
    ```
+ complete the `Dockerfile` (install dependencies with `pip3 install -r requirements.txt` and run the Flask application)
+ build the docker container (`docker build . -t eist23-final-container`)
+ run the docker container (`docker run -t eist23-final-container`) (you probably won't be able to access it, see below for an hint)
+ open the flask application in your browser  
+ retrieve the flag and update the file `SOLUTION.json`
+ commit and push all your work (`Dockerfile`,`SOLUTION.json`)

Hint: in order to be able to access the container from your machine you need to publish the ports, the flask application is running on port 8000, check [the documentation](https://docs.docker.com/config/containers/container-networking/) to understand how to publish a port.
