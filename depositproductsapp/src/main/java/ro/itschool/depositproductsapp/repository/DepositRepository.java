package ro.itschool.depositproductsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.itschool.depositproductsapp.entity.DepositModel;

@Repository
public interface DepositRepository extends JpaRepository<DepositModel, Integer> {

}
