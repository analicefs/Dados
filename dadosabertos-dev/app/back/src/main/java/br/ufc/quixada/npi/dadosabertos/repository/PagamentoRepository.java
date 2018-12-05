package br.ufc.quixada.npi.dadosabertos.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.npi.dadosabertos.repository.query.PagamentoQuery;

@SuppressWarnings("deprecation")
@Repository
@Transactional
public class PagamentoRepository {
	
	@PersistenceContext
	private EntityManager manager;
	
	private static final String DATA_INICIAL = "data_inicial";
	private static final String DATA_FINAL = "data_final";
	private static final String LIMIT = "limit";
	
	private static final String COD_ORG_SUP = "cod_org_sup";
	private static final String COD_ORG_SUB = "cod_org_sub";
	private static final String COD_PROGRAMA = "cod_programa";
	private static final String COD_ACAO = "cod_acao";
	
	public List<Map<String, Object>> buscarGastosOrgaosSuperiores(int top, int anoMesInicial, int anoMesFinal) {
		Query query = manager.createNativeQuery(PagamentoQuery.ORGAO_SUPERIOR)
				.setParameter(LIMIT, top)
				.setParameter(DATA_INICIAL, anoMesInicial)
				.setParameter(DATA_FINAL, anoMesFinal);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}

	public List<Map<String, Object>> buscarGastosOrgaoSurbordinado(int top, int anoMesInicial, int anoMesFinal) {
		Query query = manager.createNativeQuery(PagamentoQuery.ORGAO_SUBORDINADO)
				.setParameter(LIMIT, top)
				.setParameter(DATA_INICIAL, anoMesInicial)
				.setParameter(DATA_FINAL, anoMesFinal);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}
	
	public List<Map<String, Object>> buscarGastosAcao(int top, int anoMesInicial, int anoMesFinal) {
		Query query = manager.createNativeQuery(PagamentoQuery.ACAO)
				.setParameter(LIMIT, top)
				.setParameter(DATA_INICIAL, anoMesInicial)
				.setParameter(DATA_FINAL, anoMesFinal);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}

	public List<Map<String, Object>> buscarGastosPrograma(int top, int anoMesInicial, int anoMesFinal) {
		Query query = manager.createNativeQuery(PagamentoQuery.PROGRAMA)
				.setParameter(LIMIT, top)
				.setParameter(DATA_INICIAL, anoMesInicial)
				.setParameter(DATA_FINAL, anoMesFinal);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}
	
	public List<Map<String, Object>> buscarGastosElementoDespesa(int top, int anoMesInicial, int anoMesFinal) {
		Query query = manager.createNativeQuery(PagamentoQuery.ELEMENTO_DESPESA)
				.setParameter(LIMIT, top)
				.setParameter(DATA_INICIAL, anoMesInicial)
				.setParameter(DATA_FINAL, anoMesFinal);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}
	
	public List<Map<String, Object>>getAllOrgaoSuperior(){
		Query query = manager.createNativeQuery(PagamentoQuery.ORGAO_SUPERIOR_GET_ALL_NAMES);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}
	
	public List<Map<String, Object>> getAllOrgaoSubordinado(){
		Query query = manager.createNativeQuery(PagamentoQuery.ORGAO_SUBORDINADO_GET_ALL_NAMES);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}
	
	public List<Map<String, Object>> getAllAcao(){
		Query query = manager.createNativeQuery(PagamentoQuery.ACAO_GET_ALL_NAMES);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}
	
	public List<Map<String, Object>> getAllPrograma(){
		Query query = manager.createNativeQuery(PagamentoQuery.PROGRAMA_GET_ALL_NAMES);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}
	
	public List<Map<String, Object>> getAllElementoDespesa(){
		Query query = manager.createNativeQuery(PagamentoQuery.ELEMENTO_DESPESA_GET_ALL_NAMES);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}
	
	public List<Map<String, Object>> buscarGeralDetalhamento(Integer orgaoSuperior, Integer orgaoSubordinado, Integer programa, String acao, 
			Integer elementoDespesa, String groupBy, int anoMesInicial, int anoMesFinal){
		Query query = manager.createNativeQuery(PagamentoQuery.queryGeralDetalhamento(orgaoSuperior, orgaoSubordinado, programa, acao, elementoDespesa, groupBy))
				.setParameter(DATA_INICIAL, anoMesInicial)
				.setParameter(DATA_FINAL, anoMesFinal);
		if(orgaoSuperior != null){
			query.setParameter(COD_ORG_SUP, orgaoSuperior);
		}
		if(orgaoSubordinado != null){
			query.setParameter(COD_ORG_SUB, orgaoSubordinado);
		}
		if(programa != null){
			query.setParameter(COD_PROGRAMA, programa);
		}
		if(acao != null){
			query.setParameter(COD_ACAO, acao);
		}
		if(elementoDespesa != null){
			query.setParameter("cod_elemento_despesa", elementoDespesa);
		}
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}
	
	public List<Map<String, Object>> buscarGeralAutocompleteNomes(Integer orgaoSuperior, Integer orgaoSubordinado, Integer programa, String acao, String retorno){
		org.hibernate.query.Query<Map<String, Object>> query = (org.hibernate.query.Query<Map<String, Object>>) manager.createNativeQuery(PagamentoQuery.queryGeralAutocompleteNomes(orgaoSuperior, orgaoSubordinado, programa, acao, retorno));

		// Retorno representa o que será retornado na consulta, usa-se para obter quais os possíveis parametros
		if(orgaoSuperior == null && orgaoSubordinado == null && programa == null && acao == null){
			query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			return query.getResultList();
		}
		
		if(retorno.equals("ORGAO_SUBORDINADO")){
			query.setParameter(COD_ORG_SUP, orgaoSuperior, IntegerType.INSTANCE);
		}else if(retorno.equals("PROGRAMA")){
			query.setParameter(COD_ORG_SUP, orgaoSuperior, IntegerType.INSTANCE);
			query.setParameter(COD_ORG_SUB, orgaoSubordinado, IntegerType.INSTANCE);
		}else if(retorno.equals("ACAO")){
			query.setParameter(COD_ORG_SUP, orgaoSuperior, IntegerType.INSTANCE);
			query.setParameter(COD_ORG_SUB, orgaoSubordinado, IntegerType.INSTANCE);
			query.setParameter(COD_PROGRAMA, programa, IntegerType.INSTANCE);
		}else if(retorno.equals("ELEMENTO_DESPESA")){
			query.setParameter(COD_ORG_SUP, orgaoSuperior, IntegerType.INSTANCE);
			query.setParameter(COD_ORG_SUB, orgaoSubordinado, IntegerType.INSTANCE);
			query.setParameter(COD_PROGRAMA, programa, IntegerType.INSTANCE);
			query.setParameter(COD_ACAO, acao, StringType.INSTANCE);
		}
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}
}
