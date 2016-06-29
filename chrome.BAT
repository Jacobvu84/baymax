@echo off
title Running test on chrome
color a
echo Tell me at vuthelinh@gmail.com if you have any question
PAUSE
mvn clean verify serenity:aggregate -U -Dwebdriver.driver=chrome -Dwebdriver.chrome.driver=./driver/chromedriver.exe