//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.wokrpls;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloController {
    ObservableList<String> coins = FXCollections.observableArrayList(new String[]{"BTC-USD", "ETH-USD", "DOGE-USD"});
    @FXML
    LineChart<String, Number> lineChart;
    Crypto cryptoObject = new Crypto();
    Coin BTC = new Coin("BTC-USD");
    Coin ETH = new Coin("ETH-USD");
    Coin DOGE = new Coin("DOGE-USD");
    @FXML
    ChoiceBox coin;
    @FXML
    Label absoluteReturn = new Label();
    @FXML
    Label latestCryptoPrice;
    @FXML
    Label iSpent;
    @FXML
    Label iOwn;
    @FXML
    TextField quantity;
    @FXML
    TextField price;
    @FXML
    Button Generate;

    public HelloController() {
    }

    public void chartGen(ActionEvent event) throws IOException, ParseException {
        this.lineChart.getData().clear();
        Series<String, Number> series = new Series();
        String s = (String)this.coin.getSelectionModel().getSelectedItem();
        this.lineChart.setTitle(s);
        Crypto.getPrices(s, 1);

        for(int i = 0; i < Crypto.Price.size(); ++i) {
            series.getData().add(new Data((String)Crypto.PriceDates.get(i), (Number)Crypto.Price.get(i)));
        }

        this.lineChart.getData().add(series);
        BigDecimal latestPriceBD = (BigDecimal)Crypto.Price.get(Crypto.Price.size() - 1);
        this.latestCryptoPrice.setText("$" + doubleDigitConverter(latestPriceBD.toString()));
        this.price.setText(latestPriceBD.toString());
        if (s.equals("BTC-USD")) {
            this.specificCoinData(this.BTC, latestPriceBD);
        } else if (s.equals("ETH-USD")) {
            this.specificCoinData(this.ETH, latestPriceBD);
        } else if (s.equals("DOGE-USD")) {
            this.specificCoinData(this.DOGE, latestPriceBD);
        }

    System.out.println();
    }

    private String doubleDigitConverter(String toString) {
        int length=toString.length();
        StringBuilder finalString= new StringBuilder();

        for(int i=0;i<length;i++){
            if(toString.charAt(i)!='.'){
                finalString.append(toString.charAt(i));
            }
            else{

                finalString.append(toString.charAt(i));
                finalString.append(toString.charAt(i+1));
                finalString.append(toString.charAt(i+2));
                return finalString.toString();
            }
        }
        System.out.println("Returning: "+toString);
        return finalString.toString();
    }

    @FXML
    void initialize() {
        readCurrentUser();
        this.coin.setValue("BTC-USD");
        this.coin.setItems(this.coins);
        String s = (String)this.coin.getSelectionModel().getSelectedItem();
        this.quantity.setText("");
        this.price.setText("");
//        this.BTC.iBoughtSome(40000.23D, 1.0D);
//        this.BTC.iBoughtSome(3200.0D, 0.3D);
        initializeCoins();
        Generate.fire();
    }

    void specificCoinData(Coin c, BigDecimal price) {
        Crypto var10000 = this.cryptoObject;
        double aR = Crypto.absoluteReturn(price, c.getCoinsOwned(), c.getAmountSpent());
        double coinsOwned = c.getCoinsOwned();
        double amountSpent = c.getAmountSpent();
        this.iSpent.setText("I spent: " + String.valueOf(amountSpent));
        this.iOwn.setText("I Own: " + String.valueOf(coinsOwned));
        this.absoluteReturn.setText("Absolute Return: " + doubleDigitConverter(String.valueOf(aR)));
    }

    public void transactionWrapper(ActionEvent event) {
        String s = (String)this.coin.getSelectionModel().getSelectedItem();
        double q = Double.parseDouble(this.quantity.getText());
        double p = Double.parseDouble(this.price.getText());
        if (s.equals("BTC-USD")) {
            this.transaction(this.BTC, q, p);
        } else if (s.equals("ETH-USD")) {
            this.transaction(this.ETH, q, p);
        } else if (s.equals("DOGE-USD")) {
            this.transaction(this.DOGE, q, p);
        }

    }

    public void transaction(Coin c, double q, double p) {
        if (q < 0.0D) {
            c.iSoldSome(p, q);
        } else if (q > 0.0D) {
            c.iBoughtSome(p, q);
        }
        registerOnServer();

        System.out.println("Successfully Registered" + q);
        this.quantity.setText("0");
        this.price.setText("0");
        this.Generate.fire();
    }
    String currentUser;
    public void readCurrentUser(){
        try {
            File myObj = new File("userName.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                currentUser=data;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void initializeCoins(){
        serverBridge sb=new serverBridge();
        BTC.setAmountSpent(sb.getTotalSpentBTC(currentUser));
        BTC.setCoinsOwned(sb.getTotalBTC(currentUser));

        ETH.setAmountSpent(sb.getTotalSpentETH(currentUser));
        ETH.setCoinsOwned(sb.getTotalETH(currentUser));

    }

    public void registerOnServer(){
        serverBridge sb=new serverBridge();
        sb.registerTransactionBTC(currentUser, BTC);
        sb.registerTransactionETH(currentUser, ETH);
    }



}
