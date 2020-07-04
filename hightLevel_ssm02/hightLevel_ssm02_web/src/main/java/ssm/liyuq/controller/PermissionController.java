package ssm.liyuq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ssm.liyuq.domain.Permission;

import ssm.liyuq.domain.Role;
import ssm.liyuq.service.IPermissionService;


import java.util.List;

@Controller
@Transactional
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.addObject("permissionList",permissionList);
        mv.setViewName("permission-list");
        return mv;

    }

    @RequestMapping("/save")
    public String save(Permission permission) throws Exception {
        permission.setId("R4");
        permissionService.save(permission);
        return "redirect:findAll";
    }

    //查询权限详情
    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.findById(id);
        if(permission==null)
            System.out.println("没有");
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;
    }

    //修改权限信息
    @RequestMapping("/updatePermissionById")
    public String updatePermissionById(String id) throws Exception {
        permissionService.updatePermissionById(id);
        return "redirect:findAll";
    }
}
