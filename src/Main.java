import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main (String[] args){

        JFrame startingPanel = new JFrame();
        startingPanel.setVisible(true); // making this frame visible
        startingPanel.setResizable(false);
        startingPanel.setLocationRelativeTo(null); // sets the frame to the centre of the screen
        startingPanel.setSize(450, 700);  // initially 450X500
        startingPanel.getContentPane().setLayout(null);
        startingPanel.setDefaultCloseOperation(startingPanel.EXIT_ON_CLOSE); // closes

        JButton easyPlay = new JButton();
        JButton mediumPlay = new JButton();
        JButton hardPlay = new JButton();
        JButton botPlay = new JButton();

        easyPlay.setLocation(170, 150);
        mediumPlay.setLocation(170, 250);
        hardPlay.setLocation(170, 350);
        botPlay.setLocation(170, 450);

        easyPlay.setText("Easy level");
        mediumPlay.setText("Medium level");
        hardPlay.setText("Hard level");
        botPlay.setText("Bot auto-play");

        easyPlay.setSize(100, 50);
        mediumPlay.setSize(100, 50);
        hardPlay.setSize(100, 50);
        botPlay.setSize(100, 50);

        ActionListener listener1 = new ActionListener() { // метод вызывается когда кнопка нажата
            @Override
            public void actionPerformed(ActionEvent e) {

                Model model = new Model(8); // creating new Desk
                Controls controls = new Controls(model); // creating new controls

                JFrame game = new JFrame(); // creating frame

                game.setTitle("2048"); // makes title of the frame
                game.setSize(450, 700);  // initially 450X500
                game.setResizable(false); // size is not resizable
                game.setLocationRelativeTo(null); // sets the frame to the centre of the screen
                game.add(controls.getView()); // adds the View (colors etc)
                game.setVisible(true); // making this frame visible


                game.setDefaultCloseOperation(game.EXIT_ON_CLOSE); // closes
            }
        };
        easyPlay.addActionListener(listener1);

        ActionListener listener2 = new ActionListener() { // метод вызывается когда кнопка нажата
            @Override
            public void actionPerformed(ActionEvent e) {

                Model model = new Model(4); // creating new Desk
                Controls controls = new Controls(model); // creating new controls

                JFrame game = new JFrame(); // creating frame

                game.setTitle("2048"); // makes title of the frame
                game.setSize(450, 700);  // initially 450X500
                game.setResizable(false); // size is not resizable
                game.setLocationRelativeTo(null); // sets the frame to the centre of the screen
                game.add(controls.getView()); // adds the View (colors etc)
                game.setVisible(true); // making this frame visible


                game.setDefaultCloseOperation(game.EXIT_ON_CLOSE); // closes
            }
        };
        mediumPlay.addActionListener(listener2);

        ActionListener listener3 = new ActionListener() { // метод вызывается когда кнопка нажата
            @Override
            public void actionPerformed(ActionEvent e) {

                Model model = new Model(3); // creating new Desk
                Controls controls = new Controls(model); // creating new controls

                JFrame game = new JFrame(); // creating frame

                game.setTitle("2048"); // makes title of the frame
                game.setSize(450, 700);  // initially 450X500
                game.setResizable(false); // size is not resizable
                game.setLocationRelativeTo(null); // sets the frame to the centre of the screen
                game.add(controls.getView()); // adds the View (colors etc)
                game.setVisible(true); // making this frame visible


                game.setDefaultCloseOperation(game.EXIT_ON_CLOSE); // closes
            }
        };
        hardPlay.addActionListener(listener3);

        ActionListener listener4 = new ActionListener() { // метод вызывается когда кнопка нажата
            @Override
            public void actionPerformed(ActionEvent e) {

                Model model = new Model(4); // creating new Desk
                Controls controls = new Controls(model); // creating new controls

                JFrame game = new JFrame(); // creating frame

                game.setTitle("2048"); // makes title of the frame
                game.setSize(450, 700);  // initially 450X500
                game.setResizable(false); // size is not resizable
                game.setLocationRelativeTo(null); // sets the frame to the centre of the screen
                game.add(controls.getView()); // adds the View (colors etc)
                game.setVisible(true); // making this frame visible


                game.setDefaultCloseOperation(game.EXIT_ON_CLOSE); // closes
            }
        };
        botPlay.addActionListener(listener4);

        startingPanel.add(easyPlay);
        startingPanel.add(mediumPlay);
        startingPanel.add(hardPlay);
        startingPanel.add(botPlay);





//
//        Model model = new Model(); // creating new Desk
//        Controls controls = new Controls(model); // creating new controls
//
//        JFrame game = new JFrame(); // creating frame
//
//        game.setTitle("2048"); // makes title of the frame
//        game.setSize(450, 700);  // initially 450X500
//        game.setResizable(false); // size is not resizable
//        game.setLocationRelativeTo(null); // sets the frame to the centre of the screen
//        game.add(controls.getView()); // adds the View (colors etc)
////        game.getContentPane().setLayout(null);
//        game.setVisible(true); // making this frame visible
//
//
//        game.setDefaultCloseOperation(game.EXIT_ON_CLOSE); // closes
    }
}




//        JButton button = new JButton();
//        button.setText("Bot play");
//        button.setSize(70,70);
//        button.setLocation(150, 485);
//        ActionListener listener1 = new ActionListener() { // метод вызывается когда кнопка нажата
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                model.botMove();
//            }
//        };
//        button.addActionListener(listener1);
//        game.add(button);



//        JButton myButton1 = new JButton("Start game");
//        myButton1.setLayout(new GridBagLayout());
//        game.add(myButton1);
//        JButton myButton2 = new JButton("Bot game");
//        myButton2.setLayout(new GridBagLayout());
//        game.add(myButton2);
