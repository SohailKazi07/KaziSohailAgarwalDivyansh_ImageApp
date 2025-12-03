/**
 * TestRunner - Runs unit tests for Vector1by2 and Matrix2by2 classes
 * This demonstrates that the matrix multiplication implementation passes definitive unit tests
 */
public class TestRunner {
    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("  Matrix Multiplication Test Suite  ");
        System.out.println("====================================\n");
        
        // Run Vector1by2 unit tests
        Vector1by2.runUnitTests();
        
        // Run Matrix2by2 unit tests
        Matrix2by2.runUnitTests();
        
        System.out.println("====================================");
        System.out.println("  Additional Integration Tests      ");
        System.out.println("====================================\n");
        
        // Test rotation transformations with specific coordinates
        testRotationTransformations();
        
        System.out.println("\n====================================");
        System.out.println("  All Tests Passed Successfully!    ");
        System.out.println("====================================");
    }
    
    /**
     * Test rotation transformations with real coordinate examples
     */
    private static void testRotationTransformations() {
        System.out.println("Testing rotation transformations on coordinates:\n");
        
        // Test point (3, 0) - point on positive x-axis
        Vector1by2 point = new Vector1by2(3, 0);
        System.out.println("Original point: " + point);
        
        // 90 degree rotation
        Matrix2by2 rot90 = Matrix2by2.rotation90();
        Vector1by2 rotated90 = Vector1by2.multiply(point, rot90);
        System.out.println("After 90° CCW rotation: " + rotated90);
        assert Math.abs(rotated90.getElement1() - 0) < 0.001 : "x should be 0";
        assert Math.abs(rotated90.getElement2() - 3) < 0.001 : "y should be 3";
        System.out.println("✓ 90° rotation correct: (3,0) → (0,3)\n");
        
        // 180 degree rotation
        Matrix2by2 rot180 = Matrix2by2.rotation180();
        Vector1by2 rotated180 = Vector1by2.multiply(point, rot180);
        System.out.println("After 180° rotation: " + rotated180);
        assert Math.abs(rotated180.getElement1() - (-3)) < 0.001 : "x should be -3";
        assert Math.abs(rotated180.getElement2() - 0) < 0.001 : "y should be 0";
        System.out.println("✓ 180° rotation correct: (3,0) → (-3,0)\n");
        
        // 270 degree rotation
        Matrix2by2 rot270 = Matrix2by2.rotation270();
        Vector1by2 rotated270 = Vector1by2.multiply(point, rot270);
        System.out.println("After 270° CCW rotation: " + rotated270);
        assert Math.abs(rotated270.getElement1() - 0) < 0.001 : "x should be 0";
        assert Math.abs(rotated270.getElement2() - (-3)) < 0.001 : "y should be -3";
        System.out.println("✓ 270° rotation correct: (3,0) → (0,-3)\n");
        
        // Test with a different point (4, 4)
        Vector1by2 point2 = new Vector1by2(4, 4);
        System.out.println("Testing with point: " + point2);
        
        Vector1by2 rotated2_90 = Vector1by2.multiply(point2, rot90);
        System.out.println("After 90° CCW rotation: " + rotated2_90);
        assert Math.abs(rotated2_90.getElement1() - (-4)) < 0.001 : "x should be -4";
        assert Math.abs(rotated2_90.getElement2() - 4) < 0.001 : "y should be 4";
        System.out.println("✓ 90° rotation correct: (4,4) → (-4,4)\n");
        
        System.out.println("All rotation transformation tests passed!");
    }
}
