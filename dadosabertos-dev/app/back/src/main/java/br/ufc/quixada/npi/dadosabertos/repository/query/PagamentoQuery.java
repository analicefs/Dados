package br.ufc.quixada.npi.dadosabertos.repository.query;

import org.springframework.stereotype.Component;

@Component
public class PagamentoQuery {
	
	private PagamentoQuery() {}
	
	private static final String SELECT_SUP_NOME_COD_VALOR = "SELECT os.nome_orgao_superior as nome, os.cod_orgao_superior as cod, SUM(valor_pago) as total ";
	private static final String SELECT_SUB_NOME_COD_VALOR = "SELECT ob.nome_orgao_subordinado as nome, ob.cod_orgao_subordinado as cod, SUM(valor_pago) as total ";
	private static final String SELECT_PROGRAMA_NOME_COD_VALOR = "SELECT pr.nome_programa as nome, pr.cod_programa as cod, SUM(p.valor_pago) as total ";
	private static final String SELECT_ACAO_NOME_COD_VALOR = "SELECT a.nome_acao as nome, a.cod_acao as cod, SUM(p.valor_pago) as total ";
	private static final String SELECT_ELEMENTO_NOME_COD_VALOR = "SELECT el.nome_elemento_despesa as nome, el.cod_elemento_despesa as cod, SUM(p.valor_pago) as total ";

	private static final String FROM_PAGAMENTO = " FROM pagamentos.pagamento as p ";
	private static final String FROM_PAGAMENTO_CODIGOS = " FROM pagamentos.pagamento_codigos as p ";
	
	private static final String WHERE_DATA = "WHERE data >= :data_inicial AND data <= :data_final ";
	private static final String AND_COD_SUP = "AND p.cod_orgao_superior = os.cod_orgao_superior ";
	private static final String AND_COD_SUB = "AND p.cod_orgao_subordinado = ob.cod_orgao_subordinado ";
	private static final String AND_COD_PROGRAMA = "AND p.cod_programa = pr.cod_programa ";
	private static final String AND_COD_ACAO = "AND p.cod_acao = a.cod_acao ";
	private static final String AND_COD_ELEMENTO = "AND p.cod_elemento_despesa = el.cod_elemento_despesa ";
	
	private static final String GROUP_BY_SUP_COD = "GROUP BY os.cod_orgao_superior ";
	private static final String GROUP_BY_SUB_COD = "GROUP BY ob.cod_orgao_subordinado ";
	private static final String GROUP_BY_PROGRAMA_COD = "GROUP BY pr.cod_programa ";
	private static final String GROUP_BY_ACAO_COD = "GROUP BY a.cod_acao ";
	private static final String GROUP_BY_ELEMENTO_COD = "GROUP BY el.cod_elemento_despesa ";

	private static final String ORDER_BY_TOTAL_DESC = "ORDER BY total DESC ";	
	private static final String LIMIT = " LIMIT :limit";	
	
	public static final String ORGAO_SUPERIOR = SELECT_SUP_NOME_COD_VALOR
			+ FROM_PAGAMENTO + ", pagamentos.orgao_superior as os "
			+ WHERE_DATA
			+ AND_COD_SUP
			+ GROUP_BY_SUP_COD
			+ ORDER_BY_TOTAL_DESC
			+ LIMIT;
	
	public static final String ORGAO_SUBORDINADO = SELECT_SUB_NOME_COD_VALOR
			+ FROM_PAGAMENTO + ", pagamentos.orgao_subordinado as ob "
			+ WHERE_DATA
			+ AND_COD_SUB
			+ GROUP_BY_SUB_COD
			+ ORDER_BY_TOTAL_DESC
			+ LIMIT;			
	
	public static final String PROGRAMA = SELECT_PROGRAMA_NOME_COD_VALOR
			+ FROM_PAGAMENTO + ", pagamentos.programa as pr "
			+ WHERE_DATA
			+ AND_COD_PROGRAMA
			+ GROUP_BY_PROGRAMA_COD
			+ ORDER_BY_TOTAL_DESC
			+ LIMIT;
	
	public static final String ACAO =  SELECT_ACAO_NOME_COD_VALOR
			+ FROM_PAGAMENTO + ", pagamentos.acao as a "
			+ WHERE_DATA
			+ AND_COD_ACAO
			+ GROUP_BY_ACAO_COD
			+ ORDER_BY_TOTAL_DESC
			+ LIMIT;
	
	public static final String ELEMENTO_DESPESA = SELECT_ELEMENTO_NOME_COD_VALOR
			+ FROM_PAGAMENTO + ", pagamentos.elemento_despesa as el "
			+ WHERE_DATA
			+ AND_COD_ELEMENTO
			+ GROUP_BY_ELEMENTO_COD
			+ ORDER_BY_TOTAL_DESC
			+ LIMIT;
	
	public static final String ORGAO_SUPERIOR_GET_ALL_NAMES = "SELECT cod_orgao_superior as cod, nome_orgao_superior as nome"
			+ " FROM pagamentos.orgao_superior";
	
	public static final String ORGAO_SUBORDINADO_GET_ALL_NAMES = "SELECT nome_orgao_subordinado as nome, cod_orgao_subordinado as cod "
			+ " FROM pagamentos.orgao_subordinado";
	
	public static final String PROGRAMA_GET_ALL_NAMES = "SELECT nome_programa as nome, cod_programa as cod "
			+ " FROM pagamentos.programa";
	
