package main.others;
import java.util.ArrayList;
public class Food {
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
    public String myInglist(ArrayList<String> Ingredients){
        String s="";
        for(String i : Ingredients){
            s+=i+",";
        }
        s+="\b";
        return s;
    }
    @Override
    public String toString() {
        return id + "_" + name + "_" + myInglist(Ingredients) + "_" + isAvailable+"_";
    }
}