import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SIRModelApplication extends Application {

    private static Derivative derivative = new Derivative();
    private static List<Double> resultSusceptible = new ArrayList<>();
    private static List<Double> resultInfected = new ArrayList<>();
    private static List<Double> resultRecovered = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        VBox vBox = new VBox();
        vBox.setSpacing(10);

        Label labelTransmissionRate = new Label("Transmission rate: ");
        TextField textTransmissionRate = new TextField();
        textTransmissionRate.setText("1.0");
        Button button = new Button("Generate chart");
        Button buttonClean = new Button("Clear chart");

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Days");
        xAxis.setAnimated(true);
        yAxis.setAnimated(true);

        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("SIR Model for pandemics");
        lineChart.getXAxis().setAutoRanging(true);
        lineChart.getYAxis().setAutoRanging(true);

        Series<String, Number> seriesSusceptible = new Series<>();
        Series<String, Number> seriesInfected = new Series<>();
        Series<String, Number> seriesRecovered = new Series<>();

        buttonClean.setOnAction(actionEvent -> {
            seriesSusceptible.getData().clear();
            seriesInfected.getData().clear();
            seriesRecovered.getData().clear();
        });

        button.setOnAction(actionEvent -> {
            double transRate = Double.valueOf(textTransmissionRate.getText());

            resultSusceptible =
                    derivative.calculation(0.99, 0.01, 0, transRate, 0.23, 30).get("Susceptible");
            resultInfected =
                    derivative.calculation(0.99, 0.01, 0, transRate, 0.23, 30).get("Infected");
            resultRecovered =
                    derivative.calculation(0.99, 0.01, 0, transRate, 0.23, 30).get("Recovered");

            seriesSusceptible.setName("Susceptible");
            setValues(seriesSusceptible, resultSusceptible);

            seriesInfected.setName("Infected");
            setValues(seriesInfected, resultInfected);

            seriesRecovered.setName("Recovered");
            setValues(seriesRecovered, resultRecovered);
        });

        lineChart.getData().addAll(seriesSusceptible, seriesInfected, seriesRecovered);
        lineChart.setMinHeight(600);

        vBox.setMargin(lineChart, new Insets(10, 10, 10, 10));

        ObservableList list = vBox.getChildren();
        list.addAll(labelTransmissionRate, textTransmissionRate, button, buttonClean, lineChart);

        Scene scene = new Scene(vBox, 1000, 800);

        stage.setTitle("SIR Model for pandemics");
        stage.setScene(scene);
        stage.show();
    }

    private void setValues(Series<String, Number> series, List<Double> results) {
        for (int i = 0; i < results.size(); i++) {
            series.getData().add(new Data<String, Number>("Day " + i, results.get(i)));
        }
    }
}