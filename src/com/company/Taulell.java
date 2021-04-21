package com.company;

//import java.util.ArrayList;

public class Taulell {

    //Propietats de la classe taulell
    int nMines;
    int x;
    int y;
    Casella[][] taula;

    //Variables utilitzades en el metode de la bandera
    int tempMines = 0;
    int tempMinesArtificials = 0;


    //Constructor Taulell
    public Taulell(int nMines, int x, int y) {
        this.nMines = nMines;
        this.x = x;
        this.y = y;
        taula = new Casella[x][y];

    }

    //Metode getter que et retorna l'objecte taulell instanciat al constructor.
    public Casella[][] getTaulell() {
        return taula;
    }

    //Metode que rellena cada posicio del taulell amb un objecte de la classe casella.
    public void rellenarTaulell(Casella[][] taula) {
        for (int i = 0; i < taula.length; i++) {
            for (int j = 0; j < taula.length; j++) {

                //Per cada iteració del segon for es crea una nova casella
                taula[i][j] = new Casella();
                //Print que imprmeix per cada posicio si te mina, si es false no en te, si es true hi ha mina
                //System.out.print("[ "+taula[i][j].getMina()+" ]");
            }
            //Per cada fi de segon bucle printeja un salt de linea per tal de que quedi la taula ben estructurada
            //System.out.println();
        }
    }

    //Metode que imprimeix el taulell
    public void imprimirTaulell(Casella[][] taula) {
        //For d'ordenació del taulell, peque quedin els numeros de cordenades ben estructurats
        System.out.print("  ");
        for (int x = 0; x < taula.length + 1; x++) {
            if (x > 0) {
                if (x >= 9) {
                    System.out.print("  " + x + "  ");
                }
                if (x < 9) {
                    System.out.print("  " + x + "  ");
                }
            }
        }
        System.out.println();

        //Bucle que printeja la columna de coordenades
        for (int i = 0; i < taula.length; i++) {
            if (i <= 9) {
                System.out.print(i + " ");
            } else {
                System.out.print(i);
            }
            for (int j = 0; j < taula.length; j++) {

                //Aqui a l'imprimir el taulell si la casella esta destapada retornara el valor numeric
                //de les mines que te devora, si esta tapada retorna el cuadrat.
                if (taula[i][j].getTapada()) {

                    System.out.print("[ " + taula[i][j].getValor() + " ]");

                }
                if (!taula[i][j].getTapada()) {

                    System.out.print("[ " + taula[i][j].getValorNumeric() + " ]");

                }
            }
            System.out.println();
        }

    }

    //Metode que distribueix les mines
    public void rellenarAmbMines(int x, int y) {

        int tempNMines = this.nMines;

        //Bucle que iterara un numero de vegades igual al numero de mines que hi ha
        //Per cada iteracio el bucle treu les coordenades aleatories i distribueix les mines.
        for (int i = 0; i < tempNMines; i++) {

            int cordX = (int) (Math.random() * this.x);
            int cordY = (int) (Math.random() * this.y);

            // [files][columnes] -> [2][1]
            // ****
            // ****
            // *x**
            // ****
            //QUAN PARLAM DE MATRIUS ES GIREN ELS VALORS
            //CONDICIONAL QUE COMPROVA DE NO POSAR MINES A LA PRIMERA TIRADA DE LA PARTIDA
            if (cordX != x || cordY != y) {

                if (!taula[cordX][cordY].getMina()) {

                    taula[cordX][cordY].setMina(true);
                    taula[cordX][cordY].setValorNumeric(9);

                } else {

                    //Segon bucle que treura una nova coordenada si volia posar una mina a una posicio on ja nhi havia una
                    while (!taula[cordY][cordX].getMina()) {

                        cordX = (int) (Math.random() * this.x);
                        cordY = (int) (Math.random() * this.y);

                        taula[cordY][cordX].setMina(true);
                        taula[cordY][cordX].setValorNumeric(9);
                    }
                }
            }
            //SI EL RANDOM DONA LES CORDENADES DE LA PRIMERA TIRADA SUMA 1 A LITERADOR DEL BUCLE PERQUE ITERI UN COP MES
            if (cordX==x&&cordY==y){
                tempNMines++;
            }
        }
    }

