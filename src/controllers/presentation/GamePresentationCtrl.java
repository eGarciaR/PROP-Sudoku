package controllers.presentation;

import exceptions.BadNumberPositionException;
import presentation.GameView;

import controllers.domain.BoardDomainCtrl;
import controllers.domain.GameDomainCtrl;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Marc
 */

public class GamePresentationCtrl {
    private static GamePresentationCtrl gamePresentationCtrl;
    private final PresentationCtrl presentationCtrl;
    private final GameDomainCtrl gameDomainCtrl;
    private final BoardDomainCtrl boardDomainCtrl;

    private GamePresentationCtrl(PresentationCtrl pCtrl) {
        this.presentationCtrl = pCtrl;
        this.gameDomainCtrl = pCtrl.getGameDomainCtrl();
        this.boardDomainCtrl = pCtrl.getBoardDomainCtrl();
    }

    public static GamePresentationCtrl getInstance(PresentationCtrl pCtrl) {
        if (gamePresentationCtrl == null) {
            gamePresentationCtrl = new GamePresentationCtrl(pCtrl);
        }
        return gamePresentationCtrl;
    }
    
    /**
     * Tradueix el nivell d'ajuda.
     * @param helpLevel Nivell d'ajuda
     * @return La traducció del nivell d'ajuda.
     */
    public String translateHelpLevel(String helpLevel) {
        String s;
        switch (helpLevel) {
            case "BEGINNER":
                s = "PRINCIPIANT";
                break;
            case "PRINCIPIANT":
                s = "BEGINNER";
                break;
            case "INTERMEDIATE":
                s = "INTERMIG";
                break;
            case "INTERMIG":
                s = "INTERMEDIATE";
                break;
            case "ADVANCED":
                s = "AVANÇAT";
                break;
            case "AVANÇAT":
                s = "ADVANCED";
                break;
            default:
                s = null;
        }
        return s;
    }
    
    /**
     * Tradueix el nivell de dificultat.
     * @param difficulty Dificultat
     * @return La traducció de la dificultat.
     */
    public String translateDifficulty(String difficulty) {
        String s;
        switch (difficulty) {
            case "EASY":
                s = "FÀCIL";
                break;
            case "FÀCIL":
                s = "EASY";
                break;
            case "MEDIUM":
                s = "MITJÀ";
                break;
            case "MITJÀ":
                s = "MEDIUM";
                break;
            case "HARD":
                s = "DIFÍCIL";
                break;
            case "DIFÍCIL":
                s = "HARD";
                break;
            default:
                s = null;
        }
        return s;
    }
    
    /**
     * Retorna un String amb el temps format hh:mm:ss
     * @return String del temps
     */
    public String getTime() {
        long time = (gameDomainCtrl.getTime() / 1000);
        int h = (int) (time / 3600);
        int m = (int) ((time % 3600 / 60));
        int s = (int) ((time % 3600) % 60);
        String sec = (s < 10) ? ":0" : ":";
        String min = (m < 10) ? ":0" : ":";
        String hour = (m < 10) ? "0" : "";
        return hour + h + min + m + sec + s;
    }
    
    /**
     * La puntuació de la partida, únicament serà correcta quan esta finaltitzada.
     * @pre Partida finalitzada
     * @return Puntuació
     */
    public int getScore() {
        return this.gameDomainCtrl.getScore();
    }
    /**
     * Comença una nova partida.
     * @param boardId Identificador de tauler
     * @param helpLevel Nivell d'ajuda
     */
    public void startGame(int boardId, String helpLevel) {
        this.presentationCtrl.startGame(boardId, helpLevel);
    }

    /**
     * Comença una nova partida aleatoria.
     * @param n Mida
     * @param difficulty Dificultat
     * @param helpLevel Nivell d'ajuda
     */
    public void startRandomGame(int n, String difficulty, String helpLevel) {
        this.presentationCtrl.startRandomGame(n, difficulty, helpLevel);
    }
    
