/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
 
 import java.util.ArrayList;
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  public static void steganographyActivity3()
  {
    Picture beach = new Picture("beach.jpg");
    Picture beach2 = new Picture("beach.jpg");  //unaltered beach pic
    Picture robot = new Picture("robot.jpg");
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
 

    beach.explore();
    //robot.explore();
    //flower1.explore();
    //flower2.explore();

   // swan.explore();
    // these lines hide 3 pictures 
    beach.hide(robot, 65, 208);
    beach.hide(flower1, 280, 110);
    beach.hide(flower2, 322, 432);
    beach.explore();
    ArrayList<Point> pointList = Picture.findDifferences(beach2, beach);
    System.out.println("PointList has a size of " + pointList.size());
    Picture beach3 = Picture.colorDifference(beach, pointList);
    beach3.show();
    Picture beach4 = Picture.showDifferentArea(beach, pointList);
    beach.explore();
    beach4.show();
    
    beach.unhide();
    beach.explore();

  }
  
  public static void stegTestShowDifferentArea()
  {
    Picture hall = new Picture("femaleLionAndHall.jpg");
    Picture hall2 = new Picture(hall);     //unaltered pic for isSame()
    Picture robot = new Picture("robot.jpg");
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    // hide pictures
    hall.hide(robot, 50, 300);
    hall.hide(flower1, 115, 275);
    hall.hide(flower2, 180, 275);
    hall.explore();
    if(!Picture.isSame(hall, hall2))
    {
      Picture hall3 = Picture.showDifferentArea(hall, Picture.findDifferences(hall, hall2));
      hall3.show();
      hall.unhide();
      hall.show();
    }
  }
  
  public static void stegTestIsSame()
  {
    Picture swan = new Picture("swan.jpg");
    Picture swan2 = new Picture(swan);
    System.out.println("Swan and swan2 are the same: " + Picture.isSame(swan, swan2));
    swan.clearLow();
    System.out.println("Swan and swan2 are the same (after clearLow run on swan): " 
                         + Picture.isSame(swan, swan2));
  }
  
  public static void stegTestHideMultiplePics()
  {   
    Picture beach = new Picture("beach.jpg");
    Picture robot = new Picture("robot.jpg");
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    beach.explore();
    
    // these lines hide 3 pictures 
    beach.hide(robot, 65, 208);
    beach.hide(flower1, 280, 110);
    beach.hide(flower2, 322, 432);
    beach.explore();
    
    beach.unhide();
    beach.explore();
 }
 
 public static void stegTestFindDifferences()
 {
    Picture arch = new Picture("arch.jpg");
    Picture arch2 = new Picture("arch.jpg");
    Picture koala = new Picture("koala.jpg");
    Picture robot = new Picture("robot.jpg");
    ArrayList<Point> pointList = Picture.findDifferences(arch, arch2);
    System.out.println("PointList after comparing two identical pictures has a size of " + pointList.size());
    pointList = Picture.findDifferences(arch, koala);
    System.out.println("PointList after comparing two different sized pictures has a size of " + pointList.size());
    arch.hide(robot);
    pointList = Picture.findDifferences(arch, arch2);
    System.out.println("Pointlist after hiding a picture has a size of  "  + pointList.size());
    Picture arch3 = Picture.colorDifference(arch2, pointList);
    arch.show();
    arch2.show();
    arch3.show();
 }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
    //steganographyActivity3();
    //stegTestIsSame();
    stegTestHideMultiplePics();
    //stegTestFindDifferences();
    //stegTestShowDifferentArea();
  }
}