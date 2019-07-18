package com.bigdata.railway.dao;

import com.bigdata.railway.entity.SysRole;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}