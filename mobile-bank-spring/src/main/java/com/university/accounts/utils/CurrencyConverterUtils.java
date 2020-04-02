package com.university.accounts.utils;

import com.university.accounts.entity.AccCode;

import java.math.BigDecimal;

import static com.university.accounts.entity.AccCode.*;

public class CurrencyConverterUtils {

    private static final double USD2RUB = 67;
    public static final double EUR2RUB = 73;
    public static final double USD2EUR = 0.89;

    public static BigDecimal convert(AccCode accCodeFrom, AccCode accCodeTo, BigDecimal amount)  {
        double equivalent = 1;
        switch (accCodeFrom) {
            case RUB: {
                if (EUR.equals(accCodeTo)) {
                    equivalent = 1 / EUR2RUB;
                } else if (USD.equals(accCodeTo)) {
                    equivalent = 1 / USD2RUB;
                }
                break;
            }
            case EUR: {
                if (USD.equals(accCodeTo)) {
                    equivalent = 1 / USD2EUR;
                } else if (RUB.equals(accCodeTo)) {
                    equivalent = EUR2RUB;
                }
                break;
            }
            case USD: {
                if (RUB.equals(accCodeTo)) {
                    equivalent = USD2RUB;
                } else if (EUR.equals(accCodeTo)) {
                    equivalent = USD2EUR;
                }
                break;
            } default: {
                break;
            }
        }
        return amount.multiply(BigDecimal.valueOf(equivalent));
    }
}
