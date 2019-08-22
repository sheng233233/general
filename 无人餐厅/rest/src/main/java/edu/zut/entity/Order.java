package edu.zut.entity;

public class Order {
    private Integer id;

    private Integer tid;

    private String content;

    private Integer total;

    private Integer status;

    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content!=null){
            this.total = Integer.parseInt(content.split(" ")[0].split(":")[1]);
        }

        this.content = content == null ? null : content.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", tid=" + tid +
                ", content='" + content + '\'' +
                ", total=" + total +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}