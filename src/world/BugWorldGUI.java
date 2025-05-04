package world;

import bugs.*;
//import bugs.Bug;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.*;
import javax.imageio.ImageIO;

public class BugWorldGUI extends JFrame {
    private static final int GRID_SIZE = 10;
    private static final int CELL_SIZE = 50;
    private JPanel gridPanel;

    public BugWorldGUI() {
        setTitle("Bug World");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //sets up the board display that the user will see
        gridPanel = new JPanel(); //panel that will hold all the cells 
        gridPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) { //for all cells 
            JPanel cell = new JPanel();
            cell.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE)); 
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            gridPanel.add(cell);
        }
        
        add(gridPanel, BorderLayout.CENTER); 
    }
    //resizes the image to the target size
    BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null); //resizes the image
        graphics2D.dispose();
        return resizedImage;
    }
    //updates all bugs on the grid
    public void updateGrid(List<Bug> bugs) {
        System.out.println("Updating bugs!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        for (int i = 0; i < (GRID_SIZE * GRID_SIZE); i++) { //for all cells, clears them
            JPanel cell = (JPanel) gridPanel.getComponent(i); 
            cell.setBackground(Color.WHITE); 
            cell.removeAll();
        }   
        //then for each bug gets its postion and in that spot loads in the image of that bug
        for (Bug bug : bugs) { 
            int x = bug.getX();
            int y = bug.getY();
            BufferedImage myPicture= null;
            BufferedImage myPicturersized= null;
            try{ //loads in image file 
                File file = new File(bug.getImagepath());
                myPicture = ImageIO.read(file);
                myPicturersized = resizeImage(myPicture,CELL_SIZE,CELL_SIZE);
            }catch(Exception e){
                System.out.println(e);
                System.out.println("Could not load in the image");
            }
            JPanel cell = (JPanel) gridPanel.getComponent(y * GRID_SIZE + x); //gets the component at the location
            JLabel label = new JLabel(new ImageIcon(myPicturersized)); //gives it the image 
            cell.add(label);
        }
        //updates the grid panel
        gridPanel.revalidate();
        gridPanel.repaint();
    }

}

