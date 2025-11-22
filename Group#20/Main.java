import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;

// Интерфейс SocialNetwork
interface SocialNetwork {
    boolean login();
    void publish(String message);
    void logout();

    default void createPost(String message) {
        if (login()) {
            publish(message);
            logout();
        }
    }
}

// Реализация для Facebook
class Facebook implements SocialNetwork {
    private String login;
    private String password;
    private String token;

    public Facebook(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Facebook(String token) {
        this.token = token;
    }

    @Override
    public boolean login() {
        if (token != null) {
            System.out.println("Вход в Facebook с токеном: " + token);
        } else {
            System.out.println("Вход в Facebook с логином: " + login);
        }
        return true;
    }

    @Override
    public void publish(String message) {
        System.out.println("Публикация в Facebook: " + message);
    }

    @Override
    public void logout() {
        System.out.println("Выход из Facebook");
    }
}

// Реализация для Twitter
class Twitter implements SocialNetwork {
    private String login;
    private String password;
    private String token;

    public Twitter(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Twitter(String token) {
        this.token = token;
    }

    // Конструктор по умолчанию
    public Twitter() {
        this.login = "default";
        this.password = "default";
    }

    @Override
    public boolean login() {
        if (token != null) {
            System.out.println("Вход в Twitter с токеном: " + token);
        } else {
            System.out.println("Вход в Twitter с логином: " + login);
        }
        return true;
    }

    @Override
    public void publish(String message) {
        System.out.println("Публикация в Twitter: " + message);
    }

    @Override
    public void logout() {
        System.out.println("Выход из Twitter");
    }
}

// Фабрика
class SocialNetworkFactory {
    private static final Map<String, Function<Map<String, String>, SocialNetwork>> networkCreators = new HashMap<>();

    static {
        // Регистрация конструкторов
        networkCreators.put("facebook", params ->
                new Facebook(params.get("login"), params.get("password"))
        );

        networkCreators.put("facebook2", params ->
                new Facebook(params.get("token"))
        );

        networkCreators.put("twitter", params ->
                new Twitter(params.get("login"), params.get("password"))
        );

        networkCreators.put("twitter2", params ->
                new Twitter(params.get("token"))
        );

        networkCreators.put("twitter_default", params ->
                new Twitter()
        );
    }

    public static SocialNetwork getNetwork(String type, Map<String, String> params) {
        Function<Map<String, String>, SocialNetwork> creator = networkCreators.get(type.toLowerCase());
        if (creator == null) {
            throw new IllegalArgumentException("Неизвестная соцсеть: " + type);
        }
        return creator.apply(params);
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        SocialNetwork facebook = SocialNetworkFactory.getNetwork("facebook",
                Map.of("login", "user123", "password", "pass123"));
        facebook.createPost("Hello Facebook!");

        SocialNetwork facebook2 = SocialNetworkFactory.getNetwork("facebook2",
                Map.of("token", "abc123token456"));
        facebook2.createPost("Hello Facebook with token!");

        SocialNetwork twitter = SocialNetworkFactory.getNetwork("twitter",
                Map.of("login", "twitterUser", "password", "twitterPass"));
        twitter.createPost("Hello Twitter!");

        SocialNetwork twitter2 = SocialNetworkFactory.getNetwork("twitter2",
                Map.of("token", "xyz789twitterToken"));
        twitter2.createPost("Hello Twitter with token!");
    }
}
