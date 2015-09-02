@echo off
echo Starting with PHANTOMJS (Headless browser). If any problem, Tell me at vuthelinh@gmail.com
SET PATH=%PATH%;%CD%\driver
PAUSE
mvn clean verify serenity:aggregate -U -Dwebdriver.driver=phantomjs