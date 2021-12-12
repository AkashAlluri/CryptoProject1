package com.example.wokrpls;

import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.math.BigDecimal;
import yahoofinance.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.*;


class Crypto {
    static ArrayList<String> PriceDates=new ArrayList<String>();
    static ArrayList<BigDecimal> Price=new ArrayList<BigDecimal>();

    public static void getPrices(String coin, int timeframe) throws IOException, ParseException {
        // fetch prices from the database
        Price.clear();
        PriceDates.clear();
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.add(Calendar.MONTH, -timeframe); // from 1 year ago

        Stock bitcoin = YahooFinance.get(coin, true);
        List<HistoricalQuote> HistQuotes = bitcoin.getHistory(from, to, Interval.DAILY);

        int size = HistQuotes.size();
        for (int i = 0; i < size; i++) {
            Calendar gc = HistQuotes.get(i).getDate();
            String printdate = gc.get(GregorianCalendar.DAY_OF_MONTH) + "/" + gc.get(GregorianCalendar.MONTH) + "/" + gc.get(GregorianCalendar.YEAR);
            PriceDates.add(printdate);

            // System.out.print(printdate);
            // convert(printdate);
            // System.out.print("-");
            //System.out.print(HistQuotes.get(i).getClose());
            Price.add(HistQuotes.get(i).getClose());
            // System.out.println();
        }


    }
    public static void convert(String date) throws ParseException {

        // helper function to convert dates
        SimpleDateFormat fd = new SimpleDateFormat("dd/MM/yyyy");
        Date d = fd.parse(date);
    }

    public static double absoluteReturn(BigDecimal currentPrice, Double totalOwned, Double totalSpent){
        // function to calculate the absolute return on investment
        double aR= (totalOwned* currentPrice.doubleValue())-totalSpent;
        return aR;
    }

}
