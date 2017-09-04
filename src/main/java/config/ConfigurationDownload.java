package config;

import java.io.Serializable;

/**
 * @Author:tangmq
 * @Date:2017/9/4
 * @Note:
 */
public class ConfigurationDownload implements Serializable{
    private static final Integer DEFAULT_DOWNLOAD_SIZE=100;
    private String  path;
    private Integer size;

    public ConfigurationDownload() {
        this.size = DEFAULT_DOWNLOAD_SIZE;
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
}
