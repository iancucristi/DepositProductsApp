package ro.itschool.depositproductsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.itschool.depositproductsapp.entity.DepositModel;
import ro.itschool.depositproductsapp.service.DepositService;
import ro.itschool.depositproductsapp.service.exception.DepositNotFoundException;

import java.util.List;

@Controller
public class DepositController {

    @Autowired
    private DepositService depositService;

    @GetMapping("view-deposits")
    public String viewDeposits(Model model) {
        List<DepositModel> depositList = depositService.getDeposits();
        model.addAttribute("deposits", depositList);
        return "index";
    }

    @GetMapping("addDeposits")
    public String addDeposits(Model model) {
        model.addAttribute("deposit", new DepositModel());
        return "add-deposits";
    }

    @PostMapping("add-new-deposit")
    public String addNewDeposit(DepositModel depositModel) {
        depositService.addDeposit(depositModel);
        return "redirect:/view-deposits";
    }

    @GetMapping("delete-deposit/{id}")
    public String deleteDeposit(@PathVariable("id") int id) {
        depositService.deleteDeposit(id);
        return "redirect:/view-deposits";
    }

    @GetMapping("edit-deposit-page/{id}")
    public String editDepositPage(@PathVariable("id") int id, Model model) throws DepositNotFoundException {
        DepositModel depositModel = depositService.getDeposit(id);
        model.addAttribute("deposit", depositModel);
        return "edit-deposits";
    }

    @PostMapping("edit-deposit")
    public String editDeposit(DepositModel depositModel) throws DepositNotFoundException {
        depositService.updateDeposit(depositModel);
        return "redirect:/view-deposits";
    }


}
