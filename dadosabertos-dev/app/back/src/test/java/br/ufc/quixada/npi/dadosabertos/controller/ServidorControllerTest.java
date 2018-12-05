package br.ufc.quixada.npi.dadosabertos.controller;

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
public class ServidorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	/**
	*Testa se o Json traz os dados especificados de orgao_superior/ Servidor
	*/
	
	@Test
	public void gastosOrgaoSuperior() throws Exception{
		this.mockMvc
		.perform(
				get("/api/v1/servidor/orgao_superior?top=10&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=2016")
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$").value(Matchers.hasSize(10)))
		.andExpect(jsonPath("$.[0].nome", is ("MINISTERIO DA EDUCACAO")))
		.andExpect(jsonPath("$.[1].cod", is("-1")))
		.andExpect(jsonPath("$.[1].total", is( 1694028825.18)))
		.andExpect(jsonPath("$.[4].cod", is ("20113")))
		.andExpect(jsonPath("$.[4].total", is (171522678.14)));
		
	}
	/**
	* Testa se o Json traz os dados especificados de cargos/ orgao_superior/ Servidor
	*/
	@Test
	public void gastosOrgaoSuperiorCargo() throws Exception{
		this.mockMvc
		.perform(
				get("/api/v1/servidor/orgao_superior/cargo?top=10&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=2016")
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$").value(Matchers.hasSize(10)))
		.andExpect(jsonPath("$.[0].nome", is ("MINISTERIO DA EDUCACAO")))
		.andExpect(jsonPath("$.[1].cod").value(-1)).andExpect(jsonPath("$.[1].total").value(1350779899.96))
		.andExpect(jsonPath("$.[4].cod").value(20113)).andExpect(jsonPath("$.[4].total").value( 141031812.86));
		
	}
	/**
	*Testa se o Json traz os dados especificados de orgao_superior/funçao/ Servidor
	*/
	
	@Test
	public void gastosOrgaoSuperiorFuncao() throws Exception{
		this.mockMvc
		.perform(
				get("/api/v1/servidor/orgao_superior/funcao?top=10&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=2016")
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$").value(Matchers.hasSize(10)))
		.andExpect(jsonPath("$.[0].nome", is ("MINISTERIO DA EDUCACAO")))
		.andExpect(jsonPath("$.[1].cod").value(-1)).andExpect(jsonPath("$.[1].total").value( 308631664.67))
		.andExpect(jsonPath("$.[4].cod").value(20113)).andExpect(jsonPath("$.[4].total").value(29611378.6));
		
	}
	/**
	* Testa se o Json traz os dados especificados de orgao_subordinado/ Servidor
	*/
	@Test
	public void gastosOrgaoSubordinado() throws Exception{
		this.mockMvc
		.perform(
				get("/api/v1/servidor/orgao_subordinado?top=10&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=2016")
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$").value(Matchers.hasSize(10)))
		.andExpect(jsonPath("$.[0].nome", is ("MINISTERIO DA FAZENDA")))
		.andExpect(jsonPath("$.[1].cod").value(57202)).andExpect(jsonPath("$.[1].total").value(376751482.13))
		.andExpect(jsonPath("$.[4].cod").value(26245)).andExpect(jsonPath("$.[4].total").value(139920845.2));
		
	}
	/**
	*Testa se o Json traz os dados especificados em cargos/ orgao_subordinado/ Servidor
	*/
	@Test
	public void gastosOrgaoSubordinadoCargo() throws Exception{
		this.mockMvc
		.perform(
				get("/api/v1/servidor/orgao_subordinado/cargo?top=10&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=2016")
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$").value(Matchers.hasSize(10)))
		.andExpect(jsonPath("$.[0].nome", is ("MINISTERIO DA FAZENDA")))
		.andExpect(jsonPath("$.[1].cod").value(57202))
		.andExpect(jsonPath("$.[1].total").value(322974654.54))
		.andExpect(jsonPath("$.[4].cod").value(26245))
		.andExpect(jsonPath("$.[4].total").value(123972811.59));
		
	}
	
	/**
	*Testa se o Json traz os dados especificados a funçao/orgao_subordinado/ Servidor
	*/
	
	@Test
	public void gastosOrgaoSubordinadoFuncao() throws Exception{
		this.mockMvc
		.perform(
				get("/api/v1/servidor/orgao_subordinado/funcao?top=10&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=2016")
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$").value(Matchers.hasSize(10)))
		.andExpect(jsonPath("$.[0].nome").value("MINISTERIO DA FAZENDA"))
		.andExpect(jsonPath("$.[2].cod").value(25201))
		.andExpect(jsonPath("$.[1].total").value(46183334.31))
		.andExpect(jsonPath("$.[3].cod").value(45205))
		.andExpect(jsonPath("$.[4].total").value(21280398.05));
		
	}
	/**
	* Erro ao tentar colocar uma string em vez de um inteiro no parametro Top
	*/
	
	@Test
	public void gastosOrgaoSuperiorErrosString() throws Exception{
		this.mockMvc
		.perform(
				get("/api/v1/servidor/orgao_superior?top=a&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=2016"))
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
	*Erro ao faltando escolher o tipo de orgao (orgao_superior ou orgao_subordinado) da base de pesquisa
	*/
	@Test
	public void ServidorgastosOrgaoSuperior() throws Exception{
		this.mockMvc
		.perform(
				get("/api/v1/servidor?top=1&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=2016"))
		.andExpect(status().isNotFound());
	}
	
	/**
	* Confere se  números negativos inseridos em Top está sendo rejeitado
	*/
	@Test
	public void numerosNegativosEmTopServidor(){
		try {
			this.mockMvc
			.perform(get("/api/v1/servidor/orgao_superior/funcao?top=-1&mes_inicio=-1&ano_inicio=2016&mes_final=1&ano_final=2016")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(status().is4xxClientError());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	*Erro ao tentar inserir uma data final(ano_final, mes_final) menor(<) que a data inicial(ano_inicial, mes_inicial)
	*/
		@Test
		public void DataInicialMaior(){
			try {
				this.mockMvc
				.perform(get("/api/v1/servidor/orgao_subordinado?top=10&mes_inicio=11&ano_inicio=2017&mes_final=1&ano_final=2016")
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().is4xxClientError());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	/**
	*Erro ao tentar inserir no mês inicial de um mesmo ano,  um numero maior que mês final
	*/
		
		@Test
		public void MesInicialdeMesmoAnoMaior(){
					try {
						this.mockMvc
						.perform(get("/api/v1/servidor/orgao_subordinado?top=10&mes_inicio=11&ano_inicio=2016&mes_final=1&ano_final=2016")
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
						.perform(get("/api/v1/servidor/orgao_subordinado?top=10&mes_inicio=1&ano_inicio=2016&mes_final=-1&ano_final=2016")
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
				.perform(get("/api/v1/servidor/orgao_subordinado?top=10&mes_inicio=-1&ano_inicio=2016&mes_final=1&ano_final=2016")
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
				.perform(get("/api/v1/servidor/orgao_subordinado?top=10&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=-2016")
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().is4xxClientError());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**
		*Erro ao tentar inserir números negativos em ano inicial
		*/
		@Test
		public void AnoInicialNumerosNegativo(){
			try {
				this.mockMvc
				.perform(get("/api/v1/servidor/orgao_subordinado?top=10&mes_inicio=1&ano_inicio=-2016&mes_final=1&ano_final=2016")
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().is4xxClientError());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**
		* Erro ao tentar colocar uma string em ano_inicio
		*/
		
		@Test
		public void StringAnoInicio() throws Exception{
			this.mockMvc
			.perform(
					get("/api/v1/servidor/orgao_superior?top=1&mes_inicio=1&ano_inicio=a&mes_final=1&ano_final=2016"))
			.andExpect(status().isBadRequest());
	    
		}
		
		/**
		* Erro ao tentar colocar uma string em mes_inicio
		*/
		
		@Test
		public void StringMesInicio() throws Exception{
			this.mockMvc
			.perform(
					get("/api/v1/servidor/orgao_superior?top=1&mes_inicio=a&ano_inicio=2016&mes_final=1&ano_final=2016"))
			.andExpect(status().isBadRequest());
	    
		}
		
		/**
		* Erro ao tentar colocar uma string em ano_final
		*/
		
		@Test
		public void StringAnoFinal() throws Exception{
			this.mockMvc
			.perform(
					get("/api/v1/servidor/orgao_superior?top=1&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=a"))
			.andExpect(status().isBadRequest());
	    
		}
		
		/**
		* Erro ao tentar colocar uma string em mes_final
		*/
		
		@Test
		public void StringMesFinal() throws Exception{
			this.mockMvc
			.perform(
					get("/api/v1/servidor/orgao_superior?top=1&mes_inicio=1&ano_inicio=2016&mes_final=a&ano_final=2016"))
			.andExpect(status().isBadRequest());
	    
		}
		
		
		@Test
		public void getAllOrgaoSuperior() throws Exception {
			
			this.mockMvc
					.perform(
							get("/api/v1/servidor/orgao_superior/get_all")
									.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
					.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
					
				
					.andExpect(jsonPath("$.[2].nome", is( "MINISTERIO IND. COM. EXTERIOR E SERVIçOS")));
					

		}
		
		
		@Test
		public void getAllOrgaoSubordinado() throws Exception {
			
			this.mockMvc
					.perform(
							get("/api/v1/servidor/orgao_subordinado/get_all")
									.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
					.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
					
				
					.andExpect(jsonPath("$.[6].nome", is( "PREFEITURA MUNICIPAL DE FORTALEZA / CE")));
					

		}
		
		
		@Test
		public void consultaGeralAutocompleteNomes() throws Exception {
			this.mockMvc
			.perform(
					get("/api/v1/servidor/consultageralautocompletenomes?orgao_superior=-1")
							.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))

			.andExpect(jsonPath("$.[3].cod", is ("25282")));
		
			
		}
		
		@Test
		public void consultaGeralDetalhamento() throws Exception {
			this.mockMvc
			.perform(
					get("/api/v1/servidor/consultageraldetalhamento?group_by=ORGAO_SUPERIOR&mes_inicio=1&ano_inicio=2016&mes_final=1&ano_final=2016")
							.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))

			.andExpect(jsonPath("$.[2].cod", is("40105")));
		
			
		}
		
		/**
		*Erro ao tentar inserir números negativos em ano inicial
		*/
		@Test
		public void AnoInicialNumerosNegativoEmConsultaDetalhamento(){
			try {
				this.mockMvc
				.perform(get("/api/v1/servidor/consultageraldetalhamento?group_by=ORGAO_SUPERIOR&mes_inicio=-1&ano_inicio=2016&mes_final=1&ano_final=2016")
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().is4xxClientError());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		/**
		* Erro ao tentar colocar uma string em ano_final
		*/
		
		@Test
		public void StringAnoFinalDetalhamento() throws Exception{
			this.mockMvc
			.perform(
					get("/api/v1/servidor/consultageraldetalhamento?group_by=ORGAO_SUPERIOR&mes_inicio=-1&ano_inicio=2016&mes_final=1&ano_final=2016"))
			.andExpect(status().isBadRequest());
	    
		}
		
	}
	


	

