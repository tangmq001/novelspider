package util.factory;

import impl.chapterDetail.BXWXChapterSpiderDetail;
import impl.chapterDetail.DefaultChapterDetailSpider;
import interfaces.chapterDetail.IChapterDetailSpider;
import util.SpiderResourceEnum;

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
