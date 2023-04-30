import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JPanel {
    private static final Color BG_COLOR = new Color(0xbbada0);
    private static final String FONT_NAME = "Arial";
    private static final int TILE_SIZE = 96;
    private static final int TILE_MARGIN = 10;

    private Controls controls;
    boolean isGameWon = false;
    boolean isGameLost = false;

    public View(Controls controls){
        setFocusable(true);
        this.controls=controls;
        addKeyListener(controls);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(BG_COLOR);
        g.fillRect(0,0,this.getSize().width, this.getSize().height); // заполняет прямоугольник

        for(int x = 0; x<4; x++) {
            for(int y = 0; y<4; y++) {
                drawTile(g, controls.getGameTiles()[y][x], x, y);
            }
        }

        g.drawString("Score: " + controls.getScore(), 200, 465); // draws "Score" string

        // if you lose/win window will show up with certain message
        if(isGameWon){
            JOptionPane.showMessageDialog(this, "You have won!"); // if you win then
        } else if(isGameLost){
            JOptionPane.showMessageDialog(this, "You have lost :("); // if you lose then
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
