package ab.tjl.entity;

/**
 * @author:tangjilin
 * @Description:友情链接实体类
 * @Date:Created in 14:47 2019/8/10
 * @Modified By:
 */
public class Link {
    /**主键*/
    private Integer id;
    /**网站名称*/
    private String linkName;
    /**网站地址*/
    private String linkUrl;
    /**序号*/
    private Integer orderNo;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLinkName() {
        return linkName;
    }
    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }
    public String getLinkUrl() {
        return linkUrl;
    }
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
    public Integer getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

}
