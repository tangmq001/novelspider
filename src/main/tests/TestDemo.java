import entity.Chapter;
import entity.ChapterDetail;
import impl.AbstractChapterDetailSpider;
import impl.AbstractChapterSpider;
import impl.DefaultChapterDetailSpider;
import impl.DefaultChapterSpider;
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
        AbstractChapterSpider spider = new DefaultChapterSpider();
        List<Chapter> chapters = spider.getsChapter("http://book.zongheng.com/showchapter/657566.html");
        for(Chapter c:chapters){
            System.out.println(c);
        }
    }
    @Test
    public void testGetChapterDetail(){
        AbstractChapterDetailSpider detailSpider=new DefaultChapterDetailSpider();
        //ChapterDetail detail = detailSpider.getDetailByUrl("http://book.zongheng.com/chapter/637210/35372502.html");
        ChapterDetail detail = detailSpider.getDetailByUrl("http://www.woquge.com/38_38857/15139165.html");

        System.out.println(detail.getContent());
    }
}
