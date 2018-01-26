
package com.mycompany.Repositories;

import com.mycompany.Model.Customers;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author LebyanaWT
 */
public interface CustomerRepository extends CrudRepository<Customers,Integer>{
    @Query("SELECT c from Customers c where c.username = :username AND c.password = :password")
    public List<Customers> findByUsernameAndPassword(String Username ,String password );
}
