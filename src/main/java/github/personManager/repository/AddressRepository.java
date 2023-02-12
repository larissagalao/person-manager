package github.personManager.repository;

import github.personManager.model.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("SELECT a.addressId FROM Person p JOIN p.address a WHERE p.personId = :personId")
    List<Integer> findAllByPersonId(@Param("personId") Integer personId);
}
