package impl;

import entity.Chapter;
import entity.Novel;

import java.util.*;

/**
 * Created by 唐明巧 on2017/9/3.
 *
 * @Note：
 */
public class BXWXChapterSpider extends AbstractChapterSpider {
    @Override
    public Novel getsChapter(String url) {
        Novel novel = super.getsChapter(url);
        List<Chapter> chapters = novel.getList();
        //根據路徑,判斷章節先後
        Collections.sort(chapters, new Comparator<Chapter>() {
            @Override
            public int compare(Chapter c1, Chapter c2) {
                //'http://www.bxwx9.org/b/5/5169/7393368.html'
                String url1 = c1.getUrl();
                String url2 = c2.getUrl();
                String sub1 = url1.substring(url1.lastIndexOf("/") + 1, url1.lastIndexOf("."));
                String sub2 = url2.substring(url2.lastIndexOf("/") + 1, url2.lastIndexOf("."));
                int i1 = sub1.hashCode();
                int i2 = sub2.hashCode();
                if (i1 > i2) {
                    return 1;
                } else if (i1 == i2) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        return novel;
    }
}
