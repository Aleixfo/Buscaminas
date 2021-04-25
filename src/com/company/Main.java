package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //Variable booleana que indica si el joc ha acabat o no
        boolean jocAcabat = false;

        System.out.println("Com et dius?");
        String nomJug = sc.nextLine();

        //Inici del programa
        System.out.println("Hola "+nomJug+",\nAmb quina dificultat vols jugar? ");
        System.out.println("**********************");
        System.out.println("Facil = 1\nMitjana = 2\nDificil = 3\nJoc Personalitzat = 4");
        System.out.println("**********************");
        System.out.print("Dificultat: ");

        //Propietats que es demanen a l'usuari es defineixen segons l'usuari
        int dificultat = sc.nextInt();
        int midaX = 0;
        int midaY = 0;
        int nMines = 0;

        if (dificultat == 1 || dificultat == 2 || dificultat == 3 || dificultat == 4) {
            //Switch que tries la dificultat
            //Depenguent de la dificultat el taulell i el numero de mines variara
            switch (dificultat) {

                case 1:
                    System.out.println("Dificultat principiant triada.");
                    System.out.println("*************************************");
                    midaX = 8;
                    midaY = 8;
                    nMines = 10;
                    break;
                case 2:
                    System.out.println("Dificultat normal triada.");
                    System.out.println("*************************************");
                    midaX = 16;
                    midaY = 16;
                    nMines = 40;
                    break;
                case 3:
                    System.out.println("Dificultat dificil triada.");
                    System.out.println("*************************************");
                    midaX = 16;
                    midaY = 30;
                    nMines = 99;
                    break;
                case 4:
                    System.out.println("Has triat Joc Personalitzat.");
                    System.out.println("Dimensions del taulell.");
                    System.out.print("Altura del taulell (FILES): ");
                    midaX = sc.nextByte();
                    System.out.print("Ample del taulell (COL): ");
                    midaY = sc.nextByte();
                    System.out.print("Numero de mines:  ");
                    nMines = sc.nextByte();
                    System.out.println("*************************************");
                    break;
            }


            int banderes = nMines;

            //Formacio i ordenacio del taulell
            Taulell taula = new Taulell(nMines, midaX, midaY);
            taula.rellenarTaulell(taula.getTaulell());
            taula.imprimirTaulell(taula.getTaulell());
            System.out.println("*************************************");

            //PRIMERA TIRADA SEMPRE HA DE SER VALIDA
            System.out.println("Realitza la teva primera tirada.");
            System.out.print("Introdueix la coordenada X: ");
            int xPrimeraTirada = sc.nextInt();
            System.out.print("Introdueix la coordenada Y: ");
            int yPrimeraTirada = sc.nextInt();
            System.out.println("Has introduit la coordenada [" + xPrimeraTirada + "][" + yPrimeraTirada + "]");

            taula.rellenarAmbMines(xPrimeraTirada, yPrimeraTirada);
            taula.valorCasellaSegonsMines();
            taula.realitzarTirada(yPrimeraTirada, xPrimeraTirada, false);
            taula.imprimirTaulell(taula.getTaulell());

            while (!jocAcabat) {

                System.out.println("*************************************");
                System.out.println("Que vols fer: ");
                System.out.println("*************************************");
                System.out.println("1 Realitzar Tirada\n2 Posar-Llevar Bandera\n3 Veure Taulell Destapat (OPCIO ESPECIAL)");
                System.out.println("*************************************");
                System.out.print("Accio: ");
                int accio = sc.nextByte();

                if (accio == 1 || accio == 2 || accio == 3 || accio == 4 || accio == 5 || accio == 6) {

                    switch (accio) {

                        case 1:
                            System.out.print("Cordenada X (FILA): ");
                            int x = sc.nextInt();
                            System.out.print("Cordenada Y (COL): ");
                            int y = sc.nextInt();
                            System.out.println("Has introduit la coordenada [" + x + "][" + y + "]");
                            jocAcabat = taula.realitzarTirada(y, x, false);
                            System.out.println("*************************************");
                            taula.imprimirTaulell(taula.getTaulell());
                            System.out.println("*************************************");
                            boolean victoria = taula.comprovarJoc();
                            if (victoria) {
                                jocAcabat = true;
                            }
                            break;
                        case 2:
                            System.out.println("1 Posar Bandera\n2 Llevar Bandera");
                            System.out.print("Opcio: ");
                            int opcio = sc.nextInt();
                            System.out.println("*************************************");
                            if (opcio == 1 || opcio == 2) {
                                System.out.println("Quina casella vols marcar/desmarcar com a mina?");
                                System.out.print("Cordenada X (FILA): ");
                                int cX = sc.nextInt();
                                System.out.print("Cordenada Y (COL): ");
                                int cY = sc.nextInt();
                                banderes = taula.marcarMina(cX, cY, opcio, banderes);
                                System.out.println("*************************************");
                                taula.imprimirTaulell(taula.getTaulell());
                                System.out.println("*************************************");
                            } else {
                                System.out.println(opcio + " No es cap opcio disponible.");
                                System.out.println("*************************************");
                            }
                            break;
                        case 3:
                            taula.destaparCaselles();
                            System.out.println("Aqui tens el teu taulell completament destapat.");
                            System.out.println("*************************************");
                            break;

                    }
                } else {
                    System.out.println(accio+" No es una opcio disponible.");
                    System.out.println("*************************************");
                }
            }
        } else {
            System.out.println(dificultat + " No es una opcio disponible.");
        }
    }
}

