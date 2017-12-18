// Copyright (C) 2017 Kyaw Kyaw Htike @ Ali Abdul Ghafur. All rights reserved.

package KKH.StdLib.Plot;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.util.Arrays;

public class ScatterPlot2D {

    private String label_xAxis;
    private String label_yAxis;
    private String title_scatter;
    private String title_stage;
    private double lowerBound_xAxis;
    private double upperBound_xAxis;
    private double tickUnit_xAxis;
    private double lowerBound_yAxis;
    private double upperBound_yAxis;
    private double tickUnit_yAxis;

    public ScatterPlot2D()
    {
        label_xAxis = "x-axis";
        label_yAxis = "y_axis";
        title_scatter = "scatter plot";
        title_stage = "stage";
        lowerBound_xAxis = -10;
        upperBound_xAxis = 10;
        tickUnit_xAxis = 1;
        lowerBound_yAxis = -10;
        upperBound_yAxis = 10;
        tickUnit_yAxis = 1;
    }

    public void set_labels_axis(String label_xAxis, String label_yAxis)
    {
        this.label_xAxis = label_xAxis;
        this.label_yAxis = label_yAxis;
    }

    public void set_title_scatter(String title_scatter)
    {
        this.title_scatter = title_scatter;
    }

    public void set_title_stage(String title_stage)
    {
        this.title_stage = title_stage;
    }

    public void set_xAxis_range(double lowerBound, double upperBound, double tickUnit)
    {
        this.lowerBound_xAxis = lowerBound;
        this.upperBound_xAxis = upperBound;
        this.tickUnit_xAxis = tickUnit;
    }

    public void set_yAxis_range(double lowerBound, double upperBound, double tickUnit)
    {
        this.lowerBound_yAxis = lowerBound;
        this.upperBound_yAxis = upperBound;
        this.tickUnit_yAxis = tickUnit;
    }

    public void plot(double[] x, double[] y, int[] groups)
    {
        int ndata = x.length;
        if(y.length != ndata || groups.length != ndata)
            throw new IllegalArgumentException("ERROR: x, y, and groups must have the same length.");
        int[] groups_unique = Arrays.stream(groups).distinct().toArray();
        int ngroups = groups_unique.length;
        String[] names_groups = new String[ngroups];
        for(int i=0; i<ngroups; i++)
            names_groups[i] = "Class " + (i+1);

        new JFXPanel();
        Platform.runLater(()->
        {
            Stage stage = new Stage();
            stage.setTitle(title_stage);
            final NumberAxis xAxis = new NumberAxis(lowerBound_xAxis, upperBound_xAxis, tickUnit_xAxis);
            final NumberAxis yAxis = new NumberAxis(lowerBound_yAxis, upperBound_yAxis, tickUnit_yAxis);
            final ScatterChart<Number,Number> sc = new ScatterChart<Number,Number>(xAxis,yAxis);
            xAxis.setLabel(label_xAxis);
            yAxis.setLabel(label_yAxis);
            sc.setTitle(title_scatter);

            XYChart.Series series;

            for(int i = 0; i< ngroups; i++)
            {
                series = new XYChart.Series();
                series.setName(names_groups[i]);
                for(int j = 0; j< ndata; j++)
                {
                    if(groups[j]!=(i+1)) continue;
                    series.getData().add(new XYChart.Data(x[j], y[j]));
                }
                sc.getData().add(series);
            }

            Scene scene  = new Scene(sc, 500, 400);
            stage.setScene(scene);
            stage.show();

        }); // end Platform.runLater

    }

}




