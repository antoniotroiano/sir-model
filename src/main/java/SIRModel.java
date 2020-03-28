import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.List;

public class SIRModel extends Application {

    private static Derivative derivative = new Derivative();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        List<Double> resultSusceptible =
                derivative.calculation(45400, 2100, 2500, 0.00001, 0.07142857143, 30).get("Susceptible");
        List<Double> resultInfected =
                derivative.calculation(45400, 2100, 2500, 0.00001, 0.07142857143, 30).get("Infected");
        List<Double> resultRecovered =
                derivative.calculation(45400, 2100, 2500, 0.00001, 0.07142857143, 30).get("Recovered");

        try {
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Days");

            final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
            lineChart.setTitle("SIR Model for Corona");

            XYChart.Series<String, Number> seriesSusceptible = new XYChart.Series<String, Number>();
            seriesSusceptible.setName("Susceptible");
            getValues(seriesSusceptible, resultSusceptible);

            XYChart.Series<String, Number> seriesInfected = new XYChart.Series<String, Number>();
            seriesInfected.setName("Infected");
            getValues(seriesInfected, resultInfected);

            XYChart.Series<String, Number> seriesRecovered = new XYChart.Series<String, Number>();
            seriesRecovered.setName("Recovered");
            getValues(seriesRecovered, resultRecovered);

            Scene scene = new Scene(lineChart, 1000, 800);
            lineChart.getData().addAll(seriesSusceptible, seriesInfected, seriesRecovered);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getValues(XYChart.Series<String, Number> series, List<Double> results) {
        for (int i = 0; i < results.size(); i++) {
            series.getData().add(new XYChart.Data<String, Number>("Day " + i, results.get(i).doubleValue()));
        }
    }
}
