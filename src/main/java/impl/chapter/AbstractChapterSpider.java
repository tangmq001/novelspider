package impl.chapter;

import entity.Chapter;
import entity.Chapters;
import interfaces.chapter.IChapterSpider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import util.SendRequestUtil;
import util.XmlParseUtil;

import java.util.ArrayList;

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
    public Chapters getsChapter(String url) {
        Chapters novel = new Chapters();
        try {
            String result = SendRequestUtil.getInstance().crawl(url, XmlParseUtil.getSiteByUrl(url).get("charset"));
            Document document = Jsoup.parse(result);
            document.setBaseUri(url);
            String s = XmlParseUtil.getSiteByUrl(url).get("bookName");
            novel.setName(getBySelector(document,s).text());
            Elements els = document.select(XmlParseUtil.getSiteByUrl(url).get("chapter-list-select"));
            ArrayList<Chapter> chapters = new ArrayList();
            for (Element e : els) {
                Chapter chapter = new Chapter();
                chapter.setTitle(e.text());
                chapter.setUrl(e.absUrl("href"));
                chapters.add(chapter);
            }
            novel.setList(chapters);
            return novel;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    private String[] parseSelector(String[] strs) {
        String[] strN = new String[2];
        try {
            if (strs.length == 1) {
                strN[0] = strs[0];
                strN[1] = "0";
                return strN;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs;
    }

    private Element getBySelector(Document document, String selector) {
        Element p = new Element(Tag.valueOf("p"), "");
        p.text(document+" 中没有找到匹配数据!");
        String[] selects = selector.split(",");
        selects = parseSelector(selects);
        Elements eles = document.select(selects[0]);
        if(eles.size()>0){
            return eles.get(Integer.parseInt(selects[1]));
        }
        return p;
    }

}
