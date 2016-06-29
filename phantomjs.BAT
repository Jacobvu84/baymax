@echo off
title Running test on phantomjs
color a
echo Tell me at vuthelinh@gmail.com if you have any question
PAUSE
SET PATH=%PATH%;.\driver\
mvn clean verify serenity:aggregate -U -Dwebdriver.driver=phantomjs