package webapp.sharetransactions.Service;

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
        for (TransactionDTO transactionDTO : transactionDTOList) {
                TableData tableData = new TableData(transactionDTO.getName(),transactionDTO.getNumberOfShares(),transactionDTO.getPrice(),repository.findByName(transactionDTO.getLinkVariable()).get(0).getPrice(),transactionDTO.getBuyDate(), transactionDTO.getSellDate(), transactionDTO.getId());
                tableDataList.add(tableData);
        }
    }



    public List<TableData>  getTableDataList(){
        return tableDataList;
    }

}
