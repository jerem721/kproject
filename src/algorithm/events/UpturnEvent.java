package algorithm.events;

import algorithm.Price;

/**
 * Created by jerem on 02/08/14.
 */
public class UpturnEvent implements IEvent
{

    private int     startingPointIndex;
    private Price   startingPointPrice;
    private int     endingPointIndex;
    private Price   endingPointPrice;

    public UpturnEvent(int initialIndex, Price initialPrice)
    {
        startingPointIndex = initialIndex;
        endingPointIndex = initialIndex;
        startingPointPrice = initialPrice;
        endingPointPrice = initialPrice;
    }

    @Override
    public void setStartingPoint(int index, Price price)
    {
        this.startingPointIndex = index;
        this.startingPointPrice = price;
    }

    @Override
    public void setEndingPoint(int index, Price price)
    {
        this.endingPointIndex = index;
        this.endingPointPrice = price;
    }

    @Override
    public int getStartingPointIndex()
    {
        return startingPointIndex;
    }

    @Override
    public Price getStartingPointPrice()
    {
        return startingPointPrice;
    }

    @Override
    public int getEndingPointIndex()
    {
        return endingPointIndex;
    }

    @Override
    public Price getEndingPointPrice()
    {
        return endingPointPrice;
    }

    @Override
    public int getTotalMove()
    {
        return endingPointIndex - startingPointIndex;
    }
}
