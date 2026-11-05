package se.lexicon;

public class Beverage extends Product{

        private int volumeMl;

        public Beverage(int id, String name, int price, int quantity, int volumeMl) {

            super(id, name, price, quantity);
            this.volumeMl = volumeMl;
        }

        @Override
        public String getDescription() {
            return getName() + " (Beverage, "
                    + volumeMl + "ml)";
        }
    }

