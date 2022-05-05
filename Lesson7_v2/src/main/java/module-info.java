module org.exapmle.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens org.example.client to javafx.fxml;
    exports org.example.client;
}