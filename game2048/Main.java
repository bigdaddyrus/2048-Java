// This file contains a SUGGESTION for the structure of your program.  You
// may change any of it, or add additional files to this directory (package),
// as long as you conform to the project specification.  Do not, however,
// modify the contents of the 'gui' subpackage.

// We have indicated parts of the file that you might especially want to
// fill in with "// FIXME"  or "// REPLACE..." comments.  But again,
// you can change just about anything.

// Comments that start with "//" are intended to be removed from your
// solutions.

package game2048;

import ucb.util.CommandArgs;

import game2048.gui.Game;
import static game2048.Main.Side.*;

/** The main class for the 2048 game.
 *  @author
 */
public class Main {

    /** Size of the board: number of rows and of columns. */
    static final int SIZE = 4;
    /** Number of squares on the board. */
    static final int SQUARES = SIZE * SIZE;

    /** Symbolic names for the four sides of a board. */
    static enum Side { NORTH, EAST, SOUTH, WEST };

    /** The main program.  ARGS may contain the options --seed=NUM,
     *  (random seed); --log (record moves and random tiles
     *  selected.); --testing (take random tiles and moves from
     *  standard input); and --no-display. */
    public static void main(String... args) {
        CommandArgs options =
            new CommandArgs("--seed=(\\d+) --log --testing --no-display",
                            args);
        if (!options.ok()) {
            System.err.println("Usage: java game2048.Main [ --seed=NUM ] "
                               + "[ --log ] [ --testing ] [ --no-display ]");
            System.exit(1);
        }

        Main game = new Main(options);

        while (game.play()) {
            /* No action */
        }
        System.exit(0);
    }

    /** A new Main object using OPTIONS as options (as for main). */
    Main(CommandArgs options) {
        boolean log = options.contains("--log"),
            display = !options.contains("--no-display");
        long seed = !options.contains("--seed") ? 0 : options.getLong("--seed");
        _testing = options.contains("--testing");
        _game = new Game("2048", SIZE, seed, log, display, _testing);
    }

    /** Reset the score for the current game to 0 and clear the board. */
    void clear() {
        _score = 0;
        _count = 0;
        _game.clear();
        _game.setScore(_score, _maxScore);
        for (int r = 0; r < SIZE; r += 1) {
            for (int c = 0; c < SIZE; c += 1) {
                _board[r][c] = 0;
            }
        }
    }

    /** Play one game of 2048, updating the maximum score. Return true
     *  iff play should continue with another game, or false to exit. */
    boolean play() {
        // FIXME?
        
        setRandomPiece();
        while (true) {
            // FIXME?

            //setRandomPiece();
            if (gameOver()) {
                // FIXME?
                //_game.endGame();
                //_game.setScore(_score, _maxScore);

            }

        GetMove:
            while (true) {
                setRandomPiece();
                String key = _game.readKey();
                if (key == "↑"){
                    key = "Up";
                }
                if (key == "→"){
                    key = "Right";
                }
                if (key == "←"){
                    key = "Left";
                }
                if (key == "↓"){
                    key = "Down";
                }
                System.out.println(key);
                switch (key) {
                case "Up": case "Down": case "Left": case "Right":


                    
                    tiltBoard(keyToSide(key));
                    _game.displayMoves();
                    System.out.println("at (" + 0 + ", " + 1 + ") value is: " + _board[0][1]);

                    if (!gameOver() && tiltBoard(keyToSide(key))) {
                        break GetMove;
                    }
                    break;
                // FIXME?
                    
                                
                            
                    

                case "Quit":
                    return false;
                default:
                    break;

                }
            }
            // FIXME?

        }
    }

    /** Return true iff the current game is over (no more moves
     *  possible). */
    boolean gameOver() {
        // FIXME?
        /*if (_count == SQUARES){
        return true; 
        } 
        */
        return true;

    }

    /** Add a tile to a random, empty position, choosing a value (2 or
     *  4) at random.  Has no effect if the board is currently full. */
    void setRandomPiece() {
        if (_count == SQUARES) {
            return;
        }
        // FIXME?
        
        int [] randomSpec = _game.getRandomTile();
        
        int val = randomSpec[0];
        int row = randomSpec[1];
        int col = randomSpec[2];
        if (_board[row][col] != 0){
            setRandomPiece();
        } else {
        _game.addTile(val, row, col);
        _board[row][col] = val;
        }

    }

    /** Perform the result of tilting the board toward SIDE.
     *  Returns true iff the tilt changes the board. **/
    boolean tiltBoard(Side side) {
        /* As a suggestion (see the project text), you might try copying
         * the board to a local array, turning it so that edge SIDE faces
         * north.  That way, you can re-use the same logic for all
         * directions.  (As usual, you don't have to). */
        int[][] board = new int[SIZE][SIZE];
        // FIXME?


        for (int r = 0; r < SIZE; r += 1) {
            for (int c = 0; c < SIZE; c += 1) {
                board[r][c] =
                    _board[tiltRow(side, r, c)][tiltCol(side, r, c)];
                // FIXME?

            }
        }

        // FIXME?
        // check
         for (int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(board[i][j] != 0){
                    //System.out.println("in move tile at (" + i + ", " + j + ") value is: " + board[i][j]);
                    int n = 0;
                               
                    while (board[n][j] != 0 && n < i){
                            
                            n++;

                            }                       
                            
                    
                    if (n==i){
                        continue;
                    }

                    

                    int tRow = tiltRow(side, i, j);
                    int tCol = tiltCol(side, i, j);
                    int nRow = tiltRow(side, n, j);
                    int nCol = tiltCol(side, n, j);

                     /*         System.out.println(i);
                                System.out.println(j);
                                System.out.println(tRow);
                                System.out.println(tCol);
                                System.out.println(nRow);
                                System.out.println(nCol);
                                */
                    //System.out.println(n);
                        System.out.println("movetile?");
                        _game.moveTile(board[i][j], 
                            tRow, tCol, nRow, nCol);
                        
                        board[n][j] = board[i][j];
                        board[i][j] = 0;
                        }
            }
        }

