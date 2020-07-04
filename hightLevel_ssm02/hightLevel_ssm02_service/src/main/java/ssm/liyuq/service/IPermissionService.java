package ssm.liyuq.service;

import ssm.liyuq.domain.Permission;

import java.util.List;

public interface IPermissionService {
    public List<Permission> findAll()throws Exception;

    void save(Permission permission)throws Exception;

    void updatePermissionById(String id,String permissionName,String url);

    Permission findById(String id);
}
