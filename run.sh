#!/bin/sh

mvn package
java -cp target/project-euler-1.0-SNAPSHOT.jar com.vishpat.projeuler.App
