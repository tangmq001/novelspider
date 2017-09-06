package interfaces;

import entity.Chapter;
import entity.Novel;

import java.util.List;

/**
 * @Author:tangmq
 * @Date:2017/8/30
 * @Note:
 */
public interface IChapterSpider {
    /**
     * 给url,获得章节列表
     * @param url
     * @return
     */
   Novel getsChapter(String url);
}
