import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  //--constructors--
  private final int LOW_FILTER = 4;
  private final int HIGH_FILTER = 64;
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  /**
   * Hides secret in picture
   */
  public void hide(Picture secret)
  {
    Pixel leftPixel = null;
    Pixel secretPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Pixel[][] pixelsSecret = secret.getPixels2D();
    Color leftColor = null;
    Color secretColor = null;
    int width = pixelsSecret[0].length;
    int height = pixelsSecret.length;
    
    for (int row = 0, srow=0; row < pixels.length && srow < height;row++,srow++)
    {
      for (int col = 0, scol=0; 
           col < pixels[0].length-1 && scol < width; col++, scol++)
      {
        leftPixel = pixels[row][col];   
        secretPixel = pixelsSecret[srow][scol];
        leftColor = leftPixel.getColor();
        secretColor = secretPixel.getColor();
      
        leftPixel.setColor(new Color(LOW_FILTER*(leftColor.getRed()/LOW_FILTER) + 
                                     secretColor.getRed()/HIGH_FILTER,
               LOW_FILTER*(leftColor.getGreen()/LOW_FILTER) + secretColor.getGreen()/HIGH_FILTER,
               LOW_FILTER*(leftColor.getBlue()/LOW_FILTER) + secretColor.getBlue()/HIGH_FILTER));
        
       }
    
    }
  }
  
  /**
   * Hides secret in picture, starting at a given point in picture
   */
  public void hide(Picture secret, int startX, int startY)
  {
    Pixel leftPixel = null;
    Pixel secretPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Pixel[][] pixelsSecret = secret.getPixels2D();
    Color leftColor = null;
    Color secretColor = null;
    int width = pixelsSecret[0].length;
    int height = pixelsSecret.length;
    
    for (int row = startY, srow=0; row < pixels.length && srow < height;row++,srow++)
    {
      for (int col = startX, scol=0; 
           col < pixels[0].length-1 && scol < width; col++, scol++)
      {
        leftPixel = pixels[row][col];   
        secretPixel = pixelsSecret[srow][scol];
        leftColor = leftPixel.getColor();
        secretColor = secretPixel.getColor();
      
        leftPixel.setColor(new Color(LOW_FILTER*(leftColor.getRed()/LOW_FILTER) + 
                                     secretColor.getRed()/HIGH_FILTER,
               LOW_FILTER*(leftColor.getGreen()/LOW_FILTER) + secretColor.getGreen()/HIGH_FILTER,
               LOW_FILTER*(leftColor.getBlue()/LOW_FILTER) + secretColor.getBlue()/HIGH_FILTER));
      }
    
    }
  }

  
/**
 * Revels the hidden picture
 */
  public void unhide()
  {
    Pixel leftPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    
    Color leftColor = null;
    
    for (int row = 0; row < pixels.length;
        row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];   
        
        leftColor = leftPixel.getColor();
      
        leftPixel.setColor(new Color(HIGH_FILTER*(leftColor.getRed()%LOW_FILTER),
               HIGH_FILTER*(leftColor.getGreen()%LOW_FILTER),
               HIGH_FILTER*(leftColor.getBlue()%LOW_FILTER)));
        
    }
    
  }
}

/**
 * Clear the lower two bits in all pixels
 */
public void clearLow(){
    Pixel leftPixel = null;
    
    Pixel[][] pixels = this.getPixels2D();
    
    Color leftColor = null;
    
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0;  col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];   
        leftColor = leftPixel.getColor();
      
        leftPixel.setColor(new Color(LOW_FILTER*(leftColor.getRed()/LOW_FILTER), 
            LOW_FILTER*(leftColor.getGreen()/LOW_FILTER),
            LOW_FILTER*(leftColor.getBlue()/LOW_FILTER)));
        
       }
    
    }
}

/**
 * Set the lower two bits in all pixels
 */
public void setLow(Color c){
    // first clear the lowest two bits in all pixels in the picture
    clearLow();
    
    Pixel leftPixel = null;
    
    Pixel[][] pixels = this.getPixels2D();
    
    Color leftColor = null;
    
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0;  col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];   
        leftColor = leftPixel.getColor();
      
        leftPixel.setColor(new Color(leftColor.getRed() + c.getRed() % LOW_FILTER, 
            leftColor.getGreen() + c.getGreen() % LOW_FILTER,
            leftColor.getBlue() + c.getBlue() % LOW_FILTER));
        
       }
    
    }
}

/**
 * Sets the highest two bits of each pixel's colors to the lowest two bits of each pixel's colors
 */
public void reveal(){
    Pixel leftPixel = null;
    
    Pixel[][] pixels = this.getPixels2D();
    
    Color leftColor = null;
    
    
    for (int row = 0; row < pixels.length;row++)
    {
      for (int col = 0; col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];   
        
        leftColor = leftPixel.getColor();
      
        leftPixel.setColor(new Color(HIGH_FILTER * (leftColor.getRed() % LOW_FILTER),
               HIGH_FILTER * (leftColor.getGreen() % LOW_FILTER),
               HIGH_FILTER * (leftColor.getBlue() % LOW_FILTER)));
        
    }
    
  }
}

public static boolean isSame(Picture pic1, Picture pic2)
{
   Pixel[][] pixels = pic1.getPixels2D();
   Pixel[][] otherPixels = pic2.getPixels2D();
   Pixel pixel = null;
   Pixel otherPixel = null;
   
   if(pic1.getWidth() != pic2.getWidth() || pic1.getHeight() != pic2.getHeight())
      return false;
   for (int row = 0; row < pixels.length;row++)
    {
      for (int col = 0; col < pixels[0].length; col++)
      {
        pixel = pixels[row][col]; 
        otherPixel = otherPixels[row][col]; 
        //if (!leftPixel.getColor().equals(otherPixel.getColor()))
          // return false;
        if(pixel.getRed() != otherPixel.getRed() ||
           pixel.getGreen() != otherPixel.getGreen() ||
           pixel.getBlue() != otherPixel.getBlue())
           return false;
      }
    }
   return true;
}

