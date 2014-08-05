import algorithm.Market;
import algorithm.Algorithm2;
import algorithm.runs.IRun;
import file.ReaderFile;
import file.WriterFile;

import java.util.*;

/**
 * Created by jerem on 01/08/14.
 */
public class DirectionalChanges implements Algorithm2.OnDirectionalChangeListener {

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
        Algorithm2 algo;

        init();
        for (Double r : rPersList)
        {
            outputFile = new WriterFile(outFileDirectory +"/directional_changes_" + r + "%.txt");
            outputFile.write(r + "%\t\t\tStart\tPrice\tEnd\tPrice\t\tTotal move");
            market.resetMarket();
            algo = new Algorithm2(market);
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
        TreeMap<Integer, String> stockPrices;

        stockPrices = intputFile.read();
        for (Map.Entry<Integer, String> price : stockPrices.entrySet())
            market.addPrice(Double.parseDouble(price.getValue()));
    }

    @Override
    public void onUpwardRun(IRun upwardRun) {
        //System.out.println("UpwardRun");
        //System.out.println(upwardRun);

        if (upwardRun.getEvent() != null) {
            System.out.println("Upturn Event: " + upwardRun.getEvent());
            outputFile.write("Upturn\t\t\t" + upwardRun.getEvent().getStartingPointIndex() + "\t" + upwardRun.getEvent().getStartingPointPrice().getPrice()
                                + "\t " + upwardRun.getEvent().getEndingPointIndex() + "\t" + upwardRun.getEvent().getEndingPointPrice().getPrice()
                                + "\t\t" + upwardRun.getEvent().getTotalMove());
        }if (upwardRun.getOvershootEvent() != null) {
            System.out.println("UpturnOvershoot Event: " + upwardRun.getOvershootEvent());
            outputFile.write("UpturnOvershoot \t"+ upwardRun.getOvershootEvent().getStartingPointIndex() + "\t" + upwardRun.getOvershootEvent().getStartingPointPrice().getPrice()
                            + "\t " + upwardRun.getOvershootEvent().getEndingPointIndex() + "\t" + upwardRun.getOvershootEvent().getEndingPointPrice().getPrice()
                            + "\t\t" + upwardRun.getOvershootEvent().getTotalMove());
        }

       /* outputFile.write("UP\t" + upwardRun.getEvent().getStartingPointIndex() + "\t" + upwardRun.getEvent().getStartingPointPrice().getPrice()
                            + "\t " + upwardRun.getEvent().getEndingPointIndex() + "\t" + upwardRun.getEvent().getEndingPointPrice().getPrice()
                            + "\t\t" + upwardRun.getOvershootEvent().getStartingPointIndex() + "\t" + upwardRun.getOvershootEvent().getStartingPointPrice().getPrice()
                            + "\t " + upwardRun.getOvershootEvent().getEndingPointIndex() + "\t" + upwardRun.getOvershootEvent().getEndingPointPrice().getPrice()
                            + "\t\t" + upwardRun.getTotalMove());
        */
    }

    @Override
    public void onDownwardRun(IRun downwardRun) {
        //System.out.println("DownwardRun");
        //System.out.println(downwardRun);

        if (downwardRun.getEvent() != null) {
            System.out.println("Downturn Event: " + downwardRun.getEvent());
            outputFile.write("Downturn\t\t" + downwardRun.getEvent().getStartingPointIndex() + "\t" + downwardRun.getEvent().getStartingPointPrice().getPrice()
                    + "\t " + downwardRun.getEvent().getEndingPointIndex() + "\t" + downwardRun.getEvent().getEndingPointPrice().getPrice()
                    + "\t\t" + downwardRun.getEvent().getTotalMove());
        }if (downwardRun.getOvershootEvent() != null) {
            System.out.println("DownturnOvershoot Event: " + downwardRun.getOvershootEvent());
            outputFile.write("DownturnOvershoot \t"+ downwardRun.getOvershootEvent().getStartingPointIndex() + "\t" + downwardRun.getOvershootEvent().getStartingPointPrice().getPrice()
                    + "\t " + downwardRun.getOvershootEvent().getEndingPointIndex() + "\t" + downwardRun.getOvershootEvent().getEndingPointPrice().getPrice()
                    + "\t\t" + downwardRun.getOvershootEvent().getTotalMove());
        }


       /* outputFile.write("DOWN\t" + downwardRun.getEvent().getStartingPointIndex() + "\t" + downwardRun.getEvent().getStartingPointPrice().getPrice()
                + "\t " + downwardRun.getEvent().getEndingPointIndex() + "\t" + downwardRun.getEvent().getEndingPointPrice().getPrice()
                + "\t\t" + downwardRun.getOvershootEvent().getStartingPointIndex() + "\t" + downwardRun.getOvershootEvent().getStartingPointPrice().getPrice()
                + "\t " + downwardRun.getOvershootEvent().getEndingPointIndex() + "\t" + downwardRun.getOvershootEvent().getEndingPointPrice().getPrice()
                + "\t\t" + downwardRun.getTotalMove());
        */
    }
}
