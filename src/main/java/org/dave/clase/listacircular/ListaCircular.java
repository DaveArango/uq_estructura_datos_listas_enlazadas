package org.dave.clase.listacircular;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaCircular<T extends Comparable<T>> implements Iterable<T> {
    private Nodo<T> nodoPrimero;
    private Nodo<T> nodoUltimo;
    private int tamanio;

    public ListaCircular() {
        this.nodoPrimero = null;
        this.nodoUltimo = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }

    public int getTamanio() {
        return tamanio;
    }

    // ---------------- AGREGAR INICIO ----------------
    public void agregarInicio(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (estaVacia()) {
            nodoPrimero = nodoUltimo = nuevo;
            nodoUltimo.setNodoSiguiente(nodoPrimero);
        } else {
            nuevo.setNodoSiguiente(nodoPrimero);
            nodoPrimero = nuevo;
            nodoUltimo.setNodoSiguiente(nodoPrimero);
        }
        tamanio++;
    }

    // ---------------- AGREGAR FINAL ----------------
    public void agregarFinal(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (estaVacia()) {
            nodoPrimero = nodoUltimo = nuevo;
            nodoUltimo.setNodoSiguiente(nodoPrimero);
        } else {
            nodoUltimo.setNodoSiguiente(nuevo);
            nodoUltimo = nuevo;
            nodoUltimo.setNodoSiguiente(nodoPrimero);
        }
        tamanio++;
    }

    // ---------------- AGREGAR POSICION ----------------
    public void agregarPosicion(T valor, int indice) {
        if (!indiceValido(indice)) return;

        if (indice == 0) {
            agregarInicio(valor);
        } else if (indice == tamanio) {
            agregarFinal(valor);
        } else {
            Nodo<T> nuevo = new Nodo<>(valor);
            Nodo<T> aux = nodoPrimero;
            for (int i = 0; i < indice - 1; i++) {
                aux = aux.getNodoSiguiente();
            }
            nuevo.setNodoSiguiente(aux.getNodoSiguiente());
            aux.setNodoSiguiente(nuevo);
            tamanio++;
        }
    }

    // ---------------- OBTENER VALOR NODO ----------------
    public T obtenerValorNodo(int indice) {
        Nodo<T> nodo = obtenerNodo(indice);
        return nodo != null ? nodo.getValor() : null;
    }

    // ---------------- OBTENER NODO ----------------
    public Nodo<T> obtenerNodo(int indice) {
        if (!indiceValido(indice)) return null;
        Nodo<T> aux = nodoPrimero;
        for (int i = 0; i < indice; i++) {
            aux = aux.getNodoSiguiente();
        }
        return aux;
    }

    // ---------------- OBTENER POSICION DE NODO ----------------
    public int obtenerPosicionNodo(T valor) {
        Nodo<T> aux = nodoPrimero;
        for (int i = 0; i < tamanio; i++) {
            if (aux.getValor().equals(valor)) {
                return i;
            }
            aux = aux.getNodoSiguiente();
        }
        return -1;
    }

    // ---------------- VALIDAR INDICE ----------------
    public boolean indiceValido(int indice) {
        return indice >= 0 && indice <= tamanio;
    }

    // ---------------- ELIMINAR PRIMERO ----------------
    public void eliminarPrimero() {
        if (estaVacia()) return;
        if (tamanio == 1) {
            nodoPrimero = nodoUltimo = null;
        } else {
            nodoPrimero = nodoPrimero.getNodoSiguiente();
            nodoUltimo.setNodoSiguiente(nodoPrimero);
        }
        tamanio--;
    }

    // ---------------- ELIMINAR ULTIMO ----------------
    public void eliminarUltimo() {
        if (estaVacia()) return;
        if (tamanio == 1) {
            nodoPrimero = nodoUltimo = null;
        } else {
            Nodo<T> aux = nodoPrimero;
            while (aux.getNodoSiguiente() != nodoUltimo) {
                aux = aux.getNodoSiguiente();
            }
            aux.setNodoSiguiente(nodoPrimero);
            nodoUltimo = aux;
        }
        tamanio--;
    }

    // ---------------- ELIMINAR VALOR ----------------
    public void eliminar(T valor) {
        if (estaVacia()) return;

        if (nodoPrimero.getValor().equals(valor)) {
            eliminarPrimero();
            return;
        }

        Nodo<T> aux = nodoPrimero;
        while (aux.getNodoSiguiente() != nodoPrimero) {
            if (aux.getNodoSiguiente().getValor().equals(valor)) {
                if (aux.getNodoSiguiente() == nodoUltimo) {
                    nodoUltimo = aux;
                }
                aux.setNodoSiguiente(aux.getNodoSiguiente().getNodoSiguiente());
                tamanio--;
                return;
            }
            aux = aux.getNodoSiguiente();
        }
    }

    // ---------------- MODIFICAR NODO ----------------
    public void modificarNodo(int indice, T nuevoValor) {
        Nodo<T> nodo = obtenerNodo(indice);
        if (nodo != null) {
            nodo.setValor(nuevoValor);
        }
    }

    // ---------------- ORDENAR LISTA ----------------
    public void ordenarLista() {
        if (tamanio < 2) return;

        for (int i = 0; i < tamanio - 1; i++) {
            Nodo<T> actual = nodoPrimero;
            Nodo<T> siguiente = actual.getNodoSiguiente();
            for (int j = 0; j < tamanio - 1; j++) {
                if (actual.getValor().compareTo(siguiente.getValor()) > 0) {
                    T temp = actual.getValor();
                    actual.setValor(siguiente.getValor());
                    siguiente.setValor(temp);
                }
                actual = siguiente;
                siguiente = siguiente.getNodoSiguiente();
            }
        }
    }

    // ---------------- IMPRIMIR LISTA ----------------
    public void imprimirLista() {
        if (estaVacia()) {
            System.out.println("Lista vacía");
            return;
        }
        Nodo<T> aux = nodoPrimero;
        int i = 0;
        do {
            System.out.print("[" + i + "] " + aux.getValor() + " -> ");
            aux = aux.getNodoSiguiente();
            i++;
        } while (aux != nodoPrimero);
        System.out.println("(circular)");
    }

    // ---------------- ITERATOR ----------------
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = nodoPrimero;
            private int contador = 0;

            @Override
            public boolean hasNext() {
                return contador < tamanio;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T valor = actual.getValor();
                actual = actual.getNodoSiguiente();
                contador++;
                return valor;
            }
        };
    }

    // ---------------- BORRAR LISTA ----------------
    public void borrarLista() {
        nodoPrimero = nodoUltimo = null;
        tamanio = 0;
    }
}

