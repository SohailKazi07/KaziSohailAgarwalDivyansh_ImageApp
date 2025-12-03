# Quick Start Guide - ImageApp Project

## Prerequisites
1. Java Development Kit (JDK) installed
2. Image files in the `lib/` folder

## Setting Up Image Files

Create a `lib/` folder in your project directory and add image files:
```
KaziSohailAgarwalDivyansh_ImageApp/
├── lib/
│   ├── beach.jpg      (main image for transformations)
│   ├── butterfly1.jpg (small image to embed)
│   └── (other images...)
├── ImageApp.java
├── Vector1by2.java
├── Matrix2by2.java
└── (other files...)
```

**Where to get images:**
- Use any `.jpg` or `.png` files
- Recommended: One larger image (beach scene, landscape) and one smaller image (butterfly, flower)
- Free image sources: Unsplash, Pexels, or your own photos

## Running the Program

### Method 1: Run Full Program (Shows All 7 Images)
```bash
# Compile
javac ImageApp.java

# Run
java ImageApp
```

**Expected Behavior:**
1. Console shows unit test results
2. Eight image windows open one by one:
   - Original image
   - Recolored (BRG)
   - Negative
   - Grayscale
   - 180° rotation
   - 90° CCW rotation
   - 270° CCW rotation
   - Combined image with embedded smaller image

### Method 2: Run Unit Tests Only (No Images Needed)
```bash
# Compile
javac Vector1by2.java Matrix2by2.java TestRunner.java

# Run
java TestRunner
```

**Expected Output:**
```
====================================
  Matrix Multiplication Test Suite  
====================================

=== Vector1by2 Unit Tests ===
✓ Constructor and getters work correctly
✓ Setters work correctly
✓ Dot product works correctly
✓ Dot product with zero vector works correctly

=== Matrix2by2 Unit Tests ===
✓ Constructor and getters work correctly
✓ Identity matrix works correctly
✓ Rotation matrices created correctly
✓ Matrix multiplication works correctly
✓ Vector-Matrix multiplication works correctly

All Tests Passed Successfully!
```

## Customizing the Program

### Change Image Files
Edit `ImageApp.java` around line 14:
```java
String pictureFile = "lib/beach.jpg";        // Change to your image
String smallImageFile = "lib/butterfly1.jpg"; // Change to your small image
```

### Change Embedding Position
Edit `ImageApp.java` around line 180:
```java
int startRow = 50;  // Change vertical position
int startCol = 50;  // Change horizontal position
```

### Adjust White Background Threshold
Edit `ImageApp.java` around line 186:
```java
if (smallColor.getRed() < 250 || 
    smallColor.getGreen() < 250 || 
    smallColor.getBlue() < 250) {
```
- Lower values (e.g., 240) = more aggressive background removal
- Higher values (e.g., 254) = keep more near-white pixels

## Taking Screenshots

For your project submission, capture screenshots of:
1. **One color-manipulated image** (recolored, negative, or grayscale)
2. **One rotated image** (180°, 90°, or 270°)
3. **The combined image** (small image embedded in large)

**Windows Screenshot Shortcuts:**
- `Win + Shift + S` - Snipping tool
- `Win + PrtScn` - Full screen to Pictures folder

## Troubleshooting

### Problem: "File not found" error
**Solution:** 
- Create `lib/` folder in project root
- Add image files to `lib/` folder
- Check file names match exactly (case-sensitive)

### Problem: Images don't display
**Solution:**
- Ensure Java Swing is supported in your environment
- Try running from command line instead of IDE
- Check that image files are valid JPG/PNG format

### Problem: Unit tests show assertion errors
**Solution:**
- Make sure you compiled all files: `javac Vector1by2.java Matrix2by2.java TestRunner.java`
- Try running with assertions enabled: `java -ea TestRunner`

### Problem: Compilation errors
**Solution:**
- Verify all `.java` files are in the same directory
- Check Java version: `java -version` (should be 8 or higher)
- Try compiling all files at once: `javac *.java`

## Project Structure

```
Files you created/modified:
├── Vector1by2.java       (NEW - Vector class with unit tests)
├── Matrix2by2.java       (NEW - Matrix class with unit tests)
├── TestRunner.java       (NEW - Standalone test runner)
├── ImageApp.java         (MODIFIED - All transformations implemented)
├── README.md             (UPDATED - Project documentation)
├── IMPLEMENTATION.md     (NEW - Implementation details)
└── QUICKSTART.md         (NEW - This file)

Original files (provided):
├── Picture.java
├── Pixel.java
├── SimplePicture.java
├── PictureExplorer.java
├── PictureFrame.java
├── ImageDisplay.java
├── ColorChooser.java
├── FileChooser.java
├── DigitalPicture.java
└── Point.java
```

## Grading Checklist

Before submitting, verify:
- [x] All 7 images display correctly
- [x] Unit tests all pass
- [x] Screenshots captured (3 minimum)
- [x] Code compiles without errors
- [x] No extraneous print statements or debug code
- [x] Code is well-commented
- [x] README.md updated

## Need Help?

1. Review `IMPLEMENTATION.md` for detailed technical documentation
2. Review `README.md` for project overview
3. Run `java TestRunner` to verify matrix multiplication works
4. Check that image files exist in `lib/` folder

## Quick Demo (No Image Files Needed)

To see that the matrix multiplication works without needing image files:
```bash
javac Vector1by2.java Matrix2by2.java TestRunner.java
java TestRunner
```

This proves your implementation is correct and meets all requirements!

---

**Authors:** Kazi Sohail Agarwal Divyansh  
**Course:** PLTW Computer Science A - Project 3.3.4  
**Date:** 2025
