import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class XYLineChartExample extends JFrame {
  private static final long serialVersionUID = 6294689542092367723L;

  public XYLineChartExample(String title) {
    super(title);

    // Create dataset
    XYDataset dataset = createDataset();

    // Create chart
    JFreeChart chart = ChartFactory.createXYLineChart(
        "XY Line Chart Example",
        "X-Axis Time",
        "Y-Axis Number of events",
        dataset,
        PlotOrientation.VERTICAL,
        true, true, false);

    // Create Panel
    ChartPanel panel = new ChartPanel(chart);
    setContentPane(panel);
  }
  
  public XYLineChartExample(String title, Surface s) {
	    super(title);

	    // Create dataset
	    XYDataset dataset = createDataset(s);

	    // Create chart
	    JFreeChart chart = ChartFactory.createXYLineChart(
	        "XY Line Chart Example",
	        "X-Axis Time",
	        "Y-Axis Number of events",
	        dataset,
	        PlotOrientation.VERTICAL,
	        true, true, false);

	    // Create Panel
	    ChartPanel panel = new ChartPanel(chart);
	    setContentPane(panel);
	  }

  private XYDataset createDataset() {
    XYSeriesCollection dataset = new XYSeriesCollection();

    XYSeries series = new XYSeries("Y = X + 2");
    series.add(2, 4);
    series.add(8, 10);
    series.add(10, 12);
    series.add(13, 15);
    series.add(17, 19);
    series.add(18, 20);
    series.add(21, 23);

    //Add series to dataset
    dataset.addSeries(series);
    
    return dataset;
  }
  
  private XYDataset createDataset(Surface s) {
	    XYSeriesCollection dataset = new XYSeriesCollection();

	    XYSeries series = new XYSeries("Y = X + 2");
	    for (int i =0; i < s.getTime().size(); i++) {
		    series.add(  (double) s.getTime().get(i) , (double) s.getEvents().get(i) );
		    System.out.println(" TEST  " + (double) s.getTime().get(i)  + "     event " +  (double) s.getEvents().get(i));
	    }
	    //Add series to dataset
	    dataset.addSeries(series);
	    
	    return dataset;
	  }
  
  
}