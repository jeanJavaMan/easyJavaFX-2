package br.jeanderson.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.jeanderson.enums.Configuracoes;

public class LogToFile {

	/**
	 * Salva o erro em um arquivo log.txt com alguns detalhes como hora e dia do
	 * ocorrido e informações referente o erro.
	 * 
	 * @param classeErro
	 *            Classe onde ocorreu o erro
	 * @param dirLog
	 *            Diretório do arquivo log.txt
	 * @param metodoOndeOcorreu
	 *            Método onde ocorreu o erro.
	 * @param exception
	 *            Exceção lançada
	 */
	public static void registerLog(Class classeErro, String dirLog, String metodoOndeOcorreu, String exception) {
		try {
			String dataDoOcorrido = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'ás' HH:mm"));
			List<String> msg = new ArrayList<>();
			msg.add("Ocorreu uma exceção na data: " + dataDoOcorrido);
			msg.add("Na Classe: " + classeErro.getName());
			msg.add("Motivo: " + exception);
			msg.add("Onde: " + metodoOndeOcorreu);
			msg.add("");

			File arquivo = new File(dirLog + "log.txt");
			if (!arquivo.exists()) {
				arquivo.createNewFile();
			}

			Files.write(Paths.get(arquivo.getPath()), msg, StandardOpenOption.APPEND);
		} catch (IOException ex1) {
			Logger.getLogger(LogToFile.class.getName()).log(Level.SEVERE, null, ex1);
		}
	}

	/**
	 * Salva o erro em um arquivo log.txt com alguns detalhes como hora e dia do
	 * ocorrido e informações referente o erro. Obs: utilizando este método vc deve
	 * definir uma pasta padrão do log.txt através da Classe EasyJavaFX
	 * 
	 * @see EasyJavaFX
	 * @param classeErro
	 *            Classe onde ocorreu o erro.
	 * @param metodoOndeOcorreu
	 *            Método onde ocorreu o erro.
	 * @param exception
	 *            Exceção lançada.
	 */
	public static void registerLog(Class classeErro, String metodoOndeOcorreu, Exception exception) {
		String dirLog = "";
		if (Configuracoes.DEFAULT_PATH.getPathLog().isEmpty()) {
			dirLog = Configuracoes.DEFAULT_PATH.getPathLog();			
		} else {
			System.err.println("INFO: Você não definiu uma pasta padrão para salvar o log.txt, por padrão será salvo na pasta inicial! Caso queria definir utilize os métodos da classe EasyJavaFX.");
		}
		LogToFile.registerLog(classeErro, dirLog, metodoOndeOcorreu, exception.getMessage());
	}

	/**
	 * Salva o erro em um arquivo log.txt com alguns detalhes como hora e dia do
	 * ocorrido e informações referente o erro. Obs: utilizando este método vc deve
	 * definir uma pasta padrão do log.txt através da Classe EasyJavaFX
	 * 
	 * @see EasyJavaFX
	 * @param classeErro
	 *            Classe onde ocorreu o erro.
	 * @param metodoOndeOcorreu
	 *            Método onde ocorreu o erro.
	 * @param exception
	 *            Exceção lançada.
	 */
	public static void registerLog(Class classeErro, String metodoOndeOcorreu, String exception) {
		String dirLog = "";
		if (Configuracoes.DEFAULT_PATH.getPathLog().isEmpty()) {
			dirLog = Configuracoes.DEFAULT_PATH.getPathLog();			
		} else {
			System.err.println("INFO: Você não definiu uma pasta padrão para salvar o log.txt, por padrão será salvo na pasta inicial! Caso queria definir utilize os métodos da classe EasyJavaFX.");
		}
		LogToFile.registerLog(classeErro, dirLog, metodoOndeOcorreu, exception);
	}
}
