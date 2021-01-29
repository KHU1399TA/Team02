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
        this.Ingredients = ingredients;
        this.isAvailable = isAvailable;
    }
    public String isAvailable(boolean bool){
        if(bool) return "Available";
        return "notAvailable";
    }
    public void addIngredients(String ingrediants) {
        Ingredients.add(ingrediants);
    }
    public String myInglist(ArrayList<String> Ingredients){
        String s="";
        for(String i : Ingredients){
            s+=i+",";
        }
        return s;
    }
    @Override
    public String toString() {
        return id + "_" + name + "_" + myInglist(Ingredients) + "_" + isAvailable+"_";
    }
    public String toString_show() {
        return "food number "+id + ": " + name  + "  " + isAvailable(isAvailable)+ "    Ingredients: " + myInglist(Ingredients);
    }
}