	public static final String ACAO_GET_ALL_NAMES = "SELECT nome_acao as nome, cod_acao as cod "
			+ " FROM pagamentos.acao";
	
	public static final String ELEMENTO_DESPESA_GET_ALL_NAMES = "SELECT nome_elemento_despesa as nome, cod_elemento_despesa as cod "
			+ " FROM pagamentos.elemento_despesa";	
	
	public static final String CONSULTA_GERAL_DETALHEMENTO = " SUM(p.valor_pago) as total "
			+ FROM_PAGAMENTO
			+ WHERE_DATA;
		
	public static String queryGeralDetalhamento(Integer orgaoSuperior, Integer orgaoSubordinado, Integer programa, String acao, 
			Integer elementoDespesa, String groupBy){
		String strQuery = CONSULTA_GERAL_DETALHEMENTO;
		String selectDistinct = "SELECT DISTINCT cod_";
		
		if(orgaoSuperior != null){
			strQuery += " AND cod_orgao_superior = :cod_org_sup ";
		}
		if(orgaoSubordinado != null){
			strQuery += " AND cod_orgao_subordinado = :cod_org_sub ";
		}
		if(programa != null){
			strQuery += " AND cod_programa = :cod_programa ";
		}
		if(acao != null){
			strQuery += " AND cod_acao = :cod_acao ";
		}
		if(elementoDespesa != null){
			strQuery += " AND cod_elemento_despesa = :cod_elemento_despesa ";
		}
		
		// Group By
		if(groupBy.equals("ORGAO_SUPERIOR")){
			strQuery = selectDistinct + "orgao_superior as cod, " + strQuery + "GROUP BY cod_orgao_superior ";
		}else if(groupBy.equals("ORGAO_SUBORDINADO")){
			strQuery = selectDistinct + "orgao_subordinado as cod, " + strQuery + "GROUP BY cod_orgao_subordinado ";
		}else if(groupBy.equals("PROGRAMA")){
			strQuery = selectDistinct + "programa as cod, " + strQuery + "GROUP BY cod_programa ";
		}else if(groupBy.equals("ACAO")){
			strQuery = selectDistinct + "acao as cod, " + strQuery + "GROUP BY cod_acao ";
		}else if(groupBy.equals("ELEMENTO_DESPESA")){
			strQuery = selectDistinct + "elemento_despesa as cod, " + strQuery + "GROUP BY cod_elemento_despesa ";
		}
		strQuery += " HAVING SUM(p.valor_pago) > 0 " + ORDER_BY_TOTAL_DESC;
		return strQuery;
	}

	public static String queryGeralAutocompleteNomes(Integer orgaoSuperior, Integer orgaoSubordinado, Integer programa, String acao, String retorno){
		String selectDistinct = "SELECT DISTINCT ";
		String whereOrgSup = " WHERE (:cod_org_sup is NULL OR p.cod_orgao_superior = :cod_org_sup) ";
		String whereOrgSub = " AND (:cod_org_sub is NULL OR p.cod_orgao_subordinado = :cod_org_sub) ";
		String wherePrograma = " AND (:cod_programa is NULL OR p.cod_programa = :cod_programa) ";
		String whereAcao = " AND (:cod_acao is NULL OR p.cod_acao = :cod_acao) ";
		
		if(retorno.equals("ORGAO_SUPERIOR")){
			return ORGAO_SUPERIOR_GET_ALL_NAMES;
		}else if(retorno.equals("ORGAO_SUBORDINADO")){
			if(orgaoSuperior == null){
				return ORGAO_SUBORDINADO_GET_ALL_NAMES;
			}else{
				return selectDistinct 
						+ "p.cod_orgao_subordinado as cod, ob.nome_orgao_subordinado as nome "
						+ FROM_PAGAMENTO_CODIGOS + ", pagamentos.orgao_subordinado as ob"
						+ whereOrgSup + AND_COD_SUB;
			}
		}else if(retorno.equals("PROGRAMA")){
			if(orgaoSuperior == null && orgaoSubordinado == null){
				return PROGRAMA_GET_ALL_NAMES;
			}else{
				return selectDistinct 
						+ "p.cod_programa as cod, pr.nome_programa as nome "
						+ FROM_PAGAMENTO_CODIGOS + ", pagamentos.programa as pr"
						+ whereOrgSup + whereOrgSub + AND_COD_PROGRAMA;
			}
		}else if(retorno.equals("ACAO")){
			if(orgaoSuperior == null && orgaoSubordinado == null && programa == null){
				return ACAO_GET_ALL_NAMES;
			}else{
				return selectDistinct 
						+ "p.cod_acao as cod, a.nome_acao as nome "
						+ FROM_PAGAMENTO_CODIGOS + ", pagamentos.acao as a"
						+ whereOrgSup + whereOrgSub + wherePrograma + AND_COD_ACAO;
			}
		}else if(retorno.equals("ELEMENTO_DESPESA")){
			if(orgaoSuperior == null && orgaoSubordinado == null && programa == null && acao == null){
				return ELEMENTO_DESPESA_GET_ALL_NAMES;
			}else{
				return selectDistinct 
						+ "p.cod_elemento_despesa as cod, el.nome_elemento_despesa as nome "
						+ FROM_PAGAMENTO_CODIGOS + ", pagamentos.elemento_despesa as el"
						+ whereOrgSup + whereOrgSub + wherePrograma + whereAcao + AND_COD_ELEMENTO;
			}
		}
		return null;
	}
}