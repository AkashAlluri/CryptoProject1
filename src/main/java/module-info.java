module com.example.wokrpls {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires YahooFinanceAPI;
    requires java.sql;


    opens com.example.wokrpls to javafx.fxml;
    exports com.example.wokrpls;
}