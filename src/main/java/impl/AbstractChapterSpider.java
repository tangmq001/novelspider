package impl;

import entity.Chapter;
import interfaces.IChapterSpider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.SendRequestUtil;
import util.XmlParseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:tangmq
 * @Date:2017/8/30
 * @Note:
 */
public abstract class AbstractChapterSpider implements IChapterSpider {

    /**
     * 给url,获得章节列表
     *
     * @param url
     * @return
     */
    public List<Chapter> getsChapter(String url) {
        try {
            String result = SendRequestUtil.getInstance().crawl(url,XmlParseUtil.getSiteByUrl(url).get("charset"));
            Document document = Jsoup.parse(result);
            document.setBaseUri("url");
            Elements els = document.select(XmlParseUtil.getSiteByUrl(url).get("chapter-list-select"));
            ArrayList<Chapter> chapters = new ArrayList();
            for (Element e : els) {
                Chapter chapter = new Chapter();
                chapter.setTitle(e.text());
                chapter.setUrl(e.absUrl("href"));
                chapters.add(chapter);
            }
            return chapters;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
