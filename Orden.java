import java.util.ArrayList;

public class Orden {
    // Atributos cliente y estado, que son adicionales.
    private int numOrden;
    private String cliente;
    //Renombré el array a pizzas
    private ArrayList<Pizza> pizzas;
    private String estado; // El estado de la orden

    public Orden(int numOrden) {
        this.numOrden = numOrden;
        this.cliente = "Cliente general";
        this.pizzas = new ArrayList<>();
        this.estado = "Pendiente";
    }

    public Orden(int numOrden, String cliente) {
        this.numOrden = numOrden;
        this.cliente = cliente;
        this.pizzas = new ArrayList<>();
        this.estado = "Pendiente";
    }
 //3. Se implementó el método 'agregarPizza(Pizza pizza)' para permitir armar pedidos con una o varias pizzas.

    public void agregarPizza(Pizza pizza) {
        if (pizza != null) {
            this.pizzas.add(pizza);
        }
    }

    public int getNumOrden() {
        return numOrden;
    }

    public void setNumOrden(int numOrden) {
        this.numOrden = numOrden;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Orden #").append(numOrden)
          .append(" [Cliente: ").append(cliente)
          .append(", Estado: ").append(estado)
          .append(", Cantidad de Pizzas: ").append(pizzas.size()).append("]");
        for (int i = 0; i < pizzas.size(); i++) {
            sb.append("\n    -> Pizza ").append(i + 1).append(": ").append(pizzas.get(i).toString());
        }
        return sb.toString();
    }
}