    /**
     * Comenaça una nova partida amb el tauler de l'editor.
     * @param helpLevel Nivell d'ajuda.
     */
    public void startGameFromEditor(String helpLevel) {
        this.presentationCtrl.startGameFromEditor(helpLevel);
    }
    
    /**
     * Operació booleana per saber si l'usuari que ha fet login té una partida guardada.
     * @return True si té partida guardada, False altrament.
     */
    public boolean savedGame() {
        String user = this.presentationCtrl.getUserDomainCtrl().getUser();
        return this.gameDomainCtrl.savedGame(user);
    }
    
    /**
     * Carrega la partida guardada a la base de dades de l'usuari que ha fet loguin.
     */
    public void loadGame() {
        this.presentationCtrl.loadGame();
    }

    /**
     * Pausa el joc.
     */
    public void pauseGame() {
        gameDomainCtrl.pauseGame();
    }

    /**
     * Reanuda el joc pausat.
     */
    public void resumeGame() {
        gameDomainCtrl.resumeGame();
    }
    
    /**
     * Introdueix un valor en una cel·la del tauler.
     *
     * @param row Fila
     * @param column Columna
     * @param value Valor
     * @throws BadNumberPositionException Llançada quan no es pot introduïr el valor.
     */
    public void setCellValue(int row, int column, int value) throws BadNumberPositionException {
        gameDomainCtrl.setCellValue(row, column, value);
    }
    
    /**
     * Defineix en la vista de game el tauler de l'editor.
     *
     * @param gameView Vista de la partida.
     */
    public void setInitialBoard(GameView gameView) {
        String board = gameDomainCtrl.getBoard();
        String[] regions = board.split("\\|");
        int n = gameView.getN();
        int rowRegion = 0, colRegion = 0;
        int rowCell, colCell, value;
        for (String sRegion : regions) {
            String[] cells = sRegion.split(",");
            rowCell = 0;
            colCell = 0;
            for (String cell : cells) {
                String fixed = cell.substring(cell.length()-1, cell.length());
                cell = cell.substring(0, cell.length()-1);
                value = Integer.valueOf(cell);
                gameView.setCellValue(rowCell + (n * rowRegion), colCell + (n * colRegion), value);
                if (value != 0 && fixed.equals("F")) {
                    gameView.setFixedCell(rowCell + (n * rowRegion), colCell + (n * colRegion));
                }
                ++colCell;
                if (colCell >= n) {
                    ++rowCell;
                    colCell = 0;
                }
            }
            ++colRegion;
            if (colRegion >= n) {
                ++rowRegion;
                colRegion = 0;
            }
        }
    }
    
     /**
     * Array de enters possibles de la mida d'un Sudoku.
     *
     * @return Array de enters
     */
    public Integer[] getSizeModel() {
        return this.boardDomainCtrl.getSizeModel();
    }
    
     /**
     * Array de strings possibles de la dificultat d'un Sudoku.
     *
     * @return Array de strings
     */
    public String[] getDifficultyModel() {
        return this.boardDomainCtrl.getDifficultyModel();
    }
    /**
     * Array de strings possibles del nivell d'ajuda d'un Sudoku.
     *
     * @return Array de strings
     */
    public String[] getHelpLevelModel() {
        return this.gameDomainCtrl.getHelpLevelModel();
    }
    
    /**
     * Llistat de Sudokus en funció de la Mida i la dificultat.
     * @param n Mida
     * @param difficulty Dificultat
     * @return Llistat de Sudokus
     */
    public ArrayList<HashMap<String, Object>> getBoards(int n, String difficulty) {
        return this.boardDomainCtrl.getAll(n, difficulty);
    }
    
    /**
     * Operació booleana que indica si una partida s'ha finalitzat.
     * @return True si s'ha finalitzat, False altrament.
     */
    public boolean isGameEnded(){
        return presentationCtrl.isGameEnded();
    }
    
    /**
     * Finalitza una partida.
     */
    public void finishGame(){
        presentationCtrl.finishGame();
    }
}
