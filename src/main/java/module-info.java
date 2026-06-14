module org.example.tareacss {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tareacss to javafx.fxml;
    exports org.example.tareacss;
}