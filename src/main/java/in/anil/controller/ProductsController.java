package in.anil.controller;

import in.anil.dao.ProductDao;
import in.anil.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
/**
 *
 * Created by anil on 4/26/2017.
 */
@Controller
public class ProductsController {
    @Autowired
    private ProductDao productDao;

//    @RequestMapping("/product/{id}")
//    public ModelAndView getProduct(@PathVariable long id,ModelAndView modelAndView){
//        return new ModelAndView("product","product",productDao.findOne(id));
//    }
    @RequestMapping("/product/{id}")
    public String product(@PathVariable Long id, Model model){
        System.out.println("===product==="+id);
        model.addAttribute("product", productDao.findOne(id));
        return "products";
    }

    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public String productsList(Model model){
        model.addAttribute("products", productDao.findAll());
        return "products";
    }

    @RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
    @ResponseBody
    public String saveProduct(@RequestBody Product product) {
        productDao.save(product);
        return product.getId().toString();
    }

}
