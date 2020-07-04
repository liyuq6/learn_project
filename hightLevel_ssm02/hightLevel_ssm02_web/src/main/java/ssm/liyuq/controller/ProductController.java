package ssm.liyuq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ssm.liyuq.domain.Product;
import ssm.liyuq.service.IProductService;
import ssm.liyuq.utils.DateStringEditor;

import java.util.Date;
import java.util.List;

/**
 *产品的控制类
 * */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    //类型转换
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,new DateStringEditor());
    }

    //产品添加
    @RequestMapping("/save")
    public String saveProduct(Product product)throws Exception{
        product.setId("vacsaaba");
        productService.save(product);
        return "redirect:findAll";
    }

    //查询所有产品
    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> psList = productService.findAll();
        if(psList==null)
            System.out.println("没有");
        mv.addObject("productList",psList);

        mv.setViewName("product-list");
        return mv;
    }

}
