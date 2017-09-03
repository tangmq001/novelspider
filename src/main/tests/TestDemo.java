import entity.Chapter;
import entity.ChapterDetail;
import impl.*;
import interfaces.IChapterDetailSpider;
import interfaces.IChapterSpider;
import org.junit.Test;

import java.util.List;

/**
 * @Author:tangmq
 * @Date:2017/8/30
 * @Note:
 */
public class TestDemo {
    @Test
    public void testGetChapters(){
        IChapterSpider spider = new BXWXChapterSpider();
        List<Chapter> chapters = spider.getsChapter("http://www.bxwx9.org/b/5/5169/index.html");
        for(Chapter c:chapters){
            System.out.println(c);
        }
    }
    @Test
    public void testGetChapterDetail(){
        AbstractChapterDetailSpider detailSpider=new DefaultChapterDetailSpider();
        //ChapterDetail detail = detailSpider.getDetailByUrl("http://book.zongheng.com/chapter/637210/35372502.html");
        ChapterDetail detail = detailSpider.getDetailByUrl("http://www.bxwx9.org/b/5/5169/7393369.html");

        System.out.println(detail.getContent());
    }
    @Test
    public void testBXWXChapterSpider(){
        IChapterDetailSpider detailSpider=new BXWXChapterSpiderDetail();
        ChapterDetail detail = detailSpider.getDetailByUrl("http://www.bxwx9.org/b/5/5169/7393369.html");
        System.out.println(detail);
    }
}
