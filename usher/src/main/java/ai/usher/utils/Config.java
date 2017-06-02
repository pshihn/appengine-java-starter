package ai.usher.utils;

import java.util.HashMap;
import java.util.Map;

public class Config {
    public static final String PROP_BASE_URL = "baseUrl";
    public static final String COOKIE_SESSION = "X-Usher-Id";
    public static final String EXECUTION_CONTEXT = "xExecutionContext";

    private static final Map<String, String> map = new HashMap<>();
    private static boolean initialized = false;

    static {
        initialize();
    }

    private static void initialize() {
        if (!initialized) {
            map.put(PROP_BASE_URL, "http://locahost:8080");
        }
    }

    public static String property(String name) {
        String key = name.toLowerCase().trim();
        if (map.containsKey(key)) {
            return map.get(key);
        }
        String value = System.getProperty("ai.usher." + key);
        if (StringHelper.isNotBlank(value)) {
            map.put(key, value);
        }
        return value;
    }
}
