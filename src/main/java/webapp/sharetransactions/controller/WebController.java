package webapp.sharetransactions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping(value = "/")
    public String homepage() {
        return "transactionList";
    }

    @GetMapping(value = "/transactionlist/")
    public String listPage(){return "transactionList";}

    @GetMapping(value = "/transactionEdit/")
    public String editPage(){return "transactionEdit";}


}