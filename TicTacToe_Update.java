import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    //Creating array for the button options
    JButton[] buttons = new JButton[9];
    boolean player1_turn;

    boolean singlePlayer;

    String cpuChar;

    String playerChar;

    boolean xWin = false;
    boolean oWin = false;

    int difficulty;

    int cycle = 0;

    int roundCount, xWinNum, oWinNum, drawNum;

    JButton backButton;

    JButton playAgain;

    JButton leaderboard;

    JTextArea leaderboardScore;


    TicTacToe() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(0, 120, 120));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 800);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setForeground(Color.decode("#0E4749"));
        backButton.setBounds(300, 450, 200, 60);
        backButton.setVisible(false);
        backButton.setText("Back");
        frame.add(backButton);

        if(roundCount%2 == 0)
        {player1_turn = true;}
        else
        {player1_turn = false;}

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch back to the "MainMenu" panel
                System.out.println(xWinNum + " " + oWinNum + " " + drawNum);
                frame.dispose();

            }
        });

        playAgain = new JButton("Play Again");
        playAgain.setFont(new Font("Arial", Font.PLAIN, 18));
        playAgain.setForeground(Color.decode("#0E4749"));
        playAgain.setBounds(300, 350, 200, 60);
        playAgain.setVisible(false);
        frame.add(playAgain);

        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch back to the "MainMenu" panel
                TicTacToe ticTacToe = new TicTacToe();
                ticTacToe.singlePlayer = singlePlayer;
                ticTacToe.roundCount = roundCount;
                ticTacToe.xWinNum = xWinNum;
                ticTacToe.oWinNum = oWinNum;
                ticTacToe.drawNum = drawNum;
                System.out.println(xWinNum + " " + oWinNum + " " + drawNum);
                ticTacToe.cycle = 0;
                ticTacToe.difficulty = difficulty;
                frame.dispose();
                if((ticTacToe.roundCount%2 != 0) && (ticTacToe.cycle==0) && singlePlayer)
                {
                    if(difficulty == 0){ticTacToe.cpuMoveNormal();}
                    if(difficulty == 1){ticTacToe.cpuMoveNormal();}
                }
            }
        });

        leaderboardScore = new JTextArea();
        leaderboardScore.setEditable(false);
        leaderboardScore.setForeground(Color.decode("#0E4749"));
        leaderboardScore.setBackground(Color.WHITE);
        leaderboardScore.setFont(new Font("Arial", Font.PLAIN, 18));
        leaderboardScore.setBounds(330, 570, 140, 130);
        leaderboardScore.setVisible(false);
        frame.add(leaderboardScore);

        leaderboard = new JButton();
        leaderboard.setForeground(Color.decode("#0E4749"));
        leaderboard.setBackground(Color.WHITE);
        leaderboard.setFont(new Font("Arial", Font.PLAIN, 18));
        leaderboard.setBounds(300, 550, 200, 160);
        leaderboard.setVisible(false);
        frame.add(leaderboard);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Ink Free", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);

        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add((button_panel));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!singlePlayer) {
            for (int i = 0; i < 9; i++) {
                if (e.getSource() == buttons[i]) {
                    if (player1_turn) {
                        if (buttons[i].getText() == "") {
                            buttons[i].setForeground(new Color(50, 100, 250));
                            if(roundCount%2 == 0)
                            {buttons[i].setText("X");}
                            else{buttons[i].setText("O");}
                            player1_turn = false;
                            if(roundCount%2 == 0)
                            {textfield.setText("O turn");}
                            else{textfield.setText("X turn");}
                            cycle++;
                            check();
                        }
                    } else {
                        if (buttons[i].getText() == "") {
                            buttons[i].setForeground(new Color(50, 100, 250));
                            if(roundCount%2 == 0)
                            {buttons[i].setText("O");}
                            else{buttons[i].setText("X");}
                            player1_turn = true;
                            if(roundCount%2 == 0)
                            {textfield.setText("X turn");}
                            else{textfield.setText("O turn");}
                            cycle++;
                            check();
                        }

                    }
                }
            }
        }
        if (singlePlayer){
            for (int j = 0; j < 9; j++) {
                if (e.getSource() == buttons[j]) {
                    boolean tryAgain = true;
                    while(tryAgain) {
                        if (buttons[j].getText() == "")
                        {
                            buttons[j].setForeground(new Color(50, 100, 250));
                            if(roundCount%2 == 0)
                            {buttons[j].setText("X");}
                            if(roundCount%2 != 0)
                            {buttons[j].setText("X");}
                            cycle++;
                            check();
                            player1_turn = false;
                            tryAgain = false;
                            if(difficulty == 0)
                            {cpuMove();}
                            if(difficulty == 1)
                            {cpuMoveNormal();}
                        } else {
                            buttons[j].setForeground(Color.decode("#3264FA"));
                            tryAgain = false;
                        }
                    }
                }
            }
        }
    }

    //Method for deciding which user gets the first turn
    public void firstTurn() {
        /*
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

         */

        if (random.nextInt(2) == 0) {
            //player1_turn = true;
            textfield.setText("X turn");
        } else {
            //player1_turn = false;
            textfield.setText("O turn");

        }

    }

    //Method for checking who has won the game
    public void check() {
        //Check X win conditions
        if (

                (buttons[0].getText() == "X") &&
                        (buttons[1].getText() == "X") &&
                        (buttons[2].getText() == "X")
        ) {
            xWins(0, 1, 2);

        }
        if (

                (buttons[3].getText() == "X") &&
                        (buttons[4].getText() == "X") &&
                        (buttons[5].getText() == "X")
        ) {
            xWins(3, 4, 5);

        }
        if (

                (buttons[6].getText() == "X") &&
                        (buttons[7].getText() == "X") &&
                        (buttons[8].getText() == "X")
        ) {
            xWins(6, 7, 8);

        }
        if (

                (buttons[0].getText() == "X") &&
                        (buttons[3].getText() == "X") &&
                        (buttons[6].getText() == "X")
        ) {
            xWins(0, 3, 6);

        }
        if (

                (buttons[1].getText() == "X") &&
                        (buttons[4].getText() == "X") &&
                        (buttons[7].getText() == "X")
        ) {
            xWins(1, 4, 7);

        }
        if (

                (buttons[2].getText() == "X") &&
                        (buttons[5].getText() == "X") &&
                        (buttons[8].getText() == "X")
        ) {
            xWins(2, 5, 8);

        }
        if (

                (buttons[0].getText() == "X") &&
                        (buttons[4].getText() == "X") &&
                        (buttons[8].getText() == "X")
        ) {
            xWins(0, 4, 8);

        }
        if (

                (buttons[2].getText() == "X") &&
                        (buttons[4].getText() == "X") &&
                        (buttons[6].getText() == "X")
        ) {
            xWins(2, 4, 6);

        }

        //Check O win conditions
        if (

                (buttons[0].getText() == "O") &&
                        (buttons[1].getText() == "O") &&
                        (buttons[2].getText() == "O")
        ) {
            oWins(0, 1, 2);

        }
        if (

                (buttons[3].getText() == "O") &&
                        (buttons[4].getText() == "O") &&
                        (buttons[5].getText() == "O")
        ) {
            oWins(3, 4, 5);

        }
        if (

                (buttons[6].getText() == "O") &&
                        (buttons[7].getText() == "O") &&
                        (buttons[8].getText() == "O")
        ) {
            oWins(6, 7, 8);

        }
        if (

                (buttons[0].getText() == "O") &&
                        (buttons[3].getText() == "O") &&
                        (buttons[6].getText() == "O")
        ) {
            oWins(0, 3, 6);

        }
        if (

                (buttons[1].getText() == "O") &&
                        (buttons[4].getText() == "O") &&
                        (buttons[7].getText() == "O")
        ) {
            oWins(1, 4, 7);

        }
        if (

                (buttons[2].getText() == "O") &&
                        (buttons[5].getText() == "O") &&
                        (buttons[8].getText() == "O")
        ) {
            oWins(2, 5, 8);

        }
        if (

                (buttons[0].getText() == "O") &&
                        (buttons[4].getText() == "O") &&
                        (buttons[8].getText() == "O")
        ) {
            oWins(0, 4, 8);

        }
        if (

                (buttons[2].getText() == "O") &&
                        (buttons[4].getText() == "O") &&
                        (buttons[6].getText() == "O")
        ) {
            oWins(2, 4, 6);

        }
        else if((cycle>=9) && !xWin && !oWin) {

            leaderboardScore.setVisible(true);
            backButton.setVisible(true);
            playAgain.setVisible(true);
            leaderboard.setVisible(true);

                for (int i = 0; i < 9; i++)
                {
                    buttons[i].setBackground(new Color(0, 0, 255));
                    buttons[i].setEnabled(false);
                }
                roundCount++;
                drawNum++;
                textfield.setText("Draw");}
                leaderboardScore.setText(("LEADERBOARD\n\n    [X WINS: "
                        + xWinNum +"]  \n    [O WINS: " + oWinNum +
                        "]  \n    [DRAWS: " + drawNum + "]" ));
        }

    //Method for x winning
    public void xWins(int a, int b, int c) {

        leaderboardScore.setVisible(true);
        backButton.setVisible(true);
        playAgain.setVisible(true);
        leaderboard.setVisible(true);

        buttons[a].setBackground(new Color(0, 0, 255));
        buttons[b].setBackground(new Color(0, 0, 255));
        buttons[c].setBackground(new Color(0, 0, 255));

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("X Wins!");
        xWinNum++;
        roundCount++;
        if(oWin == false) {xWin = true;}
        leaderboardScore.setText(("LEADERBOARD\n\n    [X WINS: "
                + xWinNum +"]  \n    [O WINS: " + oWinNum +
                "]  \n    [DRAWS: " + drawNum + "]" ));
        leaderboardScore.setVisible(true);
    }

    //method for o winning
    public void oWins(int a, int b, int c) {

        backButton.setVisible(true);
        playAgain.setVisible(true);
        leaderboard.setVisible(true);

        buttons[a].setBackground(new Color(0, 0, 255));
        buttons[b].setBackground(new Color(0, 0, 255));
        buttons[c].setBackground(new Color(0, 0, 255));

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("O Wins!");
        oWinNum++;
        roundCount++;
        if(xWin == false) {oWin = true;}
        leaderboardScore.setText(("LEADERBOARD\n\n    [X WINS: "
                + xWinNum +"]  \n    [O WINS: " + oWinNum +
                "]  \n    [DRAWS: " + drawNum + "]" ));
        leaderboardScore.setVisible(true);
    }

    public void cpuMove()
    {
        boolean condition = false;
        Random rand = new Random();
        int cpuMoveNum = rand.nextInt(9);
        if(cycle >= 9 || xWin || oWin)
        {condition = true;}

        while(!condition) {
            if (buttons[cpuMoveNum].getText() == "") {
                buttons[cpuMoveNum].setForeground(new Color(50, 100, 250));
                if(cycle % 2 != 0) {
                    buttons[cpuMoveNum].setText("O");
                    cycle++;
                } else {
                    buttons[cpuMoveNum].setText("O");
                    cycle++;
                }
                player1_turn = true;
                condition = true;
                check();
            } else {
                condition = false;
                cpuMoveNum = rand.nextInt(9);
            }
        }
    }

    public void cpuMoveNormal() {

        boolean turnTaken = false;
        Random rand = new Random();
        if((roundCount%2 != 0) && (cycle ==0))
        {
            cpuMove();
            turnTaken = true;
        }
        cpuChar = "O";
        playerChar = "X";

            //int[] takenSpaces = new int[9];
         if(cycle < 9 && !xWin && !oWin) {
            if (
                //Looking to make a line of three with position 0 and X
                    (!turnTaken && buttons[0].getText() == "" && ((buttons[1].getText() == cpuChar
                            && buttons[2].getText() == cpuChar) ||
                            (buttons[3].getText() == cpuChar && buttons[6].getText() == cpuChar) ||
                            (buttons[4].getText() == cpuChar && buttons[8].getText() == cpuChar)))) {
                buttons[0].setForeground(Color.decode("#3264FA"));
                buttons[0].setText(cpuChar);
                cycle++;
                check();
                turnTaken = true;
            }

            if (
                //Looking to make a line of three with position 1 and X
                    (!turnTaken && buttons[1].getText() == "" && ((buttons[0].getText() == cpuChar && buttons[2].getText()==cpuChar) ||
                            (buttons[4].getText() == cpuChar && buttons[7].getText()==cpuChar )))) {
                buttons[1].setForeground(Color.decode("#3264FA"));
                buttons[1].setText(cpuChar);
                cycle++;
                check();
                turnTaken = true;
            }

            if (
                //Looking to make a line of three with position 2 and X
                    (!turnTaken && buttons[2].getText() == "" && ((buttons[0].getText() == cpuChar && buttons[1].getText()==cpuChar) ||
                            (buttons[5].getText() == cpuChar && buttons[8].getText()==cpuChar) ||
                            (buttons[4].getText() == cpuChar && buttons[8].getText()==cpuChar)))) {
                buttons[2].setForeground(Color.decode("#3264FA"));
                buttons[2].setText(cpuChar);
                cycle++;
                check();
                turnTaken = true;
            }

            if (
                //Looking to make a line of three with position 3 and X
                    (!turnTaken && buttons[3].getText() == "" && ((buttons[0].getText() ==cpuChar && buttons[6].getText()==cpuChar) ||
                            (buttons[4].getText() ==cpuChar && buttons[5].getText()==cpuChar)))) {
                buttons[3].setForeground(Color.decode("#3264FA"));
                buttons[3].setText(cpuChar);
                cycle++;
                check();
                turnTaken = true;
            }

            if (
                //Looking to make a line of three with position 4 and X
                    (!turnTaken && buttons[4].getText() == "" && ((buttons[0].getText() == cpuChar && buttons[8].getText()== cpuChar) ||
                            (buttons[2].getText() == cpuChar && buttons[6].getText()==cpuChar) ||
                            (buttons[3].getText() == cpuChar && buttons[5].getText()==cpuChar) ||
                            (buttons[1].getText() == buttons[7].getText())))) {
                buttons[4].setForeground(Color.decode("#3264FA"));
                buttons[4].setText(cpuChar);
                cycle++;
                check();
                turnTaken = true;
            }

            if (
                //Looking to make a line of three with position 5 and X
                    (!turnTaken && buttons[5].getText() == "" && ((buttons[2].getText() == cpuChar && buttons[8].getText() == cpuChar) ||
                            (buttons[3].getText() == cpuChar && buttons[4].getText() == cpuChar)))) {
                buttons[5].setForeground(Color.decode("#3264FA"));
                buttons[5].setText(cpuChar);
                cycle++;
                check();
                turnTaken = true;
            }

            if (
                //Looking to make a line of three with position 6 and X
                    (!turnTaken && buttons[6].getText() == "" && ((buttons[0].getText() == cpuChar && buttons[3].getText() == cpuChar) ||
                            (buttons[2].getText() == cpuChar && buttons[4].getText() == cpuChar) ||
                            (buttons[7].getText() == cpuChar && buttons[8].getText() == cpuChar)))) {
                buttons[6].setForeground(Color.decode("#3264FA"));
                buttons[6].setText(cpuChar);
                cycle++;
                check();
                turnTaken = true;
            }

            if (
                //Looking to make a line of three with position 7 and X
                    (!turnTaken && buttons[7].getText() == "" && ((buttons[1].getText() == cpuChar && buttons[4].getText() == cpuChar) ||
                            (buttons[6].getText() == cpuChar && buttons[8].getText() == cpuChar)))) {
                buttons[7].setForeground(Color.decode("#3264FA"));
                buttons[7].setText(cpuChar);
                cycle++;
                check();
                turnTaken = true;
            }

            if (
                //Looking to make a line of three with position 8 and X
                    (!turnTaken && buttons[8].getText() == "" && ((buttons[2].getText() == cpuChar && buttons[5].getText() == cpuChar) ||
                            (buttons[6].getText() == cpuChar && buttons[7].getText() == cpuChar) ||
                            (buttons[0].getText() == cpuChar && buttons[4].getText() == cpuChar)))) {
                buttons[8].setForeground(Color.decode("#3264FA"));
                buttons[8].setText(cpuChar);
                cycle++;
                check();
                turnTaken = true;
            } if (!turnTaken && cycle <= 9 && !xWin && !oWin){
                blockLine();
                //cpuMove();
            }
        }
    }

    public void blockLine()
    {
        boolean turnTaken = false;
        cpuChar = "O";
        playerChar = "X";

        //int[] takenSpaces = new int[9];
        if(cycle < 9 && !xWin && !oWin) {
            if (
                //Looking to make a line of three with position 0 and X
                    (!turnTaken && buttons[0].getText() == "" && ((buttons[1].getText() == playerChar
                            && buttons[2].getText() == playerChar) ||
                            (buttons[3].getText() == playerChar && buttons[6].getText() == playerChar) ||
                            (buttons[4].getText() == playerChar && buttons[8].getText() == playerChar)))) {
                buttons[0].setForeground(Color.decode("#3264FA"));
                buttons[0].setText(cpuChar);
                cycle++;
                check();
                turnTaken = true;
            }

            if (
                //Looking to make a line of three with position 1 and X
                    (!turnTaken && buttons[1].getText() == "" && ((buttons[0].getText() == playerChar
                            && buttons[2].getText()==playerChar) || (buttons[4].getText() == playerChar
                            && buttons[7].getText() == playerChar )))) {
                buttons[1].setForeground(Color.decode("#3264FA"));
                buttons[1].setText(cpuChar);
                cycle++;
                check();
                turnTaken = true;
            }

            if (
                //Looking to make a line of three with position 2 and X
                    (!turnTaken && buttons[2].getText() == "" && ((buttons[0].getText() == playerChar
                            && buttons[1].getText() == playerChar) ||
                            (buttons[5].getText() == playerChar && buttons[8].getText()==playerChar) ||
                            (buttons[4].getText() == playerChar && buttons[8].getText()==playerChar)))) {
                buttons[2].setForeground(Color.decode("#3264FA"));
                buttons[2].setText(cpuChar);
                cycle++;
                check();
                turnTaken = true;
            }

            if (
                //Looking to make a line of three with position 3 and X
                    (!turnTaken && buttons[3].getText() == "" && ((buttons[0].getText() ==playerChar
                            && buttons[6].getText()==playerChar) ||
                            (buttons[4].getText() ==playerChar && buttons[5].getText()==playerChar)))) {
                buttons[3].setForeground(Color.decode("#3264FA"));
                buttons[3].setText(cpuChar);
                cycle++;
                check();
                turnTaken = true;
            }

            if (
                //Looking to make a line of three with position 4 and X
                    (!turnTaken && buttons[4].getText() == "" && ((buttons[0].getText() == playerChar
                            && buttons[8].getText()== playerChar) ||
                            (buttons[2].getText() == playerChar && buttons[6].getText()==playerChar) ||
                            (buttons[3].getText() == playerChar && buttons[5].getText()==playerChar) ||
                            (buttons[1].getText() == buttons[7].getText())))) {
                buttons[4].setForeground(Color.decode("#3264FA"));
                buttons[4].setText(cpuChar);
                cycle++;
                check();
                turnTaken = true;
            }

            if (
                //Looking to make a line of three with position 5 and X
                    (!turnTaken && buttons[5].getText() == "" && ((buttons[2].getText() == playerChar
                            && buttons[8].getText() == playerChar) ||
                            (buttons[3].getText() == playerChar && buttons[4].getText() == playerChar)))) {
                buttons[5].setForeground(Color.decode("#3264FA"));
                buttons[5].setText(cpuChar);
                cycle++;
                check();
                turnTaken = true;
            }

            if (
                //Looking to make a line of three with position 6 and X
                    (!turnTaken && buttons[6].getText() == "" && ((buttons[0].getText() == playerChar
                            && buttons[3].getText() == playerChar) ||
                            (buttons[2].getText() == playerChar && buttons[4].getText() == playerChar) ||
                            (buttons[7].getText() == playerChar && buttons[8].getText() == playerChar)))) {
                buttons[6].setForeground(Color.decode("#3264FA"));
                buttons[6].setText(cpuChar);
                cycle++;
                check();
                turnTaken = true;
            }

            if (
                //Looking to make a line of three with position 7 and X
                    (!turnTaken && buttons[7].getText() == "" && ((buttons[1].getText() == playerChar
                            && buttons[4].getText() == playerChar) ||
                            (buttons[6].getText() == playerChar && buttons[8].getText() == playerChar)))) {
                buttons[7].setForeground(Color.decode("#3264FA"));
                buttons[7].setText(cpuChar);
                cycle++;
                check();
                turnTaken = true;
            }

            if (
                //Looking to make a line of three with position 8 and X
                    (!turnTaken && buttons[8].getText() == "" && ((buttons[2].getText() == playerChar && buttons[5].getText() == playerChar) ||
                            (buttons[6].getText() == playerChar && buttons[7].getText() == playerChar) ||
                            (buttons[0].getText() == playerChar && buttons[4].getText() == playerChar)))) {
                buttons[8].setForeground(Color.decode("#3264FA"));
                buttons[8].setText(cpuChar);
                cycle++;
                check();
                turnTaken = true;
            }
            if (!turnTaken && cycle <= 9 && !xWin && !oWin){
                cpuMove();
            }
        }
    }
}

