package ssm.liyuq.service;

import org.apache.ibatis.annotations.Select;
import ssm.liyuq.domain.Permission;
import ssm.liyuq.domain.Role;

import java.util.List;

public interface IRoleService {

    public List<Role> findAll()throws Exception;

    void save(Role role) throws Exception;

    Role findById(String roleId);

    List<Permission> findOtherPermissions(String roleId);


    void addPermissionToRole(String userId, String[] roleIds);
}
