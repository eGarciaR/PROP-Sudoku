package controllers.presentation;

import exceptions.BadNumberPositionException;

import controllers.domain.BoardDomainCtrl;
import controllers.domain.EditorDomainCtrl;
import presentation.EditorView;
import presentation.GameView;

/**
 *
 * @author Marc
 */
public class EditorPresentationCtrl {

    private static EditorPresentationCtrl editorPresentationCtrl;
    private final PresentationCtrl presentationCtrl;
    private final EditorDomainCtrl editorDomainCtrl;
    private final BoardDomainCtrl boardDomainCtrl;

    private EditorPresentationCtrl(PresentationCtrl pCtrl) {
        this.presentationCtrl = pCtrl;
        this.editorDomainCtrl = pCtrl.getEditorDomainCtrl();
        this.boardDomainCtrl = pCtrl.getBoardDomainCtrl();
    }

    public static EditorPresentationCtrl getInstance(PresentationCtrl pCtrl) {
        if (editorPresentationCtrl == null) {
            editorPresentationCtrl = new EditorPresentationCtrl(pCtrl);
        }
        return editorPresentationCtrl;
    }

    /**
     * Comença a editar un tauler.
     *
     * @param n Mida
     */
    public void startEditor(int n) {
        this.presentationCtrl.startEditor(n);
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
        editorDomainCtrl.setCellValue(row, column, value);
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
     * Operació booleana per saber si un Sudoku té solució única.
     *
     * @return True si té solució única, False altrament.
     */
    public boolean uniqueSolution() {
        return editorDomainCtrl.uniqueSolution();
    }

    /**
     * Desa el tauler de l'editor.
     *
     * @return True si s'ha desat, False altrament.
     */
    public boolean saveBoard() {
        return presentationCtrl.getDomainCtrl().saveEditor();
    }

    /**
     * Operació booleana per saber si un tauler té identificador.
     *
     * @return True si te identificador, False altrament.
     */
    public boolean haveBoardId() {
        return editorDomainCtrl.haveBoardId();
    }

    /**
     * Defineix la dificultat d'un tauler.
     */
    public void setDifficulty() {
        this.editorDomainCtrl.setDifficulty();
    }

    /**
     * Soluciona el sudoku de l'editor.
     */
    public void solve() {
        this.presentationCtrl.solveEditor();
    }

    /**
     * Defineix en la vista de l'editor el tauler de l'editor.
     *
     * @param editorView Vista de l'editor.
     */
    public void setBoard(EditorView editorView) {
        String board = editorDomainCtrl.getBoard().toString();
        String[] regions = board.split("\\|");
        int n = editorDomainCtrl.getBoard().getN();
        int rowRegion = 0, colRegion = 0;
        int rowCell, colCell, value;
        for (String sRegion : regions) {
            String[] cells = sRegion.split(",");
            rowCell = 0;
            colCell = 0;
            for (String cell : cells) {
                String fixed = cell.substring(cell.length() - 1, cell.length());
                cell = cell.substring(0, cell.length() - 1);
                value = Integer.valueOf(cell);
                editorView.setCellValue(rowCell + (n * rowRegion), colCell + (n * colRegion), value);
                if (value != 0 && fixed.equals("F")) {
                    editorView.setFixedCell(rowCell + (n * rowRegion), colCell + (n * colRegion));
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
}
