package br.jeanderson.enums;

public enum Configuracoes {
	DEFAULT_PATH("","");
	String pathFxmls;
	String pathLog;

	private Configuracoes(String path, String pathLog) {
		this.pathFxmls = path;
		this.pathLog = pathLog;
	}

	public String getPathFxmls() {
		return pathFxmls;
	}

	public void setPathFxmls(String pathFxmls) {
		this.pathFxmls = pathFxmls;
	}

	public String getPathLog() {
		return pathLog;
	}

	public void setPathLog(String pathLog) {
		this.pathLog = pathLog;
	}
	
}
