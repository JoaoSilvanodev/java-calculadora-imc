package joao.projetos.calculadoraimc;

import javafx.application.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class CalculadoraIMC extends Application {


    @Override
    public void start(Stage stage) {

        // declaração dos objetos.
        Label weightLabel = new Label("Peso");
        Label heightLabel = new Label("Altura");

        TextField weightField = new TextField();
        weightField.setPromptText("Peso em Kg");

        TextField heightField = new TextField();
        heightField.setPromptText("Altura (ex: 1.78 ou 178)");

        Label resultLabel = new Label();
        Label veredictLabel = new Label();

        Button calcButton = new Button("Calcular IMC");

        // ação do botão, ao ser clicado, com try/catch para tratamento de erro.
        calcButton.setOnAction(e -> {

            try {

                double peso = parse(weightField.getText());
                double altura = tratarAltura(heightField.getText());


                if (peso <= 0 || altura <= 0) {
                    resultLabel.setText("Valores devem ser maiores que zero!");
                    veredictLabel.setText("");
                    return;
                }

                double imc = calcularIMC(peso, altura);

                resultLabel.setText(String.format("IMC: %.2f", imc));
                veredictLabel.setText(classificarIMC(imc));

                // caso não seja um valor digitado, o resultLabel exibe a mensagem correspondente.
            } catch (NumberFormatException exception) {
               resultLabel.setText("Digite números válidos (ex: 70 ou 70,5)");
                veredictLabel.setText("");
            }
        });


        // definição da janela com a exibição em "lista" dos items declarados.
        VBox layout = new VBox(10,
                weightLabel,
                weightField,
                heightLabel,
                heightField,
                calcButton,
                resultLabel,
                veredictLabel
        );

        // ajustes de espaçamento e posição dos itens na janela.
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        // definição da cena, passando a janela e seu tamanho, titulo e exibição com .show();
        Scene scene = new Scene(layout, 400, 300);
        stage.setTitle("Calculadora de IMC");
        stage.setScene(scene);
        stage.show();
    }

    // método para ajustar o input, substituindo a virgula por ponto caso haja.
    private double parse(String valor) {
        return Double.parseDouble(valor.trim().replace(",","."));
    }

    // trata o input da altura, caso o usuário digite o valor sem virgula ou ponto;
    private double tratarAltura(String valor) {
        double altura = parse(valor);
        return altura > 3 ? altura / 100 : altura;
    }

    // método para calcular em si o imc.
    private double calcularIMC(double peso, double altura) {
        return peso / (altura * altura);
    }

    // método com os condicionais de classificação do IMC.
    private String classificarIMC(double imc) {
        if (imc < 17) return "Muito abaixo do peso";
        else if (imc < 18.49) return "Abaixo do peso";
        else if (imc < 24.99) return "Peso Normal";
        else if (imc < 29.99) return "Acima do peso";
        else if (imc < 34.99) return "Obesidade 1";
        else if (imc < 39.99) return "Obesidade 2 (severa)";
        else return "Obesidade 3 (mórbida)";
    }

    public static void main(String[] args) {
        launch(args);
    }
 }

