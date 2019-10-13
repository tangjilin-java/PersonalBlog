package ab.tjl.entity;

/**
 * @author:tangjilin
 * @Description:博客类型实体类
 * @Date:Created in 14:47 2019/8/10
 * @Modified By:
 */
public class BlogType {
    private static final long serialVersionUID = 1L;
    /**主键*/
    private Integer id;
    /**类型名称*/
    private String typeName;
    /**序号*/
    private Integer orderNo;
    /**该类型下博客的数量*/
    private Integer blogCount;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public Integer getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
    public Integer getBlogCount() {
        return blogCount;
    }
    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }


}
