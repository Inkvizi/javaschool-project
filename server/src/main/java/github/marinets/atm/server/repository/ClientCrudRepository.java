package github.marinets.atm.server.repository;

import github.marinets.atm.server.entity.Account;
import github.marinets.atm.server.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientCrudRepository extends CrudRepository<Client, Long> {
    Optional<Client> getClientByAccounts_Id(Long accountId);
}
