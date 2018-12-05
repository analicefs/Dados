package br.ufc.quixada.npi.dadosabertos.service;

import java.util.List;
import java.util.Map;


public interface PagamentoService {
	
	public List<Map<String, Object>> buscarGastosOrgaosSuperiores(int top, int mesInicial, int anoIncial, int mesFinal, int anoFinal);
		
	public List<Map<String, Object>> buscarGastosOrgaoSurbordinado(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal);
	
	public List<Map<String, Object>> buscarGastosAcao(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal);
	
	public List<Map<String, Object>> buscarGastosPrograma(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal);
	
	public List<Map<String, Object>> buscarGastosElementoDespesa(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal);
	
	public List<Map<String, Object>> getAllOrgaoSuperior();
	
	public List<Map<String, Object>> getAllOrgaoSubordinado();
	
	public List<Map<String, Object>> getAllAcao();
	
	public List<Map<String, Object>> getAllPrograma();
	
	public List<Map<String, Object>> getAllElementoDespesa();
	
	public List<Map<String, Object>> buscarGeralDetalhamento(Integer orgaoSuperior, Integer orgaoSubordinado, Integer programa, String acao, Integer elementoDespesa, String groupBy, int mesInicial, int anoInicial, int mesFinal, int anoFinal);

	public List<Map<String, Object>> buscarGeralAutocompleteNomes(Integer orgaoSuperior, Integer orgaoSubordinado, Integer programa, String acao, String retorno);
}
