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

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            //CardLayout cardLayout = new CardLayout();
            JPanel cell = new JPanel();
            cell.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            gridPanel.add(cell);
            
            

            //HERE we could have the bugs in? cell.add()?

            
        }
        
        add(gridPanel, BorderLayout.CENTER);
    }
    BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
    public void updateGrid(List<Bug> bugs) {
        System.out.println("Updating bugs!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        for (int i = 0; i < (GRID_SIZE * GRID_SIZE); i++) {
            //System.out.println(i);
            JPanel cell = (JPanel) gridPanel.getComponent(i);
            cell.setBackground(Color.WHITE);
            cell.removeAll();
        }

        for (Bug bug : bugs) {
            int x = bug.getX();
            int y = bug.getY();
            //System.out.print("X: ");
            //System.out.println(x);
            //System.out.print("Y: ");
            //System.out.println(y);
            BufferedImage myPicture= null;
            BufferedImage myPicturersized= null;
            try{
                File file = new File(bug.getImagepath());
                myPicture = ImageIO.read(file);
                myPicturersized = resizeImage(myPicture,CELL_SIZE,CELL_SIZE);
                //graphic = myPicture.createGraphics();
                //System.out.println("Was able to load image");
            }catch(Exception e){
                System.out.println(e);
                System.out.println("Could not load in the image");
            }
            JPanel cell = (JPanel) gridPanel.getComponent(y * GRID_SIZE + x);
            //JLabel label = new JLabel(bug.getName());
            JLabel label = new JLabel(new ImageIcon(myPicturersized));
            cell.add(label);
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

}

