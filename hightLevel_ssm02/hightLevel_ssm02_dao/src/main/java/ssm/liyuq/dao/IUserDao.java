package ssm.liyuq.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;
import ssm.liyuq.domain.Role;
import ssm.liyuq.domain.UserInfo;

import java.util.List;

public interface IUserDao {
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "ssm.liyuq.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users ")
    public List<UserInfo> findAll()throws Exception;

    @Select("select * from users where id=#{userId}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "ssm.liyuq.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findById(String userId)throws Exception;

    @Insert("insert into users (id,username,email,password,phoneNum,status) values (#{id},#{username},#{email},#{password},#{phoneNum},#{status})")
    public void addUser(UserInfo userInfo)throws Exception;

    @Select("select * from role where id not in(select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRole(String userId);

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId")String roleIds);
}
