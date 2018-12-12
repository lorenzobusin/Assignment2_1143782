////////////////////////////////////////////////////////////////////
// [LORENZO] [BUSIN] [1143782]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;


public class MenuItem {
    
    public enum tipo{Pizza, Primo}
    
    public tipo itemType;
    public String name;
    public double price;
    
    
   public MenuItem() {}
   
   public void setPizza(String name, double price){
       this.itemType = tipo.Pizza;
       this.name = name;
       this.price = price;
   }
   
   public void setPrimo(String name, double price){
       this.itemType = tipo.Primo;
       this.name = name;
       this.price = price;
   }
   
   public tipo getType(){
       return this.itemType;
   }
   
   public double getPrice(){
       return this.price;
   }
    
}
