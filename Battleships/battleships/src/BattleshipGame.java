import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;

public class BattleshipGame extends JFrame {
    private JButton[][] playerGrid, computerGrid;
    private int gridSize;
    private int shipCount = 5;
    private int playerShipsRemaining = 5;
    private int computerShipsRemaining = 5;
    private boolean[][] computerShips;

    public BattleshipGame() {
        askForGridSize();

    }

    private void askForGridSize() {
        try {
            gridSize = Integer.parseInt(JOptionPane.showInputDialog("Enter grid size:"));
            if (gridSize < 5) {
                JOptionPane.showMessageDialog(this, "Grid size must be at least 5. Please enter a valid size.");
                askForGridSize(); // Ask again for a valid grid size
            } else {
                initializeGrid();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer for grid size.");
            askForGridSize(); // Ask again for a valid grid size
        }
    }

    private void initializeGrid() {
        setTitle("Battleship Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use a GridLayout with 2 rows and 1 column to arrange the player and computer
        // grids vertically
        setLayout(new GridLayout(2, 1, 0, 20)); // 20 pixels vertical gap between grids

        playerGrid = new JButton[gridSize][gridSize];
        computerGrid = new JButton[gridSize][gridSize];
        computerShips = new boolean[gridSize][gridSize];

        JPanel playerPanel = new JPanel(new GridLayout(gridSize, gridSize));
        JPanel computerPanel = new JPanel(new GridLayout(gridSize, gridSize));

        initializeGrid(playerGrid, playerPanel);
        initializeGrid(computerGrid, computerPanel);

        add(new JLabel("Player", SwingConstants.CENTER)); // Centered label under the player grid
        add(playerPanel);

        add(new JLabel("Computer", SwingConstants.CENTER)); // Centered label under the computer grid
        add(computerPanel);

        setLocationRelativeTo(null);
        setVisible(true);
        pack();
        setupGame();
    }

    private void setupGame() {
        // Place computer's ships randomly
        placeComputerShips();

        // Add action listeners to computer grid buttons
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                computerGrid[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton clickedButton = (JButton) e.getSource();
                        // Handle player's move
                        handlePlayerMove(clickedButton);
                        // Check for game end condition
                        checkGameEnd();
                        // Handle computer's move
                        handleComputerMove();
                        // Check for game end condition after the computer's move
                        checkGameEnd();
                    }
                });
            }
        }

        // Allow the player to place ships on the grid
        placePlayerShips();
    }

    private void initializeGrid(JButton[][] grid, JPanel panel) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = new JButton();
                grid[i][j].setPreferredSize(new Dimension(40, 40));
                panel.add(grid[i][j]);
            }
        }
    }

    private void handleComputerMove() {
        // Implement logic for handling computer's move here
        Random rand = new Random();
        int row, col;
        do {
            row = rand.nextInt(gridSize);
            col = rand.nextInt(gridSize);
        } while (!playerGrid[row][col].isEnabled()); // Keep generating until a valid move is found

        // Disable the button to prevent further clicks on the same cell
        playerGrid[row][col].setEnabled(false);

        // Check if the computer's move hits a player's ship
        if (playerGrid[row][col].getText().equals("O")) {
            // Hit
            playerGrid[row][col].setBackground(Color.RED);
            playerShipsRemaining--;
            JOptionPane.showMessageDialog(this, "Computer hit! Your ships remaining: " + playerShipsRemaining);
        }

        else {
            // Miss
            playerGrid[row][col].setBackground(Color.BLUE);
            JOptionPane.showMessageDialog(this, "Computer missed!");
        }
    }

    private void placeComputerShips() {
        Random rand = new Random();
        for (int i = 0; i < shipCount; i++) {
            int row, col;
            do {
                row = rand.nextInt(gridSize);
                col = rand.nextInt(gridSize);
            } while (!isValidShipPlacement(computerGrid, row, col));

            // Set a flag to indicate a ship is present at this location
            computerShips[row][col] = true;
        }
    }

    private void handlePlayerMove(JButton clickedButton) {
        // Disable the button to prevent further clicks on the same cell
        clickedButton.setEnabled(false);

        int row = getRow(clickedButton);
        int col = getCol(clickedButton);

        // Implement logic for handling player's move here
        if (computerShips[row][col]) {
            // Hit
            computerGrid[row][col].setBackground(Color.RED);
            computerShipsRemaining--;

            // Set the ship icon on the computer's grid cell
            URL image = getClass().getResource("ship_icon.png");
            if (image != null) {
                try {
                    // Resize the image (adjust the width and height accordingly)
                    BufferedImage originalImage = ImageIO.read(image);
                    int newWidth = 30; // Set the desired width
                    int newHeight = 30; // Set the desired height
                    Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                    // Set the icon on the computer's grid cell
                    computerGrid[getRow(clickedButton)][getCol(clickedButton)].setIcon(new ImageIcon(resizedImage));
                } catch (IOException e) {
                    System.err.println("Error loading and resizing ship icon.");
                    e.printStackTrace();
                }
            } else {
                System.err.println("Error loading ship icon.");
            }
            JOptionPane.showMessageDialog(this, "Hit! Computer's ships remaining: " + computerShipsRemaining);
        } else {
            // Miss
            computerGrid[row][col].setBackground(Color.BLUE);
            JOptionPane.showMessageDialog(this, "Miss!");
        }
    }

    private void placePlayerShips() {
        for (int i = 0; i < shipCount; i++) {
            boolean validPlacement = false;
            int row = 0, col = 0; // Initialize row and col outside the try block

            while (!validPlacement) {
                String input = JOptionPane
                        .showInputDialog("Enter the row and column (separated by space) for ship " + (i + 1) + ":");
                String[] coordinates = input.split(" ");

                if (coordinates.length != 2) {
                    JOptionPane.showMessageDialog(this,
                            "Invalid input. Please enter row and column separated by space.");
                    continue;
                }

                try {
                    row = Integer.parseInt(coordinates[0]);
                    col = Integer.parseInt(coordinates[1]);

                    if (isValidShipPlacement(playerGrid, row, col)) {
                        URL image = getClass().getResource("ship_icon.png");

                        if (image != null) {
                            try {
                                // Resize the image (adjust the width and height accordingly)
                                // Read the image from the URL
                                BufferedImage originalImage = ImageIO.read(image);
                                int newWidth = 30; // Set the desired width
                                int newHeight = 30; // Set the desired height

                                Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight,
                                        Image.SCALE_SMOOTH);
                                // Create an ImageIcon from the resized image

                                playerGrid[row][col].setIcon(new ImageIcon(resizedImage));
                                playerGrid[row][col].setText("O");
                                validPlacement = true;
                            } catch (IOException e) {
                                System.err.println("Error loading and resizing ship icon.");
                                e.printStackTrace();
                            }
                        } else {
                            System.err.println("Error loading ship icon.");
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid placement. Please choose a different location.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this,
                            "Invalid input. Please enter valid integers for row and column.");
                }
            }
        }
    }

    private boolean isValidShipPlacement(JButton[][] grid, int row, int col) {
        // Check if the given row and column are within bounds
        if (row < 0 || row >= gridSize || col < 0 || col >= gridSize) {
            return false;
        }

        // Check if the cell is empty
        return grid[row][col].getText().equals("");
    }

    private void checkGameEnd() {
        if (playerShipsRemaining == 0) {
            JOptionPane.showMessageDialog(this, "Game over! Computer wins!");
            System.exit(0);
        } else if (computerShipsRemaining == 0) {
            JOptionPane.showMessageDialog(this, "Game over! You win!");
            System.exit(0);
        }
    }

    private int getRow(JButton button) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (button == playerGrid[i][j] || button == computerGrid[i][j]) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int getCol(JButton button) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (button == playerGrid[i][j] || button == computerGrid[i][j]) {
                    return j;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BattleshipGame());
    }
}