module be.kdg.fill {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires transitive javafx.graphics;

    opens be.kdg.fill to javafx.fxml;
    exports be.kdg.fill;
}