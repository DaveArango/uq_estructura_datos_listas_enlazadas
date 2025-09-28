package org.dave.clase.listasimpledoble;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaDoble<T extends Comparable<T>> implements Iterable<T> {
    private NodoDoble<T> nodoPrimero;
    private NodoDoble<T> nodoUltimo;
    private int tamanio;

    public ListaDoble() {
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

    // ---------------- AGREGAR ----------------
    public void agregarInicio(T valor) {
        NodoDoble<T> nuevo = new NodoDoble<>(valor);
        if (estaVacia()) {
            nodoPrimero = nodoUltimo = nuevo;
        } else {
            nuevo.setSiguiente(nodoPrimero);
            nodoPrimero.setAnterior(nuevo);
            nodoPrimero = nuevo;
        }
        tamanio++;
    }

    public void agregarFinal(T valor) {
        NodoDoble<T> nuevo = new NodoDoble<>(valor);
        if (estaVacia()) {
            nodoPrimero = nodoUltimo = nuevo;
        } else {
            nodoUltimo.setSiguiente(nuevo);
            nuevo.setAnterior(nodoUltimo);
            nodoUltimo = nuevo;
        }
        tamanio++;
    }

    public void agregarIndice(int indice, T valor) {
        if (!indiceValido(indice)) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        if (indice == 0) {
            agregarInicio(valor);
        } else if (indice == tamanio) {
            agregarFinal(valor);
        } else {
            NodoDoble<T> actual = obtenerNodo(indice);
            NodoDoble<T> nuevo = new NodoDoble<>(valor);
            NodoDoble<T> anterior = actual.getAnterior();

            anterior.setSiguiente(nuevo);
            nuevo.setAnterior(anterior);
            nuevo.setSiguiente(actual);
            actual.setAnterior(nuevo);

            tamanio++;
        }
    }

    // ---------------- OBTENER ----------------
    public T obtenerValorNodo(int indice) {
        return obtenerNodo(indice).getValor();
    }

    public NodoDoble<T> obtenerNodo(int indice) {
        if (!indiceValido(indice)) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        NodoDoble<T> aux;
        if (indice < tamanio / 2) {
            aux = nodoPrimero;
            for (int i = 0; i < indice; i++) {
                aux = aux.getSiguiente();
            }
        } else {
            aux = nodoUltimo;
            for (int i = tamanio - 1; i > indice; i--) {
                aux = aux.getAnterior();
            }
        }
        return aux;
    }

    public int obtenerPosicionNodo(T valor) {
        NodoDoble<T> aux = nodoPrimero;
        int indice = 0;
        while (aux != null) {
            if (aux.getValor().equals(valor)) {
                return indice;
            }
            aux = aux.getSiguiente();
            indice++;
        }
        return -1;
    }

    public boolean indiceValido(int indice) {
        return indice >= 0 && indice <= tamanio;
    }

    // ---------------- ELIMINAR ----------------
    public void eliminarPrimero() {
        if (estaVacia()) return;

        if (tamanio == 1) {
            nodoPrimero = nodoUltimo = null;
        } else {
            nodoPrimero = nodoPrimero.getSiguiente();
            nodoPrimero.setAnterior(null);
        }
        tamanio--;
    }

    public void eliminarUltimo() {
        if (estaVacia()) return;

        if (tamanio == 1) {
            nodoPrimero = nodoUltimo = null;
        } else {
            nodoUltimo = nodoUltimo.getAnterior();
            nodoUltimo.setSiguiente(null);
        }
        tamanio--;
    }

    public void eliminar(T valor) {
        if (estaVacia()) return;

        NodoDoble<T> actual = nodoPrimero;
        while (actual != null) {
            if (actual.getValor().equals(valor)) {
                if (actual == nodoPrimero) {
                    eliminarPrimero();
                } else if (actual == nodoUltimo) {
                    eliminarUltimo();
                } else {
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                    actual.getSiguiente().setAnterior(actual.getAnterior());
                    tamanio--;
                }
                return;
            }
            actual = actual.getSiguiente();
        }
    }

    // ---------------- MODIFICAR ----------------
    public void modificarNodo(int indice, T nuevoValor) {
        NodoDoble<T> nodo = obtenerNodo(indice);
        nodo.setValor(nuevoValor);
    }

    // ---------------- ORDENAR ----------------
    public void ordenarLista() {
        if (tamanio <= 1) return;

        boolean cambiado;
        do {
            cambiado = false;
            NodoDoble<T> actual = nodoPrimero;
            while (actual.getSiguiente() != null) {
                if (actual.getValor().compareTo(actual.getSiguiente().getValor()) > 0) {
                    T temp = actual.getValor();
                    actual.setValor(actual.getSiguiente().getValor());
                    actual.getSiguiente().setValor(temp);
                    cambiado = true;
                }
                actual = actual.getSiguiente();
            }
        } while (cambiado);
    }

    // ---------------- IMPRIMIR ----------------
    public void imprimirLista() {
        NodoDoble<T> aux = nodoPrimero;
        while (aux != null) {
            System.out.print(aux.getValor() + " <-> ");
            aux = aux.getSiguiente();
        }
        System.out.println("null");
    }

    // ---------------- BORRAR LISTA ----------------
    public void borrarLista() {
        nodoPrimero = nodoUltimo = null;
        tamanio = 0;
    }

    // ---------------- ITERATOR ----------------
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoDoble<T> actual = nodoPrimero;

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
}

