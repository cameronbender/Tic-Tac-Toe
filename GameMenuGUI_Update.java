import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

public class GameMenuGUI {

    public static void main(String[] args) {

        // Create a JFrame
        JFrame frame = new JFrame("Game Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the frame to full screen

        // Create a JPanel with CardLayout
        JPanel cardPanel = new JPanel(new CardLayout());

        // Create panels for the main menu and how to play menu
        JPanel mainMenuPanel = createMainMenuPanel(cardPanel);
        JPanel howToPlayPanel = createHowToPlayPanel(cardPanel);
        JPanel gameModePanel = createGameModePanel(cardPanel);
        JPanel diffSelectPanel = createDiffSelectPanel(cardPanel);

        // Add panels to the cardPanel with unique names
        cardPanel.add(mainMenuPanel, "MainMenu");
        cardPanel.add(howToPlayPanel, "HowToPlay");
        cardPanel.add(gameModePanel, "GameMode");
        cardPanel.add(diffSelectPanel, "DiffSelect");

        // Add the cardPanel to the frame
        frame.add(cardPanel);

        // Set the frame visibility
        frame.setVisible(true);
    }

    private static JPanel createMainMenuPanel(final JPanel cardPanel) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#002626")); // Set background color

        // Set the foreground (text) color for the buttons
        Color buttonTextColor = Color.decode("#0E4749");

        // Create a bigger font for buttons
        Font buttonFont = new Font("Impact", Font.BOLD, 24);

        // Create a "Start Game" button
        JButton startButton = new JButton("Start Game");
        startButton.setFont(buttonFont);
        startButton.setForeground(buttonTextColor);
        startButton.setBounds(300, 200, 200, 60);
        panel.add(startButton);

        // Create a "How to Play" button
        JButton howToPlayButton = new JButton("How to Play");
        howToPlayButton.setFont(buttonFont);
        howToPlayButton.setForeground(buttonTextColor);
        howToPlayButton.setBounds(300, 300, 200, 60);
        panel.add(howToPlayButton);

        // Add action listener to the "Start Game" button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle "Start Game" button click here
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "GameMode");
            }
        });


        // Add action listener to the "How to Play" button
        howToPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the "HowToPlay" panel
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "HowToPlay");
            }
        });

        return panel;
    }

    private static JPanel createHowToPlayPanel(final JPanel cardPanel) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#002626")); // Set background color


        JLabel instructionsLabel = new JLabel("Tic-Tac-Toe Rules:");
        instructionsLabel.setForeground(Color.WHITE);
        instructionsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        instructionsLabel.setBounds(50, 50, 300, 30);
        panel.add(instructionsLabel);

        JTextArea rulesTextArea = new JTextArea();
        rulesTextArea.setText("1. The game is played on a grid of 3x3 squares.\n" +
                "2. Two players take turns to mark a square with their symbol (X or O).\n" +
                "3. The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row wins the game.\n" +
                "4. If the grid is filled without any player achieving three in a row, the game is a draw.");
        rulesTextArea.setForeground(Color.WHITE);
        rulesTextArea.setBackground(Color.decode("#002626"));
        rulesTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        rulesTextArea.setBounds(50, 100, 1000, 200);
        rulesTextArea.setEditable(false);
        panel.add(rulesTextArea);

        // Create a "Back" button to return to the main menu
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setForeground(Color.decode("#0E4749"));
        backButton.setBounds(50, 350, 100, 40);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch back to the "MainMenu" panel
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "MainMenu");
            }
        });
        panel.add(backButton);

        return panel;
    }

    public static JPanel createGameModePanel(final JPanel cardPanel) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#002626")); // Set background color

        // Set the foreground (text) color for the buttons
        final Color[] buttonTextColor = {Color.decode("#0E4749")};

        // Create a bigger font for buttons
        Font buttonFont = new Font("Impact", Font.BOLD, 24);

        // Create a "Single-Player" button
        JButton singlePlayerButton = new JButton("Single-Player");
        singlePlayerButton.setFont(buttonFont);
        singlePlayerButton.setForeground(buttonTextColor[0]);
        singlePlayerButton.setBounds(300, 200, 200, 60);
        panel.add(singlePlayerButton);

        // Create a "Multi-Player" button
        JButton multiPlayerButton = new JButton("Multi-Player");
        multiPlayerButton.setFont(buttonFont);
        multiPlayerButton.setForeground(buttonTextColor[0]);
        multiPlayerButton.setBounds(300, 300, 200, 60);
        panel.add(multiPlayerButton);

        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle "Start Game" button click here
                //TicTacToe tictactoe = new TicTacToe();
                //tictactoe.singlePlayer = true;
                //int roundCount = 0;
                //tictactoe.roundCount = roundCount;
                //Switch to Difficulty Select
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "DiffSelect");
            }
        });


        multiPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle "Start Game" button click here
                TicTacToe tictactoe = new TicTacToe();
                tictactoe.singlePlayer = false;
                int roundCount, xWinNum, oWinNum, drawNum;
                roundCount = xWinNum = oWinNum = drawNum = 0;
                tictactoe.roundCount = roundCount;
                tictactoe.xWinNum = xWinNum;
                tictactoe.oWinNum = oWinNum;
                tictactoe.drawNum = drawNum;
            }
        });

        // Create a "Back" button to return to the main menu
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setForeground(Color.decode("#0E4749"));
        backButton.setBounds(50, 350, 100, 40);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch back to the "MainMenu" panel
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "MainMenu");
            }
        });
        panel.add(backButton);
        return panel;
    }

    private static JPanel createDiffSelectPanel(final JPanel cardPanel)
    {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#002626")); // Set background color

        JLabel instructionsLabel = new JLabel("Custom:");
        instructionsLabel.setForeground(Color.WHITE);
        instructionsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        instructionsLabel.setBounds(50, 50, 300, 30);
        panel.add(instructionsLabel);

        // Create an easy button to select difficulty
        JButton easyButton = new JButton("Easy");
        easyButton.setFont(new Font("Impact", Font.BOLD, 24));
        easyButton.setForeground(Color.decode("#0E4749"));
        easyButton.setBounds(300, 200, 200, 60);
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle "Start Game" button click here
                //Starts Game at Easy Difficulty
                TicTacToe tictactoe = new TicTacToe();
                tictactoe.singlePlayer = true;
                int roundCount = 0;
                tictactoe.roundCount = roundCount;
                tictactoe.difficulty = 0;
            }
        });
        panel.add(easyButton);

        // Create a normal button to select difficulty
        JButton normalButton = new JButton("Hard");
        normalButton.setFont(new Font("Impact", Font.BOLD, 24));
        normalButton.setForeground(Color.decode("#0E4749"));
        normalButton.setBounds(300, 300, 200, 60);
        normalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle "Start Game" button click here
                //Starts Game at Easy Difficulty
                TicTacToe tictactoe = new TicTacToe();
                tictactoe.singlePlayer = true;
                int roundCount = 0;
                tictactoe.roundCount = roundCount;
                tictactoe.difficulty = 1;

            }
        });
        panel.add(normalButton);

        // Create a "Back" button to return to the main menu
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setForeground(Color.decode("#0E4749"));
        backButton.setBounds(50, 350, 100, 40);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch back to the "MainMenu" panel
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "MainMenu");
            }
        });
        panel.add(backButton);
        return panel;
    }
}