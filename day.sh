#!/bin/bash

DAY=`printf %02d $1`
echo "Initializing Day${DAY}..."
touch src/main/resources/${DAY}.txt
touch src/test/resources/${DAY}-0.txt
sed -e "s/XX/${DAY}/g" template/DayXX.java > src/main/java/fr/insee/aoc/days/Day${DAY}.java
sed -e "s/XX/${DAY}/g" template/DayXXTest.java > src/test/java/fr/insee/aoc/days/Day${DAY}Test.java
echo "Done."