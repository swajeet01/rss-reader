
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Reads news from <b>RSS</b> feed
 * @author swajeetg768
 */
public class News {
	private static String feeds = "http://feeds.bbci.co.uk/news/rss.xml";
	public static void main(String[] args) {
		if(args.length == 2)
			feeds = args[1];
		try {
			URL url = new URL(feeds);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(urlConnection.getInputStream());
			NodeList nodeList = doc.getElementsByTagName("item");
			Node next = nodeList.item(0);
			Scanner s = new Scanner(System.in);
			System.out.println("hit <return> to read next news");
			while (next != null) {
				System.out.println(String.join(System.lineSeparator(),
						next.getTextContent().split("\\s{2,}")));
				next = next.getNextSibling();
				try {
					s.nextLine();
				} catch (NoSuchElementException e) { return; }
			}
		} catch (IOException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
	}
}