        for (int r = 0; r < SIZE; r += 1) {
            for (int c = 0; c < SIZE; c += 1) {
                _board[tiltRow(side, r, c)][tiltCol(side, r, c)]
                    = board[r][c];
            }
        }


        // FIXME?
        
        int counter = 0;
        //System.out.println(counter);
        for (int i = 0; i < SIZE; i += 2){
            for (int j = 0; j < SIZE; j++){
                int tRow = tiltRow(side, i, j);
                int tCol = tiltCol(side, i, j);
                int nRow = tiltRow(side, i+1, j);
                int nCol = tiltCol(side, i+1, j);
                     /*           System.out.println(tRow);
                                System.out.println(tCol);
                                System.out.println(nRow);
                                System.out.println(nCol);
                                System.out.println(_board[nRow][nCol]);
                                */
                                
                if (board[i][j] != 0 && board[i][j] % 2 == 0 && board[i][j] == board[i+1][j]){

                    System.out.println("at (" + nRow + ", " + nCol + ") value is: " + board[nRow][nCol]);
                    System.out.println("at (" + i + ", " + j + ") value is: " + board[i][j]);
                    _game.mergeTile(board[i][j], 
                        2 * board[i][j], nRow, nCol, tRow, tCol);

                    board[i][j] = 2 * board[i][j];
                    board[i][j] ++;
                    board[i+1][j] = 0;
                    counter ++;
                    //System.out.println("at (" + nRow + ", " + nCol + ") value is: " + board[nRow][nCol]);
                    //System.out.println("at (" + i + ", " + j + ") value is: " + board[i][j]);
                    //System.out.println("at (" + i++ + ", " + j + ") value is: " + board[i+1][j]);
                }
            }
        }

        

        for (int r = 0; r < SIZE; r += 1) {
            for (int c = 0; c < SIZE; c += 1) {
                _board[tiltRow(side, r, c)][tiltCol(side, r, c)]
                    = board[r][c];
            }
        }


        
        if (counter == 0){
            //System.out.println(counter);
            for (int r = 0; r < SIZE; r += 1) {
                for (int c = 0; c < SIZE; c += 1) {
                    if (board[r][c] % 2 != 0){
                        board[r][c] -= 1;
                        _board[tiltRow(side, r, c)][tiltCol(side, r, c)]
                        = board[r][c];
                        
                } 
            }
        }
            //System.out.println("finally at (" + 0 + ", " + 1 + ") value is: " + board[0][1]);
            return true;
        } else {
            tiltBoard(side);
        }
        
        return true;
    }


    /** Return the row number on a playing board that corresponds to row R
     *  and column C of a board turned so that row 0 is in direction SIDE (as
     *  specified by the definitions of NORTH, EAST, etc.).  So, if SIDE
     *  is NORTH, then tiltRow simply returns R (since in that case, the
     *  board is not turned).  If SIDE is WEST, then column 0 of the tilted
     *  board corresponds to row SIZE - 1 of the untilted board, and
     *  tiltRow returns SIZE - 1 - C. */
    int tiltRow(Side side, int r, int c) {
        switch (side) {
        case NORTH:
            return r;
        case EAST:
            return c;
        case SOUTH:
            return SIZE - 1 - r;
        case WEST:
            return SIZE - 1 - c;
        default:
            throw new IllegalArgumentException("Unknown direction");
        }
    }

    /** Return the column number on a playing board that corresponds to row
     *  R and column C of a board turned so that row 0 is in direction SIDE
     *  (as specified by the definitions of NORTH, EAST, etc.). So, if SIDE
     *  is NORTH, then tiltCol simply returns C (since in that case, the
     *  board is not turned).  If SIDE is WEST, then row 0 of the tilted
     *  board corresponds to column 0 of the untilted board, and tiltCol
     *  returns R. */
    int tiltCol(Side side, int r, int c) {
        switch (side) {
        case NORTH:
            return c;
        case EAST:
            return SIZE - 1 - r;
        case SOUTH:
            return SIZE - 1 - c;
        case WEST:
            return r;
        default:
            throw new IllegalArgumentException("Unknown direction");
        }
    }

    /** Return the side indicated by KEY ("Up", "Down", "Left",
     *  or "Right"). */
    Side keyToSide(String key) {
        switch (key) {
        case "Up":
            return NORTH;
        case "Down":
            return SOUTH;
        case "Left":
            return WEST;
        case "Right":
            return EAST;
        default:
            throw new IllegalArgumentException("unknown key designation");
        }
    }

    /** Represents the board: _board[r][c] is the tile value at row R,
     *  column C, or 0 if there is no tile there. */
    private final int[][] _board = new int[SIZE][SIZE];

    /** True iff --testing option selected. */
    private boolean _testing;
    /** THe current input source and output sink. */
    private Game _game;
    /** The score of the current game, and the maximum final score
     *  over all games in this session. */
    private int _score, _maxScore;
    /** Number of tiles on the board. */
    private int _count;
}
