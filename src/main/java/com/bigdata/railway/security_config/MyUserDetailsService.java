package com.bigdata.railway.security_config;

import com.bigdata.railway.dao.SysRoleMapper;
import com.bigdata.railway.dao.SysUserMapper;
import com.bigdata.railway.dao.SysUserRoleMapper;
import com.bigdata.railway.entity.SysUser;
import com.bigdata.railway.entity.SysUserExample;
import com.bigdata.railway.entity.SysUserRole;
import com.bigdata.railway.entity.SysUserRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("tesging"+s);
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUsernameEqualTo(s);
        SysUser sysUser = sysUserMapper.selectByExample(sysUserExample).get(0);
        if (sysUser == null)
            throw new UsernameNotFoundException(s + "用户不存在");

        //用户角色
        List<GrantedAuthority> roles = new ArrayList<>();

        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria().andUserIdEqualTo(sysUser.getId());

        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectByExample(sysUserRoleExample);
        if (sysUserRoles.size()==0) {
            System.out.println("空");
            roles.add(new SimpleGrantedAuthority("Normal"));
        }
        List<String> ss = new ArrayList<>();
        for(SysUserRole sysUserRole : sysUserRoles){
            ss.add(sysRoleMapper.selectByPrimaryKey(sysUserRole.getRoleId()).getRoleName());
        }

        for (String string : ss) {
            roles.add(new SimpleGrantedAuthority(string));
        }

        return new JwtUser(sysUser, roles);
    }
}
