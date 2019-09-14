package nil.ed.onlineexam.entity;

public class Permission {
    private Integer id;

    private String name;

    private String uri;

    private Byte delMark;

    private Long createTime;

    private Long updateTime;

    private Integer creator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Byte getDelMark() {
        return delMark;
    }

    public void setDelMark(Byte delMark) {
        this.delMark = delMark;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long lastUpdateTime) {
        this.updateTime = lastUpdateTime;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }
}