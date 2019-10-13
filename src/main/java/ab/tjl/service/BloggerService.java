package ab.tjl.service;

import ab.tjl.entity.Blogger;

/**
 * @author:tangjilin
 * @Description:业务层接口：博主相关接口
 * @Date:Created in 14:52 2019/8/10
 * @Modified By:
 */
public interface BloggerService {
    /**
     * Descrption: 根据登录名查询博主对象
     * @Param: [userName]
     * @Return: ab.tjl.entity.Blogger
     */
    public Blogger getByUserName(String userName);

    /**
     * Descrption: 更新博主对象
     * @Param: [blogger]
     * @Return: java.lang.Integer
     */
    public Integer update(Blogger blogger);

    /**
     * Descrption:查询博主
     * @Param: []
     * @Return: ab.tjl.entity.Blogger
     */
    public Blogger find();
}
