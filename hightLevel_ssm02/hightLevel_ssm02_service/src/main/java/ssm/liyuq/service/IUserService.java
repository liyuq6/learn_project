package ssm.liyuq.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ssm.liyuq.domain.Role;
import ssm.liyuq.domain.UserInfo;

import java.util.List;

public interface IUserService extends UserDetailsService {
    public List<UserInfo> findAll()throws Exception;

    public UserInfo findById(String UserId)throws Exception;

    public void addUser(UserInfo userInfo)throws Exception;

    List<Role> findOtherRoles(String userId);

    void addRoleToUser(String userId, String[] roleIds);
}
