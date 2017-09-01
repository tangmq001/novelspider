package interfaces;

import entity.Chapter;
import entity.ChapterDetail;

/**
 * @Author:tangmq
 * @Date:2017/8/31
 * @Note:
 */
public interface IChapterDetailSpider {
    /**
     * 根据url 获得章节详情对象
     * @param url
     * @return
     */
    ChapterDetail getDetailByUrl(String url);
}
