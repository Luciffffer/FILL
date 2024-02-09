module be.kdg.fill {
    requires javafx.controls;
    requires javafx.fxml;

    opens be.kdg.fill to javafx.fxml;
    exports be.kdg.fill;
}