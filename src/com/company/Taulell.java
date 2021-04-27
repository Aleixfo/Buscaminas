package com.company;

public class Taulell {

    public static final String negre = "\u001B[30m";
    public static final String vermell = "\u001B[31m";
    public static final String verd = "\u001B[32m";
    public static final String groc = "\u001B[33m";
    public static final String blau = "\u001B[34m";
    public static final String morat = "\u001B[35m";
    public static final String cyan = "\u001B[36m";
    public static final String blanc = "\u001B[37m";
    public static final String reset = "\u001B[0m";

    //Propietats de la classe taulell
    //[files][columnes] -> [x][y] -> [i][j]
    private final Casella[][] taula;
    private final int nMines;
    private final int x;
    private final int y;

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

        for (int i = 0; i < this.x; i++) {
            for (int j = 0; j < this.y; j++) {

                //[i][j]=[x][y]
                taula[i][j] = new Casella();

            }
        }
    }

    //Metode que distribueix les mines
    public void rellenarAmbMines(int x, int y) {

        //Bucle que iterara un numero de vegades igual al numero de mines que hi ha
        //Per cada iteracio el bucle treu les coordenades aleatories i distribueix les mines.
        for (int i = 0; i < this.nMines; i++) {

            int cordX;
            int cordY;

            do {
                cordX = (int) (Math.random() * this.x);
                cordY = (int) (Math.random() * this.y);

            } while (taula[cordX][cordY].getMina() || (cordX == x && cordY == y));

            taula[cordX][cordY].setMina(true);
            taula[cordX][cordY].setValorNumeric(9);
        }
    }

    //Metode que dona un valor (variable valor) segons el numero de mines que toca la casella
    public void valorCasellaSegonsMines() {

        int tempNMinesContigues = 0;

        for (int y = 0; y < this.y; y++) {
            for (int x = 0; x < this.x; x++) {

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
                        tempNMinesContigues = 0;
                    }

                    //Tractament tirada raco abaix esquerra
                    if (y == 0 && x == this.x - 1) {

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
                        tempNMinesContigues = 0;

                    }

                    //Tractament tirada raco adalt dreta
                    if (y == this.y - 1 && x == 0) {

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
                        tempNMinesContigues = 0;

                    }

                    //Tractament tirada raco abaix dreta
                    if (y == this.y - 1 && x == this.x - 1) {

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
                        tempNMinesContigues = 0;

                    }

                    //TRACTAMENTS TIRADA BANDES

                    //Tractament tirada banda esquerra
                    if (y == 0 && x != 0 && x != this.x - 1 && y != this.y - 1) {

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
                        tempNMinesContigues = 0;

                    }

                    //Tractament tirada banda alta
                    if (y != 0 && x == 0 && y != this.y - 1 && x != this.x - 1) {

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
                        tempNMinesContigues = 0;

                    }

                    //Tractament tirada banda dreta
                    if (y != 0 && x != 0 && y == this.y - 1 && x != this.x - 1) {

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
                        tempNMinesContigues = 0;

                    }

                    //Tractament tirada banda baixa
                    if (x != 0 && x == this.x - 1 && y != 0 && y != this.y - 1) {

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
                        tempNMinesContigues = 0;

                    }

                    //TRACTAMENT TOTES LES CASELLES

                    //Tractament tirada majoria caselles
                    if (x != 0 && y != 0 && x != this.x - 1 && y != this.y - 1) {

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
                        tempNMinesContigues = 0;


                    }
                }

            }

        }

    }

    //Metode que imprimeix el taulell
    public void imprimirTaulell(Casella[][] taula) {

        //For d'ordenació del taulell, peque quedin els numeros de cordenades ben estructurats

        System.out.print("     ");
        for (int x = 0; x < this.y; x++) {


            if (x<10) {
                System.out.print(" ");
                System.out.print("[ " + x + " ]");
            }
            if (x>=10){
                System.out.print("[ " + x + " ]");
            }

        }
        System.out.println();

        //Bucle que printeja la columna de coordenades
        for (int i = 0; i < this.x; i++) {
            if (i <= 9) {
                System.out.print("[ "+i+" ]" + " ");
            } else {
                System.out.print("[ "+i+" ]");
            }
            for (int j = 0; j < this.y; j++) {

                //Aqui a l'imprimir el taulell si la casella esta destapada retornara el valor numeric
                //de les mines que te devora, si esta tapada retorna el cuadrat.
                if (taula[i][j].getTapada()) {

                    System.out.print("[ " + taula[i][j].getValor() + " ] ");

                }
                if (!taula[i][j].getTapada()) {

                    if (taula[i][j].getValorNumeric()==0) {

                        System.out.print("[ "+ negre + taula[i][j].getValorNumeric() + reset + " ] ");

                    }
                    if (taula[i][j].getValorNumeric()==1) {

                        System.out.print("[ "+ blau + taula[i][j].getValorNumeric() + reset + " ] ");

                    }
                    if (taula[i][j].getValorNumeric()==2) {

                        System.out.print("[ " + vermell + taula[i][j].getValorNumeric() + reset + " ] ");

                    }
                    if (taula[i][j].getValorNumeric()==3) {

                        System.out.print("[ " + verd + taula[i][j].getValorNumeric() + reset + " ] ");

                    }
                    if (taula[i][j].getValorNumeric()==4) {

                        System.out.print("[ " + groc + taula[i][j].getValorNumeric() + reset + " ] ");

                    }
                    if (taula[i][j].getValorNumeric()==5) {

                        System.out.print("[ " + morat + taula[i][j].getValorNumeric() + reset + " ] ");

                    }
                    if (taula[i][j].getValorNumeric()==6) {

                        System.out.print("[ " + cyan + taula[i][j].getValorNumeric() + reset + " ] ");

                    }
                    if (taula[i][j].getValorNumeric()==7) {

                        System.out.print("[ " + negre + taula[i][j].getValorNumeric() + reset + " ] ");

                    }
                    if (taula[i][j].getValorNumeric()==8) {

                        System.out.print("[ " + blanc + taula[i][j].getValorNumeric() + reset + " ] ");

                    }
                    if (taula[i][j].getValorNumeric()==9) {

                        System.out.print("[ " + blanc + taula[i][j].getValorNumeric() + reset + " ] ");

                    }

                }
            }
            System.out.println();
        }

    }

    //Metode principal que realitza una tirada al taulell
    public boolean realitzarTirada(int y, int x, boolean jocAcabat) {

        //Condicional que comprova que la tirada sigui legitima
        if (y < this.y && x < this.x && y >= 0 && x >= 0) {


            if (!taula[x][y].getControl()) {

                taula[x][y].setTapada(false);

                //Condicional que si troba mina a la tirada s'acaba la partida
                if (taula[x][y].getMina()) {

                    System.out.println("*********************************************");
                    System.out.println("HAS EXPLOTAT UNA MINA! HAS PERDUT LA PARTIDA!");
                    System.out.println("*********************************************");
                    jocAcabat = true;

                } else {
                    /*
                    Condicional que si a la casella de la tirada val 0 vol dir que no hi ha cap mina
                    enrevoltant, per tant es poden destapar les caselles del voltant.
                    */
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

                                if (taula[x - 1][y].getValorNumeric() == 0) {
                                    realitzarTirada(y, x + 1, jocAcabat);
                                }

                                if (taula[x - 1][y + 1].getValorNumeric() == 0) {
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
                }
            }
        }

        if (y > this.y || x > this.x || y < 0 || x < 0) {
            System.out.println("Coordenades incorrectes.");
        }

        return jocAcabat;
    }

    //Metode que marca una casella com a mina amb una X (BANDERA)
    public int marcarMina(int x, int y, int opcio, int banderes) {

        int tempMines = banderes;

        if (y < this.y && x < this.x && y >= 0 && x >= 0) {

            if (opcio == 1) {

                if (tempMines != 0) {

                    taula[x][y].setValor("X");
                    taula[x][y].setBandera(true);
                    tempMines--;
                    System.out.println("Has marcat la casella [" + x + "][" + y + "] com una mina, et queden " + (tempMines) + " banderes restants.");

                }

                if (tempMines == 0) {
                    System.out.println("Ja has colocat les " + this.nMines + " banderes posibles.");
                }
            }

            if (opcio == 2) {

                if (tempMines != this.nMines) {

                    if (taula[x][y].getBandera()) {

                        taula[x][y].setValor("■");
                        taula[x][y].setBandera(false);
                        tempMines++;

                        System.out.println("Has desmarcat la casella [" + x + "][" + y + "] com una mina, tens " + (tempMines) + " banderes restants.");

                    } else {
                        System.out.println("Aquesta casella no estava marcada com a mina! Torna a provar.");
                    }
                }

                if (tempMines == this.nMines) {
                    System.out.println("Ara mateix no tens cap mina colocada.");
                }

            }
        } else {
            System.out.println("Coordenades no valides.");
        }

        return tempMines;

    }

    //Metode que destapa totes les caselles imprimeix el taulell i les torna a tapar. (OPCIO PER DESENVOLUPADORS)
    public void destaparCaselles() {

        for (Casella[] casellas : taula) {
            for (int j = 0; j < this.y; j++) {

                casellas[j].setTapada(false);

            }
        }
        imprimirTaulell(taula);

        for (Casella[] casellas : taula) {
            for (int j = 0; j < this.y; j++) {

                casellas[j].setTapada(true);

            }
        }
        imprimirTaulell(taula);

    }

    //Metode que comprova si shan destapat totes les caselles corresponents. (LES QUE NO SON MINES)
    public boolean comprovarJoc() {

        boolean victoria=true;

        for (int i = 0; i < this.x; i++) {
            for (int j = 0; j < this.y; j++) {

                if (!taula[i][j].getMina()){

                    if (taula[i][j].getTapada()){

                        victoria=false;

                    }

                }
            }
        }

        if (victoria){
            System.out.println("ENHORABONA HAS DESTAPAT TOTES LES CASELLES CORRECTAMENT");
            System.out.println("HAS GUANYAY LA PARTIDA!");
        }

        return victoria;

    }

}











