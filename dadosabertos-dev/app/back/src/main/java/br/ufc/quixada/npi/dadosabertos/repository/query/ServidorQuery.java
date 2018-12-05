package br.ufc.quixada.npi.dadosabertos.repository.query;

import org.springframework.stereotype.Component;

@Component
public class ServidorQuery {
	
	private ServidorQuery() {}
	
	private static final String SELECT_SUP_ORGAO = "SELECT orgsup_exercicio as nome, cod_orgsup_exercicio as cod, SUM(remuneracao_basica_bruta_brl) as total ";
	private static final String SELECT_SUB_ORGAO = "SELECT org_exercicio as nome, cod_org_exercicio as cod, SUM(remuneracao_basica_bruta_brl) as total ";
	
	private static final String FROM_FATO = " FROM new_servidores.fato_cargo_funcao_remuneracao ";
	private static final String FROM_CADASTRO = " FROM new_servidores.cadastro ";
	
	private static final String WHERE_DATA = " WHERE data >= :data_inicial AND data <= :data_final ";
	private static final String TIPO_CARGO = " AND tipo = 'C' ";
	private static final String TIPO_FUNCAO = "AND tipo = 'F' " ;
	private static final String AND_COD_SUP = " AND cod_orgsup_exercicio = :codigo_org_sup ";
	private static final String AND_COD_SUB = " AND cod_org_exercicio = :codigo_org_sub ";
	
	private static final String GROUP_BY_SUP_COD = " GROUP BY cod_orgsup_exercicio, orgsup_exercicio ";
	private static final String GROUP_BY_SUB_COD = " GROUP BY org_exercicio, cod_org_exercicio ";
	private static final String GROUP_BY_SUP = " GROUP BY cod_orgsup_exercicio ";
	private static final String GROUP_BY_SUB = " GROUP BY cod_org_exercicio ";
	
	private static final String ORDER_BY_TOTAL = " ORDER BY total ";
	private static final String DESC_LIMIT = " DESC LIMIT :limit ";
	
	public static final String ORGAO_SUPERIOR_CARGO = SELECT_SUP_ORGAO
			+ FROM_FATO
			+ WHERE_DATA
			+ TIPO_CARGO
			+ GROUP_BY_SUP_COD
			+ ORDER_BY_TOTAL
			+ DESC_LIMIT;
	
	public static final String ORGAO_SUPERIOR_FUNCAO = SELECT_SUP_ORGAO
			+ FROM_FATO
			+ WHERE_DATA
			+ TIPO_FUNCAO
			+ GROUP_BY_SUP_COD
			+ ORDER_BY_TOTAL
			+ DESC_LIMIT;
	
	public static final String ORGAO_SUPERIOR = SELECT_SUP_ORGAO
			+ FROM_FATO
			+ WHERE_DATA
			+ GROUP_BY_SUP_COD
			+ ORDER_BY_TOTAL
			+ DESC_LIMIT;
	
	public static final String ORGAO_SUBORDINADO_CARGO = SELECT_SUB_ORGAO
			+ FROM_FATO
			+ WHERE_DATA
			+ TIPO_CARGO
			+ GROUP_BY_SUB_COD
			+ ORDER_BY_TOTAL
			+ DESC_LIMIT;

	public static final String ORGAO_SUBORDINADO_FUNCAO = SELECT_SUB_ORGAO
			+ FROM_FATO
			+ WHERE_DATA
			+ TIPO_FUNCAO
			+ GROUP_BY_SUB_COD
			+ ORDER_BY_TOTAL
			+ DESC_LIMIT;
	
	public static final String ORGAO_SUBORDINADO = SELECT_SUB_ORGAO
			+ FROM_FATO
			+ WHERE_DATA
			+ GROUP_BY_SUB_COD
			+ ORDER_BY_TOTAL
			+ DESC_LIMIT;
	
	public static final String ORGAO_SUPERIOR_GET_ALL_NAMES = "SELECT orgsup_exercicio as nome, cod_orgsup_exercicio as cod "
			+ FROM_CADASTRO
			+ GROUP_BY_SUP_COD;
	
	public static final String ORGAO_SUBORDINADO_GET_ALL_NAMES = "SELECT org_exercicio as nome, cod_org_exercicio as cod "
			+ FROM_CADASTRO
			+ GROUP_BY_SUB_COD;
	
	public static final String CONSULTA_GERAL_DETALHEMENTO = " SUM(remuneracao_basica_bruta_brl) as total "
			+ FROM_FATO
			+ WHERE_DATA;
	
	public static String queryGeralDetalhamento(String orgaoSuperior, String orgaoSubordinado, String groupBy){
		String strQuery = CONSULTA_GERAL_DETALHEMENTO;

		if(orgaoSuperior != null){
			strQuery += AND_COD_SUP;
		}
		if(orgaoSubordinado != null){
			strQuery += AND_COD_SUB;
		}
		
		if(groupBy.equals("ORGAO_SUPERIOR")){
			strQuery = "SELECT DISTINCT cod_orgsup_exercicio as cod, " + strQuery + GROUP_BY_SUP;
		}else if(groupBy.equals("ORGAO_SUBORDINADO")){
			strQuery = "SELECT DISTINCT cod_org_exercicio as cod, " + strQuery + GROUP_BY_SUB;
		}
		return strQuery;
	}
	
	public static String queryGeralAutocompleteNomes(String orgaoSuperior){
		if(orgaoSuperior == null){
			return ORGAO_SUPERIOR_GET_ALL_NAMES;
		}else{
			return "SELECT org_exercicio as nome, cod_org_exercicio as cod "
					+ FROM_FATO
					+ " WHERE cod_orgsup_exercicio = :codigo "
					+ GROUP_BY_SUB_COD;
		}
	}
}
