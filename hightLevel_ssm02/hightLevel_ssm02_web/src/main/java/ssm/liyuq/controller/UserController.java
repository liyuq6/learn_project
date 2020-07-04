package ssm.liyuq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ssm.liyuq.domain.Product;
import ssm.liyuq.domain.Role;
import ssm.liyuq.domain.UserInfo;
import ssm.liyuq.service.IUserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //查询所有用户
    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        if(userList==null)
            System.out.println("没有");
        mv.addObject("userList",userList);

        mv.setViewName("user-list");
        return mv;
    }

    //查询用户详情
    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(id);
        if(user==null)
            System.out.println("没有");
        mv.addObject("user",user);
        mv.setViewName("user-show1");
        return mv;
    }


    //用户添加
    @RequestMapping("/save")
    public String saveProduct(UserInfo userInfo)throws Exception{
        userInfo.setId("afga");
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userService.addUser(userInfo);
        return "redirect:findAll";
    }


    //查询用户和用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true)String userid) throws Exception {
        ModelAndView mv =  new ModelAndView();
        //1.根据用户id查询用户
        UserInfo userInfo = userService.findById(userid);
        //2.根据用户id查询可以添加的角色
        List<Role> otherRoles = userService.findOtherRoles(userid);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    //给用户添加角色
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,@RequestParam(name = "ids",required = true)String[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll";
    }
}
