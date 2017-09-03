package util;

import impl.BXWXChapterSpider;
import impl.BXWXChapterSpiderDetail;
import impl.DefaultChapterDetailSpider;
import impl.DefaultChapterSpider;
import interfaces.IChapterDetailSpider;
import interfaces.IChapterSpider;

/**
 * Created by 唐明巧 on2017/9/3.
 *
 * @Note：
 */
public class ChapterSpiderDetailFactory {
    private ChapterSpiderDetailFactory() {
    }

    public static IChapterDetailSpider getInstanceByUrl(String url) {
        SpiderResourceEnum e = SpiderResourceEnum.getEnumByUrl(url);
        IChapterDetailSpider spider = null;
        switch (e) {
            case BIXIAWENXUE:
                spider = new BXWXChapterSpiderDetail();
                break;
            case BIQUGE:
            case ZHONGHENGZHONGWEN:
                spider = new DefaultChapterDetailSpider();
                break;
        }
        return spider;
    }
}
