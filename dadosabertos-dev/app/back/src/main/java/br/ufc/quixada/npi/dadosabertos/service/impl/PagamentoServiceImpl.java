package br.ufc.quixada.npi.dadosabertos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.npi.dadosabertos.repository.PagamentoRepository;
import br.ufc.quixada.npi.dadosabertos.service.PagamentoService;
import br.ufc.quixada.npi.dadosabertos.util.DataUtils;


@Service
public class PagamentoServiceImpl implements PagamentoService {
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public List<Map<String, Object>>buscarGastosOrgaosSuperiores(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal) {
		int anoMesInicial = DataUtils.converterParaData(mesInicial, anoInicial);
		int anoMesFinal = DataUtils.converterParaData(mesFinal, anoFinal);
		return pagamentoRepository.buscarGastosOrgaosSuperiores(top, anoMesInicial, anoMesFinal);
	}

	@Override
	public List<Map<String, Object>> buscarGastosOrgaoSurbordinado(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal) {
		int anoMesInicial = DataUtils.converterParaData(mesInicial, anoInicial);
		int anoMesFinal = DataUtils.converterParaData(mesFinal, anoFinal);
		return pagamentoRepository.buscarGastosOrgaoSurbordinado(top, anoMesInicial, anoMesFinal);
	}

	@Override
	public List<Map<String, Object>> buscarGastosAcao(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal) {
		int anoMesInicial = DataUtils.converterParaData(mesInicial, anoInicial);
		int anoMesFinal = DataUtils.converterParaData(mesFinal, anoFinal);
		return pagamentoRepository.buscarGastosAcao(top, anoMesInicial, anoMesFinal);
	}

	@Override
	public List<Map<String, Object>> buscarGastosPrograma(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal) {
		int anoMesInicial = DataUtils.converterParaData(mesInicial, anoInicial);
		int anoMesFinal = DataUtils.converterParaData(mesFinal, anoFinal);
		return pagamentoRepository.buscarGastosPrograma(top, anoMesInicial, anoMesFinal);
	}
	
	@Override
	public List<Map<String, Object>> buscarGastosElementoDespesa(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal) {
		int anoMesInicial = DataUtils.converterParaData(mesInicial, anoInicial);
		int anoMesFinal = DataUtils.converterParaData(mesFinal, anoFinal);
		return pagamentoRepository.buscarGastosElementoDespesa(top, anoMesInicial, anoMesFinal);
	}
	
	@Override
	public List<Map<String, Object>> getAllOrgaoSubordinado() {
		return pagamentoRepository.getAllOrgaoSubordinado();
	}
	
	@Override
	public List<Map<String, Object>> getAllOrgaoSuperior() {
		return pagamentoRepository.getAllOrgaoSuperior();
	}

	@Override
	public List<Map<String, Object>> getAllAcao() {
		return pagamentoRepository.getAllAcao();
	}

	@Override
	public List<Map<String, Object>> getAllPrograma() {
		return pagamentoRepository.getAllPrograma();
	}

	@Override
	public List<Map<String, Object>> getAllElementoDespesa() {
		return pagamentoRepository.getAllElementoDespesa();
	}

	@Override
	public List<Map<String, Object>> buscarGeralDetalhamento(Integer orgaoSuperior, Integer orgaoSubordinado, Integer programa, String acao, Integer elementoDespesa, String groupBy, int mesInicial, int anoInicial, int mesFinal, int anoFinal){
		int anoMesInicial = DataUtils.converterParaData(mesInicial, anoInicial);
		int anoMesFinal = DataUtils.converterParaData(mesFinal, anoFinal);
		return pagamentoRepository.buscarGeralDetalhamento(orgaoSuperior, orgaoSubordinado, programa, acao, elementoDespesa, groupBy, anoMesInicial, anoMesFinal);
	}
	
	@Override
	public List<Map<String, Object>> buscarGeralAutocompleteNomes(Integer orgaoSuperior, Integer orgaoSubordinado, Integer programa, String acao, String retorno){
		return pagamentoRepository.buscarGeralAutocompleteNomes(orgaoSuperior, orgaoSubordinado, programa, acao, retorno);
	}
}