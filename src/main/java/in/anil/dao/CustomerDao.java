package in.anil.dao;

import in.anil.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 *
 * Created by anil on 4/26/2017.
 */
@Component
public interface CustomerDao extends CrudRepository<Customer,Long> {
}
