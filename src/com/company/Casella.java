package com.company;

public class Casella {

    //Propietats Casella.
    boolean tapada=true;
    boolean control=false;
    boolean mina=false;
    boolean bandera=false;
    int valorNumeric=0;
    String valor = "■";

    //Constructor
    public Casella(){}

    //Setters
    public void setControl(boolean control){this.control=control;}
    public void setBandera(boolean bandera){this.bandera=bandera;}
    public void setValor(String valor){this.valor=valor;}
    public void setMina(boolean mina){this.mina=mina;}
    public void setTapada(boolean tapada){this.tapada=tapada;}
    public void setValorNumeric(int valorNumeric){this.valorNumeric=valorNumeric;}


    //Getters
    public int getValorNumeric(){return this.valorNumeric;}
    public boolean getTapada(){return this.tapada;}
    public boolean getBandera(){return this.bandera;}
    public boolean getMina(){return this.mina;}
    public boolean getControl(){return this.control;}
    public String getValor(){return this.valor;}

}