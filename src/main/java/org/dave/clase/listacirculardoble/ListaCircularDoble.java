package org.dave.clase.listacirculardoble;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaCircularDoble<T extends Comparable<T>> implements Iterable<T> {
    private NodoDoble<T> nodoPrimero;
    private NodoDoble<T> nodoUltimo;
    private int tamanio;

    public ListaCircularDoble() {
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
        NodoDoble<T> nuevo = new NodoDoble<>(valor);
        if (estaVacia()) {
            nodoPrimero = nodoUltimo = nuevo;
            nodoPrimero.setSiguiente(nodoPrimero);
            nodoPrimero.setAnterior(nodoPrimero);
        } else {
            nuevo.setSiguiente(nodoPrimero);
            nuevo.setAnterior(nodoUltimo);
            nodoPrimero.setAnterior(nuevo);
            nodoUltimo.setSiguiente(nuevo);
            nodoPrimero = nuevo;
        }
        tamanio++;
    }

    // ---------------- AGREGAR FINAL ----------------
    public void agregarFinal(T valor) {
        NodoDoble<T> nuevo = new NodoDoble<>(valor);
        if (estaVacia()) {
            nodoPrimero = nodoUltimo = nuevo;
            nodoPrimero.setSiguiente(nodoPrimero);
            nodoPrimero.setAnterior(nodoPrimero);
        } else {
            nuevo.setAnterior(nodoUltimo);
            nuevo.setSiguiente(nodoPrimero);
            nodoUltimo.setSiguiente(nuevo);
            nodoPrimero.setAnterior(nuevo);
            nodoUltimo = nuevo;
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
            NodoDoble<T> nuevo = new NodoDoble<>(valor);
            NodoDoble<T> aux = nodoPrimero;
            for (int i = 0; i < indice; i++) {
                aux = aux.getSiguiente();
            }
            nuevo.setSiguiente(aux);
            nuevo.setAnterior(aux.getAnterior());
            aux.getAnterior().setSiguiente(nuevo);
            aux.setAnterior(nuevo);
            tamanio++;
        }
    }

    // ---------------- OBTENER VALOR NODO ----------------
    public T obtenerValorNodo(int indice) {
        NodoDoble<T> nodo = obtenerNodo(indice);
        return nodo != null ? nodo.getValor() : null;
    }

    // ---------------- OBTENER NODO ----------------
    public NodoDoble<T> obtenerNodo(int indice) {
        if (!indiceValido(indice)) return null;
        NodoDoble<T> aux = nodoPrimero;
        for (int i = 0; i < indice; i++) {
            aux = aux.getSiguiente();
        }
        return aux;
    }

    // ---------------- OBTENER POSICION DE NODO ----------------
    public int obtenerPosicionNodo(T valor) {
        NodoDoble<T> aux = nodoPrimero;
        for (int i = 0; i < tamanio; i++) {
            if (aux.getValor().equals(valor)) return i;
            aux = aux.getSiguiente();
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
            nodoPrimero = nodoPrimero.getSiguiente();
            nodoPrimero.setAnterior(nodoUltimo);
            nodoUltimo.setSiguiente(nodoPrimero);
        }
        tamanio--;
    }

    // ---------------- ELIMINAR ULTIMO ----------------
    public void eliminarUltimo() {
        if (estaVacia()) return;
        if (tamanio == 1) {
            nodoPrimero = nodoUltimo = null;
        } else {
            nodoUltimo = nodoUltimo.getAnterior();
            nodoUltimo.setSiguiente(nodoPrimero);
            nodoPrimero.setAnterior(nodoUltimo);
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
        if (nodoUltimo.getValor().equals(valor)) {
            eliminarUltimo();
            return;
        }

        NodoDoble<T> aux = nodoPrimero.getSiguiente();
        while (aux != nodoPrimero) {
            if (aux.getValor().equals(valor)) {
                aux.getAnterior().setSiguiente(aux.getSiguiente());
                aux.getSiguiente().setAnterior(aux.getAnterior());
                tamanio--;
                return;
            }
            aux = aux.getSiguiente();
        }
    }

    // ---------------- MODIFICAR NODO ----------------
    public void modificarNodo(int indice, T nuevoValor) {
        NodoDoble<T> nodo = obtenerNodo(indice);
        if (nodo != null) nodo.setValor(nuevoValor);
    }

    // ---------------- ORDENAR LISTA ----------------
    public void ordenarLista() {
        if (tamanio < 2) return;
        for (int i = 0; i < tamanio - 1; i++) {
            NodoDoble<T> actual = nodoPrimero;
            for (int j = 0; j < tamanio - 1; j++) {
                NodoDoble<T> siguiente = actual.getSiguiente();
                if (actual.getValor().compareTo(siguiente.getValor()) > 0) {
                    T temp = actual.getValor();
                    actual.setValor(siguiente.getValor());
                    siguiente.setValor(temp);
                }
                actual = siguiente;
            }
        }
    }

    // ---------------- IMPRIMIR LISTA ----------------
    public void imprimirLista() {
        if (estaVacia()) {
            System.out.println("Lista vacía");
            return;
        }
        NodoDoble<T> aux = nodoPrimero;
        int i = 0;
        do {
            System.out.print("[" + i + "] " + aux.getValor() + " <-> ");
            aux = aux.getSiguiente();
            i++;
        } while (aux != nodoPrimero);
        System.out.println("(circular)");
    }

    // ---------------- ITERATOR ----------------
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoDoble<T> actual = nodoPrimero;
            private int contador = 0;

            @Override
            public boolean hasNext() {
                return contador < tamanio;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T valor = actual.getValor();
                actual = actual.getSiguiente();
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

    //------------ INVERTIR LISTA RECURSIVA ---------
    public void invertirContenido() {
        if (estaVacia() || tamanio == 1) return;

        // Iniciamos la recursión desde el primero
        invertirRecursivo(nodoPrimero, null);

        // Intercambiamos primero y último
        NodoDoble<T> temp = nodoPrimero;
        nodoPrimero = nodoUltimo;
        nodoUltimo = temp;

        // Restauramos circularidad
        nodoPrimero.setAnterior(nodoUltimo);
        nodoUltimo.setSiguiente(nodoPrimero);
    }

    // Método recursivo
    private void invertirRecursivo(NodoDoble<T> actual, NodoDoble<T> anterior) {
        NodoDoble<T> siguiente = actual.getSiguiente();

        // Reasignamos punteros en la vuelta
        actual.setSiguiente(anterior);
        actual.setAnterior(siguiente);

        // Caso base: si llegamos al último nodo (antes de volver al primero)
        if (actual == nodoUltimo) return;

        // Llamada recursiva
        invertirRecursivo(siguiente, actual);
    }

}

