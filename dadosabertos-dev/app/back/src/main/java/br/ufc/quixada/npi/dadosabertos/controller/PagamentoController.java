package br.ufc.quixada.npi.dadosabertos.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import br.ufc.quixada.npi.dadosabertos.service.PagamentoService;
import br.ufc.quixada.npi.dadosabertos.validation.Validator;

@RestController
@RequestMapping(path = "/api/v1/pagamento")
public class PagamentoController {

	@Autowired
	private PagamentoService pagamentoService;
	
	@RequestMapping(path = "/orgao_superior", method=RequestMethod.GET)
	public List<Map<String, Object>> gastosOrgaoSuperior(
			@RequestParam("top") int top, 
			@RequestParam("mes_inicio") int mesInicial,
			@RequestParam("ano_inicio") int anoInicial, 
			@RequestParam("mes_final") int mesFinal,
			@RequestParam("ano_final") int anoFinal) {
		Validator.getInstance().consultaValidation(top, mesInicial, anoInicial, mesFinal, anoFinal);
		return pagamentoService.buscarGastosOrgaosSuperiores(top, mesInicial, anoInicial, mesFinal, anoFinal);
	}
	
	@RequestMapping(path = "/orgao_subordinado", method=RequestMethod.GET)
	public List<Map<String, Object>> gastosOrgaoSubordinado(
			@RequestParam("top") int top,
			@RequestParam("mes_inicio") int mesInicial,
			@RequestParam("ano_inicio") int anoInicial,
			@RequestParam("mes_final") int mesFinal,
			@RequestParam("ano_final") int anoFinal) {
		Validator.getInstance().consultaValidation(top, mesInicial, anoInicial, mesFinal, anoFinal);
		return pagamentoService.buscarGastosOrgaoSurbordinado(top, mesInicial, anoInicial, mesFinal, anoFinal);
	}
	
	@RequestMapping(path = "/acao", method=RequestMethod.GET)
	public List<Map<String, Object>> gastosAcao(
			@RequestParam("top") int top, 
			@RequestParam("mes_inicio") int mesInicial,
			@RequestParam("ano_inicio") int anoInicial, 
			@RequestParam("mes_final") int mesFinal,
			@RequestParam("ano_final") int anoFinal) {
		Validator.getInstance().consultaValidation(top, mesInicial, anoInicial, mesFinal, anoFinal);
		return pagamentoService.buscarGastosAcao(top, mesInicial, anoInicial, mesFinal, anoFinal);
	}
	
	@RequestMapping(path = "/programa", method=RequestMethod.GET)
	public List<Map<String, Object>> gastosPrograma(
			@RequestParam("top") int top,
			@RequestParam("mes_inicio") int mesInicial,
			@RequestParam("ano_inicio") int anoInicial,
			@RequestParam("mes_final") int mesFinal,
			@RequestParam("ano_final") int anoFinal) {
		Validator.getInstance().consultaValidation(top, mesInicial, anoInicial, mesFinal, anoFinal);
		return pagamentoService.buscarGastosPrograma(top, mesInicial, anoInicial, mesFinal, anoFinal);
	}
	
	@RequestMapping(path = "/elemento_despesa", method=RequestMethod.GET)
	public List<Map<String, Object>> gastosElementoDespesa(
			@RequestParam("top") int top,
			@RequestParam("mes_inicio") int mesInicial,
			@RequestParam("ano_inicio") int anoInicial,
			@RequestParam("mes_final") int mesFinal,
			@RequestParam("ano_final") int anoFinal) {
		Validator.getInstance().consultaValidation(top, mesInicial, anoInicial, mesFinal, anoFinal);
		return pagamentoService.buscarGastosElementoDespesa(top, mesInicial, anoInicial, mesFinal, anoFinal);
	}
	
	@RequestMapping(path="/orgao_superior/get_all", method=RequestMethod.GET)
	public List<Map<String, Object>> getAllOrgaoSuperior(){
		return pagamentoService.getAllOrgaoSuperior();
	}	
	
	@RequestMapping(path="/orgao_subordinado/get_all", method=RequestMethod.GET)
	public List<Map<String, Object>> getAllOrgaoSubordinado(){
		return pagamentoService.getAllOrgaoSubordinado();
	}
	
	@RequestMapping(path="/acao/get_all", method=RequestMethod.GET)
	public List<Map<String, Object>> getAllAcao(){
		return pagamentoService.getAllAcao();
	}
	
	@RequestMapping(path="/programa/get_all", method=RequestMethod.GET)
	public List<Map<String, Object>> getAllPrograma(){
		return pagamentoService.getAllPrograma();
	}
	
	@RequestMapping(path="/elemento_despesa/get_all", method=RequestMethod.GET)
	public List<Map<String, Object>> getAllElementoDespesa(){
		return pagamentoService.getAllElementoDespesa();
	}
	
	@RequestMapping(path ="/consultageraldetalhamento", method=RequestMethod.GET)
	public List<Map<String, Object>> consultaGeralDetalhamento(
			@RequestParam(value = "orgao_superior", required = false) Integer orgaoSuperior, 
			@RequestParam(value = "orgao_subordinado", required = false) Integer orgaoSubordinado,
			@RequestParam(value = "programa", required = false) Integer programa, 
			@RequestParam(value = "acao", required = false) String acao,
			@RequestParam(value = "elemento_despesa", required = false) Integer elementoDespesa,
			@RequestParam(value = "group_by", required = true) String groupBy,
			@RequestParam("mes_inicio") int mesInicial,
			@RequestParam("ano_inicio") int anoInicial, 
			@RequestParam("mes_final") int mesFinal,
			@RequestParam("ano_final") int anoFinal) {
		Validator.getInstance().datasValidation(mesInicial, anoInicial, mesFinal, anoFinal);
		return pagamentoService.buscarGeralDetalhamento(orgaoSuperior, orgaoSubordinado, programa, acao, elementoDespesa, groupBy, mesInicial, anoInicial, mesFinal, anoFinal);
	}
	
	@RequestMapping(path ="/consultageralautocompletenomes", method=RequestMethod.GET)
	public List<Map<String, Object>> consultaGeralAutocompleteNomes(
			@RequestParam(value = "orgao_superior", required = false) Integer orgaoSuperior, 
			@RequestParam(value = "orgao_subordinado", required = false) Integer orgaoSubordinado,
			@RequestParam(value = "programa", required = false) Integer programa, 
			@RequestParam(value = "acao", required = false) String acao,
			@RequestParam(value = "retorno", required = true) String retorno) {
		Validator.getInstance().retornoValidation(retorno);
		return pagamentoService.buscarGeralAutocompleteNomes(orgaoSuperior, orgaoSubordinado, programa, acao, retorno);
	}
}
