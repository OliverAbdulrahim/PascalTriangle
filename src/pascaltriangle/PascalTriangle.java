package pascaltriangle;

/**
 * The {@code PascalTriangle} class contains an implementation of the 
 * mathematical set called the 
 * <a href="https://en.wikipedia.org/wiki/Pascal's_triangle">Pascal 
 * Triangle</a>. Objects of this class are immutable.
 * 
 * @author Oliver Abdulrahim
 */
public final class PascalTriangle {
    
    /**
     * Defines the default triangle depth in the case that one is not provided
     * at construction.
     */
    public static final int DEFAULT_TRIANGLE_DEPTH = 5;
    
    /**
     * Contains the triangle for this object.
     */
    private int[][] matrix;
    
    /**
     * Defines the amount of times to repeat the Pascal triangle algorithm for
     * this object.
     */
    private int depth;
    
    /**
     * Returns an object of this class with the default depth argument.
     * 
     * @return An object of this class with the default depth argument.
     */
    public static PascalTriangle getTriangle() {
        return getTriangle(DEFAULT_TRIANGLE_DEPTH);
    }
    
    /**
     * Returns an object of this class with the specified depth argument.
     * 
     * @param depth The depth argument to use.
     * @return An object of this class with the specified depth argument.
     */
    public static PascalTriangle getTriangle(int depth) {
        return new PascalTriangle(depth);
    }
    
    /**
     * Creates an object of this class using the default depth value.
     */
    private PascalTriangle() {
        this(DEFAULT_TRIANGLE_DEPTH);
    }
    
    /**
     * Creates the set using the specified depth argument.
     *
     * @param depth The number of times to perform the algorithm.
     */
    private PascalTriangle(int depth) {
        this.matrix = new int[depth][];
        this.depth = depth;
        generateTriangle();
    }

    /**
     * Calculates Pascal's Triangle beginning at zero and iteratively working 
     * upward, stopping at {@link #depth} number of iterations.
     */
    private void generateTriangle()  {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new int[i + 1];
            matrix[i][0] = 1;
            matrix[i][i] = 1;
            for (int j = 1; j < i; j++) {
                matrix[i][j] = matrix[i - 1][j] + matrix[i - 1][j - 1];
            }
        }
    }
    
    /**
     * Generates and returns a formatted {@code String} containing the Pascal 
     * triangle of this instance formatted to the specified argument.
     * 
     * @param addSpaces Specifies whether or not to format the triangle using 
     *         spaces: {@code true} to add spaces between each element of the 
     *         set, {@code false} to not.
     * @return A formatted {@code String} representing the contents of this 
     *         instance depending on the specified argument.
     */
    public String buildTriangle(boolean addSpaces) {
        StringBuilder triangle = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            if (addSpaces) {
                for (int j = depth - i; j > 0; j--) {
                    triangle.append(" ");
                }
            }
            for (int j = 0; j < matrix[i].length; j++) {
                triangle.append(" ").append(matrix[i][j]);
            }
            triangle.append("\n");
        }
        return triangle.toString();
    }
    
    /**
     * Generates and returns a formatted {@code String} containing the Pascal 
     * triangle of this instance. 
     * 
     * <p> This implementation automatically adds spaces between each element in
     * the set. To avoid this, the following method call should be made:
     * 
     * <blockquote><pre>
     * new PascalTriangle().buildTriangle(false);</pre>
     * </blockquote>
     * 
     * @return A formatted {@code String} that represents this set.
     */
    @Override
    public String toString() {
        return buildTriangle(true);
    }
    
}
