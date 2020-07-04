package ssm.liyuq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ssm.liyuq.domain.Permission;
import ssm.liyuq.domain.Role;

import ssm.liyuq.domain.UserInfo;
import ssm.liyuq.service.IRoleService;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;

    }

    //查询用户详情
    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        if(role==null)
            System.out.println("没有");
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Role role) throws Exception {
        role.setId("R4");
        roleService.save(role);
        return "redirect:findAll";
    }

    //根据roleId查询role,并查询出可以添加的权限
    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true)String roleId) throws Exception {
        ModelAndView mv =  new ModelAndView();
        //1.根据用户id查询用户
        Role role = roleService.findById(roleId);
        //2.根据用户id查询可以添加的角色
        List<Permission> otherPermissions = roleService.findOtherPermissions(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",otherPermissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    //给用户添加角色
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true)String roleId,@RequestParam(name = "ids",required = true)String[] PermissionIds){
        roleService.addPermissionToRole(roleId,PermissionIds);
        return "redirect:findAll";
    }
}
