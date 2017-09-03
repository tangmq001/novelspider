package impl;

import entity.Chapter;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 唐明巧 on2017/9/3.
 *
 * @Note：
 */
public class BXWXChapterSpider extends AbstractChapterSpider {
    @Override
    public List<Chapter> getsChapter(String url) {
        List<Chapter> chapters = super.getsChapter(url);
        //根據路徑,判斷章節先後
        Collections.sort(chapters, new Comparator<Chapter>() {
            @Override
            public int compare(Chapter c1, Chapter c2) {
                //'http://www.bxwx9.org/b/5/5169/7393368.html'
                String url1 = c1.getUrl();
                String url2 = c2.getUrl();
                String sub1 = url1.substring(url1.lastIndexOf("/") + 1, url1.lastIndexOf("."));
                String sub2 = url2.substring(url2.lastIndexOf("/") + 1, url2.lastIndexOf("."));
                return sub1.compareTo(sub2);//String 類中的compareTo 方法
            }
        });
        return chapters;
    }
}
