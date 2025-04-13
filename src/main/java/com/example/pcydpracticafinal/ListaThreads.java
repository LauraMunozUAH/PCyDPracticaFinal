package com.example.pcydpracticafinal;


import javafx.application.Platform;
import javafx.scene.control.TextField;
import java.util.*;
/* La clase ListaThreads permite gestionar las listas de threads en los monitores,
con métodos para meter y sacar threads en ella. Cada vez que una lista se modifica,
se imprime su nuevo contenido en el TextField que toma como parámetro el constructor. */
public class ListaThreads
{
    ArrayList<Thread> lista;
    TextField tf ;

    public ListaThreads(TextField tf)
    {
        lista=new ArrayList<>();
        this.tf=tf;
    }

    public ArrayList<Thread> getLista() {
        return lista;
    }
    public synchronized ArrayList<Thread> getCopiaLista(){
        return new ArrayList<>(lista);
    }

    public synchronized void meter(Thread t)
    {
        lista.add(t);
        imprimir();
    }

    public synchronized void sacar(Thread t)
    {
        lista.remove(t);
        imprimir();
    }

    public void imprimir()
    {
        String contenido="";
        for (Thread thread : lista) {
            contenido = contenido + thread.getName() + " ";
        }
        String finalContenido = contenido;
        Platform.runLater(() -> tf.setText(finalContenido));
        //tf.setText(finalContenido);


    }
}
