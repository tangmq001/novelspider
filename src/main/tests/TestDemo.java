import config.ConfigurationDownload;
import entity.Chapter;
import entity.ChapterDetail;
import impl.*;
import interfaces.IChapterDetailSpider;
import interfaces.IChapterSpider;
import interfaces.IDownloadNoval;
import org.junit.Test;
import util.MultiFileMergeUtil;
import util.NovelSpiderHttpGet;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
        System.out.println(detail.getContent());
    }
    @Test
    public void testDownload(){
        IDownloadNoval downlaod = new DownloadNovalImpl();
        ConfigurationDownload config = new ConfigurationDownload();
        config.setPath("E:/novelSpider/bxwx/frxxz");
        try {
            downlaod.download("http://www.bxwx9.org/b/5/5169/index.html",config);
        } catch (ExecutionException |InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testMerge(){
        MultiFileMergeUtil.multiFileMerge("E:/novelSpider/bxwx/frxxz","",false);
    }
}
