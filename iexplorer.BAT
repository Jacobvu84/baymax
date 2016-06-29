@echo off
title Running test on iexplorer
color a
echo Tell me at vuthelinh@gmail.com if you have any question
PAUSE
mvn clean verify serenity:aggregate -U -Dwebdriver.driver=iexplorer -Dwebdriver.ie.driver=./driver/IEDriverServer.exe