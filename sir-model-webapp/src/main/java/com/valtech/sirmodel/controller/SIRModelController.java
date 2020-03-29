package com.valtech.sirmodel.controller;

import com.valtech.sirmodel.model.ValuesSIR;
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
    public String showSIRModel(ValuesSIR values, Model model) {

        resultSusceptible =
                derivative.calculation(0.99, 0.01, 0, 3, 0.23, 30).get("Susceptible");
        resultInfected =
                derivative.calculation(0.99, 0.01, 0, 3, 0.23, 30).get("Infected");
        resultRecovered =
                derivative.calculation(0.99, 0.01, 0, 3, 0.23, 30).get("Recovered");

        model.addAttribute("values", new ValuesSIR());
        model.addAttribute("resultSusceptible", resultSusceptible);
        model.addAttribute("resultInfected", resultInfected);
        model.addAttribute("resultRecovered", resultRecovered);

        return "sir-model";
    }

    @PostMapping
    public String postDataToChar(@Valid ValuesSIR values, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "sir-model";
        }
        resultSusceptible =
                derivative.calculation(0.99, 0.01, 0, values.getTransmissionRate(), 0.5, 30).get("Susceptible");
        resultInfected =
                derivative.calculation(0.99, 0.01, 0, values.getTransmissionRate(), 0.5, 30).get("Infected");
        resultRecovered =
                derivative.calculation(0.99, 0.01, 0, values.getTransmissionRate(), 0.5, 30).get("Recovered");

        model.addAttribute("values", new ValuesSIR());
        model.addAttribute("resultSusceptible", resultSusceptible);
        model.addAttribute("resultInfected", resultInfected);
        model.addAttribute("resultRecovered", resultRecovered);

        return "sir-model";
    }
}