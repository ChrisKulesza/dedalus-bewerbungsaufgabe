package org.currency_denomination.feature.domain;

/**
 * Enum representing different currencies.
 * Each currency has a specific symbol associated with it.
 */
public enum Currency {
    EURO("€");

    private final String symbol;
    
    Currency(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbol of the currency.
     *
     * @return the symbol of the currency as a {@link String}
     */
    public String getSymbol() {
        return symbol;
    }
}
