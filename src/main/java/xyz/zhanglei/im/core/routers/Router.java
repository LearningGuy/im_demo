package xyz.zhanglei.im.core.routers;

import io.netty.channel.ChannelHandlerContext;
import xyz.zhanglei.im.core.constants.ResponseType;
import xyz.zhanglei.im.core.response.JsonResult;

import java.net.URL;
import java.util.Map;

public class Router {
    // 维护一个映射表
    private Map<URL, ServiceWrapper> mappings;

    public Router(HanderMappingParser parser) {

    }

    /**
     *
     * @param url：访问路径对象
     * @param jsonArgs：传进来的POJO对象
     */
    public void dispatch(ChannelHandlerContext context, URL url, String jsonArgs) {
        try {
            // 检查url是否存在
            if (!checkValidUrl(url)) {
                context.writeAndFlush(new JsonResult(ResponseType.FAIL, "请求链接不存在", ""));
                return;
            }

            mappings.get(matchServiceURL(url)).doDispatch(context, url, jsonArgs);

        } catch (Exception e) {
        } finally {
            context.close();
        }
    }

    private boolean checkValidUrl(URL url) {
        for (URL urlElement : mappings.keySet()) {
            if (urlElement.getPath().startsWith(url.getPath())) {
                return true;
            }
        }
        return false;
    }

    private URL matchServiceURL(URL url) {
        for (URL urlElement : mappings.keySet()) {
            if (urlElement.getPath().startsWith(url.getPath())) {
               return urlElement;
            }
        }
        return null;
    }
}
