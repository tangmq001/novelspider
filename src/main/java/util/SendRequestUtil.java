package util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * @Author:tangmq
 * @Date:2017/8/31
 * @Note:
 */
public class SendRequestUtil {
    private static SendRequestUtil instance;
    static {
        instance=new SendRequestUtil();
    }
    private SendRequestUtil() {}

    /**
     * 提供接口,给外部调用
     */
    public static SendRequestUtil getInstance(){
        return instance;
    }
    public String crawl(String url,String charset) throws Exception {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
             CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet(url))) {
            String result = EntityUtils.toString(httpResponse.getEntity(), charset);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
