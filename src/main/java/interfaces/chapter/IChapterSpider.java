package interfaces.chapter;

import entity.Chapters;

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
   Chapters getsChapter(String url);
}
