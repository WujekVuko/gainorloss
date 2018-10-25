package webapp.sharetransactions.Service;

import webapp.sharetransactions.domain.TransactionDTO;

import java.util.List;

public interface TransactionsDTOService {

    List<TransactionDTO> findAll();

    TransactionDTO findOne(Long id);

    TransactionDTO saveTransaction(TransactionDTO transactionDTO);

    void deleteTransaction(Long id);



}