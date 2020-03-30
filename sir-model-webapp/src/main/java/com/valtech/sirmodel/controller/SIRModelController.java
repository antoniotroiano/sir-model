package com.valtech.sirmodel.controller;

import com.valtech.sirmodel.model.Data;
import com.valtech.sirmodel.service.DerivativeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("sir-model")
public class SIRModelController {

    DerivativeService derivativeService = new DerivativeService();
    List<Double> resultSusceptible;
    List<Double> resultInfected;
    List<Double> resultRecovered;

    @GetMapping
    public String showSIRModel(Data data, Model model) {

        Data dataInitial = new Data();
        dataInitial.setTransmissionRate(1.0);
        dataInitial.setRecoveryRate(0.23);

        if (data.getTransmissionRate() != null && data.getRecoveryRate() != null) {
            getResult(data, model);
            return "sir-model";
        }
        getResult(dataInitial, model);
        return "sir-model";
    }

    @PostMapping("/newCalculation")
    public String postDataToChar(Data data, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "sir-model";
        }
        if (data.getTransmissionRate() == null || data.getRecoveryRate() == null) {
            return showSIRModel(data, model);
        }
        getResult(data, model);
        return showSIRModel(data, model);
    }

    private void getResult(Data data, Model model) {
        resultSusceptible =
                derivativeService.calculation(0.99, 0.01, 0, data.getTransmissionRate(),
                        data.getRecoveryRate(), 30).get("Susceptible");
        resultInfected =
                derivativeService.calculation(0.99, 0.01, 0, data.getTransmissionRate(),
                        data.getRecoveryRate(), 30).get("Infected");
        resultRecovered =
                derivativeService.calculation(0.99, 0.01, 0, data.getTransmissionRate(),
                        data.getRecoveryRate(), 30).get("Recovered");

        model.addAttribute("transmissionRate", data.getTransmissionRate());
        model.addAttribute("recoveryRate", data.getRecoveryRate());
        model.addAttribute("values", new Data());
        model.addAttribute("resultSusceptible", resultSusceptible);
        model.addAttribute("resultInfected", resultInfected);
        model.addAttribute("resultRecovered", resultRecovered);
    }
}