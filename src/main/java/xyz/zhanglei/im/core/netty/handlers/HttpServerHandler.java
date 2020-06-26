package xyz.zhanglei.im.core.netty.handlers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;
import xyz.zhanglei.im.core.routers.HanderMappingParser;
import xyz.zhanglei.im.core.routers.Router;

import java.net.URL;
import java.util.Map;

public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    // 链接分发器
    private Router router;

    public HttpServerHandler(Router router) {
        this.router = router;
    }


    /**
     * handler核心逻辑：
     * 1. 获取请求链接
     * 2. 根据请求链接获取相应的service
     * 3. 将请求体数据反序列化成POJO对象进行处理
     * 4. 获取返回对象并进行序列化，返回对象统一分装成JsonResult对象
     * @param channelHandlerContext
     * @param fullHttpRequest
     * @throws Exception
     */
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest)
            throws Exception {
        // 封装Wrapper对象
        HttpParser parser = new HttpParser(fullHttpRequest);
        router.dispatch(channelHandlerContext, parser.getUrl(), parser.parse().toJSONString());
    }


    class HttpParser {

        FullHttpRequest request;

        HttpParser(FullHttpRequest request) {
            this.request = request;
        }

        // 解析请求body中的参数
        public JSONObject parse() {
            return null;
        }

        // 解析URL中的参数
        public Map<String, String> parseParameterFromUrl() {
            return null;
        }

        // 解析URL地址
        public URL getUrl() {
            return null;
        }
    }
}


