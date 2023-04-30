import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static JFrame createStartingPanel(){
        JFrame startingPanel = new JFrame();

        startingPanel.setTitle("2048"); // makes title of the frame
        startingPanel.setSize(450, 650);  // initially 450X500
        startingPanel.setVisible(true); // making this frame visible
        startingPanel.setResizable(false);
        startingPanel.setLocationRelativeTo(null); // sets the frame to the centre of the screen
        startingPanel.getContentPane().setLayout(null);
        startingPanel.getContentPane().setBackground(new Color(0xbbada0));
        startingPanel.setDefaultCloseOperation(startingPanel.EXIT_ON_CLOSE); // closes

        return startingPanel;
    }

    public static JButton createButton(JFrame startingPanel, String title, int yCoordinate){
        JButton button = new JButton();
        button.setLocation(130, yCoordinate);
        button.setText(title);
        button.setSize(200, 50);
        startingPanel.add(button);
        return button;
    }

    public static void createGamePanel(JFrame startingPanel, Controls controls){ // creating game panel
        JFrame game = new JFrame();

        game.setTitle("2048"); // makes title of the frame
        game.setSize(450, 650);  // initially 450X500
        game.setResizable(false); // size is not resizable
        game.setLocationRelativeTo(null); // sets the frame to the centre of the screen
        game.add(controls.getView()); // adds the View (colors etc)
        game.setVisible(true); // making this frame visible

    }

    public static void main (String[] args) {

        JFrame startingPanel = createStartingPanel();

        JButton easyPlay = createButton(startingPanel, "Easy-Level", 60);
        JButton mediumPlay = createButton(startingPanel, "Medium-Level", 160);
        JButton hardPlay = createButton(startingPanel, "Hard-Level", 260);
        JButton botPlay = createButton(startingPanel, "Bot-Play Level", 360);
        JButton instructionsButton = createButton(startingPanel, "Instructions", 460);

        PermanentData permanentData = new PermanentData();


        // EASY LEVEL
        ActionListener listener1 = new ActionListener() { // метод вызывается когда кнопка нажата
            @Override
            public void actionPerformed(ActionEvent e) {

                Model model = new Model(6); // creating new Desk
                Controls controls = new Controls(model, permanentData); // creating new controls

                createGamePanel(startingPanel, controls); // creating frame

            }
        };
        easyPlay.addActionListener(listener1);

        // MEDIUM LEVEL
        ActionListener listener2 = new ActionListener() { // метод вызывается когда кнопка нажата
            @Override
            public void actionPerformed(ActionEvent e) {

                Model model = new Model(4); // creating new Desk
                Controls controls = new Controls(model, permanentData); // creating new controls

                createGamePanel(startingPanel, controls); // creating frame
            }
        };
        mediumPlay.addActionListener(listener2);


        // HARD LEVEL
        ActionListener listener3 = new ActionListener() { // метод вызывается когда кнопка нажата
            @Override
            public void actionPerformed(ActionEvent e) {

                Model model = new Model(3); // creating new Desk
                Controls controls = new Controls(model, permanentData); // creating new controls

                createGamePanel(startingPanel, controls); // creating frame

            }
        };
        hardPlay.addActionListener(listener3);



        // BOT-PLAYS LEVEL
        ActionListener listener4 = new ActionListener() { // метод вызывается когда кнопка нажата
            @Override
            public void actionPerformed(ActionEvent e) {

                Model model = new Model(4); // creating new Desk
                Controls controls = new Controls(model, permanentData); // creating new controls

                createGamePanel(startingPanel, controls); // creating frame

                while(model.canMove()){
                    model.botMove();
                }
            }
        };
        botPlay.addActionListener(listener4);

        ActionListener listener5 = new ActionListener() { // метод вызывается когда кнопка нажата
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea area = new JTextArea("1) 'R' -> Random move." + "\n\n2) 'A' -> Smart move." + "\n\n3) 'Z' -> " +
                        "undo move." + "\n\n4) You can press 'BOT-PLAYS LEVEL' button to see the resulting board." +
                        "\n\n5) The are 4 variants of how you can play the 2048 game: \n\nThe 1st one - easy mode, the 2nd - medium," +
                        " the 3rd - hard, and the last one - auto play." + "\n\n6) The game was made on JAVA language, and " +
                        "using JAVA Gui." + "\n\n Unfortunately, i could not realize the timer, however i included features like " +
                        "making " +
                        "\n random movement, smart move, and undo move." + "\n\n7) When playing bot level, when the game" +
                        "is finished you need to press either 'Enter' or " +
                        "\nany arrow button to score to be saved." + "\n\n Hope you will enjoy playing the game:)");
                Font font = new Font("Arial", Font.PLAIN, 18);
                area.setFont(font);
                JFrame instructionsFrame = new JFrame();

                instructionsFrame.setTitle("Instructions");
                instructionsFrame.setSize(730, 550);

                instructionsFrame.add(area);
                instructionsFrame.setVisible(true);
                instructionsFrame.setResizable(false); // size is not resizable
                instructionsFrame.setLocationRelativeTo(null); // sets the frame to the centre of the screen
            }
        };
        instructionsButton.addActionListener(listener5);

    }
}
