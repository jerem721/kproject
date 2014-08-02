package algorithm;

import algorithm.Price;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerem on 02/08/14.
 */
public class Market {

    private List<Price>     stocks;
    private int             index;

    public Market()
    {
        stocks = new ArrayList<Price>();
        index = 0;
    }

    public Market(List stocks)
    {
        this.stocks = stocks;
        index = 0;
    }

    public void setStock(List stocks)
    {
        this.stocks = stocks;
    }

    public List<Price>  getStocks()
    {
        return stocks;
    }

    public void addPrice(double price)
    {
        if (stocks != null)
            stocks.add(new Price(price));
    }

    public Price getPrice(int index)
    {
        if (stocks != null && index <= stocks.size())
            return stocks.get(index);
        return null;
    }

    public Double getNextPrice()
    {
        Price  price;

        if (stocks != null && index <= stocks.size())
        {
            price = stocks.get(index);
            index++;
            return price.getPrice();
        }
        return null;
    }

    public int getIndex()
    {
        return index;
    }

    public boolean isNext()
    {
        if (index < stocks.size())
            return true;
        return false;
    }

    public void resetMarket()
    {
        index = 0;
    }

    @Override
    public String toString() {
        StringBuilder   str;
        int             i;

        str = new StringBuilder();
        i = 0;
        str.append("Market{");
        for (Price price : stocks)
        {
            str.append("Index: ")
                    .append(i)
                    .append(" --> ")
                    .append(price.toString())
                    .append("\n");
            i++;
        }
        str.append("}");
        return str.toString();
    }
}
