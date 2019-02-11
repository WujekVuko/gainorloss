package webapp.sharetransactions.Service;

import org.springframework.transaction.annotation.Transactional;
import webapp.sharetransactions.domain.TransactionDTO;

import java.util.List;

public interface TransactionsDTOService {

    List<TransactionDTO> findAll();

    TransactionDTO findOne(Long id);

    void saveTransaction(TransactionDTO transactionDTO);

    @Transactional
    void deleteTransaction(Long id);



}