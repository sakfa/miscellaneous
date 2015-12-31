package pl.sakfa.budget_tracker.currencies.ecb;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EurExchangeRatesInDate {
    private LocalDate date;
    private Map<String, Double> rates;

    public EurExchangeRatesInDate(LocalDate date, Map<String, Double> rates) {
        this.date = date;
        this.rates = rates;
    }

    public LocalDate getDate() {
        return date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }


    @Override
    public String toString() {
        return String.format("Rates in %s: %s",
                date.toString(),
                rates.entrySet().stream().map( entry ->
                        entry.getKey() + "=" + entry.getValue()
                ).collect(Collectors.joining(", "))
        );
    }
}
