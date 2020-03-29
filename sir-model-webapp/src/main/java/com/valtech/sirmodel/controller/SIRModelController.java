package com.valtech.sirmodel.controller;

import com.valtech.sirmodel.model.ValuesSIR;
import com.valtech.sirmodel.service.Derivative;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("sir-model")
public class SIRModelController {

    Derivative derivative = new Derivative();
    List<Double> resultSusceptible;
    List<Double> resultInfected;
    List<Double> resultRecovered;

    @GetMapping
    public String showSIRModel(Model model, ValuesSIR valuesSIR) {

        resultSusceptible =
                derivative.calculation(45400, 2100, 2500, 0.00001, 0.07142857143,
                        30).get("Susceptible");
        resultInfected =
                derivative.calculation(45400, 2100, 2500, 0.00001, 0.07142857143,
                        30).get("Infected");
        resultRecovered =
                derivative.calculation(45400, 2100, 2500, 0.00001, 0.07142857143,
                        30).get("Recovered");

        model.addAttribute("resultSusceptible", resultSusceptible);
        model.addAttribute("resultInfected", resultInfected);
        model.addAttribute("resultRecovered", resultRecovered);

        return "sir-model";
    }

    @PostMapping("/newInput")
    public String postDataToChar(@ModelAttribute("valuesSIR") ValuesSIR valuesSIR, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "sir-model";
        }

        resultSusceptible =
                derivative.calculation(45400, 2100, 2500, valuesSIR.getTransmissionRate(),
                        0.07142857143, 30).get("Susceptible");
        resultInfected =
                derivative.calculation(45400, 2100, 2500, valuesSIR.getTransmissionRate(),
                        0.07142857143, 30).get("Infected");
        resultRecovered =
                derivative.calculation(45400, 2100, 2500, valuesSIR.getTransmissionRate(),
                        0.07142857143, 30).get("Recovered");

        model.addAttribute("resultSusceptible", resultSusceptible);
        model.addAttribute("resultInfected", resultInfected);
        model.addAttribute("resultRecovered", resultRecovered);

        return showSIRModel(model, valuesSIR);
    }
}