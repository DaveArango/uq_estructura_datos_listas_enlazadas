package org.dave.clase.listacirculardoble;

public class Main {
    public static void main(String[] args) {
        ListaCircularDoble<Integer> lista = new ListaCircularDoble<>();


        lista.agregarInicio(10);
        lista.agregarInicio(5);
        lista.imprimirLista();

        lista.agregarFinal(20);
        lista.agregarFinal(25);
        lista.imprimirLista();

        lista.agregarPosicion(15, 2);
        lista.imprimirLista();

        System.out.println("Valor en índice 2: " + lista.obtenerValorNodo(2));

        System.out.println("Nodo en índice 3: " + lista.obtenerNodo(3).getValor());

        System.out.println("Posición del valor 25: " + lista.obtenerPosicionNodo(25));

        lista.eliminarPrimero();
        lista.imprimirLista();

        lista.eliminarUltimo();
        lista.imprimirLista();

        lista.eliminar(15);
        lista.imprimirLista();

        lista.modificarNodo(1, 30);
        lista.imprimirLista();

        lista.agregarFinal(2);
        lista.agregarFinal(40);
        lista.imprimirLista();
        lista.ordenarLista();
        lista.imprimirLista();

        System.out.println("Recorriendo con Iterator:");
        for (Integer val : lista) {
            System.out.print(val + " ");
        }
        System.out.println();

        lista.borrarLista();
        lista.imprimirLista();
    }
}

