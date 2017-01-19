#!/bin/bash
#This assumes everything is already compiled and in the classes/ directory
echo "Main-Class: com.dijoism.race.RaceMain" > mainClass
cd classes
jar cmf ../mainClass ../Race-0.3b.jar *
