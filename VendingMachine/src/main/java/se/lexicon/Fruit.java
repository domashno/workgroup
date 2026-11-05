package se.lexicon;

public class Fruit extends Product{

    private String origin;
    //constructor
    public Fruit(int id, String name, int price, int quantity, String origin) {

        super(id, name, price, quantity);
        this.origin = origin;
    }

    @Override
    public String getDescription() {
        return getName() + " (Fruit, " + origin + ")";
    }

}
