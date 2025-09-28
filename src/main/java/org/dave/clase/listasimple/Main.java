package org.dave.clase.listasimple;

public class Main {
    public static void main(String[] args) {
        ListaSimple<Integer> lista = new ListaSimple<>();


        lista.imprimirLista();

        lista.agregarInicio(10);
        lista.agregarInicio(20);
        lista.agregarInicio(30);
        System.out.println("Lista después de agregar al inicio:");
        lista.imprimirLista();

        lista.agregarFinal(new Nodo<>(40));
        lista.agregarFinal(new Nodo<>(50));
        System.out.println("Lista después de agregar al final:");
        lista.imprimirLista();

        lista.agregarIndice(2, 25); //
        System.out.println("Lista después de agregar en índice 2:");
        lista.imprimirLista();

        System.out.println("Valor en índice 3: " + lista.obtenerValor(3));

        Nodo<Integer> nodo = lista.obtenerNodo(4);
        System.out.println("Nodo en índice 4: " + nodo.getValor());

        System.out.println("Posición del valor 25: " + lista.obtenerPosicionNodo(25)); // 2
        System.out.println("Posición del valor 100 (no existe): " + lista.obtenerPosicionNodo(100));

        lista.modificarNodo(1, 200);
        System.out.println("Lista después de modificar índice 1 a 200:");
        lista.imprimirLista();

        lista.eliminarPrimero();
        System.out.println("Lista después de eliminar el primero:");
        lista.imprimirLista();

        lista.eliminarUltimo();
        System.out.println("Lista después de eliminar el último:");
        lista.imprimirLista();

        lista.eliminar(25);
        System.out.println("Lista después de eliminar valor 25:");
        lista.imprimirLista();

        lista.ordenarLista();
        System.out.println("Lista después de ordenar:");
        lista.imprimirLista();

        System.out.println("Recorriendo lista con foreach:");
        for (Integer num : lista) {
            System.out.println("Elemento: " + num);
        }

        lista.invertirContenido(lista);
        System.out.println("Lista invertida:");
        lista.imprimirLista();

        lista.borrarLista();
        System.out.println("Lista después de borrar:");
        lista.imprimirLista();
        System.out.println("¿Está vacía? " + lista.esVacio());
    }
}
