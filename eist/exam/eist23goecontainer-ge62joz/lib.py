import hashlib
from os.path import exists

def check():
    tomatoes = exists("/tmp/.you_gotta_add_tomatoes.txt")
    bacon = exists("/var/remember_to_add_the_bacon.csv")
    cheddar = exists("/cheddar_is_one_of_the_most_important_things.md")
    mayonnaise = exists("/etc/i_also_add_some_mayonnaise.xml")
    return tomatoes and bacon and cheddar and mayonnaise
