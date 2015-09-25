@echo off
title Running test on phantomjs
color a
SET PATH=%PATH%;.\driver\
echo Tell me at vuthelinh@gmail.com if you have any question
PAUSE
rmdir /Q /S .\src\test\resources\features\
rmdir /Q /S .\src\test\resources\objects\
mkdir .\src\test\resources\features\
rmdir /Q /S C:\baymax_report
xcopy  C:\baymax_workspace\features .\src\test\resources\features /s /e /y /i
xcopy  C:\baymax_workspace\objects .\src\test\resources\objects /s /e /y /i
mvn clean verify serenity:aggregate -U -Dwebdriver.driver=phantomjs