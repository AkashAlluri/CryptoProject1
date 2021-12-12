package com.example.wokrpls;
// class to represent a cryptocurrency coin object

import javafx.scene.control.Alert;

public class Coin {
    String coinName;
    double amountSpent;
    double coinsOwned;

    String getCoinName(){ return coinName;}
    double getAmountSpent(){return amountSpent;}
    double getCoinsOwned(){return coinsOwned;}

    Coin(String cn){
        coinName=cn;
        amountSpent=0.0;
        coinsOwned=0.0;
    }

    void iBoughtSome(Double moneySpent, Double coinsBought){
        // simulates buying of a coin
        amountSpent+=(coinsBought*moneySpent);
        coinsOwned+=coinsBought;
    }

    void iSoldSome(Double moneyMade, Double coinsSold){
        // simulates selling of a coin
        if(coinsSold*-1 > coinsOwned){
            Alert a=new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setTitle("TRANSACTION ERROR");
            a.setContentText("Not Enough Coins to sell. Please change the number of coins and try again");
            a.show();
        }
        else {
            amountSpent += (coinsSold * moneyMade);
            coinsOwned += coinsSold;
        }
    }

    // setter functions
    void setAmountSpent(Double as){amountSpent=as;}
    void setCoinsOwned(Double cs){coinsOwned=cs;}
}
