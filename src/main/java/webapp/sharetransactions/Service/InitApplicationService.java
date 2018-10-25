package webapp.sharetransactions.Service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class InitApplicationService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(InitApplicationService.class);

    private final
    TransactionsDTOService transactionsDTOService;

    @Autowired
    public InitApplicationService(TransactionsDTOService transactionsDTOService) {
        this.transactionsDTOService = transactionsDTOService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeTestData(){
        LOGGER.info("Initialize test data");

     //   transactionsDTOService.saveTransaction(new TransactionDTO("Platige","PLATIGE-IMAGE",10,10,"28.10.2018","31.11.2018"));
    }
}
