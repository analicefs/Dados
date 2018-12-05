package br.ufc.quixada.npi.dadosabertos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.npi.dadosabertos.repository.ServidorRepository;
import br.ufc.quixada.npi.dadosabertos.service.ServidorService;
import br.ufc.quixada.npi.dadosabertos.util.DataUtils;

@Service
public class ServidorServiceImpl implements ServidorService {

	@Autowired
	private ServidorRepository servidorRepository;

	public List<Map<String, Object>> buscarOrgaoSuperiorCargo(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal){
		int anoMesInicial = DataUtils.converterParaData(mesInicial, anoInicial);
		int anoMesFinal = DataUtils.converterParaData(mesFinal, anoFinal);
		return servidorRepository.buscarOrgaoSuperiorCargo(top, anoMesInicial, anoMesFinal);
	}

	public List<Map<String, Object>> buscarOrgaoSuperior(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal){
		int anoMesInicial = DataUtils.converterParaData(mesInicial, anoInicial);
		int anoMesFinal = DataUtils.converterParaData(mesFinal, anoFinal);
		return servidorRepository.buscarOrgaoSuperior(top, anoMesInicial, anoMesFinal);
	}

	public List<Map<String, Object>> buscarOrgaoSuperiorFuncao(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal) {
		int anoMesInicial = DataUtils.converterParaData(mesInicial, anoInicial);
		int anoMesFinal = DataUtils.converterParaData(mesFinal, anoFinal);
		return servidorRepository.buscarOrgaoSuperiorFuncao(top, anoMesInicial, anoMesFinal);
	}

	@Override
	public List<Map<String, Object>> buscarOrgaoSubordinadoCargo(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal) {
		int anoMesInicial = DataUtils.converterParaData(mesInicial, anoInicial);
		int anoMesFinal = DataUtils.converterParaData(mesFinal, anoFinal);
		return servidorRepository.buscarOrgaoSubordinadoCargo(top, anoMesInicial, anoMesFinal);
	}

	@Override
	public List<Map<String, Object>> buscarOrgaoSubordinado(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal) {
		int anoMesInicial = DataUtils.converterParaData(mesInicial, anoInicial);
		int anoMesFinal = DataUtils.converterParaData(mesFinal, anoFinal);
		return servidorRepository.buscarOrgaoSubordinado(top, anoMesInicial, anoMesFinal);
	}

	@Override
	public List<Map<String, Object>> buscarOrgaoSubordinadoFuncao(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal) {
		int anoMesInicial = DataUtils.converterParaData(mesInicial, anoInicial);
		int anoMesFinal = DataUtils.converterParaData(mesFinal, anoFinal);
		return servidorRepository.buscarOrgaoSubordinadoFuncao(top, anoMesInicial, anoMesFinal);
	}
	
	@Override
	public List<Map<String, Object>> getAllOrgaoSubordinado() {
		return servidorRepository.getAllOrgaoSubordinado();
	}
	
	@Override
	public List<Map<String, Object>> getAllOrgaoSuperior() {
		return servidorRepository.getAllOrgaoSuperior();
	}

	@Override
	public List<Map<String, Object>> buscarGeralDetalhamento(String orgaoSuperior, String orgaoSubordinado, int mesInicial, int anoInicial,
			int mesFinal, int anoFinal, String groupBy) {
		int anoMesInicial = DataUtils.converterParaData(mesInicial, anoInicial);
		int anoMesFinal = DataUtils.converterParaData(mesFinal, anoFinal);
		return servidorRepository.buscarGeralDetalhamento(orgaoSuperior, orgaoSubordinado, anoMesInicial, anoMesFinal, groupBy);
	}
	
	@Override
	public List<Map<String, Object>> buscarGeralAutocompleteNomes(String orgaoSuperior){
		return servidorRepository.buscarGeralAutocompleteNomes(orgaoSuperior);
	}
}
