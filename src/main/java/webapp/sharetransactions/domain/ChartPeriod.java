package webapp.sharetransactions.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ChartPeriod {
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private List<LocalDate> chartDates = new ArrayList<>();



    private ChartPeriod(List<LocalDate> chartDates) {
        this.chartDates = chartDates;
    }

    public ChartPeriod() {
    }

    public List<LocalDate> generateChartDates(List<TransactionDTO> transactionDTOList){
        List<LocalDate> transactionDates = new ArrayList<>();

        for(TransactionDTO transactionDTO : transactionDTOList){
            LocalDate transactionBuyDate = LocalDate.parse(transactionDTO.getBuyDate(),format);
            transactionDates.add(transactionBuyDate);
            LocalDate transactionSellDate = LocalDate.parse(transactionDTO.getSellDate(),format);
            transactionDates.add(transactionSellDate);
        }
        transactionDates.sort(Comparator.naturalOrder());

        for(LocalDate date = transactionDates.get(0);date.isBefore(transactionDates.get(transactionDates.size()-1));date = date.plusDays(1L)){
            chartDates.add(date);
        }
        return chartDates;
    }


    @Override
    public String toString() {
        return chartDates.toString();
    }
}
