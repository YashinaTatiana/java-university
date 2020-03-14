package utils;

import exception.MobileBankException;
import model.AccCode;

import java.math.BigDecimal;

import static exception.MobileBankErrorCode.INVALID_CURRENCY;
import static model.AccCode.*;
import static utils.Constants.*;
import static utils.Constants.USD2EUR;

public class CurrencyConverter {

    public static BigDecimal convert(AccCode accCodeFrom, AccCode accCodeTo, BigDecimal amount)
            throws MobileBankException {
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
                throw new MobileBankException(INVALID_CURRENCY);
            }
        }
        return amount.multiply(BigDecimal.valueOf(equivalent));
    }
}
