DEMO: jar в командной строке (готовый мини-проект)

Что тут есть:
- src/..         исходник
- resources/..   ресурс, который попадет внутрь jar
- extra/config/  папка, которую можно ДОБАВИТЬ в jar через update (jar uf)
- app.jar        уже собранный исполняемый jar (Main-Class прописан)
- out/           скомпилированные классы (на всякий случай)

Самые короткие команды для показа:

1) Проверка:
   jar --version

2) Посмотреть, что внутри:
   jar tf app.jar

3) Запуск:
   java -jar app.jar

4) Посмотреть манифест:
   jar xf app.jar META-INF/MANIFEST.MF
   (потом открыть файл META-INF/MANIFEST.MF любым редактором или командой cat/type)

5) Обновить jar (добавить config внутрь):
   jar uf app.jar -C extra config/
   jar tf app.jar | findstr config   (Windows)
   jar tf app.jar | grep config      (Linux/macOS)

Если хочешь пересобрать с нуля:
   javac -encoding UTF-8 -d out src/com/example/Main.java
   (скопировать ресурс)
   cp resources/message.txt out/
   jar cfe app.jar com.example.Main -C out .
