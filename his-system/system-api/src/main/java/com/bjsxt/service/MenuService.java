package com.bjsxt.service;

import com.bjsxt.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bjsxt.domain.SimpleUser;

import java.util.List;

public interface MenuService{

    /**
     * 查询菜单信息，如果用户是超级管理员查询所有菜单
     * 如果是普通用户，根据用户id关联角色和权限
     * @param isAdmin 是否是超级管理员
     * @param simpleUser 如果isAdmin=true simpleUser可以为空
     */
    List<Menu> selectMenuTree(boolean isAdmin, SimpleUser simpleUser);

}
