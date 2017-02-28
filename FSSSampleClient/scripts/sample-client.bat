@echo off

if "%1" == "" goto default

goto param

:param
echo Calling Service to scan %1
java -jar sample-client.jar "%1"

goto end

:default
echo Please provide the path to scan!

:end
