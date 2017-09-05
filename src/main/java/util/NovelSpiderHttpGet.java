package util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

import java.net.URI;

/**
 * @Author:tangmq
 * @Date:2017/9/5
 * @Note:
 */
public class NovelSpiderHttpGet extends HttpGet {
    public NovelSpiderHttpGet() {
        super();
    }

    public NovelSpiderHttpGet(URI uri) {
        super(uri);
    }

    /**
     * @param uri
     * @throws IllegalArgumentException if the uri is invalid.
     */
    public NovelSpiderHttpGet(String uri) {
        super(uri);
    }

    private void defaultConfig() {
        this.setConfig(RequestConfig.custom()
                .setSocketTimeout(30_000)
                .setConnectTimeout(10_000)
                .setConnectionRequestTimeout(20_000)
                .build());
        this.setHeader("User-Agent", "novelSpider");
    }
}
