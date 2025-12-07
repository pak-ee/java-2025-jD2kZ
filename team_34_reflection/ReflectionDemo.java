import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface AutoInject {
}

// Зависимость
class Database {
    public void connect() {
        System.out.println(">> [Database] Соединение установлено!");
    }
}

// Сервис
class UserService {
    @AutoInject
    private Database database;

    public void start() {
        System.out.println(">> [UserService] Запуск сервиса");
        if (database != null) {
            database.connect();
        } else {
            System.out.println(">> [ERROR] Ошибка База данных не внедрена.");
        }
    }
}

class Context {
    public static <T> T getInstance(Class<T> clazz) throws Exception {
        Constructor<T> constructor = clazz.getDeclaredConstructor();

        constructor.setAccessible(true);

        // Создаем объект
        T instance = constructor.newInstance();

        // Сканируем поля
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInject.class)) {
                Class<?> fieldType = field.getType();

                Constructor<?> fieldConstructor = fieldType.getDeclaredConstructor();
                fieldConstructor.setAccessible(true);
                Object fieldInstance = fieldConstructor.newInstance();

                field.setAccessible(true);
                field.set(instance, fieldInstance);

                System.out.println(">> [Context] Успешно внедрено: " + fieldType.getSimpleName());
            }
        }
        return instance;
    }
}

public class ReflectionDemo {
    public static void main(String[] args) throws Exception {
        UserService service = Context.getInstance(UserService.class);
        service.start();
    }
}