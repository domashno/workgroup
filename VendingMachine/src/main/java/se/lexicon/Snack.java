package se.lexicon;

public class Snack extends Product{
    private int weightGrams;

    //Constructor

    public Snack(int id, String name, int price, int quantity, int weightGrams) {
        super(id,name.price,quantity);
        this.weightGrams = weightGrams;
    }

    @Override
    public String getDescription(){
        return getName() + "(Snack, "+ weightGrams + "g)";
    }
}
