package webapp.sharetransactions.Service;

import basic.dbconnect.Model.HistoricValue;
import basic.dbconnect.Model.Transaction;
import basic.dbconnect.Repository.HistoricValueRepository;
import webapp.sharetransactions.domain.ChartData;
import webapp.sharetransactions.domain.TransactionDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ChartDataServiceImpl {
    private List<ChartData> chartDataList = new ArrayList<>();

    public ChartDataServiceImpl() {}


    public List<ChartData> generateChartDataList(List<TransactionDTO> transactionDTOList, HistoricValueRepository repository){
        chartDataList.clear();
        List<Float> resultValues = new ArrayList<>();
        List<Transaction> transactionList = new ArrayList<>();
        List<LocalDate> hvDates = new ArrayList<>();
        List<HistoricValue> historicValueList = repository.findAll();
        for(HistoricValue historicValue : historicValueList){
            hvDates.add(historicValue.getDate());
        }
        hvDates.sort(Comparator.naturalOrder());
        hvDates = hvDates.stream().distinct().collect(Collectors.toList());
        for(TransactionDTO transactionDTO : transactionDTOList){
            transactionList.add(new Transaction(transactionDTO.getName(), transactionDTO.getNumberOfShares(),transactionDTO.getPrice(),transactionDTO.getBuyDate(),transactionDTO.getSellDate(),transactionDTO.getId()));
        }


        for (Transaction transaction : transactionList) {
            resultValues.clear();
            for(int i = 0;transaction.getBuyDate().isAfter(hvDates.get(i));i++){
                resultValues.add((float) 0.0);
            }
            List<HistoricValue> historicValues = repository.findByName(transaction.getLinkVariable());
            for(int i = 0; i <= historicValues.size()-1;i++){
                resultValues.add(historicValues.get(historicValues.size()-1-i).getPrice()*transaction.getNumberOfShares()-transaction.getInitialValue());
            }

            float[] resultValuesArray = new float[resultValues.size()];
            for(int i=0; i <= resultValues.size()-1; i++){
                resultValuesArray[i] = resultValues.get(i);
            }


            ChartData chartData = new ChartData(transaction.getName(), resultValuesArray);
            chartDataList.add(chartData);
        }
        return chartDataList;
    }

    public List<String> generateChartPeriod(List<TransactionDTO> transactionDTOList, HistoricValueRepository repository){
        List<LocalDate> hvDates = new ArrayList<>();
        for (TransactionDTO transaction : transactionDTOList) {
            for (HistoricValue hv : repository.findByName(transaction.getLinkVariable())) {
                hvDates.add(hv.getDate());
            }
        }
        hvDates.sort(Comparator.naturalOrder());
        List<String> hvDatesToString = new ArrayList<>();
        for(LocalDate hvDate : hvDates){
            hvDatesToString.add(hvDate.toString());
        }
        return hvDatesToString.stream().distinct().collect(Collectors.toList());
    }


}

