package in.anil;

import in.anil.dao.CustomerDao;
import in.anil.dao.OrderDao;
import in.anil.dao.ProductDao;
import in.anil.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
public class App implements CommandLineRunner {
    @Autowired
    ProductDao productDao;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    OrderDao orderDao;

    private static final Logger logger = LoggerFactory.getLogger(App.class);


    public static void main(String[] args) {
        logger.debug("======Hello World!=======");
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Product mocha = new Product();
        mocha.setProductName("Mocha");
        mocha.setProductPrice(3.95);

        Product capuccinno = new Product();
        capuccinno.setProductName("Capuccinno");
        capuccinno.setProductPrice(4.95);

        productDao.save(mocha);
        productDao.save(capuccinno);

    }
}
