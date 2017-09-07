package interfaces.download;

import config.ConfigurationDownload;

import java.util.concurrent.ExecutionException;

/**
 * Created by 唐明巧 on2017/9/3.
 *
 * @Note：
 */
public interface IDownloadNoval {
    String download(String url, ConfigurationDownload config) throws ExecutionException, InterruptedException;
}
