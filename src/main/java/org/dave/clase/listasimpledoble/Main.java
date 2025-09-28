package org.dave.clase.listasimpledoble;

public class Main{
    public static void main(String[] args) {
        ListaDoble lista = new ListaDoble();


        System.out.println("¿Lista vacía? " + lista.estaVacia());

        lista.agregarInicio(10);
        lista.agregarInicio(5);
        lista.imprimirLista();

        lista.agregarFinal(20);
        lista.agregarFinal(30);
        lista.imprimirLista();

        lista.agregarIndice(2, 15);
        lista.imprimirLista();

        System.out.println("Valor en posición 2: " + lista.obtenerValorNodo(2));

        NodoDoble nodo = lista.obtenerNodo(2);
        System.out.println("Nodo obtenido con valor 20: " + (nodo != null ? nodo.getValor() : "No encontrado"));

        int pos = lista.obtenerPosicionNodo(30);
        System.out.println("Posición del nodo con valor 30: " + pos);

        lista.modificarNodo(1, 50);
        lista.imprimirLista();

        lista.eliminarPrimero();
        lista.imprimirLista();

        lista.eliminarUltimo();
        lista.imprimirLista();

        lista.eliminar(20);
        lista.imprimirLista();

        lista.agregarFinal(40);
        lista.agregarFinal(25);
        lista.agregarFinal(5);
        lista.imprimirLista();
        System.out.println("Lista ordenada:");
        lista.ordenarLista();
        lista.imprimirLista();

        System.out.println("Recorrido con Iterator:");
        for (Object val : lista) {
            System.out.print(val + " ");
        }
        System.out.println();

        lista.borrarLista();
        lista.imprimirLista();
        System.out.println("¿Lista vacía? " + lista.estaVacia());

    }
}


