package interfaces.novel;

import entity.Novel;

import java.util.List;

/**
 * @Author:tangmq
 * @Date:2017/9/7
 * @Note:
 */
public interface INovleSpider {
    /**
     * 根据url,获得小说列表
     * @param url
     * @return
     */
    List<Novel> getsNovel(String url) throws Exception;
}
