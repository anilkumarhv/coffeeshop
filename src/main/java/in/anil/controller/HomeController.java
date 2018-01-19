package in.anil.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * Created by anil on 7/14/2017.
 */
@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome(Model model) {
        logger.debug("====home====");
        model.addAttribute("welcome", "welcome");
        model.addAttribute("welcome", "welcome");

//        return "orders";11
        return "welcome";
    }
}
