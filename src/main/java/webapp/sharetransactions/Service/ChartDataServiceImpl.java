package webapp.sharetransactions.Service;

import basic.dbconnect.Model.HistoricValue;
import basic.dbconnect.Repository.HistoricValueRepository;
import webapp.sharetransactions.domain.ChartData;
import webapp.sharetransactions.domain.ChartPeriod;
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
        ChartPeriod chartPeriod = new ChartPeriod();
        for (TransactionDTO transaction : transactionDTOList) {
            resultValues.clear();

            for(LocalDate beginning = chartPeriod.generateChartDates(transactionDTOList).get(0);repository.findByName(transaction.getLinkVariable()).get(0).getDate().isAfter(beginning);beginning = beginning.plusDays(1)){
                resultValues.add((float) 0.0);
            }

            for (HistoricValue hv : repository.findByName(transaction.getLinkVariable())) {
                resultValues.add(hv.getPrice()*transaction.getNumberOfShares());
            }
            float[] resultValuesArray = new float[resultValues.size()];
            for(int i= resultValues.size()-1; i >= 0; i--){
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

