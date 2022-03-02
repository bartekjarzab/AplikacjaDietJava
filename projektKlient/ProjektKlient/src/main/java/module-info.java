module Klient {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.sql;

    opens Klient to javafx.fxml;
    exports Klient;
}