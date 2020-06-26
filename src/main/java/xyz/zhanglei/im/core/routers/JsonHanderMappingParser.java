package xyz.zhanglei.im.core.routers;

import java.lang.reflect.Method;
import java.util.Map;

public class JsonHanderMappingParser implements HanderMappingParser {

    private String jsonFilePath;

    public JsonHanderMappingParser(String path) {
        jsonFilePath = path;
    }

    public Map<String, Method> parse() {
        // TO DO
        return null;
    }
}
