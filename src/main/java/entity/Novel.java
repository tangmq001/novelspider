package entity;

import java.util.List;

/**
 * @Author:tangmq
 * @Date:2017/9/6
 * @Note:小说类
 */
public class Novel {
    private String name;//小说名
    private List<Chapter> list;//章节列表
    private String url;//地址

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Chapter> getList() {
        return list;
    }

    public void setList(List<Chapter> list) {
        this.list = list;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
