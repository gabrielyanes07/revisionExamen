import java.util.ArrayList;

public class Pizza {
    private String masa;
    // Cambié el atributo de salsa, para que las personas especifiquen el tipo de salsa
    private String salsa;
    private boolean queso;
    private ArrayList<Topping> toppings;

    public Pizza() {
        this.masa = "Tradicional";
        this.salsa = "Normal";
        this.queso = true;
        //cambié el topping por una lista, para coleccionar todos los ingredientes dinámicos.
        this.toppings = new ArrayList<>();
    }

    // Constructor con masa y salsa
    public Pizza(String masa, String salsa) {
        this.masa = masa;
        this.salsa = salsa;
        this.queso = true;
        this.toppings = new ArrayList<>();
    }

    // Constructor completo (masa, salsa y queso)
    public Pizza(String masa, String salsa, boolean queso) {
        this.masa = masa;
        this.salsa = salsa;
        this.queso = queso;
        this.toppings = new ArrayList<>();
    }

    // Integré sobrecarga en los ingredientes, específicamente en agregarIngrediente, para la cantidad de ingredientes

    public void agregarIngrediente(String ingrediente) {
        this.toppings.add(new Topping(ingrediente, 1));
    }

    public void agregarIngrediente(String ingrediente, int cantidad) {
        this.toppings.add(new Topping(ingrediente, cantidad));
    }

    public void agregarIngrediente(String[] ingredientes) {
        if (ingredientes != null) {
            for (String ing : ingredientes) {
                this.toppings.add(new Topping(ing, 1));
            }
        }
    }

    public void agregarIngrediente(Topping topping) {
        if (topping != null) {
            this.toppings.add(topping);
        }
    }

    public String getMasa() {
        return masa;
    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public String getSalsa() {
        return salsa;
    }

    public void setSalsa(String salsa) {
        this.salsa = salsa;
    }

    public boolean tieneQueso() {
        return queso;
    }

    public void setQueso(boolean queso) {
        this.queso = queso;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }
    //Se implementó toString() para visualizar la masa, salsa, queso y lista de ingredientes.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pizza [Masa: ").append(masa)
          .append(", Salsa: ").append(salsa)
          .append(", Queso: ").append(queso ? "Sí" : "No")
          .append(", Toppings: ");

        if (toppings.isEmpty()) {
            sb.append("Sin toppings adicionales");
        } else {
            sb.append(toppings.toString());
        }
        sb.append("]");
        return sb.toString();
    }
}
