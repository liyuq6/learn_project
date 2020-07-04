package ssm.liyuq.dao;

import org.apache.ibatis.annotations.*;
import ssm.liyuq.domain.Permission;
import ssm.liyuq.domain.Role;

import java.util.List;

public interface IRoleDao {
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId}) ")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "ssm.liyuq.dao.IPermissionDao.findPerById"))
    })
    public List<Role> findRoleByUserId(String userId)throws Exception;

    @Select("select * from role")
    public List<Role> findAll()throws Exception;

    @Insert("insert into role (id,roleName,roleDesc) values(#{id},#{roleName},#{roleDesc})")
    void save(Role role)throws Exception;

    @Select("select * from role where id=#{roleId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "ssm.liyuq.dao.IPermissionDao.findPermissionByRoleId"))
    })
    Role findById(String roleId);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId} )")
    List<Permission> findOtherPermissions(String roleId);

    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId")String roleId, @Param("permissionId")String permissionId);
}
