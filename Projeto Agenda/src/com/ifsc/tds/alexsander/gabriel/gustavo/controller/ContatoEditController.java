/**
 * Sample Skeleton for 'ContatoEdit.fxml' Controller Class
 */

package com.ifsc.tds.alexsander.gabriel.gustavo.controller;

import com.ifsc.tds.alexsander.gabriel.gustavo.entity.Contato;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ContatoEditController {

    @FXML // fx:id="pnlPrincipal"
    private AnchorPane pnlPrincipal; // Value injected by FXMLLoader

    @FXML // fx:id="pnlDados"
    private GridPane pnlDados; // Value injected by FXMLLoader

    @FXML // fx:id="lblNome"
    private Label lblNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="pnlBotoes"
    private HBox pnlBotoes; // Value injected by FXMLLoader

    @FXML // fx:id="btnOK"
    private Button btnOK; // Value injected by FXMLLoader

    @FXML // fx:id="btnCancela"
    private Button btnCancela; // Value injected by FXMLLoader

    @FXML // fx:id="pnlDados2"
    private GridPane pnlDados2; // Value injected by FXMLLoader

    @FXML // fx:id="lblTelefone"
    private Label lblTelefone; // Value injected by FXMLLoader

    @FXML // fx:id="txtTelefone"
    private TextField txtTelefone; // Value injected by FXMLLoader

    @FXML // fx:id="pnlDados3"
    private GridPane pnlDados3; // Value injected by FXMLLoader

    @FXML // fx:id="lblEmail"
    private Label lblEmail; // Value injected by FXMLLoader

    @FXML // fx:id="txtEmail"
    private TextField txtEmail; // Value injected by FXMLLoader
    
    private Contato contato;
    
	private boolean okClick = false;
	
	private Stage janelaContatoEdit;
    

	@FXML
	void onClickBtnCancela(ActionEvent event) {
		this.getJanelaContatoEdit().close();
	}

	@FXML
	void onClickBtnOK(ActionEvent event) {
		if (validarCampos()) {
			this.contato.setNome(this.txtNome.getText());
			this.contato.setTelefone(this.txtTelefone.getText());
			this.contato.setEmail(this.txtEmail.getText());

			this.okClick = true;
			this.getJanelaContatoEdit().close();
		}
	}

	public Stage getJanelaContatoEdit() {
		return janelaContatoEdit;
	}

	public void setJanelaContatoEdit(Stage janelaContatoEdit) {
		this.janelaContatoEdit = janelaContatoEdit;
	}

	public void populaTela(Contato contato) {
		this.contato = contato;

		this.txtNome.setText(this.contato.getNome());

		if (this.contato.getNome() != null) {
			this.contato.setNome(txtNome.getText());
		}

		this.txtEmail.setText(this.contato.getEmail());
		
		if (this.contato.getEmail() != null) {
			this.contato.setEmail(txtEmail.getText());
		}
		
		this.txtTelefone.setText(this.contato.getTelefone());

		if (this.contato.getTelefone() != null) {
			this.contato.setTelefone(txtTelefone.getText());
		}
	}

	public boolean isOkClick() {
		return okClick;
	}

	private boolean validarCampos() {
		String mensagemErros = new String();

		if (this.txtNome.getText() == null || this.txtNome.getText().trim().length() == 0) {
			mensagemErros += "Informe o nome!\n";
		}
		
		if (this.txtEmail.getText() == null || this.txtEmail.getText().trim().length() == 0) {
			mensagemErros += "Informe o email!\n";
		}
		
		if (this.txtTelefone.getText() == null || this.txtTelefone.getText().trim().length() == 0) {
			mensagemErros += "Informe o telefone!\n";
		}

		if (mensagemErros.length() == 0) {
			return true;
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.initOwner(this.janelaContatoEdit);
			alerta.setTitle("Dados inv�lidos!");
			alerta.setHeaderText("Favor corrigir as seguintes informa��es:");
			alerta.setContentText(mensagemErros);
			alerta.showAndWait();

			return false;
		}
	}
}


