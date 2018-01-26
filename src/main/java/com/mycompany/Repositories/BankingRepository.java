
package com.mycompany.Repositories;

import com.mycompany.Model.BankDetails;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author LebyanaWT
 */
public interface BankingRepository extends CrudRepository<BankDetails, Long>{
    
}
