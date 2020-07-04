package ssm.liyuq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm.liyuq.dao.IPermissionDao;
import ssm.liyuq.domain.Permission;
import ssm.liyuq.service.IPermissionService;


import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public void updatePermissionById(String id,String permissionName,String url) {
        permissionDao.updatePermissionById(id,permissionName,url);
    }

    @Override
    public Permission findById(String id) {
        return null;
    }
}
