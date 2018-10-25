package webapp.sharetransactions.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.sharetransactions.Repository.TransactionDTORepository;
import webapp.sharetransactions.domain.TransactionDTO;

import java.util.List;

@Service
public class TransactionsDTOServiceImpl implements TransactionsDTOService {


    @Autowired
    private TransactionDTORepository transactionDTORepository;

    @Override
    public List<TransactionDTO> findAll() {return transactionDTORepository.findAll();}

    @Override
    public TransactionDTO findOne(Long id){return transactionDTORepository.findOne(id);}

    @Override
    public TransactionDTO saveTransaction(TransactionDTO transactionDTO){return transactionDTORepository.save(transactionDTO);}

    @Override
    public void deleteTransaction(Long id){transactionDTORepository.delete(id);}





}
