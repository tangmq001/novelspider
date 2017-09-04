package util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:tangmq
 * @Date:2017/8/31
 * @Note:
 */
public class XmlParseUtil{
    private static final Map<SpiderResourceEnum,Map<String,String>> CONTEXT_MAP=new HashMap();

    private XmlParseUtil() {}
    static {init();}
    private static void init(){
        SAXReader reader=new SAXReader();
        try {
            Document document = reader.read(new File("E:/workspace/novelspider/src/main/resources/config/novelSpider-config.xml"));
            Element root = document.getRootElement();
            List<Element> eles = root.elements("site");
            for (Element ele : eles) {
                List<Element> items = ele.elements();
                Map<String, String> map = new HashMap();
                for (Element item : items) {
                    map.put(item.getName(),item.getTextTrim());
                }
                CONTEXT_MAP.put(SpiderResourceEnum.getEnumByUrl(map.get("url")),map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> getSiteByUrl(String url){
        return CONTEXT_MAP.get(SpiderResourceEnum.getEnumByUrl(url));
    }
}
