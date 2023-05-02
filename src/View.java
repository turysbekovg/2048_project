import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private static final Color BG_COLOR = new Color(0xbbada0);
    private static final String FONT_NAME = "Arial";
    private static int TILE_SIZE = 0; // tile size
    private static int TILE_MARGIN = 0; // distance between tiles
    private PermanentData permanentData;
    private Controls controls;
    private Model model;
    boolean isGameWon = false;
    boolean isGameLost = false;

    public View(Controls controls, Model model, PermanentData permanentData){
        setFocusable(true);
        this.model = model;
        this.controls=controls;
        addKeyListener(controls);
        this.permanentData = permanentData;
    }


    public void setTileSizes(int x){ // 6X6 - > 60X10 ; 4x4 -> 85x20; 3x3 -> 114x25
        if(x == 4) {
            TILE_SIZE = 85;
            TILE_MARGIN = 20;
        } else if(x == 6) {
            TILE_SIZE = 60;
            TILE_MARGIN = 11;
        } else {
            TILE_SIZE = 114;
            TILE_MARGIN = 25;
        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(BG_COLOR);
        g.fillRect(0,0,this.getSize().width, this.getSize().height); // заполняет прямоугольник

        for(int x = 0; x<model.getFieldWidth(); x++) {
            for(int y = 0; y<model.getFieldWidth(); y++) {
                drawTile(g, controls.getGameTiles()[y][x], x, y);
            }
        }

        g.drawString("Score: " + model.getScore(), 150, 500); // draws "Score" string
        g.drawString("MaxScore: " + permanentData.getTempMaxScore(), 110, 580); // draws "Score" string

        // if you lose/win window will show up with certain message
        if(isGameWon){
            if(permanentData.getTempMaxScore()<model.score){
                permanentData.saveTempMaxScore(model.score);
            }

            JOptionPane.showMessageDialog(this, "You have won! " + " Your score is: "+ model.score); // if you win then
            JOptionPane.showMessageDialog(this, "Close the window and try again :)");
        } else if(isGameLost){
            if(permanentData.getTempMaxScore()<model.score){
                permanentData.saveTempMaxScore(model.score);
            }

            JOptionPane.showMessageDialog(this, "You have lost :( "  + " Your score is: "+ model.score); // if you lose then
            JOptionPane.showMessageDialog(this, "Close the window and try again :)");
        }
    }

    private void drawTile(Graphics g2, Tile tile, int x, int y){
        Graphics2D g = ((Graphics2D) g2);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int value = tile.value;
        int xOffset = offsetCoors(x); // смещение по х для round corner
        int yOffset = offsetCoors(y); // смещение по у для round corner

        g.setColor(tile.getTileColor()); // setting tile's color with certain value's color
        //  fillRoundRect позволяет нарисовать заполненный прямоугольник с закругленными углами
        g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 8, 8);
        g.setColor(tile.getFontColor()); // getting font color

        final int size = value < 100?36: value <1000 ? 32:24; // size of a number
        final Font font = new Font(FONT_NAME, Font.BOLD, size);
        g.setFont(font); // setting font

        String s = String.valueOf(value);
        final FontMetrics fm = getFontMetrics(font); // width of a font

        final int w = fm.stringWidth(s);
        final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];

        if(value!=0){
            g.drawString(s, xOffset+(TILE_SIZE-w) /2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2-2);
        }
    }

    private static int offsetCoors(int arg){
        return arg*(TILE_MARGIN+TILE_SIZE)+TILE_MARGIN;
    }


}
