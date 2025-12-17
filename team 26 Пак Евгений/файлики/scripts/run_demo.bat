@echo off
REM Небольшой сценарий для Windows, чтобы быстро прогнать команды jar.
REM Можно не использовать, если показываешь команды вручную.

echo == jar version ==
jar --version

echo.
echo == list jar ==
jar tf app.jar

echo.
echo == run ==
java -jar app.jar

echo.
echo == extract MANIFEST.MF ==
if exist tmp rmdir /S /Q tmp
mkdir tmp
cd tmp
jar xf ..\app.jar META-INF\MANIFEST.MF
cd ..

echo MANIFEST.MF file extracted to tmp\META-INF\MANIFEST.MF

echo.
echo == update jar (add config/) ==
jar uf app.jar -C extra config/

echo.
echo Now jar contains config:
jar tf app.jar | findstr /R "^config/"

echo.
echo Done.
