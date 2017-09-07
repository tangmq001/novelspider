package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author:tangmq
 * @Date:2017/9/7
 * @Note:小说类
 */
public class Novel implements Serializable {
    /**书名*/
    private String name;
    /**小说地址*/
    private String url;
    /**作者*/
    private String author;
    /**最近章节*/
    private String recentChapter;
    /**最近章节*/
    private String type;
    /**最近章节路径*/
    private String recentChapterUrl;
    /**字数:单位(千)*/
    private String wordCount;
    /**小说状态:1完结;0连载*/
    private Byte status;
    /**最后更新时间*/
    private Date lastUpdateTime;
    /**小说名首字母*/
    private Character nameFirstChar;
    /**站点id*/
    private Integer siteId;
    /**点击量*/
    private BigDecimal clickNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRecentChapter() {
        return recentChapter;
    }

    public void setRecentChapter(String recentChapter) {
        this.recentChapter = recentChapter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecentChapterUrl() {
        return recentChapterUrl;
    }

    public void setRecentChapterUrl(String recentChapterUrl) {
        this.recentChapterUrl = recentChapterUrl;
    }

    public String getWordCount() {
        return wordCount;
    }

    public void setWordCount(String wordCount) {
        this.wordCount = wordCount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Character getNameFirstChar() {
        return nameFirstChar;
    }

    public void setNameFirstChar(Character nameFirstChar) {
        this.nameFirstChar = nameFirstChar;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public BigDecimal getClickNum() {
        return clickNum;
    }

    public void setClickNum(BigDecimal clickNum) {
        this.clickNum = clickNum;
    }
}
