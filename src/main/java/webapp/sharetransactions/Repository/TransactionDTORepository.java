package webapp.sharetransactions.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webapp.sharetransactions.domain.TransactionDTO;

@Repository
public interface TransactionDTORepository extends JpaRepository <TransactionDTO, Long>{
}
