package com.ifsc.tds.alexsander.gabriel.gustavo.main;


import com.ifsc.tds.alexsander.gabriel.gustavo.controller.ContatoController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Principal extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// carregando o loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/alexsander/gabriel/gustavo/view/ContatoView.fxml"));
			Parent menuXML = loader.load();

			// carregando o controller e a scene
			ContatoController contatoController = loader.getController();
			Scene contatoLayout = new Scene(menuXML);

			Stage contatoJanela = new Stage();
			contatoJanela.initModality(Modality.APPLICATION_MODAL);
			contatoJanela.resizableProperty().setValue(Boolean.FALSE);
			contatoJanela.setScene(contatoLayout);
			contatoJanela.setTitle("CONTATOS");

			// atribuindo evento para fechar janela
			contatoJanela.setOnCloseRequest(e -> {
				if (contatoController.onCloseQuery()) {
					System.exit(0);
				} else {
					e.consume();
				}
			});

			contatoJanela.show();

			// posicionando a janela no centro da tela do computador
			Rectangle2D posicaoJanela = Screen.getPrimary().getVisualBounds();
			contatoJanela.setX((posicaoJanela.getWidth() - contatoJanela.getWidth()) / 2);
			contatoJanela.setY((posicaoJanela.getHeight() - contatoJanela.getHeight()) / 2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

