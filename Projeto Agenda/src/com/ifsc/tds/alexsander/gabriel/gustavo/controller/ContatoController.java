package com.ifsc.tds.alexsander.gabriel.gustavo.controller;

import java.io.IOException;



import java.net.URL;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ifsc.tds.alexsander.gabriel.gustavo.dao.ContatoDAO;
import com.ifsc.tds.alexsander.gabriel.gustavo.entity.Contato;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ContatoController implements Initializable {

	    @FXML // fx:id="pnlPrincipal"
	    private AnchorPane pnlPrincipal; // Value injected by FXMLLoader

	    @FXML // fx:id="pnlDivisao"
	    private SplitPane pnlDivisao; // Value injected by FXMLLoader

	    @FXML // fx:id="tbvAgenda"
	    private TableView<Contato> tbvAgenda; // Value injected by FXMLLoader

	    @FXML // fx:id="tbcCodigo"
	    private TableColumn<Contato, Long> tbcCodigo; // Value injected by FXMLLoader

	    @FXML // fx:id="tbcNome"
	    private TableColumn<Contato, String> tbcNome; // Value injected by FXMLLoader

	    @FXML // fx:id="pnlMenu"
	    private AnchorPane pnlMenu; // Value injected by FXMLLoader

	    @FXML // fx:id="lblDetalhes"
	    private Label lblDetalhes; // Value injected by FXMLLoader

	    @FXML // fx:id="pnlDetalhes"
	    private GridPane pnlDetalhes; // Value injected by FXMLLoader

	    @FXML // fx:id="lblNome"
	    private Label lblNome; // Value injected by FXMLLoader

	    @FXML // fx:id="lblTelefone"
	    private Label lblTelefone; // Value injected by FXMLLoader

	    @FXML // fx:id="barBotoes"
	    private ButtonBar barBotoes; // Value injected by FXMLLoader

	    @FXML // fx:id="btnIncluir"
	    private Button btnIncluir; // Value injected by FXMLLoader

	    @FXML // fx:id="tlpIncluir"
	    private Tooltip tlpIncluir; // Value injected by FXMLLoader

	    @FXML // fx:id="btnEditar"
	    private Button btnEditar; // Value injected by FXMLLoader

	    @FXML // fx:id="tlpEditar"
	    private Tooltip tlpEditar; // Value injected by FXMLLoader

	    @FXML // fx:id="btnExcluir"
	    private Button btnExcluir; // Value injected by FXMLLoader

	    @FXML // fx:id="tlpExcluir"
	    private Tooltip tlpExcluir; // Value injected by FXMLLoader
	    
	    @FXML
	    private Label lblEmail;
	    
	    @FXML // fx:id="lblValorEmail"
	    private Label lblValorEmail; // Value injected by FXMLLoader

	    @FXML // fx:id="lblValorNome"
	    private Label lblValorNome; // Value injected by FXMLLoader

	    @FXML // fx:id="lblValorTelefone"
	    private Label lblValorTelefone; // Value injected by FXMLLoader
	    
		private List<Contato> listaContatos;
		private ObservableList<Contato> observableListaContatos = FXCollections.observableArrayList();
		private ContatoDAO contatoDAO;

		public static final String CONTATO_EDITAR = " - Editar";
		public static final String CONTATO_INCLUIR = " - Incluir";

	    @FXML
	    void onClickBtnEditar(ActionEvent event) {
			Contato contato = this.tbvAgenda.getSelectionModel().getSelectedItem();

			if (contato != null) {
				boolean btnConfirmarClic = this.onShowContatoEdit(contato, ContatoController.CONTATO_EDITAR);

				if (btnConfirmarClic) {
					this.getContatoDAO().update(contato, null);
					this.carregarTableViewContatos();
				}
			} else {
				Alert alerta = new Alert(Alert.AlertType.ERROR);
				alerta.setContentText("Por favor, escolha um contato na tabela!");
				alerta.show();
				}
			
	    }
		@FXML
	    void onClickBtnExcluir(ActionEvent event) {
			Contato contato = this.tbvAgenda.getSelectionModel().getSelectedItem();

			if (contato != null) {

				Alert alerta = new Alert(AlertType.CONFIRMATION);
				alerta.setTitle("Pergunta");
				alerta.setHeaderText("Confirma a exclus�o do contato?\n" + contato.getNome());

				ButtonType botaoNao = ButtonType.NO;
				ButtonType botaoSim = ButtonType.YES;
				alerta.getButtonTypes().setAll(botaoSim, botaoNao);
				Optional<ButtonType> resultado = alerta.showAndWait();

				if (resultado.get() == botaoSim) {
					this.getContatoDAO().delete(contato);
					this.carregarTableViewContatos();
				}
			} else {
				Alert alerta = new Alert(Alert.AlertType.ERROR);
				alerta.setContentText("Por favor, escolha um contato na tabela!");
				alerta.show();
			}
	    }
		
		public void carregarTableViewContatos() {
			this.tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
			this.tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

			this.setListaContatos(this.getContatoDAO().getAll());
			this.setObservableListaContatos(FXCollections.observableArrayList(this.getListaContatos()));
			this.tbvAgenda.setItems(this.getObservableListaContatos());
		}
		
	    public List<Contato> getListaContatos() {
			return listaContatos;
		}
		public void setListaContatos(List<Contato> listaContatos) {
			this.listaContatos = listaContatos;
		}
		public ObservableList<Contato> getObservableListaContatos() {
			return observableListaContatos;
		}
		public void setObservableListaContatos(ObservableList<Contato> observableListaContatos) {
			this.observableListaContatos = observableListaContatos;
		}
		@FXML
	    void onClickBtnIncluir(ActionEvent event){
			Contato contato = new Contato();
			boolean btnConfirmarClic = this.onShowContatoEdit(contato, ContatoController.CONTATO_INCLUIR);

			if (btnConfirmarClic) {
				this.getContatoDAO().save(contato);
				this.carregarTableViewContatos();
			}
	    }
	    
		private boolean onShowContatoEdit(Contato contato, String operacao) {
			//carregando o loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/alexsander/gabriel/gustavo/view/ContatoEdit.fxml"));
			Parent contatoEditXML = null;
			try {
				contatoEditXML = loader.load();
			} catch (IOException e) {
				Alert erro = new Alert (AlertType.ERROR);
				erro.setHeaderText("Ocorreu um erro inesperado ao abrir o prorama \n Tente novamente");
				erro.setTitle("ERRO DE CARREGAMENTO");
				erro.showAndWait();
				e.printStackTrace();
			}

			// criando uma janela nova
			Stage janelaContatoEditar = new Stage();
			janelaContatoEditar.setTitle("Editar contato" + operacao);
			janelaContatoEditar.initModality(Modality.APPLICATION_MODAL);
			janelaContatoEditar.resizableProperty().setValue(Boolean.FALSE);

			Scene contatoEditLayout = new Scene(contatoEditXML);
			janelaContatoEditar.setScene(contatoEditLayout);

			// carregando o controller e a scene
			ContatoEditController contatoEditController = loader.getController();
			contatoEditController.setJanelaContatoEdit(janelaContatoEditar);
			contatoEditController.populaTela(contato);

			// mostrando a tela
			janelaContatoEditar.showAndWait();

			return contatoEditController.isOkClick();
		}
		public void initialize(URL arg0, ResourceBundle arg1) {
			this.setContatoDAO(new ContatoDAO());
			this.carregarTableViewContatos();
			this.selecionarItemTableViewContatos(null);

			this.tbvAgenda.getSelectionModel().selectedItemProperty()
					.addListener((observable, oldValue, newValue) -> selecionarItemTableViewContatos(newValue));
		}

		public boolean onCloseQuery() {
			Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
			alerta.setTitle("Pergunta");
			alerta.setHeaderText("Deseja sair do sistema?");
			ButtonType botaoNao = ButtonType.NO;
			ButtonType botaoSim = ButtonType.YES;
			alerta.getButtonTypes().setAll(botaoSim, botaoNao);
			Optional<ButtonType> resultado = alerta.showAndWait();
			return resultado.get() == botaoSim ? true : false;
		}
		
		public void selecionarItemTableViewContatos(Contato contato) {
			if (contato != null) {
				this.lblValorNome.setText(contato.getNome());
				this.lblValorTelefone.setText(contato.getTelefone());
				this.lblValorEmail.setText(contato.getEmail());
			} else {
				this.lblValorNome.setText("");
				this.lblValorTelefone.setText("");
				this.lblValorEmail.setText("");
			}
		}
		
	    public ContatoDAO getContatoDAO() {
			return contatoDAO;
		}

		public void setContatoDAO(ContatoDAO contatoDAO) {
			this.contatoDAO = contatoDAO;
		}


}
