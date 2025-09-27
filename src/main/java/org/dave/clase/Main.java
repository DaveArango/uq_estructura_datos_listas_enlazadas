package org.dave.clase;

public class Main {
    public static void main(String[] args) {
        ListaSimple<Integer> lista = new ListaSimple<>();

        System.out.println("===== PRUEBAS LISTA SIMPLE =====");

        lista.imprimirLista();

        // 1. Agregar al inicio
        lista.agregarInicio(10);
        lista.agregarInicio(20);
        lista.agregarInicio(30);
        System.out.println("Lista después de agregar al inicio:");
        lista.imprimirLista(); // 30 -> 20 -> 10 -> null

        // 2. Agregar al final
        lista.agregarFinal(new Nodo<>(40));
        lista.agregarFinal(new Nodo<>(50));
        System.out.println("Lista después de agregar al final:");
        lista.imprimirLista(); // 30 -> 20 -> 10 -> 40 -> 50 -> null

        // 3. Agregar en índice
        lista.agregarIndice(2, 25); // Inserta en la posición 2
        System.out.println("Lista después de agregar en índice 2:");
        lista.imprimirLista(); // 30 -> 20 -> 25 -> 10 -> 40 -> 50 -> null

        // 4. Obtener valor por índice
        System.out.println("Valor en índice 3: " + lista.obtenerValor(3)); // 10

        // 5. Obtener nodo
        Nodo<Integer> nodo = lista.obtenerNodo(4);
        System.out.println("Nodo en índice 4: " + nodo.getValor()); // 40

        // 6. Obtener posición de un valor
        System.out.println("Posición del valor 25: " + lista.obtenerPosicionNodo(25)); // 2
        System.out.println("Posición del valor 100 (no existe): " + lista.obtenerPosicionNodo(100)); // -1

        // 7. Modificar nodo
        lista.modificarNodo(1, 200);
        System.out.println("Lista después de modificar índice 1 a 200:");
        lista.imprimirLista(); // 30 -> 200 -> 25 -> 10 -> 40 -> 50 -> null

        // 8. Eliminar primero
        lista.eliminarPrimero();
        System.out.println("Lista después de eliminar el primero:");
        lista.imprimirLista(); // 200 -> 25 -> 10 -> 40 -> 50 -> null

        // 9. Eliminar último
        lista.eliminarUltimo();
        System.out.println("Lista después de eliminar el último:");
        lista.imprimirLista(); // 200 -> 25 -> 10 -> 40 -> null

        // 10. Eliminar por valor
        lista.eliminar(25);
        System.out.println("Lista después de eliminar valor 25:");
        lista.imprimirLista(); // 200 -> 10 -> 40 -> null

        // 11. Ordenar lista
        lista.ordenarLista();
        System.out.println("Lista después de ordenar:");
        lista.imprimirLista(); // 10 -> 40 -> 200 -> null

        // 12. Iterar con foreach
        System.out.println("Recorriendo lista con foreach:");
        for (Integer num : lista) {
            System.out.println("Elemento: " + num);
        }

        // 13. Borrar toda la lista
        lista.borrarLista();
        System.out.println("Lista después de borrar:");
        lista.imprimirLista(); // null
        System.out.println("¿Está vacía? " + lista.esVacio()); // true
    }
}
