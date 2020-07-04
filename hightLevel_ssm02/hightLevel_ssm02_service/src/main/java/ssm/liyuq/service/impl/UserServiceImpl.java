package ssm.liyuq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm.liyuq.dao.IUserDao;
import ssm.liyuq.domain.Role;
import ssm.liyuq.domain.UserInfo;
import ssm.liyuq.service.IUserService;

import java.util.ArrayList;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己的User对象，User实现了UserDetails
        //User user = new User(userInfo.getUsername(), new BCryptPasswordEncoder().encode(userInfo.getPassword()),getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(), userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        for (Role role:roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }

        return list;
    }

    public List<UserInfo> findAll()throws Exception{
        List<UserInfo> list = userDao.findAll();
        return list;
    }

    public UserInfo findById(String userId)throws Exception{
        UserInfo userInfo = userDao.findById(userId);
        return userInfo;
    }

    public void addUser(UserInfo userInfo)throws Exception{

        userDao.addUser(userInfo);
    }

    @Override
    public List<Role> findOtherRoles(String userId) {
        return userDao.findOtherRole(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId:roleIds)
        {
            userDao.addRoleToUser(userId,roleId);
        }

    }
}
