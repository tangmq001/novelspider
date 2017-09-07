package util;

import impl.chapter.BXWXChapterSpider;
import impl.chapter.DefaultChapterSpider;
import interfaces.chapter.IChapterSpider;

/**
 * Created by 唐明巧 on2017/9/3.
 *
 * @Note：
 */
public class ChapterSpiderFactory {
    private ChapterSpiderFactory() {
    }

    public static IChapterSpider getInstanceByUrl(String url) {
        SpiderResourceEnum e = SpiderResourceEnum.getEnumByUrl(url);
        IChapterSpider spider = null;
        switch (e) {
            case BIXIAWENXUE:
                spider = new BXWXChapterSpider();
                break;
            case BIQUGE:
            case ZHONGHENGZHONGWEN:
                spider = new DefaultChapterSpider();
                break;
        }
        return spider;
    }
}
