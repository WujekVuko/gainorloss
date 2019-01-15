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

    private List<TableData> tableDataList = new ArrayList<>();

    public void setTableDataList(List<TransactionDTO> transactionDTOList, HistoricValueRepository repository){
        tableDataList.clear();
        for (TransactionDTO transaction : transactionDTOList) {
            for (HistoricValue hv : repository.findByName(transaction.getLinkVariable())) {
                TableData tableData = new TableData(hv.getName(),transaction.getNumberOfShares(),hv.getPrice(),transaction.getNumberOfShares()*hv.getPrice(),hv.getDate(), hv.getTransactionId());
                tableDataList.add(tableData);
            }
        }

    }
    public List<TableData>  getTableDataList(){
        return tableDataList;
    }

}
