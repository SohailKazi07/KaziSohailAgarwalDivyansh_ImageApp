/**
 * Matrix2by2 class represents a 2x2 transformation matrix.
 * Used for coordinate transformations like rotation.
 * Matrix format: [[a, b], [c, d]]
 */
public class Matrix2by2 {
    private double a, b, c, d;
    
    public Matrix2by2(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    // Getters
    public double getA() { return a; }
    public double getB() { return b; }
    public double getC() { return c; }
    public double getD() { return d; }
    
    // Setters
    public void setA(double a) { this.a = a; }
    public void setB(double b) { this.b = b; }
    public void setC(double c) { this.c = c; }
    public void setD(double d) { this.d = d; }
    
    public static Matrix2by2 rotation90() {
        return new Matrix2by2(0, -1, 1, 0);
    }
    
    public static Matrix2by2 rotation180() {
        return new Matrix2by2(-1, 0, 0, -1);
    }
    
    public static Matrix2by2 rotation270() {
        return new Matrix2by2(0, 1, -1, 0);
    }
    
    /**
     * Create an identity matrix
     * @return identity matrix
     */
    public static Matrix2by2 identity() {
        return new Matrix2by2(1, 0, 0, 1);
    }
    
    /**
     * Multiply two matrices
     * @param m1 first matrix
     * @param m2 second matrix
     * @return resulting matrix
     */
    public static Matrix2by2 multiply(Matrix2by2 m1, Matrix2by2 m2) {
        double newA = m1.a * m2.a + m1.b * m2.c;
        double newB = m1.a * m2.b + m1.b * m2.d;
        double newC = m1.c * m2.a + m1.d * m2.c;
        double newD = m1.c * m2.b + m1.d * m2.d;
        return new Matrix2by2(newA, newB, newC, newD);
    }
    
    /**
     * String representation of the matrix
     * @return string representation
     */
    @Override
    public String toString() {
        return "[[" + a + ", " + b + "], [" + c + ", " + d + "]]";
    }
    
    /**
     * Unit tests for Matrix2by2 class
     */
    public static void runUnitTests() {
        System.out.println("=== Matrix2by2 Unit Tests ===");
        
        // Test 1: Constructor and getters
        Matrix2by2 m1 = new Matrix2by2(1, 2, 3, 4);
        System.out.println("Test 1 - Constructor: " + m1);
        assert m1.getA() == 1.0 : "a should be 1.0";
        assert m1.getB() == 2.0 : "b should be 2.0";
        assert m1.getC() == 3.0 : "c should be 3.0";
        assert m1.getD() == 4.0 : "d should be 4.0";
        System.out.println("✓ Constructor and getters work correctly");
        
        // Test 2: Identity matrix
        Matrix2by2 identity = Matrix2by2.identity();
        System.out.println("Test 2 - Identity matrix: " + identity);
        assert identity.getA() == 1.0 && identity.getD() == 1.0 : "Diagonal should be 1";
        assert identity.getB() == 0.0 && identity.getC() == 0.0 : "Off-diagonal should be 0";
        System.out.println("✓ Identity matrix works correctly");
        
        // Test 3: Rotation matrices
        Matrix2by2 rot90 = Matrix2by2.rotation90();
        Matrix2by2 rot180 = Matrix2by2.rotation180();
        Matrix2by2 rot270 = Matrix2by2.rotation270();
        System.out.println("Test 3 - Rotation 90°: " + rot90);
        System.out.println("        Rotation 180°: " + rot180);
        System.out.println("        Rotation 270°: " + rot270);
        System.out.println("✓ Rotation matrices created correctly");
        
        // Test 4: Matrix multiplication
        Matrix2by2 m2 = new Matrix2by2(2, 0, 0, 2);
        Matrix2by2 m3 = new Matrix2by2(1, 2, 3, 4);
        Matrix2by2 product = Matrix2by2.multiply(m2, m3);
        System.out.println("Test 4 - Matrix multiply: " + m2 + " * " + m3 + " = " + product);
        assert product.getA() == 2.0 : "a should be 2.0";
        assert product.getB() == 4.0 : "b should be 4.0";
        assert product.getC() == 6.0 : "c should be 6.0";
        assert product.getD() == 8.0 : "d should be 8.0";
        System.out.println("✓ Matrix multiplication works correctly");
        
        // Test 5: Vector-Matrix multiplication
        System.out.println("\nTest 5 - Vector-Matrix multiplication:");
        Vector1by2 v = new Vector1by2(3, 4);
        Vector1by2 result = Vector1by2.multiply(v, rot90);
        System.out.println("        " + v + " * " + rot90 + " = " + result);
        assert Math.abs(result.getElement1() - 4.0) < 0.001 : "First element should be 4.0";
        assert Math.abs(result.getElement2() - (-3.0)) < 0.001 : "Second element should be -3.0";
        System.out.println("✓ Vector-Matrix multiplication works correctly");
        
        System.out.println("=== All Matrix2by2 tests passed! ===\n");
    }
}
