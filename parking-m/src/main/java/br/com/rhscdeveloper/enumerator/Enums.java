package br.com.rhscdeveloper.enumerator;

public class Enums {

	public enum Booleano {
		
		NAO(0, "Não"),
		SIM(1, "SIM");
		
		private Integer id;
		private String descricao;
		
		private Booleano(Integer id, String descricao) {
			this.id = id;
			this.descricao = descricao;
		}

		public Integer getId() {
			return id;
		}

		public String getDescricao() {
			return descricao;
		}
	}
	
	public enum Situacao {
		ATIVO(2, "Ativo"),
		INATIVO(3, "Inativo"),
		CADASTRADO(4, "Cadastrado");
		
		private Integer id;
		private String descricao;
		
		private Situacao(Integer id, String descricao) {
			this.id = id;
			this.descricao = descricao;
		}

		public Integer getId() {
			return id;
		}

		public String getDescricao() {
			return descricao;
		}
	}
	
	public enum TipoCobranca {
		DINHEIRO(4, "Dinheiro"),
		DEBITO(5, "Débito"),
		CREDITO(6, "Crédito"),
		INDIFERENTE(7, "Indiferente");
		
		private Integer id;
		private String descricao;
		
		private TipoCobranca(Integer id, String descricao) {
			this.id = id;
			this.descricao = descricao;
		}

		public Integer getId() {
			return id;
		}

		public String getDescricao() {
			return descricao;
		}
	}
	
	public enum TipoMovimento {
		DIAUTIL(8, "Dia Util"),
		FINALSEMANA(9, "Final de Semana"),
		MENSALISTA(10, "Mensalista"),
		INDIFERENTE(11, "Indiferente");
		
		private Integer id;
		private String descricao;
		
		private TipoMovimento(Integer id, String descricao) {
			this.id = id;
			this.descricao = descricao;
		}

		public Integer getId() {
			return id;
		}

		public String getDescricao() {
			return descricao;
		}
	}
	
	public enum SituacaoMovimento {
		
		ABERTO(12, "Aberto"),
		ENCERRADO(13, "Encerrado");
		
		private Integer id;
		private String descricao;
		
		private SituacaoMovimento(Integer id, String descricao) {
			this.id = id;
			this.descricao = descricao;
		}

		public Integer getId() {
			return id;
		}

		public String getDescricao() {
			return descricao;
		}
	}
}
