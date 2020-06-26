package xyz.zhanglei.im.core.routers;

import java.lang.reflect.Method;
import java.util.Map;

public interface HanderMappingParser {

    /**
     * 对配置进行解析，替代SpringMVC中Controller层的作用
     * @return Map<String, Method> key为完整url，value是对应方法
     */
    public Map<String, Method> parse();

}
