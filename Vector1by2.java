/**
 * Vector1by2 class represents a 1x2 vector used for coordinate transformations.
 * Contains static functions for vector operations including dot product.
 */
public class Vector1by2 {
    private double element1;
    private double element2;
    
    public Vector1by2(double element1, double element2) {
        this.element1 = element1;
        this.element2 = element2;
    }
    
    public double getElement1() {
        return element1;
    }
    
    public double getElement2() {
        return element2;
    }
    
    public void setElement1(double element1) {
        this.element1 = element1;
    }
    
    public void setElement2(double element2) {
        this.element2 = element2;
    }
    
    public static double dot(Vector1by2 v1, Vector1by2 v2) {
        return v1.element1 * v2.element1 + v1.element2 * v2.element2;
    }
    
    public static Vector1by2 multiply(Vector1by2 v, Matrix2by2 m) {
        // do matrix multiplication
        Vector1by2 col1 = new Vector1by2(m.getA(), m.getC());
        Vector1by2 col2 = new Vector1by2(m.getB(), m.getD());
        
        double newElement1 = dot(v, col1);
        double newElement2 = dot(v, col2);
        
        return new Vector1by2(newElement1, newElement2);
    }
    
    /**
     * String representation of the vector
     * @return string in format [element1, element2]
     */
    @Override
    public String toString() {
        return "[" + element1 + ", " + element2 + "]";
    }
    
    /**
     * Unit tests for Vector1by2 class
     */
    public static void runUnitTests() {
        System.out.println("=== Vector1by2 Unit Tests ===");
        
        // Test 1: Constructor and getters
        Vector1by2 v1 = new Vector1by2(3.0, 4.0);
        System.out.println("Test 1 - Constructor: " + v1);
        assert v1.getElement1() == 3.0 : "Element1 should be 3.0";
        assert v1.getElement2() == 4.0 : "Element2 should be 4.0";
        System.out.println("✓ Constructor and getters work correctly");
        
        // Test 2: Setters
        v1.setElement1(5.0);
        v1.setElement2(6.0);
        System.out.println("Test 2 - After setters: " + v1);
        assert v1.getElement1() == 5.0 : "Element1 should be 5.0";
        assert v1.getElement2() == 6.0 : "Element2 should be 6.0";
        System.out.println("✓ Setters work correctly");
        
        // Test 3: Dot product
        Vector1by2 v2 = new Vector1by2(2.0, 3.0);
        Vector1by2 v3 = new Vector1by2(4.0, 5.0);
        double dotProduct = Vector1by2.dot(v2, v3);
        System.out.println("Test 3 - Dot product of " + v2 + " and " + v3 + " = " + dotProduct);
        assert dotProduct == 23.0 : "Dot product should be 23.0 (2*4 + 3*5)";
        System.out.println("✓ Dot product works correctly");
        
        // Test 4: Dot product with zeros
        Vector1by2 v4 = new Vector1by2(0.0, 0.0);
        double dotProduct2 = Vector1by2.dot(v2, v4);
        System.out.println("Test 4 - Dot product with zero vector = " + dotProduct2);
        assert dotProduct2 == 0.0 : "Dot product with zero vector should be 0";
        System.out.println("✓ Dot product with zero vector works correctly");
        
        System.out.println("=== All Vector1by2 tests passed! ===\n");
    }
}
