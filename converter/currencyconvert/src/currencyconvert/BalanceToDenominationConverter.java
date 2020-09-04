package currencyconvert;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class BalanceToDenominationConverter {
    public static void main(String []args){
        List<Currency> currencies = fetchCurrencies();
        System.out.println(convertBalanceToDenominations(findCurrency(currencies, "USD"), 287));
    }

    public static String convertBalanceToDenominations(Currency currency, Integer balance) {
        List<Denomination> denominations =  currency.getDenominations();

        Collections.sort(denominations);

        String denominationString = "";

        Integer remainingBalance = balance;
        for(Denomination denomination: denominations) {
            Integer denominationValue = denomination.getValue();
            if(remainingBalance >= denominationValue) {
                denominationString = denominationString + " " + denomination.getName() + ": " + remainingBalance/denominationValue+",";
                remainingBalance = remainingBalance%denominationValue;
            }
        }
        return "The balance of " + balance + " can be converted to " + currency.getName() + " denominations of" + denominationString;
    }

    public static List<Currency> fetchCurrencies() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            CurrencyHandler handler = new CurrencyHandler();
            saxParser.parse(new File("currencies.xml"), handler);
            return handler.getCurrencies();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Currency findCurrency(List<Currency> currencies, String name ){
        for (Currency currency : currencies) {
            if (currency.getName().equals(name)) {
                return currency;
            }
        }
        return null;
    }
}


