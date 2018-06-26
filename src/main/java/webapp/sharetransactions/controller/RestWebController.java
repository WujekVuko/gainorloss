package webapp.sharetransactions.controller;

import org.springframework.web.bind.annotation.*;
import webapp.sharetransactions.model.Transaction;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class RestWebController {

    private List<Transaction> cust = new ArrayList<Transaction>();

    @GetMapping(value = "/all")
    public List<Transaction> getResource(){
        return cust;
    }

    @PostMapping(value="/save")
    public String postCustomer(@RequestBody List<Transaction> transactions){
        cust.addAll(transactions);
        return "Post Successfully!";
    }
}