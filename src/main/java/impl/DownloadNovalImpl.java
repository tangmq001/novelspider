package impl;

import config.ConfigurationDownload;
import entity.Chapter;
import entity.ChapterDetail;
import entity.Novel;
import interfaces.IChapterDetailSpider;
import interfaces.IDownloadNoval;
import util.ChapterSpiderDetailFactory;
import util.ChapterSpiderFactory;
import util.MultiFileMergeUtil;
import util.SpiderResourceEnum;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by 唐明巧 on2017/9/3.
 * 1 获得章节列表分配给每个线程
 * 2 每个线程下载后存放本地
 * 3 下载完成后存放在本地
 *
 * @Note：
 */
public class DownloadNovalImpl implements IDownloadNoval {
    @Override
    public String download(String url, ConfigurationDownload config) {
        Novel novel = ChapterSpiderFactory.getInstanceByUrl(url).getsChapter(url);
        List<Chapter> chapters = novel.getList();
        //1 获得章节列表分配给每个线程
        //0-99;100-199...2000-2010
        int size = config.getSize();
        int maxSize = (int) Math.ceil(chapters.size() * 1.0 / size);
        Map<String, List<Chapter>> mapChapter = new HashMap<>();
        for (int i = 0; i < maxSize; i++) {
            int begin = i * size;
            int end = i * size + size;
            if (i == maxSize - 1) {
                end = chapters.size();
            }
            mapChapter.put(begin + "-" + end, chapters.subList(begin, end));
        }
        //每个线程下载后存放本地
        ExecutorService service = Executors.newFixedThreadPool(maxSize);
        Set<String> keys = mapChapter.keySet();
        List<Future<String>> futures = new ArrayList<>();
        String savePath=config.getPath()+"/"+ SpiderResourceEnum.getEnumByUrl(url).getKeyWord()+"/"+novel.getName();
        new File(savePath).mkdirs();
        for (String key : keys) {
            Future f = service.submit(new DownloadThread(mapChapter.get(key), savePath + "/" + key + ".txt",config.getTryTimes()));
            futures.add(f);
        }
        service.shutdown();//在最后一个线程执行完毕后,连接池关闭,不然会造成内存溢出
        for (Future<String> future : futures) {
            try {
                System.out.println(future.get() + ",下载完成!");  //future.get()输出的是call()返回的值,
                // 线程只有在call()有了返回值才会继续执行
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return MultiFileMergeUtil.multiFileMerge(savePath, novel.getName(), true);
    }

    class DownloadThread implements Callable<String> {
        private List<Chapter> list;
        private String path;
        private Integer tryTimes;

        public DownloadThread(List<Chapter> list, String path, Integer tryTimes) {
            this.list = list;
            this.path = path;
            this.tryTimes = tryTimes;
        }

        @Override
        public String call() throws Exception {
            try (
                    PrintWriter writer = new PrintWriter(new File(path), "UTF-8")
            ) {
                for (Chapter chapter : list) {
                    IChapterDetailSpider chapterDetailSpider = ChapterSpiderDetailFactory.getInstanceByUrl(chapter.getUrl());
                    for (int i = 0; i < tryTimes; i++) {
                        ChapterDetail detail =null;
                        try {
                            detail = chapterDetailSpider.getDetailByUrl(chapter.getUrl());
                            writer.println(detail.getTitle());
                            writer.println(detail.getContent());
                            break;
                        } catch (Exception e) {
                            System.err.println("第["+i+"/"+tryTimes+"]次下载:"+chapter.getTitle()+"失败!");
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return path;
        }
    }
}
