package org.dave.clase.listasimple;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaSimple<T extends Comparable<T>> implements Iterable<T> {
    private int tamanio;
    private Nodo<T> nodoPrimero;

    public ListaSimple() {
        this.tamanio = 0;
        this.nodoPrimero = null;
    }

    public void agregarInicio(T valor){
        Nodo<T> nodoNuevo = new Nodo<>(valor);
        if(esVacio()){
            this.nodoPrimero = nodoNuevo;
        } else if (!esVacio()) {
            nodoNuevo.setSiguiente(nodoPrimero);
            this.nodoPrimero = nodoNuevo;
        }
        tamanio++;
    }

    public void agregarFinal(Nodo<T> nodo) {
        if (esVacio()){
            nodoPrimero = nodo;
        } else {
            Nodo<T> nodoAux = nodoPrimero;
            while(nodoAux.getSiguiente() != null) {
                nodoAux = nodoAux.getSiguiente();
            }
            nodoAux.setSiguiente(nodo);
        }
        tamanio++;
    }

    public void agregarIndice(int indice, T valor){
        if(!esVacio() && indice < tamanio && indice >= 0){
            Nodo<T> nodoNuevo = new Nodo<>(valor);
            int ciclos = 0;
            Nodo<T> nodoAux = nodoPrimero;
            if(indice == 0 && nodoAux.getSiguiente() != null){
                nodoNuevo.setSiguiente(nodoAux);
                nodoPrimero = nodoNuevo;
            } else {
                while(nodoAux.getSiguiente() != null){
                    if(ciclos == indice-1){
                        Nodo<T> nodoAux2 = nodoAux.getSiguiente();
                        nodoAux.setSiguiente(nodoNuevo);
                        nodoNuevo.setSiguiente(nodoAux2);
                        break;
                    }
                    ciclos++;
                    nodoAux = nodoAux.getSiguiente();
                }
            }
            tamanio++;
        }
    }

    public T obtenerValor(int indice) {
        if (esVacio() || indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Nodo<T> nodoAux = nodoPrimero;
        int contador = 0;

        while (contador < indice) {
            nodoAux = nodoAux.getSiguiente();
            contador++;
        }

        return nodoAux.getValor();
    }

    public Nodo<T> obtenerNodo(int indice) {
        if (!indiceValido(indice)) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Nodo<T> aux = nodoPrimero;
        int contador = 0;
        while (contador < indice) {
            aux = aux.getSiguiente();
            contador++;
        }
        return aux;
    }

    public int obtenerPosicionNodo(T valor) {
        Nodo<T> aux = nodoPrimero;
        int indice = 0;
        while (aux != null) {
            if (aux.getValor().equals(valor)) {
                return indice;
            }
            aux = aux.getSiguiente();
            indice++;
        }
        return -1; // No encontrado
    }

    public boolean indiceValido(int indice) {
        return indice >= 0 && indice < tamanio;
    }

    public boolean esVacio(){
        return this.tamanio == 0;
    }

    public void eliminarPrimero() {
        if (!esVacio()) {
            nodoPrimero = nodoPrimero.getSiguiente();
            tamanio--;
        }
    }

    public void eliminarUltimo() {
        if (esVacio()) return;
        if (tamanio == 1) {
            nodoPrimero = null;
        } else {
            Nodo<T> aux = nodoPrimero;
            while (aux.getSiguiente().getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(null);
        }
        tamanio--;
    }

    public void eliminar(T valor){
        Nodo<T> nodoAux = nodoPrimero;
        while (nodoAux.getSiguiente() != null && !esVacio()) {
            if(nodoAux.getSiguiente().getValor().equals(valor)){
                nodoAux.setSiguiente(nodoAux.getSiguiente().getSiguiente());
                tamanio--;
                break;
            }
            nodoAux = nodoAux.getSiguiente();
        }
    }

    public void modificarNodo(int indice, T nuevoValor) {
        if (!indiceValido(indice)) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Nodo<T> nodo = obtenerNodo(indice);
        nodo.setValor(nuevoValor);
    }


    public void ordenarLista() {
        if (esVacio() || tamanio == 1) return;

        boolean cambiado;
        do {
            cambiado = false;
            Nodo<T> actual = nodoPrimero;
            while (actual.getSiguiente() != null) {
                if (actual.getValor().compareTo(actual.getSiguiente().getValor()) > 0) {
                    // Intercambiar valores
                    T temp = actual.getValor();
                    actual.setValor(actual.getSiguiente().getValor());
                    actual.getSiguiente().setValor(temp);
                    cambiado = true;
                }
                actual = actual.getSiguiente();
            }
        } while (cambiado);
    }

    public void imprimirLista() {
        Nodo<T> aux = nodoPrimero;
        while (aux != null) {
            System.out.print(aux.getValor() + " -> ");
            aux = aux.getSiguiente();
        }
        System.out.println("null");
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = nodoPrimero;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T valor = actual.getValor();
                actual = actual.getSiguiente();
                return valor;
            }
        };
    }

    public void borrarLista() {
        nodoPrimero = null;
        tamanio = 0;
    }

    public int getTamanio() {
        return tamanio;
    }


    // ---------------- INVERTIR CONTENIDO ----------------
    public void invertirContenido(ListaSimple<T> lista) {
        if(esVacio() || tamanio == 1){
            return;
        }
        nodoPrimero = invertirContenido(nodoPrimero, null);
    }

    public Nodo<T> invertirContenido(Nodo<T> actual, Nodo<T> anterior){
        if(actual == null){
            return anterior;
        }
        Nodo<T> siguiente = actual.getSiguiente();
        actual.setSiguiente(anterior);
        return invertirContenido(siguiente, actual);
    }


}