    public boolean realitzarTirada(int y, int x, boolean jocAcabat) {

        int tempX = this.x;
        int tempY = this.y;

        //Condicional que comprova que la tirada sigui legitima
        if (y <= tempY && x <= tempX && y >= 0 && x >= 0) {

            if (taula[x][y].getControl() == false) {

                taula[x][y].setTapada(false);

                //Condicional que si troba mina a la tirada s'acaba la partida
                if (taula[x][y].getMina()) {
                    System.out.println("*********************************************");
                    System.out.println("HAS EXPLOTAT UNA MINA! HAS PERDUT LA PARTIDA!");
                    System.out.println("*********************************************");

                    jocAcabat = true;

                } else {

                    //Condicional que si a la casella de la tirada val 0 vol dir que no hi ha cap mina
                    //Enrevoltant, per tant es poden destapar les caselles del voltant


                    if (taula[x][y].getValorNumeric() == 0) {

                        //TRACTAMENT CASELLES CIRCUNDANTS DE LA TIRADA

                        //TRACTAMENT CORNERS

                        //Tractament tirada raco adalt-esquerra
                        if (y == 0 && x == 0) {

                            taula[x + 1][y].setTapada(false);
                            taula[x][y + 1].setTapada(false);
                            taula[x + 1][y + 1].setTapada(false);


                            if (!taula[x][y].getControl()) {
                                taula[x][y].setControl(true);

                                if (taula[x + 1][y + 1].getValorNumeric() == 0) {
                                    realitzarTirada(y + 1, x + 1, jocAcabat);
                                }

                                if (taula[x][y + 1].getValorNumeric() == 0) {
                                    realitzarTirada(y + 1, x, jocAcabat);
                                }

                                if (taula[x + 1][y].getValorNumeric() == 0) {
                                    realitzarTirada(y, x + 1, jocAcabat);
                                }

                            }


                        }

                        //Tractament tirada raco abaix esquerra
                        if (y == 0 && x == this.x - 1) {

                            taula[x][y + 1].setTapada(false);
                            taula[x - 1][y + 1].setTapada(false);
                            taula[x - 1][y].setTapada(false);


                            if (!taula[x][y].getControl()) {
                                taula[x][y].setControl(true);

                                if (taula[x + 1][y].getValorNumeric() == 0) {
                                    realitzarTirada(y, x + 1, jocAcabat);
                                }

                                if (taula[x + 1][y].getValorNumeric() == 0) {
                                    realitzarTirada(y - 1, x + 1, jocAcabat);
                                }

                                if (taula[x][y + 1].getValorNumeric() == 0) {
                                    realitzarTirada(y + 1, x, jocAcabat);
                                }

                            }

                        }

                        //Tractament tirada raco adalt dreta
                        if (y == this.y - 1 && x == 0) {

                            taula[x + 1][y - 1].setTapada(false);
                            taula[x][y - 1].setTapada(false);
                            taula[x + 1][y].setTapada(false);


                            if (!taula[x][y].getControl()) {
                                taula[x][y].setControl(true);

                                if (taula[x + 1][y].getValorNumeric() == 0) {
                                    realitzarTirada(y, x + 1, jocAcabat);
                                }

                                if (taula[x][y - 1].getValorNumeric() == 0) {
                                    realitzarTirada(y - 1, x, jocAcabat);
                                }

                                if (taula[x + 1][y - 1].getValorNumeric() == 0) {
                                    realitzarTirada(y - 1, x + 1, jocAcabat);
                                }

                            }

                        }

                        //Tractament tirada raco abaix dreta
                        if (y == this.y - 1 && x == this.x - 1) {

                            taula[x - 1][y - 1].setTapada(false);
                            taula[x][y - 1].setTapada(false);
                            taula[x - 1][y].setTapada(false);


                            if (!taula[x][y].getControl()) {
                                taula[x][y].setControl(true);

                                if (taula[x - 1][y].getValorNumeric() == 0) {
                                    realitzarTirada(y, x - 1, jocAcabat);
                                }

                                if (taula[x][y - 1].getValorNumeric() == 0) {
                                    realitzarTirada(y - 1, x, jocAcabat);
                                }

                                if (taula[x - 1][y - 1].getValorNumeric() == 0) {
                                    realitzarTirada(y - 1, x - 1, jocAcabat);
                                }


                            }

                        }

                        //TRACTAMENTS TIRADA BANDES

                        //Tractament tirada banda esquerra
                        if (y == 0 && x != 0 && x != this.x - 1 && y != this.y - 1) {

                            taula[x + 1][y].setTapada(false);
                            taula[x][y + 1].setTapada(false);
                            taula[x - 1][y + 1].setTapada(false);
                            taula[x + 1][y + 1].setTapada(false);
                            taula[x - 1][y].setTapada(false);


                            if (!taula[x][y].getControl()) {
                                taula[x][y].setControl(true);

                                if (taula[x - 1][y].getValorNumeric() == 0) {
                                    realitzarTirada(y, x - 1, jocAcabat);
                                }

                                if (taula[x + 1][y + 1].getValorNumeric() == 0) {
                                    realitzarTirada(y + 1, x + 1, jocAcabat);
                                }

                                if (taula[x - 1][y + 1].getValorNumeric() == 0) {
                                    realitzarTirada(y + 1, x - 1, jocAcabat);
                                }

                                if (taula[x][y + 1].getValorNumeric() == 0) {
                                    realitzarTirada(y + 1, x, jocAcabat);
                                }

                                if (taula[x + 1][y].getValorNumeric() == 0) {
                                    realitzarTirada(y, x + 1, jocAcabat);
                                }

                            }

                        }

                        //Tractament tirada banda alta
                        if (y != 0 && x == 0 && y != this.y - 1 && x != this.x - 1) {

                            taula[x + 1][y].setTapada(false);
                            taula[x][y + 1].setTapada(false);
                            taula[x + 1][y - 1].setTapada(false);
                            taula[x + 1][y + 1].setTapada(false);
                            taula[x][y - 1].setTapada(false);


                            if (!taula[x][y].getControl()) {
                                taula[x][y].setControl(true);

                                if (taula[x][y - 1].getValorNumeric() == 0) {
                                    realitzarTirada(y - 1, x, jocAcabat);
                                }


                                if (taula[x + 1][y + 1].getValorNumeric() == 0) {
                                    realitzarTirada(y + 1, x + 1, jocAcabat);
                                }

                                if (taula[x + 1][y - 1].getValorNumeric() == 0) {
                                    realitzarTirada(y - 1, x + 1, jocAcabat);
                                }

                                if (taula[x][y + 1].getValorNumeric() == 0) {
                                    realitzarTirada(y + 1, x, jocAcabat);
                                }

                                if (taula[x + 1][y].getValorNumeric() == 0) {
                                    realitzarTirada(y, x + 1, jocAcabat);
                                }
                            }


                        }

                        //Tractament tirada banda dreta
                        if (y != 0 && x != 0 && y == this.y - 1 && x != this.x - 1) {

                            taula[x + 1][y].setTapada(false);
                            taula[x][y - 1].setTapada(false);
                            taula[x + 1][y - 1].setTapada(false);
                            taula[x - 1][y - 1].setTapada(false);
                            taula[x - 1][y].setTapada(false);


                            if (!taula[x][y].getControl()) {
                                taula[x][y].setControl(true);

                                if (taula[x - 1][y].getValorNumeric() == 0) {
                                    realitzarTirada(y, x - 1, jocAcabat);
                                }

                                if (taula[x - 1][y - 1].getValorNumeric() == 0) {
                                    realitzarTirada(y - 1, x - 1, jocAcabat);
                                }

                                if (taula[x + 1][y - 1].getValorNumeric() == 0) {
                                    realitzarTirada(y - 1, x + 1, jocAcabat);
                                }

                                if (taula[x][y - 1].getValorNumeric() == 0) {
                                    realitzarTirada(y - 1, x, jocAcabat);
                                }

                                if (taula[x + 1][y].getValorNumeric() == 0) {
                                    realitzarTirada(y, x + 1, jocAcabat);
                                }


                            }


                        }

                        //Tractament tirada banda baixa
                        if (x != 0 && x == this.x - 1 && y != 0 && y != this.y - 1) {

                            taula[x][y + 1].setTapada(false);
                            taula[x][y - 1].setTapada(false);
                            taula[x - 1][y - 1].setTapada(false);
                            taula[x - 1][y + 1].setTapada(false);
                            taula[x - 1][y].setTapada(false);

                            if (!taula[x][y].getControl()) {
                                taula[x][y].setControl(true);

                                if (taula[x - 1][y].getValorNumeric() == 0) {
                                    realitzarTirada(y, x - 1, jocAcabat);
                                }

                                if (taula[x - 1][y + 1].getValorNumeric() == 0) {
                                    realitzarTirada(y + 1, x - 1, jocAcabat);
                                }

                                if (taula[x - 1][y - 1].getValorNumeric() == 0) {
                                    realitzarTirada(y - 1, x - 1, jocAcabat);
                                }

                                if (taula[x][y - 1].getValorNumeric() == 0) {
                                    realitzarTirada(y - 1, x, jocAcabat);
                                }

                                if (taula[x][y + 1].getValorNumeric() == 0) {
                                    realitzarTirada(y + 1, x, jocAcabat);
                                }


                            }


                        }

                        //TRACTAMENT TOTES LES CASELLES

                        //Tractament tirada majoria caselles
                        if (x != 0 && y != 0 && x != this.x - 1 && y != this.y - 1) {

                            taula[x + 1][y].setTapada(false);
                            taula[x][y + 1].setTapada(false);
                            taula[x + 1][y + 1].setTapada(false);
                            taula[x - 1][y].setTapada(false);
                            taula[x][y - 1].setTapada(false);
                            taula[x - 1][y - 1].setTapada(false);
                            taula[x + 1][y - 1].setTapada(false);
                            taula[x - 1][y + 1].setTapada(false);


                            if (!taula[x][y].getControl()) {
                                taula[x][y].setControl(true);

                                if (taula[x - 1][y + 1].getValorNumeric() == 0 && !taula[x - 1][y + 1].getTapada()) {
                                    realitzarTirada(y + 1, x - 1, jocAcabat);
                                }

                                if (taula[x + 1][y - 1].getValorNumeric() == 0 && !taula[x + 1][y - 1].getTapada()) {
                                    realitzarTirada(y - 1, x + 1, jocAcabat);
                                }

                                if (taula[x - 1][y - 1].getValorNumeric() == 0 && !taula[x - 1][y - 1].getTapada()) {
                                    realitzarTirada(y - 1, x - 1, jocAcabat);
                                }

                                if (taula[x][y - 1].getValorNumeric() == 0 && !taula[x][y - 1].getTapada()) {
                                    realitzarTirada(y - 1, x, jocAcabat);
                                }

                                if (taula[x - 1][y].getValorNumeric() == 0 && !taula[x - 1][y].getTapada()) {
                                    realitzarTirada(y, x - 1, jocAcabat);
                                }

                                if (taula[x + 1][y + 1].getValorNumeric() == 0 && !taula[x + 1][y + 1].getTapada()) {
                                    realitzarTirada(y + 1, x + 1, jocAcabat);
                                }

                                if (taula[x][y + 1].getValorNumeric() == 0 && !taula[x][y + 1].getTapada()) {
                                    realitzarTirada(y + 1, x, jocAcabat);
                                }

                                if (taula[x + 1][y].getValorNumeric() == 0 && !taula[x + 1][y].getTapada()) {
                                    realitzarTirada(y, x + 1, jocAcabat);
                                }
                            }
                        }
                    }

                    else {
                        System.out.println("Coordenades incorrectes.");
                    }


                }
            }
        }

        return jocAcabat;
    }





