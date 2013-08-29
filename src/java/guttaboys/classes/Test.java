package guttaboys.classes;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.rowset.CachedRowSet;

@Named
@ApplicationScoped
public class Test implements Serializable {
    private String databasedriver = "org.apache.derby.jdbc.ClientDriver";
    private String databasename = "jdbc:derby://db.stud.aitel.hist.no/13ing1gr12;user=team12;password=Ggt54Wr";
    //private String databasename = "jdbc:derby://localhost:1527/Systemering2013";
    private String halp;
    private boolean checkbox =false;
    private ArrayList<Order> orderlist = new ArrayList<Order>();
    private ArrayList<Dish> dishlist = new ArrayList<Dish>();
    private ArrayList<Ingredient> ingredientlist = new ArrayList<Ingredient>();
    private User user = new User(0, "","","","","",false);
    private Dish helpDish = new Dish("");
    private Ingredient ingrHelp = new Ingredient();
    private ArrayList<String> helpIngr = new ArrayList<String>();
    private String password ="";
    private String username = "";
    private boolean isLoggedIn=false;
    private String adress;
    private int postnr;

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPostnr(int postnr) {
        this.postnr = postnr;
    }

    public String getAdress() {
        return adress;
    }

    public int getPostnr() {
        return postnr;
    }

    
    
    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    
    
    public void setHelpIngr(ArrayList<String> helpIngr) {
        this.helpIngr = helpIngr;
    }

    public ArrayList<String> getHelpIngr() {
        return helpIngr;
    }
    
    
    public ArrayList<Ingredient> getIngredientlist() {
        return ingredientlist;
    }
    
    public void setIngrHelp(Ingredient nIngr){
        ingrHelp = nIngr;
    }
    
    public Ingredient getIngrHelp(){
        return ingrHelp;
    }

    public void setIngredientlist(ArrayList<Ingredient> ingredientlist) {
        this.ingredientlist = ingredientlist;
    }
    
    public Dish getHelpDish(){
        return helpDish;
    }
    
    public void setHelpDish(Dish otherDish){
        helpDish = otherDish;
    }
    
    public Test() throws SQLException, ClassNotFoundException{
        getStartDishList();
    }
    
    
    public ResultSet getAll() throws SQLException,ClassNotFoundException{
            Class.forName(databasedriver);
            Connection c = DriverManager.getConnection(databasename);
         try{
            
            
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from dish");
            CachedRowSet crs = new com.sun.rowset.CachedRowSetImpl();
            crs.populate(rs);
            return crs;
        
        }finally{
            c.close(); 
         }
        
        
        
    }
    
    public ResultSet getDishIngredients() throws SQLException,ClassNotFoundException{
            Class.forName(databasedriver);
            Connection c = DriverManager.getConnection(databasename);
         try{
            
            
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select ingredientname from dishIngr where dishname = '" + halp+ "'");
            CachedRowSet crs = new com.sun.rowset.CachedRowSetImpl();
            crs.populate(rs);
            return crs;
        
        }finally{
            c.close(); 
         }
    
     }  
    
    public ArrayList<Ingredient> getIngredients() throws SQLException,ClassNotFoundException{
            Class.forName(databasedriver);
            Connection c = DriverManager.getConnection(databasename);
            Ingredient i = new Ingredient();
         try{
            ingredientlist = new ArrayList<Ingredient>();
            
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from ingredient");
            while(rs.next()){
               i.setName(rs.getString("ingredientname"));
               i.setDescription(rs.getString("description"));
               ingredientlist.add(i);
               i = new Ingredient();
            }
        return ingredientlist;
        }finally{
            c.close(); 
         }
    
     }   
        
         public String jumpToIngredients(String i) throws SQLException, ClassNotFoundException{
            Class.forName(databasedriver);
            Connection c = DriverManager.getConnection(databasename);
         try{
            
            
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select *  from dishIngr where dishname = '" + i + "'");
            CachedRowSet crs = new com.sun.rowset.CachedRowSetImpl();
            crs.populate(rs);
            while(crs.next()){
                if(crs.getString("dishname").equals(i)){ 
                    halp = i;
                    return "ingredient";
                }
            }
           return "index";
        
        }finally{
            c.close(); 
         } 
         
    }
         
    public boolean isEditable(){
       return checkbox;
    }
        
