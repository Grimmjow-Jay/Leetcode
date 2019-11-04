package arrayAndString;

public class DiagonalTraverse {

    public static void main(String[] args) {
        int m = 5;
        int n = 4;
        int x = 1;
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            int[] ints = new int[n];
            for (int j = 0; j < ints.length; j++) {
                ints[j] = x++;
            }
            matrix[i] = ints;
        }
        int[] diagonalOrder = findDiagonalOrder(matrix);
        for (int i : diagonalOrder) {
            System.out.print(i + ", ");
        }
    }

    private static int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] result = new int[m * n];
        int i = 0;
        int j = 0;

        boolean toRight = true;
        int x = 0;
        while (i < m && j < n) {
            result[x++] = matrix[i][j];
            if (i == m - 1 && j == n - 1) {
                break;
            }
            if (toRight) {
                if (i == 0) {
                    if (j == n - 1) {
                        i++;
                    } else {
                        j++;
                    }
                } else if (j == n - 1) {
                    i++;
                } else {
                    i--;
                    j++;
                }
            } else {
                if (j == 0) {
                    if (i == m - 1) {
                        j++;
                    } else {
                        i++;
                    }
                } else if (i == m -1) {
                    j++;
                } else {
                    i++;
                    j--;
                }
            }
            toRight = (i + j) % 2 == 0;
        }
        return result;
    }
}
