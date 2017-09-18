package br.jeanderson.util;

import br.jeanderson.enums.Configuracoes;

public class EasyJavaFX {

	/**
	 * Define uma pasta comum para todos os arquivos FXML, utilizando este método, bastará apenas
	 * informar o nome do seu arquivo fxml na anotação defineConfiguration.
	 * @param defaultPath Pasta padrão dos arquivos fxml.
	 */
	public static void definePathFXML(String defaultPath) {
		Configuracoes.DEFAULT_PATH.setPathFxmls(defaultPath);
	}
	
	/**
	 * Define uma pasta para salvar arquivos logs gerado pelo LogSave.
	 * @param defaultPathLog pasta padrão do log.
	 */
	public static void definePathLog(String defaultPathLog) {
		Configuracoes.DEFAULT_PATH.setPathLog(defaultPathLog);
	}
	
	public EasyJavaFX defaultPathFXML(String defaultPathFXML) {
		Configuracoes.DEFAULT_PATH.setPathFxmls(defaultPathFXML);
		return this;
	}
	
	public EasyJavaFX defaultPathLog(String defaultPathLog) {
		Configuracoes.DEFAULT_PATH.setPathLog(defaultPathLog);
		return this;
	}
}
