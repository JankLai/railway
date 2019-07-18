package com.bigdata.railway.service;

import com.bigdata.railway.security_config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 登录
 */
@Service
public class UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
//    @Autowired
//    private SysUserMapper sysUserMapper;

//    public UserService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, SysUserMapper sysUserMapper) {
//        this.authenticationManager = authenticationManager;
//        this.userDetailsService = userDetailsService;
//        this.jwtTokenUtil = jwtTokenUtil;
//        this.sysUserMapper = sysUserMapper;
//    }

//    public String login(String username, String password) {
//        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
//        Authentication authentication = authenticationManager.authenticate(upToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        return jwtTokenUtil.generateToken(userDetails);
//    }

    public Map<String, Object> login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        System.out.println(userDetails.getAuthorities().toArray()[0]);
        Map<String, Object> map = new HashMap<>();
        map.put("token", jwtTokenUtil.generateToken(userDetails));

        map.put("role", userDetails.getAuthorities().toArray()[0]);

        return map;
//        return jwtTokenUtil.generateToken(userDetails);
    }

//    @Override
//    public String register(SysUser user) {
//        String username = user.getUsername();
//        if (userRepository.findByUsername(username) != null) {
//            return "用户已存在";
//        }
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String rawPassword = user.getPassword();
//        user.setPassword(encoder.encode(rawPassword));
//        List<String> roles = new ArrayList<>();
//        roles.add("ROLE_USER");
//        user.setRoles(roles);
//        userRepository.insert(user);
//        return "success";
//    }

    public String refreshToken(String oldToken) {
        String token = oldToken.substring("Bearer ".length());
        if (!jwtTokenUtil.isTokenExpired(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return "error";
    }
}
