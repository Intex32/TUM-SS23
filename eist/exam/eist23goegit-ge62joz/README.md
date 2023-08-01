# EIST SoSe23 Final exam: Git exercise: Merge the right merge

+ Notice: There are `Warnings` in the end of the problem statement. Make sure to check them out.


We provide you with the following:
src/git/MergeSort.java

After cloning the repository, without making any changes to it (if you do make changes, they will be stashed): run the following:

> `python3 create_branches.py`

And then, see a list of the branches you have.

> `git branch`

You should now have the following branches

+ 1) `main`
+ 2) `feature/merge-i` | i ∈ N in [1,5]


In the main branch, You have an implementation for `Merge Sort`, but the merge function that it calls has not yet been implemented.

* You have a job as a software engineer, and you are working on a project with `5 other teammates`.

* Your teammates are inexperienced programmers and thus they make a lot of mistakes

* For the current sprint, the task of each one of your teammates was to create `a feature branch for the implementation of the ‘merge’ function`.

* Only **one of them** has implemented the correct merge function.

* Find which branch has the correct implementation, then **merge** it to the main branch. Your teammates trust that you will make the right choice, thus they left the choice to you!

* Feel free to put whatever you want in the main function and run the program to see that it works as expected

* Commit, and push.

We wish you **merge the right merge**!

Good luck


Warnings:
+ Don’t just checkout to one of the branches, copy the code, and paste it to the other branch. That won’t pass our tests. **You need to use the git merge command.**
+ If it sorts the given array correctly, it doesn’t most necessarily mean it is the right implementation.

