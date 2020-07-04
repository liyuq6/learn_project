package ssm.liyuq.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ssm.liyuq.domain.Orders;
import ssm.liyuq.service.IOrderService;

import java.util.List;

/**
 * 订单控制类
 * */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    /**
     * 查询所有未分页
     * */
//    @RequestMapping("/findAll")
//    public ModelAndView findAll() throws Exception{
//        ModelAndView mv = new ModelAndView();
//        List<Orders> ordersList = orderService.findAll();
//        mv.addObject("ordersList",ordersList);
//        mv.setViewName("orders-list");
//        return mv;
//
//    }
    /**
     * 分页查询所有
     * */
    @RequestMapping("/findAll")
    @ResponseBody
    public ModelAndView findAll(@RequestParam(name = "pages",required = true,defaultValue = "1")int pages,@RequestParam(name = "size",required = true,defaultValue = "4")int size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = orderService.findAll(pages,size);
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;

    }

    /**
     * 查询订单详情页
     * */
    @RequestMapping("/findById")
    @ResponseBody
    public ModelAndView findById(@RequestParam(name = "id",required = true)String ordersId ) throws Exception{
        ModelAndView mv = new ModelAndView();
        Orders order = orderService.findById(ordersId);
        mv.addObject("orders", order);
        mv.setViewName("orders-show");
        return mv;

    }
}