    //METODE QUE DESTAPA TOTES LES CASELLES
    public void destaparCaselles() {

        for (Casella[] casellas : taula) {
            for (int j = 0; j < taula.length; j++) {

                casellas[j].setTapada(false);

            }
        }
        imprimirTaulell(taula);
    }

    //METODE QUE TAPA TOTES LES CASELLES (PER FER PROVES)
    /*public void taparCaselles(){

        for (int i = 0; i < taula.length; i++) {
            for (int j = 0; j < taula.length; j++) {

                taula[i][j].setTapada(true);

            }
        }
    }*/

    //Metode que marca una casella com a mina amb una X (BANDERA)
    public void marcarMina(int x, int y, int opcio) {

        if (opcio==1||opcio==2) {

            if (opcio == 1) {

                if (tempMinesArtificials != this.nMines) {

                    taula[x][y].setValor("X");
                    taula[x][y].setBandera(true);
                    tempMinesArtificials++;
                    System.out.println("Has marcat la casella [" + x + "][" + y + "] com una mina, et queden " + tempMinesArtificials + " banderes restants.");

                    if (taula[x][y].getMina()) {
                        tempMines++;
                    }

                }
                if (tempMinesArtificials==nMines&&tempMines!=nMines){
                    System.out.println("Jas has posat totes les banderes posibles,\nsi el joc no s'ha acabat es que tens alguna bandera mal colocada.");
                }
                if (tempMines == this.nMines) {
                    System.out.println("Has guanyat la partida! Enhorabona!");
                }
            }

            if (opcio == 2) {

                if (tempMinesArtificials!=0) {

                    if (taula[x][y].getBandera()) {

                        taula[x][y].setValor("■");
                        taula[x][y].setBandera(false);

                        System.out.println("Has desmarcat la casella [" + x + "][" + y + "] com una mina, tens " +(nMines-tempMinesArtificials) + " banderes restants.");


                        tempMinesArtificials--;
                    } else {
                        System.out.println("Aquesta casella no estava marcada com a mina! Torna a provar.");
                    }
                }

                if (tempMinesArtificials==0){
                    System.out.println("Ara mateix no tens cap mina colocada.");
                }

            }
        }

        if (opcio!=1&&opcio!=2) {
            System.out.println(opcio+" No es cap opcio disponible.");
        }

    }



