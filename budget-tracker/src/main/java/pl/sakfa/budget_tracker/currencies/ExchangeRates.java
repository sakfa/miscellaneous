package pl.sakfa.budget_tracker.currencies;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ExchangeRates {
    private Map<String, SortedMap<LocalDate, BigDecimal>> eurRatesMap = new HashMap<>();

    public void addEurDataPoint(String currencyIsoCode, LocalDate localDate, BigDecimal rate) {
        if (!eurRatesMap.containsKey(currencyIsoCode)) {
            eurRatesMap.put(currencyIsoCode, new TreeMap<>());
        }

        eurRatesMap.get(currencyIsoCode).put(localDate, rate);
    }

    public Set<String> getKnownCurrencies() {
        return eurRatesMap.keySet();
    }

    public BigDecimal getRate(LocalDate date, String currencyIsoCode) {
        if (!eurRatesMap.containsKey(currencyIsoCode)) {
            throw new RuntimeException(String.format("No data for currency %s", currencyIsoCode));
        }

        SortedMap<LocalDate, BigDecimal> rates = eurRatesMap.get(currencyIsoCode);
        if (rates.containsKey(date)) {
            return rates.get(date);
        } else if (date.isBefore(rates.firstKey())) {
            throw new RuntimeException(String.format("No data for currency %s on %s. Earliest data point available is %s.", currencyIsoCode, date, rates.firstKey()));
        } else if (date.isAfter(rates.lastKey())) {
            return rates.get(rates.lastKey());
        } else {
            LocalDate before = rates.headMap(date).lastKey();
            LocalDate after = rates.tailMap(date).firstKey();
            double rateBefore = rates.get(before).doubleValue();
            double rateAfter = rates.get(after).doubleValue();

            double slope = (rateAfter - rateBefore) / (ChronoUnit.DAYS.between(before, after));
            return BigDecimal.valueOf(rateBefore + slope * ChronoUnit.DAYS.between(before, date))
                    .setScale(5, BigDecimal.ROUND_HALF_UP)
                    .stripTrailingZeros();
        }
    }
}
