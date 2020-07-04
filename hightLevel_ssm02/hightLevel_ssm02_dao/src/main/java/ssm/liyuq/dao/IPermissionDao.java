package ssm.liyuq.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ssm.liyuq.domain.Permission;
import ssm.liyuq.domain.Role;

import java.util.List;

public interface IPermissionDao {
    @Select("select * from permission where id in(select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findPerById(String roleId)throws Exception;

    @Select("select * from permission")
    List<Permission> findAll()throws Exception;

    @Insert("insert into permission (id,permissionName,url) values(#{id},#{permissionName},#{url})")
    void save(Permission permission)throws Exception;

    //查询与role关联的所有的权限
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id} )")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;

    @Update("update permission set permissionName=#{permissionName} and url=#{url}")
    void updatePermissionById(String id,String permissionName,String url);

    @Select("select * from permission where id=#{id}")
    Permission findById(String id);
}
