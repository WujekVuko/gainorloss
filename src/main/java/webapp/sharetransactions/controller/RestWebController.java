package webapp.sharetransactions.controller;

import basic.dbconnect.Model.GetData;
import basic.dbconnect.Model.HistoricValue;
import basic.dbconnect.Model.Transaction;
import basic.dbconnect.Repository.HistoricValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import webapp.sharetransactions.Service.ChartDataServiceImpl;
import webapp.sharetransactions.Service.TableDataServiceImpl;
import webapp.sharetransactions.Service.TransactionsDTOService;
import webapp.sharetransactions.domain.TransactionDTO;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RestWebController {
    private final HistoricValueRepository repository;

    private final TransactionsDTOService transactionsDTOService;

    private ChartDataServiceImpl chartDataService = new ChartDataServiceImpl();

    private TableDataServiceImpl tableDataService = new TableDataServiceImpl();


    @Autowired
    public RestWebController(HistoricValueRepository repository, TransactionsDTOService transactionsDTOService) {
        this.repository = repository;
        this.transactionsDTOService = transactionsDTOService;
    }

    @RequestMapping(value = {"/"})
    public String transactionsDTOList(Model model) {
        tableDataService.setTableDataList(transactionsDTOService.findAll(), repository);
        model.addAttribute("transactionList", transactionsDTOService.findAll());
        model.addAttribute("chartDataList", chartDataService.generateChartDataList(transactionsDTOService.findAll(), repository));
        model.addAttribute("tableDataList", tableDataService.getTableDataList());
        model.addAttribute("chartPeriod", chartDataService.generateChartPeriod(transactionsDTOService.findAll(), repository));
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
    public String transactionEdit(Model model, @Valid @ModelAttribute("transactionDTO") TransactionDTO transactionDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || Transaction.isShareNameNotInNamesList(transactionDTO.getName())) {
            if (Transaction.isShareNameNotInNamesList(transactionDTO.getName())) {
                transactionDTO.setName("Invalid Name!");
            }
            return "transactionEdit";
        } else {
            transactionsDTOService.saveTransaction(transactionDTO);
            repository.deleteAllByTransactionID(transactionDTO.getId());
            Transaction t = new Transaction(transactionDTO.getName(), transactionDTO.getNumberOfShares(), transactionDTO.getPrice(), transactionDTO.getBuyDate(), transactionDTO.getSellDate(), transactionDTO.getId());
            transactionDTO.setLinkVariable(t.getLinkVariable());
            transactionsDTOService.saveTransaction(transactionDTO);
            GetData data = new GetData();
            List<HistoricValue> sharesList;
            sharesList = data.jSoupGetData(t);
            repository.save(sharesList);
            TableDataServiceImpl tableDataService = new TableDataServiceImpl();
            tableDataService.setTableDataList(transactionsDTOService.findAll(), repository);
            model.addAttribute("transactionList", transactionsDTOService.findAll());
            model.addAttribute("chartDataList", chartDataService.generateChartDataList(transactionsDTOService.findAll(), repository));
            model.addAttribute("tableDataList", tableDataService.getTableDataList());
            model.addAttribute("chartPeriod", chartDataService.generateChartPeriod(transactionsDTOService.findAll(), repository));
            return "index";
        }
    }

    @RequestMapping(value = "/transactionDelete/{id}", method = RequestMethod.GET)
    public String TransactionDelete(Model model, @PathVariable(name = "id") Long id) {
        repository.deleteAllByTransactionID(id);
        transactionsDTOService.deleteTransaction(id);
        model.addAttribute("transactionList", transactionsDTOService.findAll());
        model.addAttribute("chartDataList", chartDataService.generateChartDataList(transactionsDTOService.findAll(),repository));
        model.addAttribute("tableDataList", tableDataService.getTableDataList());
        model.addAttribute("chartPeriod", chartDataService.generateChartPeriod(transactionsDTOService.findAll(),repository));
        return "index";
    }
}