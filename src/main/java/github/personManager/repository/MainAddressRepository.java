package github.personManager.repository;

import github.personManager.model.domain.MainAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainAddressRepository extends JpaRepository<MainAddress, Integer> {
}
