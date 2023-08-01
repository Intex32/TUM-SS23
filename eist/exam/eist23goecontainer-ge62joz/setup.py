

import json
import subprocess
output = subprocess.check_output(["git", "config", "--get", "remote.origin.url"])
repo = output.decode().strip().split("/")[-1]
        
dict={
      "Flag": 'insert_your_flag_here',
      "Repo": repo
}
file_json = json.dumps(dict, indent=4)
        
with open("SOLUTION.json", "w") as f:
    f.write(file_json)
        
print("Correctly set up your exercise.")
    
