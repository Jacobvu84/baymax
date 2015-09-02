@echo off
echo Starting with IEXPLORER browser. If any problem, Tell me at vuthelinh@gmail.com
PAUSE
mvn clean verify serenity:aggregate -U -Dwebdriver.driver=iexplorer -Dwebdriver.ie.driver=./driver/IEDriverServer.exe