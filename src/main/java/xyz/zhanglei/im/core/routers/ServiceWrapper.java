package xyz.zhanglei.im.core.routers;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import xyz.zhanglei.im.core.constants.ResponseType;
import xyz.zhanglei.im.core.response.JsonResult;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Map;

public class ServiceWrapper {

    // channel上下文
    private ChannelHandlerContext context;

    // 业务层对象
    private Object service;

    // Method 映射对象
    private Map<URL, Method> methodMappings;

    public void doDispatch(ChannelHandlerContext context, URL url, String jsonArgs) throws Exception{
        // 定位处理器方法
        Method method = null;
        if ((method = getMethodFromURL(url)) == null) {
            context.writeAndFlush(new JsonResult(ResponseType.FAIL, "请求路径有误", ""));
            return;
        }

        // 将POJO对象进行反序列化
        Object args = null;
        if ((args = convertArgsFromJsonString(jsonArgs, method.getParameterTypes())) == null) {
            context.writeAndFlush(new JsonResult(ResponseType.FAIL, "参数不匹配", ""));
            return;
        }

        JsonResult result = (JsonResult) method.invoke(service, args);
        context.writeAndFlush(result);
    }

    private Object convertArgsFromJsonString(String jsonStr, Class<?>[] clazz) {
        if (clazz.length != 0) {
            return JSON.parseObject(jsonStr, clazz[0]);
        }
        return null;
    }

    private Method getMethodFromURL(URL url) {
        for (URL urlElement : methodMappings.keySet()) {
            if (urlElement.getPath().endsWith(url.getPath())) {
                return methodMappings.get(urlElement);
            }
        }
        return null;
    }
}
