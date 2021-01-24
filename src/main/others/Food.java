package main.others;
import java.util.ArrayList;
class Food {
    public int id;
    public String name;
    public ArrayList<String> Ingredients;
    public boolean isAvailable;
    public Food(int id, String name, ArrayList<String> ingredients, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.Ingredients = new ArrayList<String>();
        this.isAvailable = isAvailable;
    }

    public void addIngredients(String ingrediants) {
        Ingredients.add(ingrediants);
    }

    @Override
    public String toString() {
        return "Food [Ingredients=" + Ingredients + ", id=" + id + ", isAvailable=" + isAvailable + ", name=" + name
                + "]";
    }
}