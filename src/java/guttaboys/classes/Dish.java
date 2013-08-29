/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guttaboys.classes;

import java.util.ArrayList;

/**
 *
 * @author Glenn
 */
public class Dish {
    private String dishname;
    private boolean editable;
    private int price;
    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    int number = 0;

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public Dish(String dishname){
        this.dishname=dishname;
    }
    
    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
    
    public void addIngredient(String ingredientname){
        Ingredient i = new Ingredient();
        i.setName(ingredientname);
        ingredients.add(i);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    
    
    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getDishname() {
        return dishname;
    }

    public boolean isEditable() {
        return editable;
    }
    
}
