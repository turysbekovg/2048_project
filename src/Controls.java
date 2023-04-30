import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controls extends KeyAdapter {
    private Model model;
    private View view;

    private static final int WINNING_TILE = 2048;

    public Controls(Model model){ // constructor
        this.model = model;
        view = new View(this);
    }

    Tile[][] getGameTiles(){ // to get Tiles
        return model.getGameTiles();
    }

    int getScore(){ // to get score of the user
        return model.score;
    }

    void resetGame(){  // resets the game
        model.score = 0;
        model.maxTile = 2;
        view.isGameLost = false;
        view.isGameWon = false;
        model.resetGameTiles();
    }

    @Override
    public void keyPressed(KeyEvent e){  // movement
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            resetGame();
        }

        if(model.canMove() == false){
            view.isGameLost = true;
        }

        if(view.isGameLost == false && view.isGameWon == false){
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                model.left();
            }

            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                model.right();
            }

            if(e.getKeyCode() == KeyEvent.VK_UP){
                model.up();
            }

            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                model.down();
            }

            if(e.getKeyCode() == KeyEvent.VK_Z){
                model.rollBack();
            }

            if(e.getKeyCode() == KeyEvent.VK_A){
                model.autoMove();
            }

            if(e.getKeyCode() == KeyEvent.VK_R){
                model.randomMove();
            }
        }

        if(model.maxTile == WINNING_TILE){
            view.isGameWon = true;
        }

        view.repaint();
    }

    public View getView() {
        return view;
    }
}
