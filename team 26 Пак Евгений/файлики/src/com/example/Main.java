package com.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Маленький демо-проект, чтобы показать команды jar в консоли.
 *
 * Идея максимально простая:
 *  - печатаем приветствие
 *  - читаем текстовый ресурс message.txt изнутри JAR
 *
 * На защите удобно: видно, что jar реально содержит не только классы,
 * но и обычные файлы (ресурсы).
 */
public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello from JAR!");

        // Ресурс должен лежать в classpath. В нашем случае мы кладём message.txt прямо в корень jar.
        String line = readFirstLineFromResource("message.txt");

        if (line == null) {
            System.out.println("message.txt не найден. Скорее всего, забыли добавить ресурс при сборке.");
            return;
        }

        System.out.println("resource message.txt: " + line);

        // Небольшая подсказка, чтобы на презентации было понятно, что дальше показывать.
        // (Можно удалить, если кажется лишним.)
        System.out.println("Tip: jar tf app.jar  |  jar xf app.jar META-INF/MANIFEST.MF  |  jar uf app.jar -C extra config/");
    }

    /**
     * Читает первую строку из ресурса внутри jar (или из папки classpath при запуске из IDE).
     */
    private static String readFirstLineFromResource(String resourceName) {
        ClassLoader cl = Main.class.getClassLoader();

        try (InputStream in = cl.getResourceAsStream(resourceName)) {
            if (in == null) {
                return null;
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                return br.readLine();
            }
        } catch (Exception e) {
            // Тут специально не кидаем исключение дальше, чтобы демо не падало из-за мелочи.
            System.out.println("Не получилось прочитать ресурс: " + e.getMessage());
            return null;
        }
    }
}
