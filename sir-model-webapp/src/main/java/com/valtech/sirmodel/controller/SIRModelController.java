package com.valtech.sirmodel.controller;

import com.valtech.sirmodel.model.Data;
import com.valtech.sirmodel.service.Derivative;
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

    Derivative derivative = new Derivative();
    List<Double> resultSusceptible;
    List<Double> resultInfected;
    List<Double> resultRecovered;

    @GetMapping
    public String showSIRModel(Data data, Model model) {

        Data dataInitial = new Data();
        dataInitial.setTransmissionRate(1.5);

        if (data.getTransmissionRate() != null) {
            getResult(data, model);
            return "sir-model";
        }
        getResult(dataInitial, model);

        return "sir-model";
    }

    @PostMapping("/newCalculation")
    public String postDataToChar(@Valid Data data, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "sir-model";
        }
        getResult(data, model);

        return "sir-model";
    }

    private void getResult(Data data, Model model) {
        resultSusceptible =
                derivative.calculation(0.99, 0.01, 0, data.getTransmissionRate(), 0.23, 30).get("Susceptible");
        resultInfected =
                derivative.calculation(0.99, 0.01, 0, data.getTransmissionRate(), 0.23, 30).get("Infected");
        resultRecovered =
                derivative.calculation(0.99, 0.01, 0, data.getTransmissionRate(), 0.23, 30).get("Recovered");

        model.addAttribute("transmissionRate", data.getTransmissionRate());
        model.addAttribute("values", new Data());
        model.addAttribute("resultSusceptible", resultSusceptible);
        model.addAttribute("resultInfected", resultInfected);
        model.addAttribute("resultRecovered", resultRecovered);
    }
}