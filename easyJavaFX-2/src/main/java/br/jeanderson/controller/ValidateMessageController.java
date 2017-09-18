package br.jeanderson.controller;

import br.jeanderson.annotations.DefineConfiguration;
import br.jeanderson.componentes.EasyFXFunctions;
import br.jeanderson.control.ControlWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;

@DefineConfiguration(url_fxml = "/br/jeanderson/view/ValidateMessage.fxml", stageStyle = StageStyle.UNDECORATED, title = "Campo não preenchido corretamente.")
public class ValidateMessageController extends EasyFXFunctions {
	@FXML
	private Label lbMessage;
	private ControlWindow<ValidateMessageController> janela;

	@Override
	public void afterConstruct(ControlWindow control) {
		this.janela = control;
	}

	@Override
	public void afterShow() {
		this.janela.getStage().focusedProperty().addListener(evento -> {
			if (!janela.getStage().isFocused()) {
				janela.close();
			}
		});
		;
		this.janela.getRoot().setOnMouseClicked(evento -> {
			janela.close();
		});
	}

	public void textoDaMensagem(String texto) {
		this.lbMessage.setText(texto);
	}
}
