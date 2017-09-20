package br.jeanderson.util;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.jeanderson.annotations.ClearFields;
import br.jeanderson.annotations.NotClear;
import br.jeanderson.annotations.ValidateField;
import br.jeanderson.control.ControlWindow;
import br.jeanderson.controller.ValidateMessageController;
import br.jeanderson.enums.ValidateType;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;

public class FunctionAnnotations {

	public static boolean validateFields(Object objValidate) {
		Field[] atributos = objValidate.getClass().getDeclaredFields();
		boolean verificado = true;
		try {
			for (Field atributo : atributos) {
				if (atributo.isAnnotationPresent(ValidateField.class)) {					
					atributo.setAccessible(true);
					Object componente = atributo.get(objValidate);
					ValidateField validateField = atributo.getAnnotation(ValidateField.class);
					if (!validate(componente, validateField)) {
						verificado = false;
						break;
					}
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException ex) {
			System.err.println("Houve um erro ao tentar validar campos. Exceção: " + ex);
			Logger.getLogger(FunctionAnnotations.class.getName()).log(Level.SEVERE, null, ex);
		}
		return verificado;
	}

	private static boolean validate(Object componente, ValidateField validateField) {
		if(componente instanceof TextInputControl) {
			if(!validarTextField((TextInputControl)componente, validateField)) {
				exibirMensagem(validateField);
				return false;
			}
			return true;
		} else if (componente instanceof ComboBox) {
            if (((ComboBox) componente).getSelectionModel().getSelectedIndex() == -1) {
                exibirMensagem(validateField);
                return false;
            }
        } else if (componente instanceof DatePicker) {
            if (((DatePicker) componente).getEditor().getText().isEmpty()) {
                exibirMensagem(validateField);
                return false;
            }
        } else if (componente instanceof ChoiceBox) {
            if (((ChoiceBox) componente).getSelectionModel().isSelected(-1)) {
                exibirMensagem(validateField);
                return false;
            }
        } else if (componente instanceof CheckBox) {
            if (((CheckBox) componente).isSelected()) {
                exibirMensagem(validateField);
                return false;
            }
        } else if (componente instanceof TableView) {
            if (((TableView) componente).getSelectionModel().getSelectedIndex() == -1) {
            	exibirMensagem(validateField);
                return false;
            }
        }
        return true;
	}

	private static boolean validarTextField(TextInputControl textField, ValidateField validateField) {
		if (!textField.getText().trim().isEmpty()) {
			if (validateField.type() != ValidateType.NONE) {
				String textoSoNumeros = textField.getText().replaceAll("[^0-9]", "");
				switch (validateField.type()) {
				case TELEFONE:
					return textoSoNumeros.length() == 10 || textoSoNumeros.length() == 11;
				case CPF:
					return textoSoNumeros.length() == 11;
				case DATA:
					return textoSoNumeros.length() == 8;
				default:
					return false;
				}
			} else {
				// já que não informou o tipo,retorna sempre true.
				return true;
			}
		} else {
			return false;
		}
	}
	
	private static void exibirMensagem(ValidateField validate) {
		ControlWindow<ValidateMessageController> janelaMessage = new ControlWindow<>(ValidateMessageController.class);
		janelaMessage.getController().textoDaMensagem(validate.type().message(validate.fieldName()));
		janelaMessage.show();
	}
	
	/**
     * Faz a limpeza do campo de acordo com o seu tipo.
     *
     * @param componente Field.
     */
    private static void clearField(Object componente) {
        if (componente instanceof TextInputControl) {
            ((TextInputControl) componente).setText("");
        } else if (componente instanceof ComboBox) {
            ((ComboBox) componente).getSelectionModel().clearSelection();
        } else if (componente instanceof DatePicker) {
            ((DatePicker) componente).setValue(null);
        } else if (componente instanceof ChoiceBox) {
            ((ChoiceBox) componente).getSelectionModel().clearSelection();
        } else if (componente instanceof CheckBox) {
            ((CheckBox) componente).setSelected(false);
        }
    }

    private static void clear(Field[] atributos, Object objeto) {
        try {
            for (Field atributo : atributos) {
                if (atributo.isAnnotationPresent(FXML.class)) {
                    if (!atributo.isAnnotationPresent(NotClear.class)) {
                        atributo.setAccessible(true);
                        Object componente = atributo.get(objeto);
                        FunctionAnnotations.clearField(componente);
                    }
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException ex) {
            System.err.println("Houve um erro ao tentar limpar o componente. Exceção: " + ex);
            Logger.getLogger(FunctionAnnotations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Faz a limpeza de componentes que possue a anotação FXML ou ClearFields.
     * Obs: funciona somente se a classe tiver a anotação ClearFields.
     * Para que um campo não seja limpo automáticamente utilize a anotação NotClear neste campo.
     * @see NotClear
     * @see ClearFields
     * @param objeto Classe de controller.
     */
    public static void clearFieldsWithAnnotations(Object objeto) {
        if (objeto.getClass().isAnnotationPresent(ClearFields.class)) {
            Field[] atributos = objeto.getClass().getDeclaredFields();
            FunctionAnnotations.clear(atributos, objeto);
        }

    }
}
