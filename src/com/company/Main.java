package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //Variable booleana que indica si el joc ha acabat o no
        boolean jocAcabat=false;

        //Inici del programa
        System.out.println("Amb quina dificultat vols jugar? ");
        System.out.println("Facil = 1 / Mitjana = 2 / Dificil = 3 / Joc Personalitzat = 4");
        System.out.print("Dificultat: ");
        //Propietats que es demanen a l'usuari, dificultat que determina la mida i numero de mines
        int dificultat = sc.nextInt();
        int midaX=0;
        int midaY=0;
        int nMines=0;

        //Switch que tries la dificultat
        //Depenguent de la dificultat el taulell i el numero de mines variara
        switch(dificultat){

            case 1:
                System.out.println("Dificultat principiant triada.");
                midaX =  8;
                midaY= 8;
                nMines = 10;

                break;

            case 2:
                System.out.println("Dificultat normal triada.");
                midaX = 16;
                midaY= 16;
                nMines = 40;

                break;

            case 3:
                System.out.println("Dificultat dificil triada.");
                midaX=16;
                midaY=32;
                nMines=99;

                break;

            case 4:
                System.out.println("Has triat Joc Personalitzat.");
                System.out.println("Quina mida vols que tengui el taulell? ");
                midaX = sc.nextByte();
                midaY = sc.nextByte();
                System.out.println("Cuantes mines vols que tengui el taulell? ");
                nMines = sc.nextByte();
                break;
        }

        //Formacio i ordenacio del taulell
        Taulell taula = new Taulell(nMines, midaX, midaY);
        taula.rellenarTaulell(taula.getTaulell());
        //PRIMERA TIRADA SEMPRE HA DE SER VALIDA
        System.out.println("Realitza la teva primera tirada.");
        System.out.print("Introdueix la coordenada X: ");
        int xPrimeraTirada = sc.nextInt();
        System.out.print("Introdueix la coordenada Y: ");
        int yPrimeraTirada = sc.nextInt();
        System.out.println("Has introduit la coordenada ["+xPrimeraTirada+"]["+yPrimeraTirada+"]");
        taula.rellenarAmbMines(xPrimeraTirada, yPrimeraTirada);
        taula.valorCasellaSegonsMines();
        taula.realitzarTirada(yPrimeraTirada, xPrimeraTirada, false);
        taula.imprimirTaulell(taula.getTaulell());

        while (!jocAcabat) {

            System.out.println("Que vols fer: ");
            System.out.println("1 Imprimir Taulell / 2 Realitzar Tirada / \n3 Destapar Caselles / 4 Bandera / 5 Acabar partida" );
            System.out.print("Accio: ");
            int accio = sc.nextByte();

            switch (accio){

                case 1:
                    taula.imprimirTaulell(taula.getTaulell());
                    break;

                case 2:
                    System.out.print("Introdueix la coordenada X: ");
                    int x = sc.nextInt();
                    System.out.print("Introdueix la coordenada Y: ");
                    int y = sc.nextInt();
                    System.out.println("Has introduit la coordenada ["+x+"]["+y+"]");
                    jocAcabat=taula.realitzarTirada(y, x, false);
                    taula.imprimirTaulell(taula.getTaulell());
                    break;

                case 3:
                    taula.destaparCaselles();
                    System.out.println("Has destapat totes les caselles! S'ha acabat el joc.");
                    jocAcabat=true;
                    break;


                case 4:
                    System.out.println("Si vols marcar mina introdueix un 1,\nSi la vols desmarcar introdueix un 2");
                    System.out.print("Opcio: ");
                    int opcio = sc.nextInt();
                    System.out.println("Quina casella vols marcar com a mina?");
                    System.out.print("Cordenada X: ");
                    int cX = sc.nextInt();
                    System.out.print("Cordenada Y: ");
                    int cY = sc.nextInt();
                    taula.marcarMina(cX, cY, opcio);
                    break;

                case 5:
                    System.out.println("Estas segur que vols finalitzar la partida?");
                    System.out.print("Resposta (SI/NO): ");
                    String resposta=sc.nextLine();
                    if (resposta.equalsIgnoreCase("si")) {
                        System.out.println("-------------------");
                        System.out.println("Partida finalitzada.");
                        System.out.println("-------------------");
                        jocAcabat = true;
                    }
                    if (resposta.equalsIgnoreCase("si")){
                        break;
                    }
                    break;
            }



        }
    }
}
