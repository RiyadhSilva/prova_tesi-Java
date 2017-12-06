package terceiraquestao;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//Acesse o site www.pi.gov.br e extraia das 5 matérias da capa
// Acesse o link da matéria e baixe as tags
public class Dom3 {
	public static void main(String[] args) throws IOException {
		String url = "http://www.pi.gov.br/";
		Document doc = Jsoup.connect(url).get();
		
		Elements contents = doc.getElementsByTag("h4");
		for (Element content: contents) {
			String linkText = content.parent().attr("href");
			System.out.println("Tags do Link: "+linkText+"\n");
			Document document = Jsoup.connect(url+linkText).get();
			Elements elements = document.getElementsByClass("btn-tags");
			for (Element e: elements) {
				String tag = e.text();
				System.out.println(tag);
			}
			System.out.println("\n");
		}

	}

}
