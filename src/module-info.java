module pokemonPartyManagementTool {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires javafx.base;

    opens application to javafx.graphics, javafx.fxml, Main.fxml, javafx.base;
    opens controller to javafx.graphics, javafx.fxml, Main.fxml, javafx.base;
    opens model to javafx.graphics, javafx.fxml, Main.fxml, javafx.base;
    opens dao to javafx.graphics, javafx.fxml, Main.fxml, javafx.base;
    opens dto to javafx.graphics, javafx.fxml, Main.fxml, javafx.base;
}
