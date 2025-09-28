package org.dave.clase.listacircular;

public class Nodo<T> {
    private T valor;
    private Nodo<T> nodoSiguiente;

    public Nodo(T valor) {
        this.valor = valor;
        this.nodoSiguiente = null;
    }

    public T getValor() { return valor; }

    public void setValor(T valor) { this.valor = valor; }

    public Nodo<T> getNodoSiguiente() {
        return nodoSiguiente;
    }

    public void setNodoSiguiente(Nodo<T> nodoSiguiente) {
        this.nodoSiguiente = nodoSiguiente;
    }
}

