import java.awt.*;
import java.io.File;
import javax.swing.*;

/**
 * ImageAppGUI - Interactive image manipulation with GUI controls
 * Features: Dropdown menus for image selection, rotation buttons, color effects
 */
public class ImageAppGUI extends JFrame {
    
    private Picture currentPicture;
    private String currentBackgroundImage;
    private String currentOverlayImage;
    private PictureFrame pictureFrame;
    private int bgRotationAngle = 0;
    private int overlayRotationAngle = 0;
    
    private JComboBox<String> backgroundDropdown;
    private JComboBox<String> overlayDropdown;
    private JComboBox<String> bgColorEffectDropdown;
    private JComboBox<String> overlayColorEffectDropdown;
    private JComboBox<String> bgRotateDropdown;
    private JComboBox<String> overlayRotateDropdown;
    private JButton applyButton;
    
    public ImageAppGUI() {
        setTitle("ImageApp - Interactive Image Manipulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 320);
        setLayout(new BorderLayout());
        
        // setup control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(8, 2, 10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Background image dropdown
        controlPanel.add(new JLabel("Background Image (lib):"));
        backgroundDropdown = new JComboBox<>();
        backgroundDropdown.addItem("None");
        loadImagesFromFolder("lib", backgroundDropdown);
        backgroundDropdown.setSelectedItem("beach.jpg");
        controlPanel.add(backgroundDropdown);
        
        // Background color effect dropdown
        controlPanel.add(new JLabel("Background Color Effect:"));
        bgColorEffectDropdown = new JComboBox<>();
        bgColorEffectDropdown.addItem("None");
        bgColorEffectDropdown.addItem("Recolor (BRG)");
        bgColorEffectDropdown.addItem("Negative");
        bgColorEffectDropdown.addItem("Grayscale");
        controlPanel.add(bgColorEffectDropdown);
        
        // Background rotation dropdown
        controlPanel.add(new JLabel("Background Rotation:"));
        bgRotateDropdown = new JComboBox<>();
        bgRotateDropdown.addItem("None (0°)");
        bgRotateDropdown.addItem("90° Clockwise");
        bgRotateDropdown.addItem("90° Counter-Clockwise");
        bgRotateDropdown.addItem("180° Flip");
        controlPanel.add(bgRotateDropdown);
        
        // Overlay image dropdown
        controlPanel.add(new JLabel("Overlay Image (lib2):"));
        overlayDropdown = new JComboBox<>();
        overlayDropdown.addItem("None");
        loadImagesFromFolder("lib2", overlayDropdown);
        controlPanel.add(overlayDropdown);
        
        // Overlay color effect dropdown
        controlPanel.add(new JLabel("Overlay Color Effect:"));
        overlayColorEffectDropdown = new JComboBox<>();
        overlayColorEffectDropdown.addItem("None");
        overlayColorEffectDropdown.addItem("Recolor (BRG)");
        overlayColorEffectDropdown.addItem("Negative");
        overlayColorEffectDropdown.addItem("Grayscale");
        controlPanel.add(overlayColorEffectDropdown);
        
        // Overlay rotation dropdown
        controlPanel.add(new JLabel("Overlay Rotation:"));
        overlayRotateDropdown = new JComboBox<>();
        overlayRotateDropdown.addItem("None (0°)");
        overlayRotateDropdown.addItem("90° Clockwise");
        overlayRotateDropdown.addItem("90° Counter-Clockwise");
        overlayRotateDropdown.addItem("180° Flip");
        controlPanel.add(overlayRotateDropdown);
        
        // Apply button
        controlPanel.add(new JLabel(""));
        applyButton = new JButton("Apply Changes");
        applyButton.addActionListener(e -> applyChanges());
        controlPanel.add(applyButton);
        
        add(controlPanel, BorderLayout.NORTH);
        
        setVisible(true);
        
        // Load default image
        applyChanges();
    }
    
    private void loadImagesFromFolder(String folderPath, JComboBox<String> dropdown) {
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            // get jpg and png files
            File[] files = folder.listFiles((dir, name) -> 
                name.toLowerCase().endsWith(".jpg") || 
                name.toLowerCase().endsWith(".png") ||
                name.toLowerCase().endsWith(".jpeg"));
            
            if (files != null) {
                for (File file : files) {
                    dropdown.addItem(file.getName());
                }
            }
        }
    }
    
    
    private void applyChanges() {
        String background = (String) backgroundDropdown.getSelectedItem();
        String overlay = (String) overlayDropdown.getSelectedItem();
        String bgColorEffect = (String) bgColorEffectDropdown.getSelectedItem();
        String overlayColorEffect = (String) overlayColorEffectDropdown.getSelectedItem();
        String bgRotation = (String) bgRotateDropdown.getSelectedItem();
        String overlayRotation = (String) overlayRotateDropdown.getSelectedItem();
        
        if (background == null || background.equals("None")) {
            return;
        }
        
        try {
            // Load background image
            currentBackgroundImage = "lib/" + background;
            Picture workingPicture = new Picture(currentBackgroundImage);
            Pixel[][] pixels = workingPicture.getPixels2D();
            
            // Apply background color effect
            if (bgColorEffect != null && !bgColorEffect.equals("None")) {
                applyColorEffect(pixels, bgColorEffect);
            }
            
            // Apply background rotation
            int bgRotationAngle = getRotationAngle(bgRotation);
            if (bgRotationAngle != 0) {
                pixels = applyRotation(pixels, bgRotationAngle);
                workingPicture = createPictureFromPixels(pixels);
            }
            
            // Apply overlay
            if (overlay != null && !overlay.equals("None")) {
                currentOverlayImage = "lib2/" + overlay;
                int overlayRotationAngle = getRotationAngle(overlayRotation);
                applyOverlayWithEffects(workingPicture, currentOverlayImage, overlayColorEffect, overlayRotationAngle);
            }
            
            // Display the result
            currentPicture = workingPicture;
            workingPicture.explore();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private int getRotationAngle(String rotationChoice) {
        if (rotationChoice == null || rotationChoice.equals("None (0°)")) {
            return 0;
        } else if (rotationChoice.equals("90° Clockwise")) {
            return 270; // 90 clockwise = 270 counter-clockwise
        } else if (rotationChoice.equals("90° Counter-Clockwise")) {
            return 90;
        } else if (rotationChoice.equals("180° Flip")) {
            return 180;
        }
        return 0;
    }
    
    private void applyColorEffect(Pixel[][] pixels, String effect) {
        // loop through all pixels
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[0].length; col++) {
                Color originalColor = pixels[row][col].getColor();
                int r = originalColor.getRed();
                int g = originalColor.getGreen();
                int b = originalColor.getBlue();
                
                Color newColor;
                if (effect.equals("Recolor (BRG)")) {
                    newColor = new Color(b, r, g);
                } else if (effect.equals("Negative")) {
                    newColor = new Color(255 - r, 255 - g, 255 - b);
                } else if (effect.equals("Grayscale")) {
                    int avg = (r + g + b) / 3;
                    newColor = new Color(avg, avg, avg);
                } else {
                    newColor = originalColor;
                }
                
                pixels[row][col].setColor(newColor);
            }
        }
    }
    
