package primeiraquestao;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/*
 * O IBGE possui um servi�o que prov� informa��es a respeito de nomes
 * de pessoas no Brasil
 * exemplo: https://servicodados.ibge.gov.br/api/v1/censos/nomes/basica?nome=marvin&sexo=m
 * Consulta pelo nome 'marvin' do sexo masculino
 * Com base nisso, crie um script em que se digite o nome da pessoa e
 * o sistema exiba os nomes semelhantes (atributos nomes) e a quantidade
 * de pessoas com esse nome registradas (atributo freq)
 */
public class Dom {
	public static void main(String[] args) throws IOException {
		// O Scanner ir� ler a entrada do usu�rio
		Scanner entrada = new Scanner(System.in);
		String  nome;
		
		System.out.println("Insira o nome que deseja buscar: ");
		// A vari�vel nome ir� receber a entrada do usu�rio e aplicar um UpperCase (Caixa Alta)
		nome = entrada.next().toUpperCase();
		
		// Efetua-se a conex�o com a p�gina, e o get faz o parser html
		Document doc = Jsoup.connect("https://servicodados.ibge.gov.br/api/v1/censos/nomes/basica?nome="+nome+"&sexo=m").ignoreContentType(true).get();
		// A vari�vel doc_string receber� o texto contido na p�gina doc
		String doc_string = doc.text();
		// Inst�ncia da classe JSONParser
		JSONParser parser = new JSONParser();
		
		Long frequencia = null;
		String nomes = "";
		
		try {
			Object object = parser.parse(doc_string);
			// A vari�vel JSONArray recebe os dados presente do objeto, no caso, somente 1 dado, um dicion�rio
			JSONArray array = (JSONArray)object;
			// A vari�vel objeto recebe todos os dados contidos no dicion�rio, e separa-os por chave - valor
			JSONObject obj = (JSONObject)array.get(0);
	        // Semelhante ao HashMap, para todo objeto key presente em obj.keySet() fa�a:
			for (Object key: obj.keySet()) {
				// O objeto valor recebe os valores contidos em obj.get(key)
				Object value = obj.get(key);
				// Se a key for iqual a freq, a variavel global frequencia recebe o valor
				if(key.toString().equals("freq")) {
					frequencia = (Long)value;
				}else {
					// Se a key for igual a nomes, a variavel global nomes recebe o valor
					if(key.toString().equals("nomes")) {
						nomes = (String)value;
					}
				}
			}
			
		}catch(ParseException pe) {
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
		// Sa�da
		System.out.println("Num de frequencia: " + frequencia.toString());
		System.out.println("Nomes semelhantes: " + nomes);

	}

}