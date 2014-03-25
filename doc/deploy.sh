#!/bin/sh

mvn clean package -Dmaven.test.skip=true

sleep 2

cd /Users/omar/software/wordEditor/

bin/shutdown.sh

cd webapps/

rm -r ROOT

rm ROOT.war

mv /Users/omar/workspace/engine/wordsEditor/target/*.war .

mv *.war ROOT.war

cd /Users/omar/software/wordEditor

rm -rf work/Catalina/

bin/startup.sh
