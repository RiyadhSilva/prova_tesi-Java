package segundaquestao;
/*
 * Acesse o link http://www.portaldatransparencia.gov.br/cnep/ . 
 * Com base na listagem, permita que se faça uma consulta por estado 
 * e liste as empresas que estão punidas. 
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Dom {

	public static void main(String[] args) throws IOException {
		Scanner entrada = new Scanner(System.in);
		String  estado;
		
		Map<String,String> empresa_estado = new HashMap<String, String>();
		
		System.out.println("Digite a UF do estado: ");
		estado = entrada.next().toUpperCase();
		System.out.println("Estado escolhido: " + estado);
		
		Document doc;
		String url;
		String busca = "";
		
		System.out.print("Buscando..");
		int pagina_atual = 1;
		int count = 0;
		while(pagina_atual < 3) {
			if(pagina_atual == 1) {
				url = "http://www.portaldatransparencia.gov.br/cnep";
				doc = Jsoup.connect(url).get();
				System.out.print("....");
			}else {
				url = "http://www.portaldatransparencia.gov.br/cnep?pagina=2";
				doc = Jsoup.connect(url).get();
				System.out.print("...."+"\n");
			}
			
			Elements elements = doc.getElementsByTag("td");
			
			for(Element e: elements) {
				String empresa = e.parent().child(1).text();
				String uf      = e.parent().child(5).text();
				String multa   = e.parent().child(2).text();
				empresa_estado.put(empresa+" "+multa, uf);
			}
			
			for (String empresa : empresa_estado.keySet()) {
				String uf = empresa_estado.get(empresa);
				if(uf.equals(estado)) {
					busca = busca + empresa + " = " + uf +"\n";
					count++;
				}
			}
			
			pagina_atual++;
		}
		
		
		if (count == 0) {
			System.out.println("Nenhuma empresa encontrada para esse estado!");
		} else {
			System.out.print(busca);
			System.out.println(count + " resultado(s) encontrado(s)");
		}
		
	}

}
