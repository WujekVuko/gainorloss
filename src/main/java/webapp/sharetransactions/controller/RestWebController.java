package webapp.sharetransactions.controller;

import basic.dbconnect.Model.HistoricValueDAO;
import basic.dbconnect.Model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class RestWebController {
    private final
    HistoricValueDAO dao;

    private List<Transaction> transactionDTOList = new ArrayList<>();

    @Autowired
    public RestWebController(HistoricValueDAO dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/all")
    public List<Transaction> getResource() {
        return transactionDTOList;
    }

    @PostMapping(value = "/save")
    public String postTransaction(@RequestBody List<Transaction> transactions) {
        transactionDTOList.addAll(transactions);

        for( Transaction transDto : transactionDTOList) {
            Transaction t = new Transaction();
            t.setName(transDto.getName());
            t.setLinkVariable(t.getShareName(t.getName()));
            t.setPrice(transDto.getPrice());
            t.setNumberOfShares(transDto.getNumberOfShares());
            t.setBuyDate(transDto.getBuyDate());
            t.setSellDate(transDto.getSellDate());
            dao.process(t);
        }
        return "Post Successfully!";
    }
}