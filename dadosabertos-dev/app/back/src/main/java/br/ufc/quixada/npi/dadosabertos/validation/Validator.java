package br.ufc.quixada.npi.dadosabertos.validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Validator {


	private static Validator validation;

	private Validator() {
	}

	public static Validator getInstance(){
		if(validation == null){
			validation = new Validator();
		}
		return validation;
	}

	public void consultaValidation(int top, int mesInicial, int anoInicial, int mesFinal, int anoFinal){
		if(top <= 0){
			throw new IllegalArgumentException("Não é permitido inserir números negativos, apenas números inteiros positivos");
		}
		datasValidation(mesInicial, anoInicial, mesFinal, anoFinal);
	}
	
	public void retornoValidation(String retorno){
		if(retorno == null){
			throw new IllegalArgumentException("É necessário determinar o tipo de retorno");
		}
		if(!retorno.equals("ORGAO_SUPERIOR") && !retorno.equals("ORGAO_SUBORDINADO") &&
		!retorno.equals("PROGRAMA") && !retorno.equals("ACAO") && !retorno.equals("ELEMENTO_DESPESA")){
			throw new IllegalArgumentException("Tipo de retorno não válido");
		}
	}
	
	public void saveSugestaoValidation(String nome, String email, String tipoRelato, String observacao){
		if(nome.isEmpty() || nome == null){
			throw new IllegalArgumentException("O parâmetro nome não pode estar vazio");
		}
		if(email.isEmpty() || email == null){
			throw new IllegalArgumentException("O parâmetro email não pode estar vazio");
		}
		if(observacao.isEmpty() || observacao == null){
			throw new IllegalArgumentException("O parâmetro observação não pode estar vazio");
		}
		if(tipoRelato.isEmpty() || tipoRelato == null){
			throw new IllegalArgumentException("O parâmetro tipoRelato não pode estar vazio");
		}
	}
	
	public void datasValidation(int mesInicial, int anoInicial, int mesFinal, int anoFinal){
		if(mesInicial <= 0 || mesFinal <= 0){
			throw new IllegalArgumentException("Não é permitido inserir números negativos, apenas números inteiros positivos");
		}
		if(mesInicial > 12 || mesFinal > 12){
			throw new IllegalArgumentException("Não é permitido o mês ser maior que doze");
		}
		if(anoInicial > anoFinal){
			throw new IllegalArgumentException("O parâmetro ano inicial não pode ser maior que o ano final");
		}
		if(anoInicial < 2016){
			throw new IllegalArgumentException("A base de dados não possui valores anteriores ao ano de 2016");
		}
		if(anoFinal > Integer.parseInt(DateTimeFormatter.ofPattern("yyyy").format(LocalDateTime.now()))){
			throw new IllegalArgumentException("Não é possível obter dados posteriores ao ano atual");
		}
		if(mesInicial > mesFinal && anoInicial == anoFinal){
			throw new IllegalArgumentException("O parâmetro mês inicial não pode ser maior que o mês final");
		}
	}
}