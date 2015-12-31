package pl.sakfa.budget_tracker.currencies;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import pl.sakfa.budget_tracker.currencies.ecb.CurrencyProviderECB;
import pl.sakfa.budget_tracker.currencies.ecb.EurExchangeRatesInDate;
import pl.sakfa.budget_tracker.currencies.ecb.HttpClientWrapper;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestCurrencyProviderECB {
    private class TestHttpClientWrapper extends HttpClientWrapper {
        private String readFixture(String path) {
            InputStream fixtureIS = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
            if (fixtureIS == null) {
                throw new RuntimeException("Resource not found: " + path);
            }
            String result = null;
            try {
                result = IOUtils.toString(fixtureIS, "UTF-8");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                IOUtils.closeQuietly(fixtureIS);
            }

            return result;
        }

        public String fetch(String url) {
            switch (url) {
                case CurrencyProviderECB.LATEST_FEED_URL:
                    return readFixture("ecb/feedLatest.xml");
                case CurrencyProviderECB.LAST_90_DAYS_FEED_URL:
                    return readFixture("ecb/feed90Days.xml");
                default:
                    throw new RuntimeException("URL not supported: " + url);
            }
        }
    }

    @Test
    public void testGetLast90Days() {
        CurrencyProviderECB currencyProviderECB = new CurrencyProviderECB(new TestHttpClientWrapper());

        ExchangeRates exchangeRates = new ExchangeRates();
        currencyProviderECB.loadLast90Days(exchangeRates);
    }
}