//pre-condition - pictures are the same size, returns empty list if they are not the same size
public static ArrayList<Point> findDifferences(Picture pic1, Picture pic2)
{
   ArrayList<Point> list = new ArrayList<Point>();
   Pixel[][] pixels = pic1.getPixels2D();
   Pixel[][] otherPixels = pic2.getPixels2D();
   Pixel pixel = null;
   Pixel otherPixel = null;
   
   if(pic1.getWidth() !=pic2.getWidth() || pic1.getHeight() != pic2.getHeight())
      return list;
   for (int row = 0; row < pixels.length;row++)
   {
      for (int col = 0; col < pixels[0].length; col++)
      {
        pixel = pixels[row][col]; 
        otherPixel = otherPixels[row][col]; 
        
        //if (!pixel.getColor().equals(otherPixel.getColor()))
          // list.add(new Point(row, col);
          
        if(pixel.getRed() != otherPixel.getRed() 
             || pixel.getGreen() != otherPixel.getGreen() 
               || pixel.getBlue() != otherPixel.getBlue())
        {
           list.add(new Point(row, col));
        }
      }
    }
    return list;
}

//pre-condtion - all point in Arraylist are on the picture calling this method.
public static Picture colorDifference(Picture pic, ArrayList<Point> myPoints)
{
   Picture result = new Picture(pic);
   Pixel pixel = null;
   for(Point p: myPoints)
   { 
      pixel = result.getPixel(p.getCol(), p.getRow());
      pixel.setColor(Color.magenta); 
   }
   return result;
}

// pre-condition all of myPoints are on the Picture pic
// pre-condition - myPoints contains at least two points, otherwise what is the point
public static Picture showDifferentArea (Picture pic, ArrayList<Point> myPoints)
{
   Picture result = new Picture(pic);
   //set starting points so that first Point examined changes it, could also set to myPoints.get(0).getX()
   //but that assumes myPoints is not empty, which a pre-condition
   int minRow = pic.getHeight()-1, minCol = pic.getWidth() - 1;  // students may forget to subtract 1, 
   int maxRow = 0, maxCol = 0;
   // find the upper left and lower right point in the arraylist myPoints
   for(Point p: myPoints)
   {
      int row = p. getRow();
      int col = p.getCol();
      if(row < minRow)
         minRow = row;
      if(row > maxRow)
         maxRow = row;
      if(col < minCol)
         minCol = col;
      if(col > maxCol)
        maxCol = col;
   }
   Pixel pixel = null;
   //color top and bottom of bounding rectangle
   for(int col = minCol; col <= maxCol; col++)
   {
      pixel = result.getPixel(col, minRow);
      pixel.setColor(Color.red);
      pixel = result.getPixel(col, maxRow);
      pixel.setColor(Color.red);
   }
   //color sides of bounding rectangle
   for(int row = minRow + 1; row < maxRow; row++)
   {
      pixel = result.getPixel(minCol,row);
      pixel.setColor(Color.red);
      pixel = result.getPixel(maxCol, row);
      pixel.setColor(Color.red);
   }
    /*
   Outside of AP subset, but easy way to accomplish the task
   int width = maxCol - minCol;
   int height = maxRow - minRow;   
   Graphics g = result.getGraphics();
   g.setColor(Color.red);
   g.drawRect(minCol, minRow, width, height);
   */
   return result;
}

 
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    Picture beach2 = new Picture("beach.jpg");  //unaltered beach pic
    Picture swan = new Picture("swan.jpg");
    Picture swan2 = new Picture("swan.jpg");
    //Picture robot = new Picture("robot.jpg");
    //Picture flower1 = new Picture("flower1.jpg");
    //Picture flower2 = new Picture("flower2.jpg");
    System.out.println("Beach and swan: " + isSame(beach, swan));
    System.out.println("Swan and swan2: " + isSame(swan, swan2));
    //swan.clearLow();
    System.out.println("Swan and swan2 after clearLow on swan: " + isSame(swan, swan2));
    ArrayList<Point> pointList = findDifferences(swan2, swan2);
    System.out.println("PointList has a size of " + pointList.size());
    for(int i = 0; i < pointList.size(); i++)
      System.out.println(pointList.get(i));
  // these lines hide and revel a hidden pic
    beach.explore();
    swan.explore();
    beach.hide(swan);
    beach.explore();
    beach.unhide();
    beach.explore();


   // swan.explore();
    //beach.hide(robot, 65, 208);
    //beach.hide(flower1, 280, 110);
    //beach.hide(flower2, 322, 432);
    //beach.explore();
    pointList = findDifferences(beach2, beach);
    System.out.println("PointList has a size of " + pointList.size());
   
    //Picture beach3 = colorDifference(beach, pointList);
    //beach3.show();
    //Picture beach4 = showDifferentArea(beach, pointList);
    //beach.explore();
    //beach4.show();
    //beach.explore();
    //beach.unhide();
    //beach.explore();
        
   /* // these next lines call Activity 1 methods
    beach.explore();
    beach.clearLow();
    beach.explore();
    beach.setLow(Color.GREEN);
    beach.explore();
    beach.reveal();
    beach.explore();*/
  }
  
} // this } is the end of class Picture, put all new methods before this
