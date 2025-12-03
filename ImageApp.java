/*
  ImageApp: 
 */
import java.awt.Color;

public class ImageApp
{
  public static void main(String[] args)
  {
    // Run unit tests for Vector1by2 and Matrix2by2
    System.out.println("Running unit tests...\n");
    Vector1by2.runUnitTests();
    Matrix2by2.runUnitTests();

    // use any file from the lib folder
    String pictureFile = "lib/beach.jpg";

    // Get an image, get 2d array of pixels, show a color of a pixel, and display the image
    Picture origImg = new Picture(pictureFile);
    Pixel[][] origPixels = origImg.getPixels2D();
    System.out.println("Original pixel color at [0][0]: " + origPixels[0][0].getColor());
    origImg.explore();

    // Image #1 Using the original image and pixels, recolor an image by changing the RGB color of each Pixel
    Picture recoloredImg = new Picture(pictureFile);
    Pixel[][] recoloredPixels = recoloredImg.getPixels2D();
    
    // Reorder RGB to BRG (Blue, Red, Green)
    for (int row = 0; row < recoloredPixels.length; row++) {
      for (int col = 0; col < recoloredPixels[0].length; col++) {
        Color originalColor = recoloredPixels[row][col].getColor();
        int red = originalColor.getRed();
        int green = originalColor.getGreen();
        int blue = originalColor.getBlue();
        
        // Set color with reordered values (BRG)
        recoloredPixels[row][col].setColor(new Color(blue, red, green));
      }
    }
    recoloredImg.explore();
    System.out.println("Image #1: Recolored image (BRG order) displayed");

    // Image #2 Using the original image and pixels, create a photographic negative of the image
    Picture negImg = new Picture(pictureFile);
    Pixel[][] negPixels = negImg.getPixels2D();
    
    // Create negative by subtracting each RGB value from 255
    for (int row = 0; row < negPixels.length; row++) {
      for (int col = 0; col < negPixels[0].length; col++) {
        Color originalColor = negPixels[row][col].getColor();
        int red = 255 - originalColor.getRed();
        int green = 255 - originalColor.getGreen();
        int blue = 255 - originalColor.getBlue();
        
        negPixels[row][col].setColor(new Color(red, green, blue));
      }
    }
    negImg.explore();
    System.out.println("Image #2: Negative image displayed");

    // Image #3 Using the original image and pixels, create a grayscale version of the image
    Picture grayscaleImg = new Picture(pictureFile);
    Pixel[][] grayscalePixels = grayscaleImg.getPixels2D();
    
    // Create grayscale by averaging RGB values
    for (int row = 0; row < grayscalePixels.length; row++) {
      for (int col = 0; col < grayscalePixels[0].length; col++) {
        Color originalColor = grayscalePixels[row][col].getColor();
        int average = (originalColor.getRed() + originalColor.getGreen() + originalColor.getBlue()) / 3;
        
        grayscalePixels[row][col].setColor(new Color(average, average, average));
      }
    }
    grayscaleImg.explore();
    System.out.println("Image #3: Grayscale image displayed");

    // Image #4 Using the original image and pixels, rotate it 180 degrees
    Picture upsidedownImage = new Picture(pictureFile);
    Pixel[][] upsideDownPixels = upsidedownImage.getPixels2D();
    
    // Rotate 180 degrees using matrix multiplication approach
    Matrix2by2 rot180 = Matrix2by2.rotation180();
    int height = origPixels.length;
    int width = origPixels[0].length;
    
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        // Transform coordinates relative to center
        int centerRow = height / 2;
        int centerCol = width / 2;
        int relRow = row - centerRow;
        int relCol = col - centerCol;
        
        // Apply rotation matrix
        Vector1by2 original = new Vector1by2(relCol, relRow);
        Vector1by2 rotated = Vector1by2.multiply(original, rot180);
        
        // Convert back to absolute coordinates
        int newCol = (int)Math.round(rotated.getElement1()) + centerCol;
        int newRow = (int)Math.round(rotated.getElement2()) + centerRow;
        
        // Check bounds and copy pixel
        if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width) {
          upsideDownPixels[row][col].setColor(origPixels[newRow][newCol].getColor());
        } else {
          upsideDownPixels[row][col].setColor(Color.WHITE);
        }
      }
    }
    upsidedownImage.explore();
    System.out.println("Image #4: 180-degree rotated image displayed");

    // Image #5 Using the original image and pixels, rotate image 90 degrees counterclockwise
    Picture rotateImg = new Picture(pictureFile);
    Pixel[][] rotatePixels = rotateImg.getPixels2D();
    
    // Rotate 90 degrees counterclockwise using matrix multiplication
    Matrix2by2 rot90 = Matrix2by2.rotation90();
    
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int centerRow = height / 2;
        int centerCol = width / 2;
        int relRow = row - centerRow;
        int relCol = col - centerCol;
        
        Vector1by2 original = new Vector1by2(relCol, relRow);
        Vector1by2 rotated = Vector1by2.multiply(original, rot90);
        
        int newCol = (int)Math.round(rotated.getElement1()) + centerCol;
        int newRow = (int)Math.round(rotated.getElement2()) + centerRow;
        
        if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width) {
          rotatePixels[row][col].setColor(origPixels[newRow][newCol].getColor());
        } else {
          rotatePixels[row][col].setColor(Color.WHITE);
        }
      }
    }
    rotateImg.explore();
    System.out.println("Image #5: 90-degree counterclockwise rotated image displayed");

    // Image #6 Using the original image and pixels, rotate image 270 degrees (-90)
    Picture rotateImg2 = new Picture(pictureFile);
    Pixel[][] rotatePixels2 = rotateImg2.getPixels2D();
    
    // Rotate 270 degrees counterclockwise (90 clockwise) using matrix multiplication
    Matrix2by2 rot270 = Matrix2by2.rotation270();
    
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int centerRow = height / 2;
        int centerCol = width / 2;
        int relRow = row - centerRow;
        int relCol = col - centerCol;
        
        Vector1by2 original = new Vector1by2(relCol, relRow);
        Vector1by2 rotated = Vector1by2.multiply(original, rot270);
        
        int newCol = (int)Math.round(rotated.getElement1()) + centerCol;
        int newRow = (int)Math.round(rotated.getElement2()) + centerRow;
        
        if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width) {
          rotatePixels2[row][col].setColor(origPixels[newRow][newCol].getColor());
        } else {
          rotatePixels2[row][col].setColor(Color.WHITE);
        }
      }
    }
    rotateImg2.explore();
    System.out.println("Image #6: 270-degree counterclockwise rotated image displayed");


    // Final Image: Add a small image to a larger one
    String largeImageFile = "lib/beach.jpg";
    String smallImageFile = "lib/koala.jpg";  // Using koala as the small image
    
    Picture largeImg = new Picture(largeImageFile);
    Picture smallImg = new Picture(smallImageFile);
    
    Pixel[][] largePixels = largeImg.getPixels2D();
    Pixel[][] smallPixels = smallImg.getPixels2D();
    
    // Position where to paste the small image (top-left corner)
    int startRow = 50;
    int startCol = 50;
    
    // Copy small image onto large image, eliminating white background
    for (int row = 0; row < smallPixels.length && (startRow + row) < largePixels.length; row++) {
      for (int col = 0; col < smallPixels[0].length && (startCol + col) < largePixels[0].length; col++) {
        Color smallColor = smallPixels[row][col].getColor();
        
        // Only copy if the pixel is not close to white (threshold for white background removal)
        if (smallColor.getRed() < 250 || smallColor.getGreen() < 250 || smallColor.getBlue() < 250) {
          largePixels[startRow + row][startCol + col].setColor(smallColor);
        }
      }
    }
    
    largeImg.explore();
    System.out.println("Final Image: Small image embedded in large image (with background removal)");
    System.out.println("\nAll image transformations complete!");
  }
}
