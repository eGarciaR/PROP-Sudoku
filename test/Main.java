/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
import controllers.domain.DomainCtrl;
import controllers.domain.EditorDomainCtrl;
import controllers.domain.GameDomainCtrl;
import domain.BoardSudoku;
import domain.Game;
import java.util.Scanner;


public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //CtrlPresentacion mainWindow = new CtrlPresentacion();
                //mainWindow.inicializarPresentacion();
                Scanner scan = new Scanner(System.in);
                DomainCtrl dc = DomainCtrl.getInstance();
                GameDomainCtrl gdc =  dc.getGameDomainCtrl();
                EditorDomainCtrl edc = dc.getEditorDomainCtrl();
                printMenu();
                int option = scan.nextInt();
                while(option != 3){
                   switch(option){
                        case 1: 
                            System.out.println("--------Jugar Partida--------");
                            System.out.println("Introdueix: mida[2..6]");
                            int t = scan.nextInt();
                            System.out.println("Introdueix: dificultat(EASY, MEDIUM, HARD)");
                            // agafar dificultat
                            System.out.println("Introdueix: nivell d'ajuda(BEGINNER, INTERMEDIATE, ADVANCED) ");
                            // agafar nivell d'ajuda
                            BoardSudoku b = gdc.generateRandomSudoku(t, BoardSudoku.Difficulty.EASY);
                            b.printBoard();
                            gdc.startPlayGame(b, "BEGINNER", null);
                            while(!gdc.gameEnded()){
                               joc(scan,gdc,b); 
                            }
                            break;
                        case 2: 
                            System.out.println("---------Proposar Sudoku---------");
                            System.out.println("Introdueix: mida[2..6]");
                            t = scan.nextInt();
                            edc.startEditor(t, "Marc");
                            int option2 = 0;
                            while(option2 != 4){
                                System.out.println("1: continuar 2: solucionar 3: jugar 4:sortir");
                                option2 = scan.nextInt();
                                if(option2 == 2){
                                    if (!edc.solvePC()) {
                                        System.out.println("No té una solució unica has de introduïr més xifres.");
                                    }
                                    break;
                                }
                                else if(option2 == 3){
                                    dc.saveAndPlay("BEGINNER");
                                    while(!gdc.gameEnded()){
                                        joc(scan,gdc,edc.getBoard());
                                    }
                                }
                                else if(option2 == 4)break;
                                editor(scan,edc);
                            }                           
                            break;
                        case 3: 
                            System.out.println("Sortir");
                            break;
                        default:
                            break;
                           
                    } 
                    printMenu();

                    option = scan.nextInt();
                }  
            }
            
            public void printMenu(){
                System.out.println("-------MENU--------");
                System.out.println("1.- Jugar Partida");
                System.out.println("2.- Proposar Sudoku");
                System.out.println("3.- Sortir");
                System.out.println("-------------------");
            }

            void recollirDadesNecesaries(GameDomainCtrl gdc, Scanner scan){
                System.out.println("Jugar Partida");
                System.out.println("Introdueix: mida[2..6]");
                int t = scan.nextInt();
                System.out.println("Introdueix: dificultat(EASY, MEDIUM, HARD)");
                // agafar dificultat
                System.out.println("Introdueix: nivell d'ajuda(BEGINNER, INTERMEDIATE, ADVANCED) ");
                // agafar nivell d'ajuda
                BoardSudoku b = gdc.generateRandomSudoku(t, BoardSudoku.Difficulty.EASY);
                gdc.startPlayGame(b, "BEGINNER", null);
            }    
            
            public void joc(Scanner scan, GameDomainCtrl gdc, BoardSudoku b){                
                    System.out.println("Introdueix xifra: fila columna i valor");
                    int fila = scan.nextInt();
                    int col = scan.nextInt();
                    int valor = scan.nextInt();
                    gdc.setCellValue(fila, col, valor);
                    b.printBoard();                
            }
            public void editor(Scanner scan, EditorDomainCtrl edc){
                edc.getBoard().printBoard();
                System.out.println("Introdueix xifra: fila columna i valor");
                int fila = scan.nextInt();
                int col = scan.nextInt();
                int valor = scan.nextInt();
                edc.setCellValue(fila, col, valor);
            }
        });
        
    }

}
