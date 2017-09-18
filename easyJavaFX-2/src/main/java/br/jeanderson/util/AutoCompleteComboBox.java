package br.jeanderson.util;

import java.text.Normalizer;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;

/**
 * Tem como base http://aprendendo-javafx.blogspot.com.br/2016/03/completar-automaticamente-combobox.html
 * @author jeanderson
 *
 */
public class AutoCompleteComboBox {
	private ObservableList<?> itensOriginais;
	private ObservableList listaFiltrada;
	private final ComboBox comboBox;
	private String textoDigitado;

	public AutoCompleteComboBox(ComboBox<?> comboBox) {
		this.comboBox = comboBox;
		this.comboBox.setTooltip(new Tooltip());
		this.comboBox.addEventFilter(KeyEvent.KEY_RELEASED, this::onKeyReleased);
		this.comboBox.focusedProperty().addListener(this::onChangeFocused);
		this.comboBox.itemsProperty().addListener(this::onChangeItens);
		// caso o combox já esteja preenchido desde do inicio.
		if (comboBox.getItems() != null) {
			this.onChangeItens(null);
		}
		this.textoDigitado = "";
	}

	private void onKeyReleased(KeyEvent evento) {
		KeyCode teclaDigitada = evento.getCode();
		if (teclaDigitada != KeyCode.UP && teclaDigitada != KeyCode.DOWN && teclaDigitada != KeyCode.TAB) {
			if (teclaDigitada == KeyCode.ENTER) {
				int indexSelecionada = comboBox.getSelectionModel().getSelectedIndex();
				if (indexSelecionada != -1) {
					comboBox.getSelectionModel().select(indexSelecionada);
					comboBox.hide();
				} else {
					textoDigitado = "";
					comboBox.getTooltip().hide();
					comboBox.getItems().setAll(itensOriginais);
				}
			} else {
				comboBox.hide();
				if (teclaDigitada.isLetterKey()) {
					textoDigitado += evento.getText();
				}

				if (teclaDigitada == KeyCode.BACK_SPACE && textoDigitado.length() > 0) {
					textoDigitado = textoDigitado.substring(0, textoDigitado.length() - 1);
				}
				if (teclaDigitada == KeyCode.SPACE) {
					this.textoDigitado += " ";
				}
				if (textoDigitado.length() == 0) {
					comboBox.getTooltip().hide();
					comboBox.getItems().setAll(itensOriginais);
					comboBox.show();
					listaFiltrada.clear();
				} else {
					this.showToolTip();
					this.textoDigitado = this.removerAcentos(textoDigitado);
					this.listaFiltrada.clear();
					itensOriginais.stream().filter(this::verificarItens).forEach(listaFiltrada::add);
					this.comboBox.getItems().setAll(listaFiltrada);
					this.comboBox.show();
				}
			}
		}
	}

	private void onChangeFocused(Observable valor) {
		if(!comboBox.isFocused()) {
			this.textoDigitado = "";
			this.comboBox.getTooltip().hide();
			Object itemSelecionado = comboBox.getSelectionModel().getSelectedItem();
			comboBox.getItems().setAll(itensOriginais);
			comboBox.getSelectionModel().select(itemSelecionado);
		}
	}

	private void onChangeItens(Observable valor) {
		this.itensOriginais = FXCollections.observableArrayList(comboBox.getItems());
		this.listaFiltrada = FXCollections.observableArrayList();
	}

	private String removerAcentos(String texto) {
		texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
		return texto.replaceAll("[^\\p{ASCII}]", "");
	}

	private boolean verificarItens(Object item) {
		String itemSemAcento = this.removerAcentos(comboBox.getConverter().toString(item));
		return itemSemAcento.toLowerCase().startsWith(textoDigitado);
	}

	private void showToolTip() {
		comboBox.getTooltip().setText(textoDigitado);
		Window stage = comboBox.getScene().getWindow();
		double posicaoX = stage.getX() + comboBox.getBoundsInParent().getMinX();
		double posicaoY = stage.getY() + comboBox.getBoundsInParent().getMinY();
		comboBox.getTooltip().show(stage, posicaoX, posicaoY);
	}
}
