package ro.itschool.depositproductsapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.itschool.depositproductsapp.entity.DepositModel;
import ro.itschool.depositproductsapp.repository.DepositRepository;
import ro.itschool.depositproductsapp.service.exception.DepositNotFoundException;

import java.util.List;
import java.util.Optional;

@Component
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    public List<DepositModel> getDeposits() {
        List<DepositModel> depositModels = depositRepository.findAll();
        return depositModels;
    }

    public void addDeposit(DepositModel depositModel) {
        depositRepository.save(depositModel);
    }

    public void deleteDeposit(int id) {
        depositRepository.deleteById(id);
    }

    public DepositModel getDeposit(int id) throws DepositNotFoundException {
        Optional<DepositModel> depositModel = depositRepository.findById(id);
        if (depositModel.isEmpty()) {
            throw new DepositNotFoundException("there is no deposit with id: " + id);
        }
        DepositModel deposit = depositModel.get();
        return deposit;
    }

    public void updateDeposit(DepositModel oldDepositModel) throws DepositNotFoundException {
        DepositModel newDepositModel = getDeposit(oldDepositModel.getRegNumber());
        newDepositModel.setCity(oldDepositModel.getCity());
        newDepositModel.setStreet(oldDepositModel.getStreet());
        depositRepository.save(newDepositModel);
    }
}