    //Metode que dona un valor (variable valor) segons el numero de mines que toca la casella
    public void valorCasellaSegonsMines(){

        int tempX=this.x;
        int tempY=this.y;
        int tempNMinesContigues=0;

        for (int y = 0; y < tempY; y++) {
            for (int x = 0; x < tempX; x++) {

                //TRACTAMENT CASELLES CIRCUNDANTS DE LA TIRADA
                if (!taula[x][y].getMina()) {

                    //TRACTAMENT CORNERS

                    //Tractament tirada raco adalt-esquerra
                    if (y == 0 && x == 0) {

                        if (taula[x + 1][y].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x][y + 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x + 1][y + 1].getMina()) {

                            tempNMinesContigues++;

                        }
                        taula[x][y].setValorNumeric(tempNMinesContigues);
                        tempNMinesContigues=0;
                    }

                    //Tractament tirada raco abaix esquerra
                    if (y == 0 && x == tempX - 1) {

                        if (taula[x][y + 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x - 1][y + 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x - 1][y].getMina()) {

                            tempNMinesContigues++;

                        }

                        taula[x][y].setValorNumeric(tempNMinesContigues);
                        tempNMinesContigues=0;

                    }

                    //Tractament tirada raco adalt dreta
                    if (y == tempY - 1 && x == 0) {

                        if (taula[x + 1][y - 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x][y - 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x + 1][y].getMina()) {

                            tempNMinesContigues++;

                        }

                        taula[x][y].setValorNumeric(tempNMinesContigues);
                        tempNMinesContigues=0;

                    }

                    //Tractament tirada raco abaix dreta
                    if (y == tempY - 1 && x == tempX - 1) {

                        if (taula[x - 1][y - 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x][y - 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x - 1][y].getMina()) {

                            tempNMinesContigues++;

                        }

                        taula[x][y].setValorNumeric(tempNMinesContigues);
                        tempNMinesContigues=0;

                    }

                    //TRACTAMENTS TIRADA BANDES

                    //Tractament tirada banda esquerra
                    if (y == 0 && x != 0 && x != tempX - 1 && y != tempY - 1) {

                        if (taula[x + 1][y].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x][y + 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x - 1][y + 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x + 1][y + 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x - 1][y].getMina()) {

                            tempNMinesContigues++;

                        }

                        taula[x][y].setValorNumeric(tempNMinesContigues);
                        tempNMinesContigues=0;

                    }

                    //Tractament tirada banda alta
                    if (y != 0 && x == 0 && y != tempY - 1 && x != tempX - 1) {

                        if (taula[x + 1][y].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x][y + 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x + 1][y - 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x + 1][y + 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x][y - 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        taula[x][y].setValorNumeric(tempNMinesContigues);
                        tempNMinesContigues=0;

                    }

                    //Tractament tirada banda dreta
                    if (y != 0 && x != 0 && y == tempY - 1 && x != tempX - 1) {

                        if (taula[x + 1][y].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x][y - 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x + 1][y - 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x - 1][y - 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x - 1][y].getMina()) {

                            tempNMinesContigues++;

                        }

                        taula[x][y].setValorNumeric(tempNMinesContigues);
                        tempNMinesContigues=0;

                    }

                    //Tractament tirada banda baixa
                    if (x != 0 && x == tempX - 1 && y != 0 && y != tempY - 1) {

                        if (taula[x][y + 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x][y - 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x - 1][y - 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x - 1][y + 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        if (taula[x - 1][y].getMina()) {

                            tempNMinesContigues++;

                        }

                        taula[x][y].setValorNumeric(tempNMinesContigues);
                        tempNMinesContigues=0;

                    }

                    //TRACTAMENT TOTES LES CASELLES

                    //Tractament tirada majoria caselles
                    if (x != 0 && y != 0 && x != tempX - 1 && y != tempY - 1) {

                        if (taula[x + 1][y].getMina()) {

                            tempNMinesContigues++;

                        }
                        if (taula[x][y + 1].getMina()) {

                            tempNMinesContigues++;

                        }
                        if (taula[x + 1][y + 1].getMina()) {

                            tempNMinesContigues++;

                        }
                        if (taula[x - 1][y].getMina()) {

                            tempNMinesContigues++;

                        }
                        if (taula[x][y - 1].getMina()) {

                            tempNMinesContigues++;

                        }
                        if (taula[x - 1][y - 1].getMina()) {

                            tempNMinesContigues++;

                        }
                        if (taula[x + 1][y - 1].getMina()) {

                            tempNMinesContigues++;

                        }
                        if (taula[x - 1][y + 1].getMina()) {

                            tempNMinesContigues++;

                        }

                        taula[x][y].setValorNumeric(tempNMinesContigues);
                        tempNMinesContigues=0;



                    }
                }

            }

        }

    }


}











