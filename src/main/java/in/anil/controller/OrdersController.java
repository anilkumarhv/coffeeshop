package in.anil.controller;

import in.anil.dao.CustomerDao;
import in.anil.dao.OrderDao;
import in.anil.dao.ProductDao;
import in.anil.model.Customer;
import in.anil.model.CustomerOrder;
import in.anil.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Created by anil on 4/26/2017.
 */
@Controller
public class OrdersController {

    private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CustomerDao customerDao;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String productsList(Model model) {
        model.addAttribute("products", productDao.findAll());
        model.addAttribute("orders", orderDao.findAll());
        return "orders";
    }

    @RequestMapping(value = "/createorder", method = RequestMethod.POST)
    @ResponseBody
    public String saveOrder(@RequestParam String firstName, @RequestParam String lastName, @RequestParam(value = "productIds[]") Long[] productIds) {

        logger.debug("save order");
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customerDao.save(customer);
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customerDao.findOne(customer.getId()));
        Set<Product> productSet = new HashSet<>();
        for (Long productId : productIds) {
            productSet.add(productDao.findOne(productId));
        }
        customerOrder.setProducts(productSet);
        Double total = 0.0;
        for (Long productId : productIds) {
            total = total + (productDao.findOne(productId).getProductPrice());
        }
        customerOrder.setTotal(total);
        orderDao.save(customerOrder);

        return customerOrder.getId().toString();
    }

    @RequestMapping(value = "/removeorder", method = RequestMethod.POST)
    @ResponseBody
    public String removeOrder(@RequestParam Long Id) {
        orderDao.delete(Id);
        return Id.toString();
    }
}
