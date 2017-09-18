package br.jeanderson.componentes;

import br.jeanderson.control.ControlWindow;

public class EasyFXFunctions {

	/**
	 * Após o construção(feito a instancia do objeto) ele será passado por parâmetro.
	 * Ideal para caso onde você queria recuperar a instancia de seu objeto, dentro da própria classe de controller.
	 * @param control
	 */
	public void afterConstruct(ControlWindow<?> control) {}
	/**
	 * Método é chamado após a exibição da Janela.
	 */
	public void afterShow() {}
}
