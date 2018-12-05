package br.ufc.quixada.npi.dadosabertos.controller;
import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PagamentoControllerTest {


	@Autowired
	private MockMvc mockMvc;
	
	/**
	*Confere se o caminho está ok e se está trazendo o json correto da  classe gastosOrgaoSuperior
	*/
	@Test
	public void gastosOrgaoSuperior() throws Exception {
		this.mockMvc
				.perform(
						get("/api/v1/pagamento/orgao_superior?top=10&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=2016")
								.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$").value(Matchers.hasSize(10)))
				.andExpect(jsonPath("$.[0].nome", is("MINISTERIO DA FAZENDA")))
				.andExpect(jsonPath("$.[0].cod").value(25000))
				.andExpect(jsonPath("$.[0].total").value(248375661628.85995))
				.andExpect(jsonPath("$.[1].cod").value(33000))
				.andExpect(jsonPath("$.[1].total").value(8843891327.980005));
	}
	
	/**
	*Confere se o caminho está ok e se está trazendo algum json correto da classe gastosOrgaoSubordinado
	*/
	 @Test
	public void gastosOrgaoSubordinado() throws Exception {
		this.mockMvc
				.perform(
						get("/api/v1/pagamento/orgao_subordinado?top=10&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=2016")
								.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$").value(Matchers.hasSize(10)))
				.andExpect(jsonPath("$.[0].nome", is("MINISTERIO DA FAZENDA - UNIDADES COM VÍNCULO DIRETO")))
				.andExpect(jsonPath("$.[1].cod").value(37904))
				.andExpect(jsonPath("$.[1].total").value( 8843757029.390005))
				.andExpect(jsonPath("$.[2].cod").value(38901))
				.andExpect(jsonPath("$.[2].total").value(6552001880.369999));

	}
	
	/**
	*Erro ao tentar colocar uma string em vez de um inteiro no parametro Top
	*/
	@Test
	public void gastosOrgaoSuperiorErrosString() throws Exception{
		this.mockMvc
		.perform(
				get("/api/v1/pagamento/orgao_superior?top=a&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=2016"))
		.andExpect(status().isBadRequest());
    
	}
	
	/**
	*Erro ao faltando escolher a base de pesquisa
	*/
	@Test
	public void gastosOrgaoSuperiorErrosFalta() throws Exception{
		this.mockMvc
		.perform(
				get("/api/v1/orgao_superior?top=1&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=2016"))
		.andExpect(status().isNotFound());
	}
		
	/**
	* Confere se o caminho está ok e se está trazendo algum json da classe gastosAcao
	*/
	@Test
	public void gastosAcao() throws Exception {
		this.mockMvc
				.perform(get("/api/v1/pagamento/acao?top=10&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=2016")
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$").value(Matchers.hasSize(10)))
				.andExpect(jsonPath("$.[1].nome", is( "SERVICOS DA DIVIDA PUBLICA FEDERAL INTERNA")))
				.andExpect(jsonPath("$.[0].cod").value("0365")).andExpect(jsonPath("$.[0].total").value(169688521966.45))
				.andExpect(jsonPath("$.[2].cod").value("20TP")).andExpect(jsonPath("$.[2].total").value(6064059438.309999));

	}

	
	/**
	*Confere se o caminho está ok e se está trazendo algum json correto da classe gastosPrograma	
	*/
	@Test
	public void gastosPrograma() throws Exception {
		
		this.mockMvc
				.perform(
						get("/api/v1/pagamento/programa?top=10&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=2016")
								.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				
				
				.andExpect(jsonPath("$").value(Matchers.hasSize(10)))
				.andExpect(jsonPath("$.[9].nome",
						is( "PROGRAMA DE GESTAO E MANUTENCAO DO MINISTERIO DA DEFESA")))
				.andExpect(jsonPath("$.[8].cod", is(906)))
				.andExpect(jsonPath("$.[7].cod", is(2015)))
				.andExpect(jsonPath("$.[7].total", Matchers.closeTo(new BigDecimal(4633321388.420002), new BigDecimal(0.0001))));

	}
	
	/**
	 * Confere se  números negativos inseridos em Top está sendo rejeitado
	 */
	@Test
	public void numerosNegativosEmTopPagamento(){
		try {
			this.mockMvc
			.perform(get("/api/v1/pagamento/acao?top=-1&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=2016")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(status().is4xxClientError());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Erro ao tentar inserir uma data final(ano_final, mes_final) menor(<) que a data inicial(ano_inicial, mes_inicial)
	 */
	@Test
	public void DataInicialMaior(){
		try {
			this.mockMvc
			.perform(get("/api/v1/pagamento/orgao_subordinado?top=10&mes_inicio=11&ano_inicio=2017&mes_final=1&ano_final=2016")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(status().is4xxClientError());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Erro ao tentar inserir no mês inicial de um mesmo ano um numero maior que que mês final
	 */
	@Test
	public void MesInicialdeMesmoAnoMaior(){
		try {
			this.mockMvc
			.perform(get("/api/v1/pagamento/orgao_subordinado?top=10&mes_inicio=11&ano_inicio=2016&mes_final=1&ano_final=2016")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(status().is4xxClientError());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	*Erro ao tentar inserir números negativos em mês final
	*/
	
	@Test
	public void MesFinalNumerosNegativo(){
				try {
					this.mockMvc
					.perform(get("/api/v1/pagamento/orgao_subordinado?top=10&mes_inicio=1&ano_inicio=2016&mes_final=-1&ano_final=2016")
							.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
					.andExpect(status().is4xxClientError());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
	/**
	*Erro ao tentar inserir números negativos em mês inicial
	*/
	@Test
	public void MesInicialNumerosNegativo(){
		try {
			this.mockMvc
			.perform(get("/api/v1/pagamento/orgao_subordinado?top=10&mes_inicio=-1&ano_inicio=2016&mes_final=1&ano_final=2016")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(status().is4xxClientError());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	*Erro ao tentar inserir números negativos em ano final
	*/
	@Test
	public void AnoFinalNumerosNegativo(){
		try {
			this.mockMvc
			.perform(get("/api/v1/pagamento/orgao_subordinado?top=10&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=-2016")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(status().is4xxClientError());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	*Erro ao tentar colocar uma string em ano_inicio
	*/
	@Test
	public void StringEmAnoInicio() throws Exception{
		this.mockMvc
		.perform(
				get("/api/v1/pagamento/orgao_superior?top=10&mes_inicio=1&ano_inicio=a&mes_final=1&ano_final=2016"))
		.andExpect(status().isBadRequest());
    
	}
	
	/**
	*Erro ao tentar colocar uma string em mes_inicio
	*/
	@Test
	public void StringEmMesInicio() throws Exception{
		this.mockMvc
		.perform(
				get("/api/v1/pagamento/orgao_superior?top=10&mes_inicio=a&ano_inicio=2016&mes_final=1&ano_final=2016"))
		.andExpect(status().isBadRequest());
    
	}
	/**
	*Erro ao tentar colocar uma string em ano_final
	*/
	@Test
	public void StringEmAnoFinal() throws Exception{
		this.mockMvc
		.perform(
				get("/api/v1/pagamento/orgao_superior?top=10&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=a"))
		.andExpect(status().isBadRequest());
    
	}
	/**
	*Erro ao tentar colocar uma string em mes_final
	*/
	@Test
	public void StringEmMesFinal() throws Exception{
		this.mockMvc
		.perform(
				get("/api/v1/pagamento/orgao_superior?top=10&mes_inicio=1&ano_inicio=2016&mes_final=a&ano_final=2016"))
		.andExpect(status().isBadRequest());
    
	}
	
	/**
	*Erro ao tentar colocar um ano_inicio menor que 2016
	*/
	@Test
	public void AnoMenorQue2016() throws Exception{
		this.mockMvc
		.perform(
				get("/api/v1/pagamento/orgao_superior?top=10&mes_inicio=1&ano_inicio=1&mes_final=1&ano_final=2016"))
		.andExpect(status().isBadRequest());
    
	}
	
	/**
	*Erro ao tentar colocar um ano_final maior que o atual
	*/
	@Test
	public void AnoMaiorQueAtual() throws Exception{
		this.mockMvc
		.perform(
				get("/api/v1/pagamento/orgao_superior?top=10&mes_inicio=1&ano_inicio=1&mes_final=1&ano_final=2020"))
		.andExpect(status().isBadRequest());
    
	}
	
	/**
	*Confere se o caminho está ok e se está trazendo o json correto da  classe getAllOrgaoSuperior
	*/
	
	@Test
	public void getAllOrgaoSuperior() throws Exception {
		
		this.mockMvc
				.perform(
						get("/api/v1/pagamento/orgao_superior/get_all")
								.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				
			
				.andExpect(jsonPath("$.[9].nome", is( "MINISTERIO DO DESENVOLVIMENTO SOCIAL")));
				

	}
	@Test
	public void getAllOrgaoSubordinado() throws Exception {
		
		this.mockMvc
				.perform(
						get("/api/v1/pagamento/orgao_subordinado/get_all")
								.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				
			
				.andExpect(jsonPath("$.[1].nome", is( "INST.FED.DE EDUC.,CIENC.E TEC.GOIANO")));
				

	}
	
	@Test
	public void getAllPrograma() throws Exception {
		
		this.mockMvc
				.perform(
						get("/api/v1/pagamento/programa/get_all")
								.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
	
				.andExpect(jsonPath("$.[1].nome", is( "EDUCACAO PROFISSIONAL E TECNOLOGICA")));
				

	}
	
	@Test
	public void getAllOrgaoAcao() throws Exception {
		
		this.mockMvc
				.perform(
						get("/api/v1/pagamento/acao/get_all")
								.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
	
				.andExpect(jsonPath("$.[4].nome", is( "FOMENTO A PRODUCAO PESQUEIRA E AQUICOLA")));
			
	}
	
	
	@Test
	public void getAllElementoDespesa() throws Exception {
		
		this.mockMvc
				.perform(
						get("/api/v1/pagamento/elemento_despesa/get_all")
								.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))

				.andExpect(jsonPath("$.[1].nome", is( "AUXÍLIO FINANCEIRO A ESTUDANTES")));
				
	}
	
	@Test
	public void consultaGeralDetalhamento() throws Exception {
		this.mockMvc
		.perform(
				get("/api/v1/pagamento/consultageraldetalhamento?mes_inicio=1&ano_inicio=2016&mes_final= 1&ano_final= 2016&group_by=ACAO")
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))

		.andExpect(jsonPath("$.[8].cod", is("0425")));
	
		
	}
	
	
	
	@Test
	public void consultaGeralAutocompleteNomes() throws Exception {
		this.mockMvc
		.perform(
				get("/api/v1/pagamento/consultageralautocompletenomes?retorno=ACAO")
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))

		.andExpect(jsonPath("$.[3].cod", is("00M1")));
	
		
	}
	
	/**
	*Erro ao tentar inserir números negativos em ano inicial
	*/
	@Test
	public void NumerosNegativoEmConsultaDetalhamento(){
		try {
			this.mockMvc
			.perform(get("/api/v1/pagamento/consultageraldetalhamento?group_by=PROGRAMA&mes_inicio=1&ano_inicio=2016&mes_final=-1&ano_final=2016")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(status().is4xxClientError());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	*Erro ao tentar colocar uma string em mes_final
	*/
	@Test
	public void StringEmMesFinalDetalhadamento() throws Exception{
		this.mockMvc
		.perform(
				get("/api/v1/pagamento/consultageraldetalhamento?group_by=PROGRAMA&mes_inicio=1&ano_inicio=2016&mes_final=-1&ano_final=2016"))
		.andExpect(status().isBadRequest());
    
	}
	
	
	
}
