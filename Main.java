import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Cocina cocina = new Cocina();
        Cocinero chef = new Cocinero("Gabriel Yanes");
        int contadorNumOrden = 21940;

        boolean salir = false;

        while (!salir) {
            System.out.println("\n=================================================================");
            System.out.println("          PIZZERÍA - SIMULADOR");
            System.out.println("  1. Crear y enviar una nueva Orden a la Cocina");
            System.out.println("  2. Ver órdenes pendientes en Cocina (Arreglo estático máx. 5)");
            System.out.println("  3. Despachar y preparar siguiente orden (Cocinero)");
            System.out.println("  4. Salir");
            System.out.println("=================================================================");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    contadorNumOrden++;
                    crearNuevaOrdenInteractiva(cocina, contadorNumOrden);
                    break;

                case 2:
                    System.out.println();
                    // Demostración del recorrido estático sin usar .length
                    cocina.mostrarOrdenesPendientes();
                    break;

                case 3:
                    System.out.println("\n--- DESPACHO Y PREPARACIÓN DE ORDEN ---");
                    Orden ordenDespachada = cocina.despacharSiguienteOrden();
                    if (ordenDespachada != null) {
                        chef.preparar(ordenDespachada);
                    }
                    break;

                case 4:
                    System.out.println("\nGracias por usar el simulador de Pizza.");
                    salir = true;
                    break;

                default:
                    System.out.println("Opción inválida. Por favor seleccione un número del 1 al 5.");
                    break;
            }
        }

        scanner.close();
    }

    private static void crearNuevaOrdenInteractiva(Cocina cocina, int numOrden) {
        System.out.println("\n--- CREACIÓN DE NUEVA ORDEN #" + numOrden + " ---");
        System.out.print("Ingrese el nombre del cliente: ");
        String cliente = scanner.nextLine().trim();
        if (cliente.isEmpty()) {
            cliente = "Cliente general";
        }

        Orden orden = new Orden(numOrden, cliente);
        boolean agregarMasPizzas = true;

        while (agregarMasPizzas) {
            Pizza pizza = armarPizzaInteractiva();
            orden.agregarPizza(pizza);
            System.out.println("\nPizza agregada a la orden");
            System.out.println("   " + pizza);

            System.out.print("\nDesea agregar otra pizza a esta misma orden? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (!respuesta.equals("s") && !respuesta.equals("si") && !respuesta.equals("sí")) {
                agregarMasPizzas = false;
            }
        }

        System.out.println("\n--- ENVIANDO ORDEN A LA COCINA ---");
        boolean agregada = cocina.agregarOrden(orden);
        if (!agregada) {
            System.out.println("ADVERTENCIA: La orden #" + numOrden + " no pudo entrar a la cocina");
            System.out.println("porque el límite de 5 órdenes pendientes está lleno.");
            System.out.println("Despache una orden pendiente para liberar espacio.");
        }
    }

    private static Pizza armarPizzaInteractiva() {
        System.out.println("\n-------------------------------------------");
        System.out.println("        ENSAMBLADO DE PIZZA");
        System.out.println("Seleccione el tipo de masa (base):");
        System.out.println("  1. Tradicional");
        System.out.println("  2. Delgada");
        System.out.println("  3. Gruesa / Pan pizza");
        System.out.println("  4. Borde de Queso");
        System.out.print("Opción (1-4): ");
        int opcMasa = leerEntero();
        String masa;
        switch (opcMasa) {
            case 2: masa = "Delgada"; break;
            case 3: masa = "Gruesa"; break;
            case 4: masa = "Borde de Queso"; break;
            default: masa = "Tradicional"; break;
        }
        System.out.println("\nSeleccione el tipo de salsa:");
        System.out.println("  1. Normal (Tomate clásico)");
        System.out.println("  2. Picante");
        System.out.println("  3. Barbacoa");
        System.out.println("  4. Salsa Blanca / Alfredo");
        System.out.print("Opción (1-4): ");
        int opcSalsa = leerEntero();
        String salsa;
        switch (opcSalsa) {
            case 2: salsa = "Picante"; break;
            case 3: salsa = "Barbacoa"; break;
            case 4: salsa = "Salsa Blanca"; break;
            default: salsa = "Normal"; break;
        }

        System.out.print("\n¿Lleva queso mozzarella? (s/n): ");
        String respQueso = scanner.nextLine().trim().toLowerCase();
        boolean tieneQueso = !respQueso.equals("n") && !respQueso.equals("no");

        Pizza pizza = new Pizza(masa, salsa, tieneQueso);

        boolean agregandoIngredientes = true;
        while (agregandoIngredientes) {
            System.out.println("\n--- AGREGAR INGREDIENTES ---");
            System.out.println("  1. Agregar 1 ingrediente simple");
            System.out.println("  2. Agregar ingrediente con cantidad específica");
            System.out.println("  3. Agregar varios ingredientes a la vez");
            System.out.println("  4. Listo, no agregar más ingredientes a esta pizza");
            System.out.print("Opción (1-4): ");
            int opcTopp = leerEntero();

            switch (opcTopp) {
                case 1:
                    System.out.print("Nombre del ingrediente (ej. Jamón, Pepperoni, Piña): ");
                    String ingSimple = scanner.nextLine().trim();
                    if (!ingSimple.isEmpty()) {
                        pizza.agregarIngrediente(ingSimple);
                        System.out.println("Ingrediente agregado: " + ingSimple);
                    }
                    break;

                case 2:
                    System.out.print("Nombre del ingrediente (ej. Pepperoni, Tocino): ");
                    String ingCant = scanner.nextLine().trim();
                    System.out.print("Cantidad (porciones): ");
                    int cantidad = leerEntero();
                    if (!ingCant.isEmpty() && cantidad > 0) {
                        pizza.agregarIngrediente(ingCant, cantidad);
                        System.out.println("Ingrediente agregado: " + ingCant + " x" + cantidad);
                    }
                    break;

                case 3:
                    System.out.print("Ingrese los ingredientes separados por coma (ej. Jamón, Champiñones, Cebolla): ");
                    String linea = scanner.nextLine().trim();
                    if (!linea.isEmpty()) {
                        String[] partes = linea.split(",");
                        for (int i = 0; i < partes.length; i++) {
                            partes[i] = partes[i].trim();
                        }
                        pizza.agregarIngrediente(partes);
                        System.out.println("Arreglo de " + partes.length + " ingredientes agregado.");
                    }
                    break;

                case 4:
                    agregandoIngredientes = false;
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        return pizza;
    }

    private static int leerEntero() {
        while (true) {
            try {
                String linea = scanner.nextLine().trim();
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.print("Entrada no válida. Ingrese un número entero: ");
            }
        }
    }
}
