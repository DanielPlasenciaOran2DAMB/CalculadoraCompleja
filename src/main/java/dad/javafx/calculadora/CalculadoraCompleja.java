package dad.javafx.calculadora;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class CalculadoraCompleja extends Application {

	private TextField realAText, realCText, imaginarioBText, imaginarioDText, resultadoRealText, resultadoImaginText;
	private String suma = "+", resta = "-", multiplicacion = "*", division = "/";
	private String[] operandos = { suma, resta, multiplicacion, division };
	private ComboBox<String> operacionesCombo;
	private Separator separadorSeparator;
	private Complejo operacion1, operacion2, resultado;

	@Override
	public void start(Stage primaryStage) throws Exception {
		realAText = new TextField("0");
		realAText.setMaxWidth(50);
		realAText.setAlignment(Pos.CENTER);

		realCText = new TextField("0");
		realCText.setMaxWidth(50);
		realCText.setAlignment(Pos.CENTER);

		imaginarioBText = new TextField("0");
		imaginarioBText.setMaxWidth(50);
		imaginarioBText.setAlignment(Pos.CENTER);

		imaginarioDText = new TextField("0");
		imaginarioDText.setMaxWidth(50);
		imaginarioDText.setAlignment(Pos.CENTER);

		resultadoRealText = new TextField("0");
		resultadoRealText.setMaxWidth(50);
		resultadoRealText.setDisable(true);
		resultadoRealText.setAlignment(Pos.CENTER);

		resultadoImaginText = new TextField("0");
		resultadoImaginText.setMaxWidth(50);
		resultadoImaginText.setDisable(true);
		resultadoImaginText.setAlignment(Pos.CENTER);

		operacionesCombo = new ComboBox<String>();
		operacionesCombo.getItems().addAll(operandos);
		operacionesCombo.getSelectionModel().selectFirst();

		separadorSeparator = new Separator();
		separadorSeparator.setMaxWidth(50);

		operacion1 = new Complejo();
		operacion2 = new Complejo();
		resultado = new Complejo();

		VBox operacionesVBox = new VBox(5, operacionesCombo);
		operacionesVBox.setAlignment(Pos.CENTER);

		HBox primeraOperacionBox = new HBox(5, realAText, new Label(" + "), imaginarioBText, new Label(" i"));
		primeraOperacionBox.setAlignment(Pos.CENTER);

		HBox segundaOperacionBox = new HBox(5, realCText, new Label(" + "), imaginarioDText, new Label(" i"));
		segundaOperacionBox.setAlignment(Pos.CENTER);

		HBox resultadoOperacionBox = new HBox(5, resultadoRealText, new Label(" + "), resultadoImaginText,
				new Label(" i"));
		resultadoOperacionBox.setAlignment(Pos.CENTER);

		VBox calculadoraVBox = new VBox(5, primeraOperacionBox, segundaOperacionBox, separadorSeparator,
				resultadoOperacionBox);
		calculadoraVBox.setAlignment(Pos.CENTER);

		HBox root = new HBox(5, operacionesVBox, calculadoraVBox);
		root.setAlignment(Pos.CENTER);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("CalculadoraView.fxml");
		primaryStage.setScene(scene);
		primaryStage.show();

		realAText.textProperty().bindBidirectional(operacion1.realProperty(), new NumberStringConverter());
		imaginarioBText.textProperty().bindBidirectional(operacion1.imaginarioProperty(), new NumberStringConverter());
		realCText.textProperty().bindBidirectional(operacion2.realProperty(), new NumberStringConverter());
		imaginarioDText.textProperty().bindBidirectional(operacion2.imaginarioProperty(), new NumberStringConverter());

		resultadoRealText.textProperty().bindBidirectional(resultado.realProperty(), new NumberStringConverter());
		resultadoImaginText.textProperty().bindBidirectional(resultado.imaginarioProperty(),
				new NumberStringConverter());

		operacionesCombo.getSelectionModel().selectedIndexProperty().addListener((o, ov, nv) -> operaciones());
	}

	private void operaciones() {
		if (operacionesCombo.getSelectionModel().getSelectedIndex() == 0) {
			resultado.realProperty().bind(operacion1.realProperty().add(operacion2.realProperty()));
			resultado.imaginarioProperty().bind(operacion1.imaginarioProperty().add(operacion2.imaginarioProperty()));
		} else if (operacionesCombo.getSelectionModel().getSelectedIndex() == 1) {
			resultado.realProperty().bind(operacion1.realProperty().subtract(operacion2.realProperty()));
			resultado.imaginarioProperty()
					.bind(operacion1.imaginarioProperty().subtract(operacion2.imaginarioProperty()));
		} else if (operacionesCombo.getSelectionModel().getSelectedIndex() == 2) {
			resultado.realProperty().bind(operacion1.realProperty().multiply(operacion2.realProperty()));
			resultado.imaginarioProperty()
					.bind(operacion1.imaginarioProperty().multiply(operacion2.imaginarioProperty()));
		} else if (operacionesCombo.getSelectionModel().getSelectedIndex() == 3) {
			resultado.realProperty().bind((operacion1.realProperty().multiply(operacion2.realProperty()))
					.add((operacion1.imaginarioProperty().multiply(operacion2.imaginarioProperty()
							.divide(operacion2.realProperty().multiply(operacion2.realProperty()))
							.add((operacion2.imaginarioProperty().multiply(operacion2.imaginarioProperty())))))));
			resultado.imaginarioProperty().bind((operacion1.imaginarioProperty().multiply(operacion2.realProperty()))
					.subtract((operacion1.realProperty().multiply(operacion2.imaginarioProperty()
							.divide(operacion2.realProperty().multiply(operacion2.realProperty()))
							.add((operacion2.imaginarioProperty().multiply(operacion2.imaginarioProperty())))))));
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
