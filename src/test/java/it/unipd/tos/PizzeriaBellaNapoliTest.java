////////////////////////////////////////////////////////////////////
// [LORENZO] [BUSIN] [1143782]
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

public class PizzeriaBellaNapoliTest {
    
    public PizzeriaBellaNapoliTest() {
    }
    
    
    @Test //testa il calcolo del conto
    public void getOrderPrice_simpleValues_computeBill() throws RestaurantBillException {  
        
        List<MenuItem> items = new ArrayList<>();
        
        MenuItem m1 = new MenuItem();
        m1.setPizza("Margherita", 5.00);
        
        MenuItem m2 = new MenuItem();
        m2.setPizza("Marinara", 4.00);
        
        MenuItem m3 = new MenuItem();
        m3.setPizza("Capricciosa", 6.50);
        
        MenuItem m4 = new MenuItem();
        m4.setPrimo("Spaghetti alla Carbonara", 7.00);
        
        MenuItem m5 = new MenuItem();
        m5.setPizza("Quattro stagioni", 7.00);
        
        items.add(m1);
        items.add(m2);
        items.add(m3);
        items.add(m4);
        items.add(m5);
        
        PizzeriaBellaNapoli test = new PizzeriaBellaNapoli(items);

        assertEquals(29.50, test.getOrderPrice(items));
    }
    
        @Test(expected = RestaurantBillException.class) //testa se viene sollevata l'eccezione
    public void getOrderPrice_itemsNumberMoreThan20_exceptionThrown() throws RestaurantBillException { 
        
        List<MenuItem> items = new ArrayList<>();
        MenuItem m1 = new MenuItem();
        m1.setPizza("Margherita", 5.00);
        
        for(int i = 0; i < 25; i++){
            items.add(m1);
        }
        
        PizzeriaBellaNapoli test = new PizzeriaBellaNapoli(items);
        
        test.getOrderPrice(items);
    }
    
    @Test //testa che il prezzo della pizza che costa meno venga rimosso se l'ordine ne contiene piu di 10
    public void getOrderPrice_itemsPizzaMoreThan10_applyReduction() throws RestaurantBillException{
        List<MenuItem> items = new ArrayList<>();
        MenuItem m1 = new MenuItem();
        m1.setPizza("Margherita", 5.00);
        
        for(int i = 0; i < 10; i++){
            items.add(m1);
        }
        
        MenuItem m2 = new MenuItem();
        m2.setPizza("Marinara", 4.50);
        items.add(m2);
        
        PizzeriaBellaNapoli test = new PizzeriaBellaNapoli(items);
        
        assertEquals(50.00, test.getOrderPrice(items));
    }
    

    @Test //testa che il prezzo della pizza che costa meno venga rimosso se l'ordine ne contiene piu di 10
    public void getOrderPrice_billMoreThan100_applyReduction() throws RestaurantBillException{
        List<MenuItem> items = new ArrayList<>();
        MenuItem m1 = new MenuItem();
        m1.setPizza("Margherita", 5.00);
        MenuItem m2 = new MenuItem();
        m2.setPrimo("Spaghetti alla carbonara", 7.50);
        
        for(int i = 0; i < 10; i++){
            items.add(m1);
            items.add(m2);
        }
        
        PizzeriaBellaNapoli test = new PizzeriaBellaNapoli(items); //totale senza sconto: 125.00

        assertEquals(118.75, test.getOrderPrice(items));  //totale con lo sconto: 118.75
    }
    
    @Test //testa che il prezzo della pizza che costa meno venga rimosso se l'ordine ne contiene piu di 10 e
          //che applichi lo sconto del 5% per ordini superiori a 100
    public void getOrderPrice_billMoreThan100AndItemsPizzaMoreThan10_applyFullReduction() throws RestaurantBillException{
        List<MenuItem> items = new ArrayList<>();
        MenuItem m1 = new MenuItem();
        m1.setPizza("Margherita", 5.00);
        MenuItem m2 = new MenuItem();
        m2.setPizza("Marinara", 4.50);
        MenuItem m3 = new MenuItem();
        m3.setPrimo("Spaghetti allo scoglio", 16.00);
        
        for(int i = 0; i < 8; i++)
            items.add(m3);
        
        for(int i = 0; i < 10; i++)
            items.add(m1);
        
        items.add(m2);
        
        PizzeriaBellaNapoli test = new PizzeriaBellaNapoli(items); //totale senza sconto: 182.5

        assertEquals(169.1, test.getOrderPrice(items));  //totale con lo sconto: 
    }
    

}
