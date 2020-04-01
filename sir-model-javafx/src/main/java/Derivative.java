import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Derivative {

    private static final Logger log = Logger.getLogger("Derivative");

    public Map<String, List<Double>> calculation(double susStart, double infStart, double reStart, double transRate, double reRate, int maxT) {

        final String WHITE = "\033[0;37m";
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

            derivativeS = -transRate * susList.get(i) * infList.get(i);
            derivativeI = transRate * susList.get(i) * infList.get(i) - reRate
                    * infList.get(i);
            derivativeR = reRate * infList.get(i);

            susceptible = susList.get(i) + derivativeS * 1;
            infected = infList.get(i) + derivativeI * 1;
            recovered = reList.get(i) + derivativeR * 1;

            susList.add(susceptible);
            infList.add(infected);
            reList.add(recovered);

            log.info(WHITE + "Day " + i + ": Susceptible: " + BLUE + susList.get(i) + WHITE + " Infected: " + RED
                    + infList.get(i) + WHITE + " Recovered: " + GREEN + reList.get(i) + WHITE);
        }

        Map<String, List<Double>> map = new HashMap<>();
        map.put("Susceptible", susList);
        map.put("Infected", infList);
        map.put("Recovered", reList);

        return map;
    }
}