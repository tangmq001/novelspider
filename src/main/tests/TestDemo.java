import config.ConfigurationDownload;
import entity.Chapter;
import entity.ChapterDetail;
import entity.Chapters;
import entity.Novel;
import impl.chapter.BXWXChapterSpider;
import impl.chapterDetail.AbstractChapterDetailSpider;
import impl.chapterDetail.BXWXChapterSpiderDetail;
import impl.chapterDetail.DefaultChapterDetailSpider;
import impl.download.DownloadNovalImpl;
import impl.novel.BXWXNovelSpider;
import interfaces.chapterDetail.IChapterDetailSpider;
import interfaces.chapter.IChapterSpider;
import interfaces.download.IDownloadNoval;
import interfaces.novel.INovelSpider;
import org.junit.Test;
import util.MultiFileMergeUtil;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @Author:tangmq
 * @Date:2017/8/30
 * @Note:
 */
public class TestDemo {
    @Test
    public void testGetChapters() {
        IChapterSpider spider = new BXWXChapterSpider();
        Chapters novel = spider.getsChapter("http://www.bxwx9.org/b/5/5169/index.html");
        System.out.println(novel.getName() + "/书名");
        for (Chapter c : novel.getList()) {
            System.out.println(c);
        }
    }

    @Test
    public void testGetChapterDetail() {
        AbstractChapterDetailSpider detailSpider = new DefaultChapterDetailSpider();
        //ChapterDetail detail = detailSpider.getDetailByUrl("http://book.zongheng.com/chapter/637210/35372502.html");
        ChapterDetail detail = detailSpider.getDetailByUrl("http://www.bxwx9.org/b/5/5169/7393369.html");

        System.out.println(detail.getContent());
    }

    @Test
    public void testBXWXChapterSpider() {
        IChapterDetailSpider detailSpider = new BXWXChapterSpiderDetail();
        ChapterDetail detail = detailSpider.getDetailByUrl("http://www.bxwx9.org/b/5/5169/7393369.html");
        System.out.println(detail.getContent());
    }

    @Test
    public void testDownload() {
        IDownloadNoval downlaod = new DownloadNovalImpl();
        ConfigurationDownload config = new ConfigurationDownload();
        try {
            downlaod.download("http://www.bxwx9.org/b/5/5169/index.html", config);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMerge() {
        MultiFileMergeUtil.multiFileMerge("D:/novelSpider/", null, false);
    }

    @Test
    public void testDemo() {
        ArrayList<String> list = new ArrayList();
        list.add("http://www.bxwx9.org/b/5/5169/12204251.html");
        list.add("http://www.bxwx9.org/b/5/5169/12204249.html");
        list.add("http://www.bxwx9.org/b/5/5169/7393392.html");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String c1, String c2) {
                //'http://www.bxwx9.org/b/5/5169/7393368.html'
                //String c1 = c1.getUrl();
                //String c2 = c2.getUrl();
                String sub1 = c1.substring(c1.lastIndexOf("/") + 1, c1.lastIndexOf("."));
                String sub2 = c2.substring(c2.lastIndexOf("/") + 1, c2.lastIndexOf("."));

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
        for (String s : list) {
            System.out.println(s);
        }

    }
    @Test
    public void testNovelSpider(){
        INovelSpider spider = new BXWXNovelSpider();
        try {
            List<Novel> list = spider.getsNovel("http://www.bxwx9.org/modules/article/index.php?fullflag=1");
            for (Novel novel : list) {
                System.out.println(novel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testIterater(){
        BXWXNovelSpider spider = new BXWXNovelSpider();
        Iterator<List<Novel>> iterator = spider.getIterator("http://www.bxwx9.org/modules/article/index.php?fullflag=1");
        while (iterator.hasNext()){
            System.out.println(spider.next());
            List<Novel> next = iterator.next();
            for (Novel novel : next) {
                System.out.println(novel);
            }
        }
    }
}
