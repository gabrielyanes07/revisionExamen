public class Cocina {
    // Corregí orden para que el máximo de órdenes sea de 5, esto mediante el atributo de Max_órdenes.
    public static final int MAX_ORDENES = 5;
    private Orden[] ordenesPendientes = new Orden[MAX_ORDENES];
    //Agregado atributo de cantidad órdenes, esto para llevar un control de órgenes pendientes
    private int cantidadOrdenes = 0;
    private String utensilios;
    private boolean lavaplatosDisponible;

    public Cocina() {
        this.utensilios = "Horno de leña, cortadores, palas de pizza";
        this.lavaplatosDisponible = true;
    }
    //Agregué métodos de agregarOrden, despacharOrden y mostrarOrdenPendiente, para llevar control de órdenes.
    public boolean agregarOrden(Orden nuevaOrden) {
        if (nuevaOrden == null) {
            System.out.println("[Cocina] Error: La orden proporcionada es nula.");
            return false;
        }
        if (cantidadOrdenes < MAX_ORDENES) {
            ordenesPendientes[cantidadOrdenes] = nuevaOrden;
            cantidadOrdenes++;
            System.out.println("[Cocina] Orden #" + nuevaOrden.getNumOrden() + " agregada exitosamente. (" + cantidadOrdenes + "/5)");
            return true;
        } else {
            System.out.println("[Cocina] Espacio lleno: La cocina ya tiene el máximo de 5 órdenes pendientes.");
            return false;
        }
    }

    public Orden despacharSiguienteOrden() {
        if (cantidadOrdenes == 0) {
            System.out.println("[Cocina] No hay órdenes pendientes para despachar.");
            return null;
        }

        Orden ordenDespachada = ordenesPendientes[0];
        ordenDespachada.setEstado("Completada");
        for (int i = 0; i < cantidadOrdenes - 1; i++) {
            ordenesPendientes[i] = ordenesPendientes[i + 1];
        }

        ordenesPendientes[cantidadOrdenes - 1] = null;
        cantidadOrdenes--;

        System.out.println("[Cocina] Se despachó la Orden #" + ordenDespachada.getNumOrden() + ". Quedan " + cantidadOrdenes + " pendientes.");
        return ordenDespachada;
    }

    public void mostrarOrdenesPendientes() {
        System.out.println("========== ÓRDENES PENDIENTES EN COCINA (" + cantidadOrdenes + "/" + MAX_ORDENES + ") ==========");
        if (cantidadOrdenes == 0) {
            System.out.println("  (No hay órdenes pendientes en este momento)");
            System.out.println("===============================================================");
            return;
        }

        for (int i = 0; i < cantidadOrdenes; i++) {
            System.out.println("  [" + (i + 1) + "] " + ordenesPendientes[i]);
        }
        System.out.println("===============================================================");
    }
    
    public int getCantidadOrdenes() {
        return cantidadOrdenes;
    }

    public boolean tieneEspacioDisponible() {
        return cantidadOrdenes < MAX_ORDENES;
    }

    public String getUtensilios() {
        return utensilios;
    }

    public void setUtensilios(String utensilios) {
        this.utensilios = utensilios;
    }

    public boolean isLavaplatosDisponible() {
        return lavaplatosDisponible;
    }

    public void setLavaplatosDisponible(boolean lavaplatosDisponible) {
        this.lavaplatosDisponible = lavaplatosDisponible;
    }
}
