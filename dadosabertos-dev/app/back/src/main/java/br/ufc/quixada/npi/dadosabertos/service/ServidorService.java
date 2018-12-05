package br.ufc.quixada.npi.dadosabertos.service;

import java.util.List;
import java.util.Map;

public interface ServidorService {
	
	public List<Map<String, Object>> buscarOrgaoSuperiorCargo(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal);

	public List<Map<String, Object>> buscarOrgaoSuperior(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal);

	public List<Map<String, Object>> buscarOrgaoSuperiorFuncao(int top, int mesInicial, int anoIncial, int mesFinal, int anoFinal);

	public List<Map<String, Object>> buscarOrgaoSubordinadoCargo(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal);

	public List<Map<String, Object>> buscarOrgaoSubordinado(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal);

	public List<Map<String, Object>> buscarOrgaoSubordinadoFuncao(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal);

	public List<Map<String, Object>> getAllOrgaoSuperior();
	
	public List<Map<String, Object>> getAllOrgaoSubordinado();
	
	public List<Map<String, Object>> buscarGeralDetalhamento(String orgaoSuperior, String orgaoSubordinado, int mesInicial, int anoInicial, int mesFinal, int anoFinal, String groupBy);

	public List<Map<String, Object>> buscarGeralAutocompleteNomes(String orgaoSuperior);
}
