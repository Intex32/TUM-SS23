import os
patch_directory = "patches"
patch_files = os.listdir(patch_directory)

print(f"Stashing local changes (if any)")
os.system(f"git stash")

for patch_file in patch_files:


    # extract the branch name from the patch file name
    branch_name =  "feature/" + os.path.splitext(patch_file)[0]

    # create the branch
    print(f"Checking out: {branch_name} ")
    os.system(f"git checkout -b {branch_name}")


    # apply the patch
    patch_path = os.path.join(patch_directory, patch_file)
    print(f"Applying {patch_path} ")
    os.system(f"git apply {patch_path}")




    # commit
    print(f"Staging the changes.")
    os.system("git add .")
    print(f"Committing to: {branch_name} ")
    os.system(f'git commit -m "Apply patch{patch_path}" --author="SOMEONE ELSE<other.developer@tum.de>"')


    # optionally, push the branch to the remote repository
    # print(f"Pushing {branch_name} ")
    # os.system(f"git push origin {branch_name}")
    
    os.system("git checkout main")
patch_path = os.path.join("final-patch", "Add-initial-false-implementation.patch")
os.system(f"git apply {patch_path}")

os.system("git add .")

os.system(f'git commit -m "Apply patch{patch_path}"')
print("Here's a list of your branches. Make sure you see the feature branches. They are relevant for this exercise.")
os.system("git branch")
