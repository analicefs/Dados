package br.ufc.quixada.npi.dadosabertos.util;

public class DataUtils {
	
	private DataUtils(){}
	
	public static final int converterParaData(int mes, int ano){
		return ano * 100 + mes;
	}
}
