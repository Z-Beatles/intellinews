package com.fintech.intellinews.shiro;

import java.util.LinkedHashMap;

/**
 * @author waynechu
 * Created 2017-10-23 14:03
 */
public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {

        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("/static/**", "anon");
        map.put("/resources/**", "anon");

        map.put("/v1/users", "anon"); // 注册
        map.put("/v1/users/**", "user");

        //map.put("/channels/**", "roles[]");//admin 设置角色
        //map.put("/**", "perms[add]");// 设置权限
        return map;
    }
}
