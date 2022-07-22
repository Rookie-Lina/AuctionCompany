package com.sg.utilObject;

import com.alibaba.fastjson.annotation.JSONField;
import com.sg.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {
    private User user;
    private List<String> permission;
    //存储SpringSecurity所需要的权限信息的集合
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(permission==null||permission.size()==0){
            return null;
        }
        authorities=new ArrayList<>();
        //把permissions中字符串类型的权限信息转换成GrantedAuthority对象存入authorities中
        for (String s : permission) {
            SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(s);
            authorities.add(simpleGrantedAuthority);
        }
        return authorities;
    }

    public LoginUser(User user, List<String> permission) {
        this.user = user;
        this.permission = permission;
    }

    @Override
    public String getPassword() {
        return user.getLoginPwd();
    }

    @Override
    public String getUsername() {
        return user.getLoginPwd();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
