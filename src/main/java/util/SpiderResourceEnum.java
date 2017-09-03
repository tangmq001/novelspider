package util;

/**
 * @Author:tangmq
 * @Date:2017/8/31
 * @Note:
 */
public enum SpiderResourceEnum {
    BIQUGE(1, "woquge"), ZHONGHENGZHONGWEN(2, "zongheng"),BIXIAWENXUE(3,"bxwx9");
    private int id;
    private String keyWord;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    SpiderResourceEnum(int id, String keyWord) {
        this.id=id;
        this.keyWord=keyWord;
    }
    /**
     * 根据id获取枚举的公共方法
     */
    public static SpiderResourceEnum getEnumById(int id){
        switch (id) {
            case 1:
                return BIQUGE;
            case 2:
                return ZHONGHENGZHONGWEN;
            case 3:
                return BIXIAWENXUE;
            default:
                throw new RuntimeException("id="+id+",不在搜索范围内");
        }
    }

    /**
     * 根据url获取枚举的公共方法
     * @param url
     * @return
     */
    public static SpiderResourceEnum getEnumByUrl(String url){
        for (SpiderResourceEnum e : values()) {
            if(url.contains(e.getKeyWord())){
                return e;
            }
        }
        throw new RuntimeException("url="+url+",不在搜索范围");
    }
}
