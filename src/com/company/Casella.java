package com.company;

public class Casella {

    //Propietats Casella.
    private boolean tapada=true;
    private boolean control=false;
    private boolean mina=false;
    private boolean bandera=false;
    private int valorNumeric=0;
    private String valor = "■";

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