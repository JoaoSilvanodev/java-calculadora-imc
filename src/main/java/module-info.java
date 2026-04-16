module joao.projetos.calculadoraimc {
    requires javafx.controls;
    requires javafx.fxml;


    opens joao.projetos.calculadoraimc to javafx.fxml;
    exports joao.projetos.calculadoraimc;
}