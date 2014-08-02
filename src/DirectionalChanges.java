import file.ReaderFile;
import file.WriterFile;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by jerem on 01/08/14.
 */
public class DirectionalChanges {

    private ReaderFile                      intputFile;
    private List<Double>                    rPersList;
    private Hashtable<Integer, Double>      stockPrices;


    public DirectionalChanges(String inputFile, String outputFile, String rPers[])
    {
        this.intputFile = new ReaderFile(inputFile);
        this.rPersList = new ArrayList<Double>();
        for (String r : rPers)
            rPersList.add(Double.parseDouble(r));
    }

    public void start()
    {

        intputFile.read();
        stockPrices = intputFile.convertStringToDouble();
        for (Double r : rPersList)
        {

        }
    }

    public void finish(){
        if (intputFile != null)
            intputFile.close();
        if (outputFile != null)
            outputFile.close();
    }
}
