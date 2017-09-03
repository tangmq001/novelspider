package impl;

import entity.Chapter;
import interfaces.IDownloadNoval;
import util.ChapterSpiderFactory;

import java.util.List;

/**
 * Created by 唐明巧 on2017/9/3.
 *
 * @Note：
 */
public class DownloadNovalImpl implements IDownloadNoval{
    @Override
    public String download(String url) {
        List<Chapter> chapters = ChapterSpiderFactory.getInstanceByUrl(url).getsChapter(url);
        return null;
    }
}
