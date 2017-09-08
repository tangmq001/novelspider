package impl.novel;

import entity.Novel;
import interfaces.novel.INovelSpider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.SendRequestUtil;
import util.XmlParseUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author:tangmq
 * @Date:2017/9/7
 * @Note:
 */
public abstract class AbstractNovelSpider implements INovelSpider {
    protected String nextPageUrl;

    protected Elements selEles(String url) {
        Map<String, String> configMap = XmlParseUtil.getSiteByUrl(url);
        Document doc = null;
        try {
            String document = SendRequestUtil.getInstance().crawl(url, configMap.get("charset"));
            doc = Jsoup.parse(document);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        doc.setBaseUri(url);
        Element e1 = doc.select(configMap.get("novel-nextPage-select")).first();
        nextPageUrl = e1 == null ? "" : e1.absUrl("href");
        Elements eles = doc.select(configMap.get("novel-list-select"));
        if (eles == null) throw new RuntimeException("url=" + url + ",当前不支持改站点");
        return eles;
    }

    protected boolean hasNext() {
        return !nextPageUrl.isEmpty();
    }

    protected Elements next() {
        return selEles(nextPageUrl);
    }

    class NovelIterator implements Iterator {

        public NovelIterator() {}

        @Override
        public boolean hasNext() {
            return AbstractNovelSpider.this.hasNext();
        }

        @Override
        public List<Novel> next() {
            return getsNovel();
        }
    }
}
