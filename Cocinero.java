public class Cocinero {
    //Agregué atributo de nombre para el cocinero
    private String nombre;
    private boolean ocupado;

    public Cocinero() {
        this.nombre = "Gabriel Yanes";
        this.ocupado = false;
    }

    public Cocinero(String nombre) {
        this.nombre = nombre;
        this.ocupado = false;
    }

    public boolean verificarOrden() {
        return ocupado;
    }
    //Funcionalidad del método de preparar
    public void preparar(Orden orden) {
        if (orden == null) {
            System.out.println("[Cocinero] No hay orden asignada para preparar.");
            return;
        }

        this.ocupado = true;
        orden.setEstado("Preparándose");
        System.out.println("[Cocinero] " + nombre + " está preparando la Orden #" + orden.getNumOrden() + "...");

        orden.setEstado("Entregándose");
        this.ocupado = false;
        System.out.println("[Cocinero] " + nombre + " terminó de preparar la Orden #" + orden.getNumOrden() + ".");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
}
