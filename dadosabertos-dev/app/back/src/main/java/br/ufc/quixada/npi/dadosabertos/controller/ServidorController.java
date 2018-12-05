package br.ufc.quixada.npi.dadosabertos.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.quixada.npi.dadosabertos.service.ServidorService;
import br.ufc.quixada.npi.dadosabertos.validation.Validator;

@RestController
@RequestMapping(path = "/api/v1/servidor")
public class ServidorController {

	@Autowired
	private ServidorService servidorService;
	
	@RequestMapping(path="/orgao_superior", method=RequestMethod.GET)
	public List<Map<String, Object>> gastosOrgaoSuperior(
			@RequestParam("top") int top, 
			@RequestParam("mes_inicio") int mesInicial,
			@RequestParam("ano_inicio") int anoInicial, 
			@RequestParam("mes_final") int mesFinal,
			@RequestParam("ano_final") int anoFinal){
		Validator.getInstance().consultaValidation(top, mesInicial, anoInicial, mesFinal, anoFinal);
		return servidorService.buscarOrgaoSuperior(top, mesInicial, anoInicial, mesFinal, anoFinal);
	}
	
	@RequestMapping(path="/orgao_superior/cargo", method=RequestMethod.GET)
	public List<Map<String, Object>>gastosOrgaoSuperiorCargo(
			@RequestParam("top") int top, 
			@RequestParam("mes_inicio") int mesInicial,
			@RequestParam("ano_inicio") int anoInicial, 
			@RequestParam("mes_final") int mesFinal,
			@RequestParam("ano_final") int anoFinal){
		Validator.getInstance().consultaValidation(top, mesInicial, anoInicial, mesFinal, anoFinal);
		return servidorService.buscarOrgaoSuperiorCargo(top, mesInicial, anoInicial, mesFinal, anoFinal);
	}

	@RequestMapping(path="/orgao_superior/funcao", method=RequestMethod.GET)
	public List<Map<String, Object>> gastosOrgaoSuperiorFuncao(
			@RequestParam("top") int top, 
			@RequestParam("mes_inicio") int mesInicial,
			@RequestParam("ano_inicio") int anoInicial, 
			@RequestParam("mes_final") int mesFinal,
			@RequestParam("ano_final") int anoFinal){
		Validator.getInstance().consultaValidation(top, mesInicial, anoInicial, mesFinal, anoFinal);
		return servidorService.buscarOrgaoSuperiorFuncao(top, mesInicial, anoInicial, mesFinal, anoFinal);
	}
	
	@RequestMapping(path="/orgao_subordinado", method=RequestMethod.GET)
	public List<Map<String, Object>> gastosOrgaoSubordinado(
			@RequestParam("top") int top, 
			@RequestParam("mes_inicio") int mesInicial,
			@RequestParam("ano_inicio") int anoInicial, 
			@RequestParam("mes_final") int mesFinal,
			@RequestParam("ano_final") int anoFinal){
		Validator.getInstance().consultaValidation(top, mesInicial, anoInicial, mesFinal, anoFinal);
		return servidorService.buscarOrgaoSubordinado(top, mesInicial, anoInicial, mesFinal, anoFinal);
	}
	
	@RequestMapping(path="/orgao_subordinado/cargo", method=RequestMethod.GET)
	public List<Map<String, Object>> gastosOrgaoSubordinadoCargo(
			@RequestParam("top") int top, 
			@RequestParam("mes_inicio") int mesInicial,
			@RequestParam("ano_inicio") int anoInicial, 
			@RequestParam("mes_final") int mesFinal,
			@RequestParam("ano_final") int anoFinal){
		Validator.getInstance().consultaValidation(top, mesInicial, anoInicial, mesFinal, anoFinal);
		return servidorService.buscarOrgaoSubordinadoCargo(top, mesInicial, anoInicial, mesFinal, anoFinal);
	}

	@RequestMapping(path="/orgao_subordinado/funcao", method=RequestMethod.GET)
	public List<Map<String, Object>> gastosOrgaoSubordinadoFuncao(
			@RequestParam("top") int top, 
			@RequestParam("mes_inicio") int mesInicial,
			@RequestParam("ano_inicio") int anoInicial, 
			@RequestParam("mes_final") int mesFinal,
			@RequestParam("ano_final") int anoFinal){
		Validator.getInstance().consultaValidation(top, mesInicial, anoInicial, mesFinal, anoFinal);
		return servidorService.buscarOrgaoSubordinadoFuncao(top, mesInicial, anoInicial, mesFinal, anoFinal);
	}
	
	@RequestMapping(path="/orgao_superior/get_all", method=RequestMethod.GET)
	public List<Map<String, Object>> getAllOrgaoSuperior(){
		return servidorService.getAllOrgaoSuperior();
	}
	
	@RequestMapping(path="/orgao_subordinado/get_all", method=RequestMethod.GET)
	public List<Map<String, Object>> getAllOrgaoSubordinado(){
		return servidorService.getAllOrgaoSubordinado();
	}
	
	@RequestMapping(path ="/consultageraldetalhamento", method=RequestMethod.GET)
	public List<Map<String, Object>> consultaGeralDetalhamento(
			@RequestParam(value = "orgao_superior", required = false) String orgaoSuperior, 
			@RequestParam(value = "orgao_subordinado", required = false) String orgaoSubordinado,
			@RequestParam(value = "group_by", required = true) String groupBy,
			@RequestParam("mes_inicio") int mesInicial,
			@RequestParam("ano_inicio") int anoInicial, 
			@RequestParam("mes_final") int mesFinal,
			@RequestParam("ano_final") int anoFinal) {
		Validator.getInstance().datasValidation(mesInicial, anoInicial, mesFinal, anoFinal);
		return servidorService.buscarGeralDetalhamento(orgaoSuperior, orgaoSubordinado, mesInicial, anoInicial, mesFinal, anoFinal, groupBy);
	}
	
	@RequestMapping(path ="/consultageralautocompletenomes", method=RequestMethod.GET)
	public List<Map<String, Object>> consultaGeralAutocompleteNomes(
			@RequestParam(value = "orgao_superior", required = false) String orgaoSuperior) {
		return servidorService.buscarGeralAutocompleteNomes(orgaoSuperior);
	}
}