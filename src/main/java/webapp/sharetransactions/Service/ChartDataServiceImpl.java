package webapp.sharetransactions.Service;

import basic.dbconnect.Model.HistoricValue;
import basic.dbconnect.Repository.HistoricValueRepository;
import webapp.sharetransactions.domain.ChartData;
import webapp.sharetransactions.domain.TransactionDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChartDataServiceImpl {
    private List<ChartData> chartDataList = new ArrayList<>();

    public void editChartDataList(List<TransactionDTO> transactionDTOList, HistoricValueRepository repository){
        for (TransactionDTO transaction : transactionDTOList) {
            for (HistoricValue hv : repository.findByName(transaction.getLinkVariable())) {
                resultDates.add(hv.getDate());
                resultValues.add(hv.getPrice());
            }
            float[] resultValuesArray = new float[resultValues.size()];
            for(int i= resultValues.size()-1; i >= 0; i--){
                resultValuesArray[i] = resultValues.get(i);
            }
            String[] resultDatesArray = new String[resultDates.size()];
            for(int i= resultDates.size()-1; i >= 0; i--) {
                resultDatesArray[i] = resultDates.get(i).toString();
            }

            ChartData chartData = new ChartData(transaction.getName(), transaction.getLinkVariable(),transaction.getNumberOfShares(), resultValuesArray, resultDatesArray);
            chartDataList.add(chartData);
        }
    }

    public ChartDataServiceImpl() {
    }

    private List<LocalDate> resultDates = new ArrayList<>();
    private List<Float> resultValues = new ArrayList<>();

    public List<ChartData> getChartDataList() {

        return chartDataList;
    }
}

