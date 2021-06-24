package br.com.project.util.all;

public enum StatusPersistencia {

	ERRO("Erro"),
	SUCESSO("Sucesso"),
	OBJETO_REFERENCIADO("Esse objeto n�o pode ser apagado por possuir refer�ncias ao mesmo.");
	
	private String name;
	
	private StatusPersistencia(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
