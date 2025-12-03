# ImageApp Project - PLTW CSA 3.3.4

## Project Overview
This project implements image manipulation algorithms using 2D arrays and matrix multiplication for transformations. The application demonstrates:
- Color manipulation (recoloring, negative, grayscale)
- Image rotation using matrix multiplication (180°, 90° CCW, 270° CCW)
- Image composition (embedding smaller images with background removal)

## Project Structure

### Core Files
- **ImageApp.java** - Main application with all image transformations
- **Vector1by2.java** - 1x2 vector class for coordinate transformations with unit tests
- **Matrix2by2.java** - 2x2 matrix class for rotation transformations with unit tests

### Supporting Files
- Picture.java, Pixel.java, SimplePicture.java - Image manipulation framework
- PictureExplorer.java, PictureFrame.java, ImageDisplay.java - UI components
- ColorChooser.java, FileChooser.java - Utility classes

## Features Implemented

### Part A: Image Manipulation - Color (15 Points)
1. **Reorder RGB values** (+5 points)
   - Changes color by reordering RGB to BRG (Blue, Red, Green)
   
2. **Photographic Negative** (+5 points)
   - Creates negative by subtracting each RGB value from 255
   
3. **Grayscale Conversion** (+5 points)
   - Converts to grayscale by averaging RGB values

### Part B: Image Manipulation - Orientation (15 Points)
Uses Matrix Multiplication with Vector1by2 and Matrix2by2 classes:

1. **180° Rotation** (+5 points)
   - Matrix: [[-1, 0], [0, -1]]
   
2. **90° Counterclockwise Rotation** (+5 points)
   - Matrix: [[0, -1], [1, 0]]
   
3. **270° Counterclockwise Rotation (90° Clockwise)** (+5 points)
   - Matrix: [[0, 1], [-1, 0]]

### Part C: Combining Images (15 Points)
1. **Embed Smaller Image** (+15 points)
   - Pastes small image onto large image
   - Removes white background (threshold < 250 for RGB values)

## Matrix Multiplication Implementation

### Vector1by2 Class
- Represents a 1x2 vector for coordinate transformations
- **Methods:**
  - `dot(Vector1by2, Vector1by2)` - Computes dot product
  - `multiply(Vector1by2, Matrix2by2)` - Vector-matrix multiplication
  - `runUnitTests()` - Comprehensive unit tests

### Matrix2by2 Class
- Represents a 2x2 transformation matrix
- **Static Factory Methods:**
  - `rotation90()` - 90° CCW rotation matrix
  - `rotation180()` - 180° rotation matrix
  - `rotation270()` - 270° CCW rotation matrix
  - `identity()` - Identity matrix
- **Methods:**
  - `multiply(Matrix2by2, Matrix2by2)` - Matrix-matrix multiplication
  - `runUnitTests()` - Comprehensive unit tests

## How to Run

### Prerequisites
1. Ensure you have Java Development Kit (JDK) installed
2. Place image files in appropriate directories:
   - Main images in `lib/` folder (e.g., beach.jpg)
   - Small images in `lib/` folder (e.g., butterfly1.jpg)

### Execution
```bash
javac ImageApp.java
java ImageApp
```

### Expected Output
1. Unit test results for Vector1by2 and Matrix2by2 classes
2. Seven image windows will open sequentially:
   - Original image
   - Recolored image (BRG order)
   - Negative image
   - Grayscale image
   - 180° rotated image
   - 90° CCW rotated image
   - 270° CCW rotated image
   - Final composite image with embedded small image

## 2D Array Algorithms Used

### Color Manipulation
```java
for (int row = 0; row < pixels.length; row++) {
    for (int col = 0; col < pixels[0].length; col++) {
        // Process each pixel
    }
}
```

### Rotation with Matrix Multiplication
```java
// Transform coordinates relative to center
Vector1by2 original = new Vector1by2(col, row);
Vector1by2 rotated = Vector1by2.multiply(original, rotationMatrix);
// Apply rotated coordinates
```

### Image Composition
```java
// Iterate through small image and paste onto large image
for (int row = 0; row < smallPixels.length; row++) {
    for (int col = 0; col < smallPixels[0].length; col++) {
        // Copy pixel if not white background
    }
}
```

## Scoring Guidelines Compliance

### Requirements Met ✓
- Shows 7 images (3 color manipulated, 3 rotated, 1 combined)
- Uses 2D array algorithms throughout
- Implements matrix multiplication for rotations
- Includes comprehensive unit tests

### No Penalties
- No extraneous code with side effects
- No errors in execution
- Clean, well-documented code

## Testing

### Unit Tests
Run automatically when executing ImageApp:
- Vector1by2 dot product tests
- Vector1by2 multiplication tests
- Matrix2by2 constructor tests
- Matrix2by2 rotation matrix tests
- Matrix2by2 multiplication tests
- Vector-Matrix multiplication integration tests

All tests include assertions and print success messages.

## Image Files

The program expects images in the `lib/` folder. You can use:
- Any .jpg or .png files for main transformations
- Smaller images for composition (e.g., butterfly1.jpg, flower1.jpg)

Modify the file paths in ImageApp.java to use different images:
```java
String pictureFile = "lib/beach.jpg";
String smallImageFile = "lib/butterfly1.jpg";
```

## Authors
- Kazi Sohail Agarwal Divyansh

## Course
PLTW Computer Science A - Project 3.3.4
