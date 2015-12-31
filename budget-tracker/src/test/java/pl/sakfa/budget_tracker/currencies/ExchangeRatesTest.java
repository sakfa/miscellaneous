package pl.sakfa.budget_tracker.currencies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

public class ExchangeRatesTest {
    ExchangeRates exchangeRates;

    @Before
    public void setUp() {
        exchangeRates = new ExchangeRates();
        exchangeRates.addEurDataPoint("PLN", LocalDate.of(2015, 3, 1), new BigDecimal("4.20"));
        exchangeRates.addEurDataPoint("PLN", LocalDate.of(2015, 3, 3), new BigDecimal("4.25"));
        exchangeRates.addEurDataPoint("PLN", LocalDate.of(2015, 3, 4), new BigDecimal("4.22"));
        exchangeRates.addEurDataPoint("PLN", LocalDate.of(2015, 4, 1), new BigDecimal("4.10"));
        exchangeRates.addEurDataPoint("USD", LocalDate.of(2015, 3, 1), new BigDecimal("1.1"));
        exchangeRates.addEurDataPoint("USD", LocalDate.of(2015, 3, 3), new BigDecimal("1.11"));
        exchangeRates.addEurDataPoint("USD", LocalDate.of(2015, 3, 4), new BigDecimal("1.12"));
    }

    @Test
    public void testGetContainedCurrencies() {
        Assert.assertEquals(new HashSet<>(Arrays.asList("PLN", "USD")), exchangeRates.getKnownCurrencies());
    }

    @Test
    public void testGetRateExact() {
        Assert.assertEquals(new BigDecimal("4.20"), exchangeRates.getRate(LocalDate.of(2015, 3, 1), "PLN"));
    }

    @Test
    public void testGetRateGap() {
        Assert.assertEquals(new BigDecimal("4.225"), exchangeRates.getRate(LocalDate.of(2015, 3, 2), "PLN"));
    }

    @Test
    public void testGetRateGap2() {
        Assert.assertEquals(new BigDecimal("4.13429"), exchangeRates.getRate(LocalDate.of(2015, 3, 24), "PLN"));
    }

    @Test
    public void testGetRatePast() {
        try {
            exchangeRates.getRate(LocalDate.of(2015, 2, 28), "PLN");
            Assert.fail("Expected exception but none thrown.");
        } catch (RuntimeException e) {
            Assert.assertEquals("No data for currency PLN on 2015-02-28. Earliest data point available is 2015-03-01.", e.getMessage());
        }

    }

    @Test
    public void testGetRateFuture() {
        Assert.assertEquals(new BigDecimal("4.10"), exchangeRates.getRate(LocalDate.of(2015, 4, 2), "PLN"));
    }

    @Test
    public void testNoSuchCurrency() {
        try {
            exchangeRates.getRate(LocalDate.of(2015, 3, 1), "CAD");
            Assert.fail("Expected exception but none thrown.");
        } catch (RuntimeException e) {
            Assert.assertEquals("No data for currency CAD", e.getMessage());
        }
    }


}
