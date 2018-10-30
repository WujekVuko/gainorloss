package webapp.sharetransactions.controller;

import basic.dbconnect.Model.GetData;
import basic.dbconnect.Model.HistoricValue;
import basic.dbconnect.Model.Transaction;
import basic.dbconnect.Repository.HistoricValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import webapp.sharetransactions.Service.ChartDataServiceImpl;
import webapp.sharetransactions.Service.TableDataServiceImpl;
import webapp.sharetransactions.Service.TransactionsDTOService;
import webapp.sharetransactions.domain.ChartData;
import webapp.sharetransactions.domain.TransactionDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RestWebController {
    private final HistoricValueRepository repository;

    private final TransactionsDTOService transactionsDTOService;

    private ChartDataServiceImpl chartDataService = new ChartDataServiceImpl();

    @Autowired
    public RestWebController(HistoricValueRepository repository, TransactionsDTOService transactionsDTOService) {
        this.repository = repository;
        this.transactionsDTOService = transactionsDTOService;
    }

    @RequestMapping(value ={"/"})
    public String transactionsDTOList(Model model) {
        TableDataServiceImpl tableDataService = new TableDataServiceImpl();
        model.addAttribute("transactionList", transactionsDTOService.findAll());
        model.addAttribute("chartDataList", chartDataService.findAll(transactionsDTOService.findAll(), repository));
        model.addAttribute("tableDataList", tableDataService.getTableDataList(transactionsDTOService.findAll(), repository));
        return "index";
    }


    @RequestMapping(value = {"/transactionEdit", "/transactionEdit/{id}"}, method = RequestMethod.GET)
    public String transactionEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            model.addAttribute("transactionDTO", transactionsDTOService.findOne(id));
        } else {
            model.addAttribute("transactionDTO", new TransactionDTO());
        }
        return "transactionEdit";
    }

    @RequestMapping(value = "/transactionEdit", method = RequestMethod.POST)
    public String transactionEdit(Model model, TransactionDTO transactionDTO) {
        Transaction t = new Transaction(transactionDTO.getName(), transactionDTO.getNumberOfShares(), transactionDTO.getPrice(), transactionDTO.getBuyDate(), transactionDTO.getSellDate());
        transactionDTO.setLinkVariable(t.getLinkVariable());
        transactionsDTOService.saveTransaction(transactionDTO);
        GetData data = new GetData();
        List<HistoricValue> sharesList;
        sharesList = data.jSoupGetData(t);
        repository.save(sharesList);

        TableDataServiceImpl tableDataService = new TableDataServiceImpl();

        model.addAttribute("transactionList", transactionsDTOService.findAll());
        List<String> list = new ArrayList<>();

        List<ChartData> all = chartDataService.findAll(transactionsDTOService.findAll(), repository);
        for (ChartData chartData : all) {
            Collections.addAll(list, chartData.getDates());
        }
        model.addAttribute("chartDataList", list.stream().collect(Collectors.joining(", ")));

        return "/";
    }

    @RequestMapping(value = "/transactionDelete/{id}", method = RequestMethod.GET)
    public String TransactionDelete(Model model, @PathVariable(required = true, name = "id") Long id) {


        transactionsDTOService.deleteTransaction(id);
        model.addAttribute("transactionList", transactionsDTOService.findAll());
        model.addAttribute("chartDataList", chartDataService.findAll(transactionsDTOService.findAll(), repository));

        return "/";
    }
}