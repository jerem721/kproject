import algorithm.Algorithm;
import algorithm.Market;
import algorithm.runs.IRun;
import file.ReaderFile;
import file.WriterFile;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by jerem on 01/08/14.
 */
public class DirectionalChanges implements Algorithm.OnDirectionalChangeListener {

    private ReaderFile                      intputFile;
    private WriterFile                      outputFile;
    private String                          outFileDirectory;
    private List<Double>                    rPersList;
    private Market market;

    public DirectionalChanges(String inputFile, String outputDirectory, String rPers[])
    {
        this.intputFile = new ReaderFile(inputFile);
        this.outFileDirectory = outputDirectory;
        this.rPersList = new ArrayList<Double>();
        for (String r : rPers)
            rPersList.add(Double.parseDouble(r));
        market = new Market();
    }

    public void start()
    {
        Algorithm       algo;

        init();
        for (Double r : rPersList)
        {
            outputFile = new WriterFile(outFileDirectory +"/directional_changes_" + r + "%.txt");
            outputFile.write(r + "%");
            market.resetMarket();
            algo = new Algorithm(market);
            algo.setOnDirectionalChangeListener(this);
            algo.launch(r);
            outputFile.close();
        }
    }

    public void finish(){
        if (intputFile != null)
            intputFile.close();
    }

    public void init()
    {
        Hashtable<Integer, Double>      stockPrices;

        intputFile.read();
        stockPrices = intputFile.convertStringToDouble();
        for (Map.Entry<Integer, Double> price : stockPrices.entrySet())
            market.addPrice(price.getValue());
    }

    @Override
    public void onUpwardRun(IRun upwardRun) {
        System.out.println("UpwardRun");
        System.out.println(upwardRun);
        outputFile.write("UP\t" + upwardRun.getEvent().getStartingPointIndex() + "\t" + upwardRun.getEvent().getStartingPointPrice().getPrice()
                            + "\t " + upwardRun.getEvent().getEndingPointIndex() + "\t" + upwardRun.getEvent().getEndingPointPrice().getPrice()
                            + "\t\t" + upwardRun.getOvershootEvent().getStartingPointIndex() + "\t" + upwardRun.getOvershootEvent().getStartingPointPrice().getPrice()
                            + "\t " + upwardRun.getOvershootEvent().getEndingPointIndex() + "\t" + upwardRun.getOvershootEvent().getEndingPointPrice().getPrice()
                            + "\t\t" + upwardRun.getTotalMove());
    }

    @Override
    public void onDownwardRun(IRun downwardRun) {
        System.out.println("DownwardRun");
        System.out.println(downwardRun);
        outputFile.write("DOWN\t" + downwardRun.getEvent().getStartingPointIndex() + "\t" + downwardRun.getEvent().getStartingPointPrice().getPrice()
                + "\t " + downwardRun.getEvent().getEndingPointIndex() + "\t" + downwardRun.getEvent().getEndingPointPrice().getPrice()
                + "\t\t" + downwardRun.getOvershootEvent().getStartingPointIndex() + "\t" + downwardRun.getOvershootEvent().getStartingPointPrice().getPrice()
                + "\t " + downwardRun.getOvershootEvent().getEndingPointIndex() + "\t" + downwardRun.getOvershootEvent().getEndingPointPrice().getPrice()
                + "\t\t" + downwardRun.getTotalMove());
    }
}
