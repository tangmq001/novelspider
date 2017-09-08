package util.factory;

import impl.novel.BXWXNovelSpider;
import interfaces.novel.INovelSpider;
import util.SpiderResourceEnum;

/**
 * @Author:tangmq
 * @Date:2017/9/8
 * @Note:
 */
public class NovelSpiderFactory {
    private NovelSpiderFactory() {
    }

    public static INovelSpider getNovelSpider(String url) {
        INovelSpider spider = null;
        int id = SpiderResourceEnum.getEnumByUrl(url).getId();
        switch (id) {
            case 3:
                spider = new BXWXNovelSpider();
                break;
            default:
                throw new RuntimeException(url + "不支持该站点");
        }
        return spider;
    }
}
