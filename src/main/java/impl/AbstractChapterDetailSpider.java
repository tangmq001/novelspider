package impl;

import entity.ChapterDetail;
import interfaces.IChapterDetailSpider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import util.SendRequestUtil;
import util.XmlParseUtil;

/**
 * @Author:tangmq
 * @Date:2017/8/31
 * @Note:
 */
public abstract class AbstractChapterDetailSpider implements IChapterDetailSpider {
    /**
     * 根据url 获得章节详情对象
     *
     * @param url
     * @return
     */
    @Override
    public ChapterDetail getDetailByUrl(String url) {
        try {
            ChapterDetail detail = new ChapterDetail();
            String result = SendRequestUtil.getInstance().crawl(url, XmlParseUtil.getSiteByUrl(url).get("charset"));
            result=result.replace("&nbap;"," ");
            Document document = Jsoup.parse(result);
            document.setBaseUri(url);
            String contSel = XmlParseUtil.getSiteByUrl(url).get("chapter-detail-content-select");
            detail.setContent(getBySelector(document, contSel).text());//存储内容
            String titSel = XmlParseUtil.getSiteByUrl(url).get("chapter-detail-title-select");
            detail.setTitle(getBySelector(document, titSel).text());//存储标题
            String provSel = XmlParseUtil.getSiteByUrl(url).get("chapter-detail-prev-select");
            detail.setPrev(getBySelector(document, provSel).absUrl("href"));//存储上一页
            String nextSel = XmlParseUtil.getSiteByUrl(url).get("chapter-detail-next-select");
            detail.setNext(getBySelector(document, nextSel).absUrl("href"));//存储下一页
            return detail;
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
