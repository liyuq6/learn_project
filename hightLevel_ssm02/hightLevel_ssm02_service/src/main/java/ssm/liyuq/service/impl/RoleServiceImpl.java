package ssm.liyuq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm.liyuq.dao.IRoleDao;
import ssm.liyuq.domain.Permission;
import ssm.liyuq.domain.Role;
import ssm.liyuq.service.IRoleService;

import java.util.List;
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }


    @Override
    public void save(Role role) throws Exception {
       roleDao.save(role);
    }

    @Override
    public Role findById(String roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermissions(String roleId) {
        return roleDao.findOtherPermissions(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId:permissionIds)
        {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
