package impl.novel;

import entity.Novel;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.NovelSpiderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:tangmq
 * @Date:2017/9/7
 * @Note:
 */
public class BXWXNovelSpider extends AbstractNovelSpider {

    /**
     * 根据url,获得小说列表
     *
     * @param url
     * @return
     */
    @Override
    public List<Novel> getsNovel(String url) throws Exception {
        Elements elements = selEles(url);
        ArrayList list = new ArrayList();
        for (int i = 1, size = elements.size(); i < size; i++) {
            Novel novel = new Novel();
            Elements tds = elements.get(i).getElementsByTag("td");
            //存储小说名
            Element e0 = tds.get(0).getElementsByTag("a").get(0);
            novel.setName(e0.text());
            novel.setUrl(e0.absUrl("href"));
            //存储最近章节
            Element e1 = tds.get(1).getElementsByTag("a").get(0);
            novel.setRecentChapter(e1.text());
            novel.setRecentChapterUrl(e1.absUrl("href"));
            //存储作者
            Element e2 = tds.get(2);
            novel.setAuthor(e2.text());
            //存储字数
            Element e3 = tds.get(3);
            novel.setWordCount(e3.text());
            //存储最近更新时间
            Element e4 = tds.get(4);
            novel.setLastUpdateTime(NovelSpiderUtil.getDate(e4.text(),"yyyy-MM-dd"));
            //

        }
        return list;
    }
}
