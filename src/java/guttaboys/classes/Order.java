/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guttaboys.classes;

/**
 *
 * @author Glenn
 */
public class Order {
    private int number;
    private String dishname;
    private boolean editable;
    
    public Order(int number, String dishname){
        this.number = number;
        this.dishname = dishname;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public int getNumber() {
        return number;
    }

    public String getDishname() {
        return dishname;
    }
    
    
    
    
    
}
