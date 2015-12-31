package pl.sakfa.budget_tracker.currencies.ecb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import pl.sakfa.budget_tracker.currencies.ExchangeRates;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * provides programmatic access to ECB currencies exchange rates
 */
@Component
public class CurrencyProviderECB {
    public static final String LATEST_FEED_URL = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
    public static final String LAST_90_DAYS_FEED_URL = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml";
    public static final String SINCE1999_FEED_URL = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist.xml";

    private HttpClientWrapper httpClientWrapper;

    private static DateTimeFormatter ecbDateFormat = DateTimeFormatter.ISO_LOCAL_DATE;

    @Autowired
    public CurrencyProviderECB(HttpClientWrapper httpClientWrapper) {
        this.httpClientWrapper = httpClientWrapper;
    }

    public void loadLast90Days(ExchangeRates exchangeRates) {
        loadDataFromFeed(LAST_90_DAYS_FEED_URL, exchangeRates);
    }

    public void loadSince1999(ExchangeRates exchangeRates) {
        loadDataFromFeed(SINCE1999_FEED_URL, exchangeRates);
    }

    private void loadDataFromFeed(String url, ExchangeRates exchangeRates) {
        String feed = httpClientWrapper.fetch(url);
        Document parsedFeed = parseFeed(feed);

        Element rootCube = getCubeChildren(parsedFeed.getDocumentElement()).get(0);

        getCubeChildren(rootCube).stream().forEach( dateCube -> {
            LocalDate date = LocalDate.parse(dateCube.getAttribute("time"), ecbDateFormat);
            getCubeChildren(dateCube).stream().forEach( rateCube -> {
                String currency = rateCube.getAttribute("currency");
                BigDecimal rate = new BigDecimal(rateCube.getAttribute("rate"));
                exchangeRates.addEurDataPoint(currency, date, rate);
            });
        });
    }

    private List<Element> getCubeChildren(Element element) {
        List<Element> cubes = new ArrayList<>();
        NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element maybeCube = (Element) node;
                if (maybeCube.getTagName().equals("Cube")) {
                    cubes.add(maybeCube);
                }
            }
        }
        return cubes;
    }

    private Document parseFeed(String feed) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            return dbFactory.newDocumentBuilder().parse(new InputSource(new StringReader(feed)));
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse currency feed", e);
        }
    }
}
