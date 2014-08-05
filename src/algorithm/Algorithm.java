package algorithm;

import algorithm.events.IEvent;
import algorithm.runs.*;

/**
 * Created by jerem on 02/08/14.
 */
public class Algorithm
{

    private Market                          market;
    private double                          threshold;

    private UpwardRun                       upwardRun;
    private DownwardRun                     downwardRun;
    private ARun                            currentRun;

    private OnDirectionalChangeListener     listener;

    public interface OnDirectionalChangeListener{
        public void onUpwardRun(IRun upwardRun);
        public void onDownwardRun(IRun downwardRun);
    }

    public Algorithm(Market market)
    {
        this.market = market;
    }

    public void setOnDirectionalChangeListener(OnDirectionalChangeListener listener)
    {
        this.listener = listener;
    }

    public void launch(double threshold){
        Price           currentPrice;

        this.threshold = threshold;
        if (market.isNext())
        {
            upwardRun = new UpwardRun(market.getIndex(), market.getPrice(market.getIndex()));
            downwardRun = new DownwardRun(market.getIndex(), market.getPrice(market.getIndex()));
            currentRun = upwardRun;
            while (market.isNext())
            {
                currentPrice = market.nextPrice();
                if (currentRun.getRunType() == ERun.UPWARD_RUN)
                    upturnEvent(currentPrice);
                else
                    downturnEvent(currentPrice);
            }
        }
    }

    private boolean     error = true;

    private void upturnEvent(Price currentPrice)
    {
        if (currentRun.isDirectionalChange(currentPrice, threshold) == true)
        {
            error = true;
            // upward run est fini ici.
            if (listener != null)
                listener.onUpwardRun(currentRun);
            //upwardRun.getEvent().setStartingPoint(market.getNextIndex(), market.getPrice(market.getNextIndex())); // add
            currentRun = downwardRun;
            downwardRun.updateLowPrice(currentPrice);
            downwardRun.getEvent().setEndingPoint(market.getIndex(), currentPrice);
            downwardRun.getOvershootEvent().setStartingPoint(market.getNextIndex(), market.getPrice(market.getNextIndex()));
            //downwardRun.getOvershootEvent().setEndingPoint(market.getNextIndex(), market.getPrice(market.getNextIndex())); // add
        } else if (upwardRun.isHighestPrice(currentPrice) == true)
        {
            upwardRun.updateHighPrice(currentPrice);
            downwardRun.getEvent().setStartingPoint(market.getIndex(), currentPrice);
            upwardRun.getOvershootEvent().setEndingPoint(market.getPreviousIndex(), market.getPrice(market.getPreviousIndex()));
        }
    }

    private void downturnEvent(Price currentPrice)
    {
        if (currentRun.isDirectionalChange(currentPrice, threshold) == true)
        {
            if (error == true)
                System.out.println("------> Error");
            // downward run est fini ici.
            if (listener != null)
                listener.onDownwardRun(currentRun);
            //downwardRun.getEvent().setStartingPoint(market.getNextIndex(), market.getPrice(market.getNextIndex())); // add
            currentRun = upwardRun;
            upwardRun.updateHighPrice(currentPrice);
            upwardRun.getEvent().setEndingPoint(market.getIndex(), currentPrice);
            upwardRun.getOvershootEvent().setStartingPoint(market.getNextIndex(), market.getPrice(market.getNextIndex()));
            //upwardRun.getOvershootEvent().setEndingPoint(market.getNextIndex(), market.getPrice(market.getNextIndex())); // add
        } else if (downwardRun.isLowestPrice(currentPrice) == true)
        {
            error = false;
            downwardRun.updateLowPrice(currentPrice);
            upwardRun.getEvent().setStartingPoint(market.getIndex(), currentPrice);
            downwardRun.getOvershootEvent().setEndingPoint(market.getPreviousIndex(), market.getPrice(market.getPreviousIndex()));
        }
    }

}
