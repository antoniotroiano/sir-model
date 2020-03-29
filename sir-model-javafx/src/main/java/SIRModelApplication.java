import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SIRModel extends Application {

    private static Derivative derivative = new Derivative();
    List<Double> resultSusceptible = new ArrayList<>();
    List<Double> resultInfected = new ArrayList<>();
    List<Double> resultRecovered = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        VBox vBox = new VBox();
        vBox.setSpacing(10);

        Label transmissionLabel = new Label("Transmission rate: ");

        Label transmissionValue = new Label("-");

        Slider transmissionSlider = new Slider(1, 5, 2);
        transmissionSlider.setShowTickLabels(true);
        transmissionSlider.setShowTickMarks(true);

        transmissionSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                transmissionValue.textProperty().setValue(String.valueOf(t1.intValue()));

                //transmissionValue.setText("V "+t1.intValue());
            }
        });

        resultSusceptible =
                derivative.calculation(45400, 2100, 2500, 0.00001, 0.07142857143, 30).get("Susceptible");

        resultInfected =
                derivative.calculation(45400, 2100, 2500, 0.00001, 0.07142857143, 30).get("Infected");

        resultRecovered =
                derivative.calculation(45400, 2100, 2500, 0.00001, 0.07142857143, 30).get("Recovered");

        try {
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Days");
            xAxis.setAnimated(true);
            yAxis.setAnimated(true);

            final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
            lineChart.setTitle("SIR Model for Corona");

            Series<String, Number> seriesSusceptible = new Series<>();
            seriesSusceptible.setName("Susceptible");
            getValues(seriesSusceptible, resultSusceptible);

            Series<String, Number> seriesInfected = new Series<>();
            seriesInfected.setName("Infected");
            getValues(seriesInfected, resultInfected);

            Series<String, Number> seriesRecovered = new Series<>();
            seriesRecovered.setName("Recovered");
            getValues(seriesRecovered, resultRecovered);

            lineChart.getData().addAll(seriesSusceptible, seriesInfected, seriesRecovered);
            lineChart.setMinHeight(600);

            vBox.setMargin(transmissionLabel, new Insets(10, 10, 10, 10));
            vBox.setMargin(transmissionSlider, new Insets(10, 10, 10, 10));
            vBox.setMargin(transmissionValue, new Insets(10, 10, 10, 10));
            vBox.setMargin(lineChart, new Insets(10, 10, 10, 10));

            ObservableList list = vBox.getChildren();
            list.addAll(transmissionLabel, transmissionSlider, transmissionValue, lineChart);

            Scene scene = new Scene(vBox, 1000, 800);

            stage.setTitle("SIR Model for pandemics");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getValues(Series<String, Number> series, List<Double> results) {
        for (int i = 0; i < results.size(); i++) {
            series.getData().add(new Data<String, Number>("Day " + i, results.get(i).doubleValue()));
        }
    }
}