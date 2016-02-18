package presentation;


import exceptions.BadNumberPositionException;
import exceptions.ColumnLocationException;
import exceptions.RegionLocationException;
import exceptions.RowLocationException;
import controllers.presentation.PresentationCtrl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;

/**
 *
 * @author Marc
 */
public abstract class SudokuView {

    private final JPanel sudokuPanel = new JPanel();
    private final JPanel infoPanel = new JPanel();
    private final JPanel boardPanel = new JPanel();
    private final JPanel buttonsPanel = new JPanel();
    private final JLabel errorMessage = new JLabel();
    private JTextField[][] board;
    
    private final Border redBorder2 = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red);
    private final Border redBorder4 = BorderFactory.createMatteBorder(4, 4, 4, 4, Color.red);
    private final Border defaultBorder = BorderFactory.createLineBorder(new Color(102, 102, 102));
    //------------- Constants --------------
    private final int CELLSIZE;
    //------------ Variables-------------
    private final int n;
    private final int n2;
    private final PresentationCtrl pCtrl;

    //------------ Constructores i metodes publics -------------
    /**
     * Constructor de la classe Editor
     *
     * @param n mida
     * @param pCtrl control de presentació
     */
    public SudokuView(int n, PresentationCtrl pCtrl) {
        this.pCtrl = pCtrl;
        this.n = n;
        this.n2 = (int) Math.pow(n, 2);
        this.CELLSIZE = calculateCellSize();
        initializeComponents();
    }

    /**
     * Getter del controlador de presentacio
     *
     * @return control de presentació
     */
    public PresentationCtrl getPCtrl() {
        return this.pCtrl;
    }

    /**
     * Getter del panel sudoku
     *
     * @return panell del sudoku
     */
    public JPanel getSudokuPanel() {
        return this.sudokuPanel;
    }
    
    /**
     * Getter del panel del tauler
     *
     * @return panell del tauler
     */
    public JPanel getBoardPanel() {
        return this.boardPanel;
    }

    /**
     * Getter del panel d'informacio
     *
     * @return panell d'informacio
     */
    public JPanel getInfoPanel() {
        return this.infoPanel;
    }

    /**
     * Getter del panel de botons
     *
     * @return panell de botons
     */
    public JPanel getButtonsPanel() {
        return this.buttonsPanel;
    }

    /**
     * Retorna la mida necesaria per que capiguin tots els components
     * @return mida necessaria
     */
    public Dimension getSize() {
        int size = 200 + this.calculateBoardSize();
        return new Dimension(size, size);
    }

    public int getN() {
        return this.n;
    }
    
    public Border getDefaultBorder() {
        return this.defaultBorder;
    }
    
    public Border getRed2Border() {
        return this.redBorder2;
    }
    
    public Border getRed4Border() {
        return this.redBorder4;
    }

    /**
     * Setter de missatges d'error
     *
     * @param s missatge
     */
    public void setErrorMessage(String s) {
        String message = "<html><p>"+s+"</p></html>";
        errorMessage.setText(message);
        pCtrl.repaint();
    }

    /**
     * Setter del valor d'una cel·la
     *
     * @param row fila
     * @param col columna
     * @param value valor
     */
    public void setCellValue(int row, int col, int value) {
        if (value == 0) {
            board[row][col].setText("");
        } else {
            board[row][col].setText(Integer.toString(value));
        }
    }

    /**
     * Fa fica la cela amb posició fila = i columna = j
     *
     * @param i fila
     * @param j columna
     */
    public void setFixedCell(int i, int j) {
        board[i][j].setBackground(Color.gray);
        board[i][j].setEditable(false);
    }
 
    private void paintCell(int row, int col, boolean b){
        if(b) board[row][col].setBorder(redBorder2);
        else board[row][col].setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102)));
    }
    
    public void paintRow(int row, boolean b){
        for(int c = 0; c < n2; ++c){
            paintCell(row,c,b);
        }
    }
    
    public void paintColumn(int col, boolean b){
        for(int r = 0; r < n2; ++r){
            paintCell(r,col,b);
        }
    }
    
    public void paintRegion(int row, int col, boolean b){
        int initialrow = row - (row % this.n);
        int finalrow = initialrow + this.n;
        int initialcol = col - (col % this.n);
        int finalcol = initialcol + this.n;
        for (int r = initialrow; r < finalrow; ++r) {
            for (int c = initialcol; c < finalcol; ++c) {
                paintCell(r,c,b);
            }
        }
    }

    //-------------- listeners -------------------
    private void cellFocusGained(FocusEvent evt) {
        JTextField cell = (JTextField) evt.getSource();
        if (cell.isEditable()) cell.setBackground(new Color(220, 220, 220));
    }

    private void cellFocusLost(FocusEvent evt){
        JTextField cell = (JTextField) evt.getSource();
        if (cell.isEditable()) cell.setBackground(Color.white);
    }

    private void cellMouseClicked(MouseEvent evt) {
        JTextField field = (JTextField) evt.getSource();
        field.setCaretPosition(field.getDocument().getLength());
    }
    

    //-------------- metodes privats ----------------
    private void initializeComponents() {
        initializeInfoPanel();
        initializeBoardPanel();
        initializeErrorMessages();
        initializeButtonsPanel();
        initializeSudokuPanel();
    }

    private void initializeSudokuPanel() {
        sudokuPanel.setLayout(new BoxLayout(sudokuPanel, BoxLayout.PAGE_AXIS));
        sudokuPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
        sudokuPanel.setBackground(new Color (0,0,0,0));
        sudokuPanel.add(infoPanel);
        sudokuPanel.add(boardPanel);
        sudokuPanel.add(errorMessage);
        sudokuPanel.add(buttonsPanel);
    }

    private void initializeBoardPanel() {
        int boardSize = calculateBoardSize();
        boardPanel.setLayout(null);
        boardPanel.setMinimumSize(new Dimension(boardSize, boardSize));
        boardPanel.setMaximumSize(boardPanel.getMinimumSize());
        boardPanel.setPreferredSize(boardPanel.getMinimumSize());
        boardPanel.setBackground(Color.gray);
        createBoard();
    }

    private void initializeErrorMessages() {
        errorMessage.setMinimumSize(new Dimension(200, 20));
//        errorMessage.setPreferredSize(errorMessage.getMinimumSize());
        errorMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorMessage.setHorizontalAlignment(JLabel.CENTER);
        errorMessage.setForeground(new Color(255, 0, 51));
        errorMessage.setBackground(new Color (0,0,0,0));
    }

    public void initializeButtonsPanel() {
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.setBackground(new Color(0,0,0,0));
    }

    private void initializeInfoPanel() {
        infoPanel.setMaximumSize(new Dimension(200, 50));
        infoPanel.setBackground(new Color(0,0,0,0));
    }

    /**
     * Calcula el tamany necessari per generar un tauler
     *
     * @return mida necessaria per generar un tauler
     */
    public int calculateBoardSize() {
        return 5 + ((CELLSIZE) * n2) + (n * 5);
    }

    /**
     * Calcula el tamany d'una cela en funció d'n
     *
     * @return
     */
    private int calculateCellSize() {
        int ret = ((9 - n * 2) * 10);
        if (ret < 0) {
            ret = 0;
        }
        int aux = 0;
        if (n <= 3) aux = 33;
        else if( n == 4)aux=25;
        else {
            aux = 25 - (n-4)*5;
        }
        return ret + aux;
    }

    private DocumentFilter createDocumentFilter() {
        DocumentFilter df = new DocumentFilter() {
            // comprova que el text de la cel·la sigui valid
            private boolean isValid(String testText) {
                if (testText.length() > (1 + n / 4)) {
                    return false;
                }
                if (testText.isEmpty()) {
                    return true;
                }
                int intValue;
                try {
                    intValue = Integer.parseInt(testText.trim());
                } catch (NumberFormatException e) {
                    return false;
                }
                return !(intValue < 1 || intValue > n2);
            }

            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                StringBuilder sb = new StringBuilder();
                sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
                sb.insert(offset, text);
                if (isValid(sb.toString())) {
                    super.insertString(fb, offset, text, attr);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                StringBuilder sb = new StringBuilder();
                sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
                int end = offset + length;
                sb.replace(offset, end, text);
                if (isValid(sb.toString())) {
                    super.replace(fb, offset, length, text, attrs);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        };
        return df;
    }

    /**
     * Crea un tauler en funció d'n i CELLSIZE
     */
    private void createBoard() {
        int x = 5, y = 5;
        this.board = new JTextField[n2][n2];
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n2; ++j) {
                board[i][j] = new JTextField();
                PlainDocument doc = (PlainDocument) board[i][j].getDocument();
                DocumentFilter df = this.createDocumentFilter();
                doc.setDocumentFilter(df);
                doc.setDocumentFilter(new DocumentFilter() {
                    // comprova que el text de la cel·la sigui valid
                    private boolean isValid(String testText) {
                        if (testText.length() > (1 + n / 4)) {
                            return false;
                        }
                        if (testText.isEmpty()) {
                            return true;
                        }
                        int intValue;
                        try {
                            intValue = Integer.parseInt(testText.trim());
                        } catch (NumberFormatException e) {
                            return false;
                        }
                        return !(intValue < 1 || intValue > n2);
                    }

                    @Override
                    public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                        StringBuilder sb = new StringBuilder();
                        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
                        sb.insert(offset, text);
                        if (isValid(sb.toString())) {
                            super.insertString(fb, offset, text, attr);
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }

                    @Override
                    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                        StringBuilder sb = new StringBuilder();
                        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
                        int end = offset + length;
                        sb.replace(offset, end, text);
                        if (isValid(sb.toString())) {
                            super.replace(fb, offset, length, text, attrs);
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                });
                board[i][j].setBounds(x, y, CELLSIZE, CELLSIZE);
                board[i][j].getAccessibleContext().setAccessibleName(i + "," + j);
                boardPanel.add(board[i][j]);
                initCell(board[i][j]);
                if (j != 0 && (j + 1) % n == 0) {
                    x += (CELLSIZE + 5);
                } else {
                    x += (CELLSIZE);
                }
            }
            if (i != 0 && (i + 1) % n == 0) {
                y += (CELLSIZE + 5);
            } else {
                y += (CELLSIZE);
            }
            x = 5;
        }
    }

    /**
     * Inicialitza una cela
     *
     * @param cell
     */
    private void initCell(JTextField cell) {
        // --- Modificadors ---
        cell.setFont(new Font("Lucida Grande", 0, ((6 - n) * 10) + 5));//TODO
        cell.setHorizontalAlignment(JTextField.CENTER);
        cell.setCaretColor(new Color(220, 220, 220));
        cell.setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102)));

        // --- Listeners ---
        cell.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                cellFocusGained(evt);
            }

            @Override
            public void focusLost(FocusEvent evt) {
                cellFocusLost(evt);
            }
        });
        cell.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt){
                cellKeyPressed(evt);
            }
        });
        cell.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                cellMouseClicked(evt);
            }
        });
        cell.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                return correctInput((JTextField)input);
            }
        });
    }
   
    private boolean correctInput(JTextField cell){
        String[] position = cell.getAccessibleContext().getAccessibleName().split(",");
        int row = Integer.parseInt(position[0]);
        int col = Integer.parseInt(position[1]);
        try{
            paintRow(row, false);
            paintColumn(col, false);
            paintRegion(row, col, false);
            setErrorMessage("");
            setInput(cell);
        }
        catch (RowLocationException ex) {
            paintRow(row, true);
            setErrorMessage(ex.getMessage());
            return false;
        } 
        catch (ColumnLocationException ex) {
            paintColumn(col, true);
            setErrorMessage(ex.getMessage());
            return false;
        } 
        catch (RegionLocationException ex) {
            paintRegion(row, col, true);
            setErrorMessage(ex.getMessage());
            return false;
        }
        catch (BadNumberPositionException e) {
            setErrorMessage(e.getMessage());
            return false;
        }
        return true;
    }
    
    protected abstract void setInput(JTextField cell) throws BadNumberPositionException;

    private  void cellKeyPressed(KeyEvent evt){
        JTextField field = (JTextField) evt.getSource();
        try {
            Thread.sleep(150);
        } catch (InterruptedException ex) {
            System.err.println("Error en sleep");
        }
        field.getInputVerifier().verify(field);
    }

}