    public void setEditable(boolean b){
       checkbox = b;
    }
    
    public void makeOrder(String dishname, int number){
        for(int i = 0; i < orderlist.size(); i++){
            Order o = (Order)orderlist.get(i);
            if(o.getDishname().equals(dishname) || number <= 0){
                return;
            }
        }
        if(!(number <= 0)){
        orderlist.add(new Order(number, dishname));
        }
    }
    
    public ArrayList<Order> getOrderList(){
        return orderlist;
    }
    
    public void getStartDishList() throws SQLException, ClassNotFoundException{
        ResultSet r = getAll();
        while(r.next()){
            helpDish = new Dish(r.getString("dishname"));
            helpDish.setPrice(r.getInt("price"));
            dishlist.add(helpDish);
            helpDish=new Dish("");
                    
        }
    }
    
     public ArrayList<Dish> getDishList(){
         return dishlist;
     }
    public synchronized String putInDatabase()throws SQLException, ClassNotFoundException{
         Class.forName(databasedriver);
         Connection c = DriverManager.getConnection(databasename);
         try{
            c.setAutoCommit(false);
            PreparedStatement s = c.prepareStatement("insert into orders values (default, ?,?)");
            PreparedStatement z = c.prepareStatement("insert into usersOrders values(? , ?)");
            PreparedStatement y = c.prepareStatement("insert into ordDish values(?, ?,?)");
            s.setString(1, adress);
            s.setInt(2, postnr);
            s.execute();
            Statement l = c.createStatement();
            ResultSet r = l.executeQuery("select count(*)\"Count\" from orders");
            int i=0;
            while(r.next()){
                 i = r.getInt("Count");
            }
            z.setInt(1, i);
            z.setInt(2, user.getUsernr());
            z.execute();
            for(Order o : orderlist){
                y.setInt(1, i);
                y.setString(2, o.getDishname());
                y.setInt(3, o.getNumber());
                y.execute();
            }
            
            orderlist.clear();
            dishlist.clear();
            getStartDishList();
            c.commit();
            return "index";
         
         }catch(Exception e){
             c.rollback();
         }finally{
             c.setAutoCommit(true);
             c.close();
             
         }
         return "";
     }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public ResultSet getOrderHistory() throws SQLException,ClassNotFoundException{
            Class.forName(databasedriver);
            Connection c = DriverManager.getConnection(databasename);
         try{
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select orddish.ORDERNR, orddish.DISHNAME, orddish.DISHESORDERED from "
                    + "ordDish, orders, usersorders where orddish.ORDERNR = orders.ORDERNR and usersorders.ORDERNR\n" 
                    + "= orders.ORDERNR and usersorders.USERNR = " + user.getUsernr());
            CachedRowSet crs = new com.sun.rowset.CachedRowSetImpl();
            crs.populate(rs);
            return crs;
         }finally{
             c.close();
         }
    }
    
    public synchronized String inputDish() throws SQLException, ClassNotFoundException{
        if(!helpDish.getDishname().equals("")){
            dishlist.add(helpDish);
            addDish(helpDish);
            helpDish = new Dish("");
            return "index";
        }
        return "index";
    }
    
    
    private synchronized void addDish(Dish d) throws SQLException, ClassNotFoundException{
            Class.forName(databasedriver);
            Connection c = DriverManager.getConnection(databasename);
         try{
            if(!d.getDishname().equals("")){
                c.setAutoCommit(false);
                PreparedStatement t = c.prepareStatement("insert into dish values(?,?)"); 
                PreparedStatement s = c.prepareStatement("insert into dishIngr values(?, ?)"); 
                t.setString(1, d.getDishname());
                t.setInt(2, d.getPrice());
                t.execute();
                for(String y: helpIngr){
                    s.setString(1, d.getDishname());
                    s.setString(2, y);
                    s.execute();
                
                }
                c.commit();
            }
         }catch(Exception e){
             c.rollback();
         
         }finally{
             c.setAutoCommit(true);
             c.close();
             
         }
    
    }
    
    private synchronized void addIngredient(Ingredient i) throws SQLException, ClassNotFoundException{
        Class.forName(databasedriver);
            Connection c = DriverManager.getConnection(databasename);
         try{
            c.setAutoCommit(false);
            PreparedStatement t = c.prepareStatement("insert into ingredient values(?,?)"); 
            if(!i.getName().equals("")){
                t.setString(1, i.getName());
                t.setString(2, i.getDescription());
                t.execute();
                c.commit();
            }
         }catch(Exception e){
             c.rollback();
         
         }finally{
             c.setAutoCommit(true);
             c.close();
             
         }
    }
    
    public synchronized String inputIngredient(boolean b) throws SQLException, ClassNotFoundException{
        if(!ingrHelp.getName().equals("")){
            ingredientlist.add(ingrHelp);
            addIngredient(ingrHelp);
            ingrHelp = new Ingredient();
            if(b == true) return "index";
            return "";
        }
         if(b == true) return "index";
         return "";
    }
    
    public String checkPswrd() throws SQLException, ClassNotFoundException{
            Class.forName(databasedriver);
            Connection c = DriverManager.getConnection(databasename);
         try{
            c.setAutoCommit(false);
            Statement t = c.createStatement(); 
            ResultSet r = t.executeQuery("select * from users");
            while(r.next()){
                String dbUser = r.getString("username");
                String dbPswrd = r.getString("password"); 
                if(dbUser.equals(username) && dbPswrd.equals(password)){
                    this.user.setEmail(r.getString("email"));
                    this.user.setFirstName(r.getString("firstName"));
                    this.user.setUsernr(r.getInt("usernr"));
                    this.user.setSurName(r.getString("surName"));
                    this.user.setUsername(r.getString("username"));
                    this.user.setIsEmployee(r.getBoolean("isEmployee"));
                    isLoggedIn = true;
                    return "index";
                }
            }
         }catch(Exception e){
             c.rollback();
         
         }finally{
             c.setAutoCommit(true);
             c.close();
             
         }
         return "";
    }
    
    public String deleteRow(Order rowToDelete){
        orderlist.remove(rowToDelete);
        return null;
    }
    
    public int getTotalPrice(){
        int totalprice = 0;
        for(Dish d : dishlist){
            for(Order o : orderlist){
                if(o.getDishname().equals(d.getDishname())){
                    totalprice += d.getPrice() * o.getNumber();
                }
            }
        }
        return totalprice;
    }
    
    public String logout(){
        isLoggedIn = false;
        return "login.xhtml";
    }
    
    public String addUser() throws SQLException, ClassNotFoundException{
        Class.forName(databasedriver);
        Connection c = DriverManager.getConnection(databasename);
        try{
            c.setAutoCommit(false);
            PreparedStatement t = c.prepareStatement("insert into users values(default, ?, ?, ?, ?, ? , 0)"); 
            if(user.getEmail().equals(user.getConfirmemail()) && user.getPassword().equals(user.getConfirmpassword())){
                t.setString(1, user.getEmail());
                t.setString(2, user.getFirstName());
                t.setString(3, user.getSurName());
                t.setString(4, user.getUsername());
                t.setString(5, user.getPassword());
                t.execute();
                c.commit();
                return "regConfirmation";
            }
            return "";
         }catch(Exception e){
             c.rollback();
         
         }finally{
             c.setAutoCommit(true);
             c.close();
             
         }
        return ""; 
    }
    
    public String backToLogin(){
        user = new User(0,"","","","","", false);
        return "login";
    }
    
    public ArrayList<Order> getDishesToBeMade() throws SQLException, ClassNotFoundException{
        Class.forName(databasedriver);
        Connection c = DriverManager.getConnection(databasename);
        ArrayList<Order> list = new ArrayList<Order>();
        String dishname = "";
        int number = 0; 
        
        try{
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("select * from dishesToBeMade");
            while(r.next()){
                dishname = r.getString("dishname");
                number = r.getInt("dishesordered");
                
                list.add(new Order(number, dishname));
            }
        
        return list;
        }finally{
        c.close();
        }
    }
    
    public void deleteDishesToBeMade(Order o)throws SQLException, ClassNotFoundException{
        Class.forName(databasedriver);
        Connection c = DriverManager.getConnection(databasename);
        try{
            PreparedStatement s = c.prepareStatement("delete from dishesToBeMade where ordernr = ?");
            s.setInt(1, o.getNumber());
            s.execute();
            
        }finally{
            c.close();
        }
    }
}
