////////////////////////////////////////////////////////////////////
// [LORENZO] [BUSIN] [1143782]
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import it.unipd.tos.business.RestaurantBill;
import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class PizzeriaBellaNapoli implements RestaurantBill{
    
    public List<MenuItem> items;
    
    public PizzeriaBellaNapoli(List<MenuItem> clone){
        this.items = clone;
    }

    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered) throws RestaurantBillException {
        if(itemsOrdered.size() > 20) //se vengono ordinati pi� di 20 item lancia un'eccezione
            throw new RestaurantBillException("ERRORE");
        
        double tot = itemsOrdered.stream().collect(Collectors.summingDouble(x -> x.getPrice()));
        
        List<MenuItem> getPizzeFromOrder = new ArrayList<>();
        getPizzeFromOrder = itemsOrdered.stream().filter(x -> x.getType().toString().equals("Pizza")).collect(Collectors.toList());
        
        if(getPizzeFromOrder.size() > 10){ // rimuove il prezzo della pizza che costa meno se l'ordine ne contiene pi� di 10
            double min = getPizzeFromOrder.stream().mapToDouble(x -> x.getPrice()).min().getAsDouble();
            tot -= min;
        }

        if(tot > 100) // sconto 5% se totale supera 100 
            tot *= 0.95;
        
        return tot;
    }
    
}
