package br.jeanderson.control;

import java.io.IOException;
import java.lang.reflect.Field;

import br.jeanderson.annotations.AutoCompleteComboBox;
import br.jeanderson.annotations.ClearFields;
import br.jeanderson.annotations.DefineConfiguration;
import br.jeanderson.annotations.MaskFormatter;
import br.jeanderson.componentes.EasyFXFunctions;
import br.jeanderson.enums.Configuracoes;
import br.jeanderson.util.FunctionAnnotations;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ControlWindow<T> {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private FXMLLoader fxmlLoader;
	private T classController;
	private DefineConfiguration defineConfiguration;
	private ControlWindow<?> windowOwner;

	/**
	 * Faz toda a construção da Janela baseado na anotação DefineConfiguration presente na classe de controller.
	 * @see DefineConfiguration
	 * @param classController Classe de Controller da Janela
	 */
	public ControlWindow(Class<T> classController) {
		if (classController.isAnnotationPresent(DefineConfiguration.class)) {
			this.defineConfiguration = classController.getAnnotation(DefineConfiguration.class);
			this.builder();
		} else {
			System.err.println(
					"a classe passada: " + classController.getName() + " não possui a anotação @DefineConfiguration.");
		}
	}

	private void builder() {
		if (carregarComponentes()) {
			this.scene = new Scene(this.root);
			this.stage = new Stage(defineConfiguration.stageStyle());
			this.configurarStage();
			stage.setScene(scene);
			if (classController instanceof EasyFXFunctions) {
				((EasyFXFunctions) classController).afterConstruct(this);
			}
			this.inicializarComponentesComAnotacoes();
		}
	}

	private void inicializarComponentesComAnotacoes() {
		br.jeanderson.util.MaskFormatter mascara = new br.jeanderson.util.MaskFormatter();
		Field[] fields = this.classController.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(MaskFormatter.class)) {
				MaskFormatter mascaraAnotacao = field.getDeclaredAnnotation(MaskFormatter.class);
				field.setAccessible(true);
				try {
					mascara.addComponente(field.get(classController), mascaraAnotacao.type(),
							mascaraAnotacao.showMask());
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
					System.err.println("Não foi possível adicionar a máscara ao componente " + field.getName()
							+ " devido a um erro: " + e.getMessage());
				}
			} else if (field.isAnnotationPresent(AutoCompleteComboBox.class)) {
				field.setAccessible(true);
				try {
					Object fieldObjeto = field.get(classController);
					if (fieldObjeto instanceof ComboBox<?>) {
						new br.jeanderson.util.AutoCompleteComboBox((ComboBox)fieldObjeto);
					} else {
						System.err.println("O componente informado com anotação AutoCompleteComboBox com o nome "
								+ field.getName() + " não parece ser um comboBox");
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
					System.err.println("Não foi possível utilizar o autoCompleteComboBox no componente "
							+ field.getName() + " devido a um erro: " + e.getMessage());
				}
			}
		}
	}

	private boolean carregarComponentes() {
		this.fxmlLoader = new FXMLLoader(getClass().getResource(this.prepararURLdoFXML()));
		try {
			this.root = fxmlLoader.load();
			this.classController = fxmlLoader.getController();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private void configurarStage() {
		this.stage.setTitle(defineConfiguration.title());
		this.stage.setFullScreen(defineConfiguration.fullscreen());
		stage.setMaximized(defineConfiguration.maximized());
		stage.setResizable(defineConfiguration.resizable());
		stage.getIcons().add(new Image(defineConfiguration.url_icon()));
	}

	private String prepararURLdoFXML() {
		String urlInformadaNaClasse = defineConfiguration.url_fxml();
		if (!Configuracoes.DEFAULT_PATH.getPathFxmls().isEmpty()) {
			if (!urlInformadaNaClasse.contains("/")) {
				String urlPronta = urlInformadaNaClasse.contains(".") ? urlInformadaNaClasse
						: urlInformadaNaClasse + ".fxml";
				return Configuracoes.DEFAULT_PATH.getPathFxmls() + urlPronta;
			} else {
				return urlInformadaNaClasse;
			}
		} else {
			return urlInformadaNaClasse;
		}
	}

	/**
	 * Faz a exibição da Janela.
	 */
	public void show() {
		this.limparCampos();
		this.stage.show();
		this.stage.requestFocus();
		this.root.requestFocus();
		callAfterShow();
	}

	private void callAfterShow() {
		if (classController instanceof EasyFXFunctions) {
			((EasyFXFunctions) classController).afterShow();
		}
	}
	
	private void limparCampos() {
		if(classController.getClass().isAnnotationPresent(ClearFields.class)) {
			FunctionAnnotations.clearFieldsWithAnnotations(classController);
		}
	}

	/**
	 * Faz a exibição e aguarda.
	 */
	public void showAndWait() {
		this.limparCampos();
		this.stage.showAndWait();
		this.stage.requestFocus();
		this.root.requestFocus();
		callAfterShow();
	}

	/**
	 * Faz a exibição da Janela, tendo dependência de uma janela pai.
	 * @param windowFather Janela Pai
	 */
	public void show(ControlWindow<?> windowFather) {
		preparaOwnerParaStage(windowFather);
		this.show();
	}

	/**
	 * Faz a exibição e aguarda. tendo dependência de uma janela pai.
	 * @param windowFather Janela Pai
	 */
	public void showAndWait(ControlWindow<?> windowFather) {
		this.preparaOwnerParaStage(windowFather);
		this.showAndWait();
	}

	/**
	 * Fecha a Janela.
	 */
	public void close() {
		this.stage.close();
	}

	private void preparaOwnerParaStage(ControlWindow<?> windowFather) {
		if (this.windowOwner == null) {
			this.windowOwner = windowFather;
			this.stage.initOwner(windowFather.stage);
		} else if (!this.windowOwner.equals(windowFather)) {
			this.stage = new Stage(defineConfiguration.stageStyle());
			this.configurarStage();
			this.stage.setScene(scene);
			this.stage.initOwner(windowFather.stage);
			this.windowOwner = windowFather;
		}
	}

	/**
	 * Retorna o Stage referente a janela.
	 * @return Stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * Retorna a Scene referente a janela.
	 * @return
	 */
	public Scene getScene() {
		return scene;
	}

	/**
	 * Retorna o Parent referente a janela.
	 * @return
	 */
	public Parent getRoot() {
		return root;
	}

	/**
	 * Retorna o FXMLLoad usado para carregar o arquivo FMXL.
	 * @return
	 */
	public FXMLLoader getFxmlLoader() {
		return fxmlLoader;
	}

	/**
	 * Retorna a classe de controller da janela.
	 * @return
	 */
	public T getController() {
		return classController;
	}

	/**
	 * Retorna as configurações utilizadas para a construção da janela.
	 * @return
	 */
	public DefineConfiguration getDefineConfiguration() {
		return defineConfiguration;
	}

}
