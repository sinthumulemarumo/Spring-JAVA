
package com.mycompany.RestController;

import com.mycompany.Model.BankDetails;
import com.mycompany.Service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LebyanaWT
 */
@RestController
public class BankingController {
    
    @Autowired
    BankingService bankService;
    
    @RequestMapping(value="/banks",method=RequestMethod.POST)
    public void placeBANK(@RequestBody BankDetails bankD){
        bankService.saveBank(bankD);
    }
     
    @RequestMapping(method = RequestMethod.GET, value = "/pay",produces = {MediaType.APPLICATION_JSON_VALUE})
    public BankDetails accessBank(@RequestParam String bankType,String accNo, String pin) {
        return bankService.pay(bankType,accNo, pin);
    }
}
