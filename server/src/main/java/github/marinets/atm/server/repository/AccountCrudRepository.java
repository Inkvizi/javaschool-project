package github.marinets.atm.server.repository;

import github.marinets.atm.server.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountCrudRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByCards_Id(Long cardId);
}
