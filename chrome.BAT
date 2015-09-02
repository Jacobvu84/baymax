@echo off
echo Starting with chrome browser. If any problem, Tell me at vuthelinh@gmail.com
PAUSE
mvn clean verify serenity:aggregate -U -Dwebdriver.driver=chrome -Dwebdriver.chrome.driver=./driver/chromedriver.exe