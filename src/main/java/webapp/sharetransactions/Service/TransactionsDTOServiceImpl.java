package webapp.sharetransactions.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import webapp.sharetransactions.Repository.TransactionDTORepository;
import webapp.sharetransactions.domain.TransactionDTO;

import java.util.List;
@Validated
@Service
public class TransactionsDTOServiceImpl implements TransactionsDTOService {


    private final TransactionDTORepository transactionDTORepository;

    @Autowired
    public TransactionsDTOServiceImpl(TransactionDTORepository transactionDTORepository) {
        this.transactionDTORepository = transactionDTORepository;
    }

    @Override
    public List<TransactionDTO> findAll() {return transactionDTORepository.findAll();}

    @Override
    public TransactionDTO findOne(Long id){return transactionDTORepository.findOne(id);}

    @Override
    public void saveTransaction(TransactionDTO transactionDTO){
        transactionDTORepository.save(transactionDTO);
    }

    @Override
    public void deleteTransaction(Long id){transactionDTORepository.delete(id);}









}