    private Pixel[][] applyRotation(Pixel[][] originalPixels, int angle) {
        int height = originalPixels.length;
        int width = originalPixels[0].length;
        
        // get the right rotation matrix
        Matrix2by2 rotMatrix;
        if (angle == 90) {
            rotMatrix = Matrix2by2.rotation90();
        } else if (angle == 180) {
            rotMatrix = Matrix2by2.rotation180();
        } else if (angle == 270) {
            rotMatrix = Matrix2by2.rotation270();
        } else {
            return originalPixels;
        }
        
        Picture rotatedPicture = new Picture(height, width);
        Pixel[][] rotatedPixels = rotatedPicture.getPixels2D();
        
        // rotate around center
        int centerRow = height / 2;
        int centerCol = width / 2;
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int relRow = row - centerRow;
                int relCol = col - centerCol;
                
                // use vector multiplication
                Vector1by2 original = new Vector1by2(relCol, relRow);
                Vector1by2 rotated = Vector1by2.multiply(original, rotMatrix);
                
                int newCol = (int)Math.round(rotated.getElement1()) + centerCol;
                int newRow = (int)Math.round(rotated.getElement2()) + centerRow;
                
                if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width) {
                    rotatedPixels[row][col].setColor(originalPixels[newRow][newCol].getColor());
                } else {
                    rotatedPixels[row][col].setColor(Color.WHITE);
                }
            }
        }
        
        return rotatedPixels;
    }
    
    private void applyOverlayWithEffects(Picture basePicture, String overlayPath, String colorEffect, int rotation) {
        Picture overlayPicture = new Picture(overlayPath);
        Pixel[][] overlayPixels = overlayPicture.getPixels2D();
        
        // apply effects to overlay
        if (colorEffect != null && !colorEffect.equals("None")) {
            applyColorEffect(overlayPixels, colorEffect);
        }
        
        if (rotation != 0) {
            overlayPixels = applyRotation(overlayPixels, rotation);
        }
        
        Pixel[][] basePixels = basePicture.getPixels2D();
        
        int startRow = 50;
        int startCol = 50;
        
        for (int row = 0; row < overlayPixels.length && (startRow + row) < basePixels.length; row++) {
            for (int col = 0; col < overlayPixels[0].length && (startCol + col) < basePixels[0].length; col++) {
                Color overlayColor = overlayPixels[row][col].getColor();
                
                // skip white pixels
                if (overlayColor.getRed() < 250 || 
                    overlayColor.getGreen() < 250 || 
                    overlayColor.getBlue() < 250) {
                    basePixels[startRow + row][startCol + col].setColor(overlayColor);
                }
            }
        }
    }
    
    private Picture createPictureFromPixels(Pixel[][] pixels) {
        int height = pixels.length;
        int width = pixels[0].length;
        Picture picture = new Picture(height, width);
        Pixel[][] newPixels = picture.getPixels2D();
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                newPixels[row][col].setColor(pixels[row][col].getColor());
            }
        }
        
        return picture;
    }
    
    public static void main(String[] args) {
        // Run unit tests first
        System.out.println("Running unit tests...\n");
        Vector1by2.runUnitTests();
        Matrix2by2.runUnitTests();
        System.out.println("\nStarting GUI...\n");
        
        SwingUtilities.invokeLater(() -> new ImageAppGUI());
    }
}
