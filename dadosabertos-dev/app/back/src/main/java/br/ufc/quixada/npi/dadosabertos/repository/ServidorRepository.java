package br.ufc.quixada.npi.dadosabertos.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.npi.dadosabertos.repository.query.ServidorQuery;

@SuppressWarnings("deprecation")
@Repository
@Transactional
public class ServidorRepository {

	@PersistenceContext
	private EntityManager manager;
	
	private static final String DATA_INICIAL = "data_inicial";
	private static final String DATA_FINAL = "data_final";
	private static final String LIMIT = "limit";
	
	public List<Map<String, Object>> buscarOrgaoSuperiorCargo(int top, int anoMesInicial, int anoMesFinal) {
		Query query = manager.createNativeQuery(ServidorQuery.ORGAO_SUPERIOR_CARGO)
				.setParameter(DATA_INICIAL, anoMesInicial)
				.setParameter(DATA_FINAL, anoMesFinal)
				.setParameter(LIMIT, top);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return  query.getResultList();
	}

	public List<Map<String, Object>> buscarOrgaoSuperior(int top, int anoMesInicial, int anoMesFinal) {
		Query query = manager.createNativeQuery(ServidorQuery.ORGAO_SUPERIOR)
				.setParameter(DATA_INICIAL, anoMesInicial)
				.setParameter(DATA_FINAL, anoMesFinal)
				.setParameter(LIMIT, top);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return  query.getResultList();
	}
	
	public List<Map<String, Object>> buscarOrgaoSuperiorFuncao(int top, int anoMesInicial, int anoMesFinal) {
		Query query = manager.createNativeQuery(ServidorQuery.ORGAO_SUPERIOR_FUNCAO)
				.setParameter(DATA_INICIAL, anoMesInicial)
				.setParameter(DATA_FINAL, anoMesFinal)
				.setParameter(LIMIT, top);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}
	
	public List<Map<String, Object>> buscarOrgaoSubordinadoCargo(int top, int anoMesInicial, int anoMesFinal) {
		Query query = manager.createNativeQuery(ServidorQuery.ORGAO_SUBORDINADO_CARGO)
				.setParameter(DATA_INICIAL, anoMesInicial)
				.setParameter(DATA_FINAL, anoMesFinal)
				.setParameter(LIMIT, top);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}

	public List<Map<String, Object>> buscarOrgaoSubordinado(int top, int anoMesInicial, int anoMesFinal) {
		Query query = manager.createNativeQuery(ServidorQuery.ORGAO_SUBORDINADO)
				.setParameter(DATA_INICIAL, anoMesInicial)
				.setParameter(DATA_FINAL, anoMesFinal)
				.setParameter(LIMIT, top);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return  query.getResultList();
	}
	
	public List<Map<String, Object>> buscarOrgaoSubordinadoFuncao(int top, int anoMesInicial, int anoMesFinal) {
		Query query = manager.createNativeQuery(ServidorQuery.ORGAO_SUBORDINADO_FUNCAO)
				.setParameter(DATA_INICIAL, anoMesInicial)
				.setParameter(DATA_FINAL, anoMesFinal)
				.setParameter(LIMIT, top);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return  query.getResultList();
	}
	
	public List<Map<String, Object>> getAllOrgaoSuperior(){
		Query query = manager.createNativeQuery(ServidorQuery.ORGAO_SUPERIOR_GET_ALL_NAMES);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}
	
	public List<Map<String, Object>> getAllOrgaoSubordinado(){
		Query query = manager.createNativeQuery(ServidorQuery.ORGAO_SUBORDINADO_GET_ALL_NAMES);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}
	
	public List<Map<String, Object>>  buscarGeralDetalhamento(String orgaoSuperior, String orgaoSubordinado, int anoMesInicial, int anoMesFinal, String groupBy){
		Query query = manager.createNativeQuery(ServidorQuery.queryGeralDetalhamento(orgaoSuperior, orgaoSubordinado, groupBy))
				.setParameter(DATA_INICIAL, anoMesInicial)
				.setParameter(DATA_FINAL, anoMesFinal);
		if(orgaoSuperior != null){
			query.setParameter("codigo_org_sup", orgaoSuperior);
		}
		if(orgaoSubordinado != null){
			query.setParameter("codigo_org_sub", orgaoSubordinado);
		}
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}

	public List<Map<String, Object>> buscarGeralAutocompleteNomes(String orgaoSuperior){
		Query query = manager.createNativeQuery(ServidorQuery.queryGeralAutocompleteNomes(orgaoSuperior));
		if(orgaoSuperior != null){
			query.setParameter("codigo", orgaoSuperior);
		}				
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}	
}
