package terceiraquestao;
//Acesse o site www.pi.gov.br e extraia das 5 mat�rias da capa
//O link da mat�ria
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Dom2 {
	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://www.pi.gov.br/").get();
		
		Elements contents = doc.getElementsByTag("h4");
		for (Element content: contents) {
			String linkText = content.parent().attr("href");
			System.out.println(linkText);
		}

	}

}