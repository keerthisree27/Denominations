package currencyconvert;

public class Denomination implements Comparable{
    private String name;
    private Integer value;
    private Integer weight;
    private String color;

    Denomination(String name, Integer value, Integer weight, String color) {
        this.name = name;
        this.value = value;
        this.color = color;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    @Override
    public int compareTo(Object o) {
        int compareAge = ((Denomination) o).getValue();
        return  compareAge - this.value;
    }

    @Override
    public String toString() {
        return "Denomination{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }

}
