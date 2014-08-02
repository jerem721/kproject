package algorithm.runs;

import algorithm.Price;
import algorithm.events.IEvent;

/**
 * Created by jerem on 02/08/14.
 */
public abstract class ARun implements IRun
{

    protected ERun          eventType;
    protected IEvent        event;
    protected IEvent        overshootEvent;

    public ARun(ERun eventType, IEvent event, IEvent overshootEvent)
    {
        this.eventType = eventType;
        this.event = event;
        this.overshootEvent = overshootEvent;
    }

    public ERun getEventType()
    {
        return eventType;
    }

    public void setEventType(ERun eventType)
    {
        this.eventType = eventType;
    }

    @Override
    public IEvent getEvent() {
        return event;
    }

    @Override
    public IEvent getOvershootEvent() {
        return overshootEvent;
    }

    @Override
    public int getDirectionalChangeIndex() {
        return event.getEndingPointIndex();
    }

    @Override
    public Price getDirectionalChangePrice() {
        return event.getEndingPointPrice();
    }

    @Override
    public final int getTotalMove() {
        return event.getTotalMove() + overshootEvent.getTotalMove();
    }

    @Override
    public String toString() {
        return "ARun{" +
                "eventType=" + eventType +
                ", event=" + event.toString() +
                ", overshootEvent=" + overshootEvent.toString() +
                '}';
    }
}
