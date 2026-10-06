public class Topping {
    //Cambié atributo ambiguo para especificar el nombre y la cantidad de toppings que desea el usuario
    private String nombre;
    private int cantidad;

    //Actualicé el constructor para instanciar toppings por nombre y cantidad
    public Topping(String nombre) {
        this.nombre = nombre;
        this.cantidad = 1;
    }

    public Topping(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        if (cantidad > 1) {
            return nombre + " (x" + cantidad + ")";
        }
        return nombre;
    }
}