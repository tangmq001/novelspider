package config;

import java.io.Serializable;

/**
 * @Author:tangmq
 * @Date:2017/9/4
 * @Note:
 */
public class ConfigurationDownload implements Serializable{
    private static final Integer DEFAULT_DOWNLOAD_SIZE=100;
    private static final Integer DEFAULT_TRY_TIMES=3;
    private static final String DEFAULT_SAVE_PATH="D:/novelSpider";
    private String  path;
    private Integer size;
    private Integer tryTimes;

    public ConfigurationDownload() {
        this.size = DEFAULT_DOWNLOAD_SIZE;
        this.tryTimes=DEFAULT_TRY_TIMES;
        this.path=DEFAULT_SAVE_PATH;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTryTimes() {
        return tryTimes;
    }

    public void setTryTimes(Integer tryTimes) {
        this.tryTimes = tryTimes;
    }
}
