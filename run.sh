#!/bin/bash

# Create the out directory if it doesn't exist
mkdir -p out

# Compile the Java files
jdk-8/bin/javac -d out src/main/java/com/tronai/**/*.java src/main/java/com/tronai/Main.java

# Check if the compilation was successful
if [ $? -eq 0 ]; then
    # Execute the Main class
    jdk-8/bin/java -cp out com.tronai.Main
else
    echo "Compilation failed."
fi
