package pl.sakfa.budget_tracker.currencies.ecb;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.protocol.HTTP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class HttpClientWrapper {
    private static final Logger logger = LogManager.getLogger(HttpClientWrapper.class);

    public String fetch(String url) {
        try {
            Request request = Request.Get(url);

            String proxy = System.getenv("http_proxy");

            if (proxy != null) {
                if (proxy.endsWith("/")) {
                    proxy = proxy.substring(0, proxy.length() - 1);
                }

                logger.info("Will fetch feed via proxy {} because http_proxy environment variable is set.", proxy);
                request.viaProxy(proxy);
            }

            logger.info("Fetching feed {}", url);
            Content content = request.execute().returnContent();
            logger.debug("Fetched feed, content type is {}, charset is {}, content size is {} bytes",
                    () -> content.getType(),
                    () -> content.getType().getCharset(),
                    () -> content.asBytes().length);

            //no need to specify charset here as it is derived from Content-Type header or defaulted to iso-8859-1
            return content.asString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch feed from " + url, e);
        }
    }
}
