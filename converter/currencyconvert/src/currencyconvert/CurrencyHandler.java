package currencyconvert;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CurrencyHandler extends DefaultHandler {
    private List<Currency> currencyList = new ArrayList<>();
    private List<Denomination> denominations = null;

    private String currencyName = null;

    private String denominationsName;
    private Integer denominationsValue;
    private Integer denominationsWeight;
    private String denominationsColor;

    private boolean atDenominationElement = false;


    public List<Currency> getCurrencies() {
        return  currencyList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if (qName.equalsIgnoreCase("currency")) {
    	denominations=new ArrayList<>();
        currencyName = attributes.getValue("name");
    } else if (qName.equalsIgnoreCase("denomination")) {
        denominationsValue = Integer.parseInt(attributes.getValue("value"));
        denominationsWeight = Integer.parseInt(attributes.getValue("weightInGrams"));
        denominationsColor = attributes.getValue("color");
        atDenominationElement = true;
    }
}

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("currency")) {
            Currency currency = new Currency(currencyName, denominations);
            currencyList.add(currency);
        } else if (qName.equalsIgnoreCase("denomination")) {
            Denomination denomination = new Denomination(denominationsName, denominationsValue, denominationsWeight, denominationsColor);
            denominations.add(denomination);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (atDenominationElement) {
            denominationsName = new String(ch, start, length);
            atDenominationElement = false;
        }
    }
}


