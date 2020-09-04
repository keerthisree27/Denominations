package currencyconvert;

import java.util.List;

public class Currency {
    private String name;
    private List<Denomination> denominations;

    public Currency(String name, List<Denomination> denominations) {
        this.name = name;
        this.denominations = denominations;
    }

    public String getName() {
        return name;
    }

    public List<Denomination> getDenominations() {
        return denominations;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", denominations=" + denominations +
                '}';
    }
}
