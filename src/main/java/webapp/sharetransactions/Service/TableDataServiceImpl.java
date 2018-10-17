package webapp.sharetransactions.Service;

import basic.dbconnect.Model.HistoricValue;
import basic.dbconnect.Repository.HistoricValueRepository;
import webapp.sharetransactions.domain.TableData;
import webapp.sharetransactions.domain.TransactionDTO;

import java.util.ArrayList;
import java.util.List;

public class TableDataServiceImpl {


    public TableDataServiceImpl() {
    }
    public List<TableData> getTableDataList(List<TransactionDTO> transactionDTOList, HistoricValueRepository repository){
        List<TableData> tableDataList = new ArrayList<>();
        for (TransactionDTO transaction : transactionDTOList) {
            for (HistoricValue hv : repository.findByName(transaction.getLinkVariable())) {
                TableData tableData = new TableData(hv.getName(),transaction.getNumberOfShares(),hv.getPrice(),transaction.getNumberOfShares()*hv.getPrice(),hv.getDate());
                tableDataList.add(tableData);
            }
        }
        return tableDataList;
    }
}
