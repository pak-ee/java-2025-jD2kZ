#!/usr/bin/env bash
set -e

# Небольшой сценарий, чтобы на презентации быстро прогнать команды.
# Можно не использовать, если показываешь команды вручную.

echo "== jar version =="
jar --version

echo
echo "== list jar =="
jar tf app.jar | head -n 30

echo
echo "== run =="
java -jar app.jar

echo
echo "== extract MANIFEST.MF =="
rm -rf tmp && mkdir -p tmp
( cd tmp && jar xf ../app.jar META-INF/MANIFEST.MF )
echo "MANIFEST.MF:"
cat tmp/META-INF/MANIFEST.MF

echo
echo "== update jar (add config/) =="
jar uf app.jar -C extra config/
echo "Now jar contains:"
jar tf app.jar | grep -E '^config/' || true

echo
echo "Done."
