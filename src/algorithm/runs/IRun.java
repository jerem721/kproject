package algorithm.runs;

import algorithm.Price;
import algorithm.events.IEvent;

/**
 * Created by jerem on 02/08/14.
 */
public interface IRun
{

    public IEvent   getEvent();
    public IEvent   getOvershootEvent();

    public int      getDirectionalChangeIndex();
    public Price    getDirectionalChangePrice();
    public int      getTotalMove();

    public boolean  isDirectionalChange(Price current, double threshold);

}
