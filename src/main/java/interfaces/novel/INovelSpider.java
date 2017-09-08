package interfaces.novel;

import entity.Novel;

import java.util.Iterator;
import java.util.List;

/**
 * @Author:tangmq
 * @Date:2017/9/7
 * @Note:
 */
public interface INovelSpider {
    /**
     * 根据url,获得小说列表
     * @param url
     * @return
     */
    List<Novel> getsNovel(String url) throws Exception;
    /**
     * 根据url返回一个迭代器
     */
    Iterator<List<Novel>> getIterator(String firstPageUrl);
}
