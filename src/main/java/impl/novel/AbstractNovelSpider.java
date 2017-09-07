package impl.novel;

import entity.Novel;
import interfaces.novel.INovleSpider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.SendRequestUtil;
import util.XmlParseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author:tangmq
 * @Date:2017/9/7
 * @Note:
 */
public abstract class AbstractNovelSpider implements INovleSpider {
    protected Elements selEles(String url) throws Exception {
        Map<String, String> configMap = XmlParseUtil.getSiteByUrl(url);
        String document=SendRequestUtil.getInstance().crawl(url,configMap.get("charset"));
        Document doc = Jsoup.parse(document);
        doc.setBaseUri(url);
        Elements eles = doc.select(configMap.get("novel-list-select"));
        if(eles==null) throw new RuntimeException("url="+url+",当前不支持改站点");
        return eles;
    }

}
