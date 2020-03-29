package com.valtech.sirmodel.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Derivative {
    //logger lombok für den output
    public Map<String, List<Double>> calculation(double susStart, double infStart, double reStart, double transRate, double reRate, int maxT) {

        final String RESET = "\033[0m";
        final String BLUE = "\033[0;34m";
        final String RED = "\033[0;31m";
        final String GREEN = "\033[0;32m";

        double susceptible;
        double infected;
        double recovered;

        double derivativeS;
        double derivativeI;
        double derivativeR;

        List<Double> susList = new ArrayList<>();
        List<Double> infList = new ArrayList<>();
        List<Double> reList = new ArrayList<>();

        susList.add(susStart);
        infList.add(infStart);
        reList.add(reStart);

        for (int i = 0; i <= maxT; i++) {

            derivativeS = -transRate * susList.get(i).doubleValue() * infList.get(i).doubleValue();
            derivativeI = transRate * susList.get(i).doubleValue() * infList.get(i).doubleValue() - reRate
                    * infList.get(i).doubleValue();
            derivativeR = reRate * infList.get(i);

            susceptible = susList.get(i).doubleValue() + derivativeS * 1;
            infected = infList.get(i).doubleValue() + derivativeI * 1;
            recovered = reList.get(i).doubleValue() + derivativeR * 1;

            susList.add(susceptible);
            infList.add(infected);
            reList.add(recovered);

            System.out.println("Day " + i + ": Susceptible: " + BLUE + susList.get(i).doubleValue() + RESET
                    + " Infected: " + RED + infList.get(i).doubleValue() + RESET + " Recovered: " + GREEN
                    + reList.get(i).doubleValue() + RESET);
        }
        System.out.println();

        Map<String, List<Double>> map = new HashMap<>();
        map.put("Susceptible", susList);
        map.put("Infected", infList);
        map.put("Recovered", reList);

        return map;
        /*return Stream.of(susList, infList, reList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());*/
    }
}