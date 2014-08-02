package algorithm.runs;

import algorithm.Price;
import algorithm.events.UpturnEvent;
import algorithm.events.UpturnOvershootEvent;

/**
 * Created by jerem on 02/08/14.
 */
public class UpwardRun extends ARun {

    private Price       highPrice;

    public UpwardRun(int initialIndex, Price initialPrice) {
        super(ERun.UPWARD_RUN,
                new UpturnEvent(initialIndex, initialPrice),
                new UpturnOvershootEvent(initialIndex, initialPrice));
        this.highPrice = initialPrice;
    }

    @Override
    public final boolean isDirectionalChange(Price current, double threshold) {
        return current.getPrice() <= highPrice.getPrice() * (1.0 - (threshold * 100));
    }

    public final boolean isHighestPrice(Price price)
    {
        if (highPrice == null)
            return true;
        return price.getPrice() > highPrice.getPrice();
    }

    public final void updateHighPrice(Price price)
    {
        highPrice = price;
    }
}
