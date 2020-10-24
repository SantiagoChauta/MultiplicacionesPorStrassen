/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strassen;

public class Strassen {

    public static void main(String[] args) {
        int MatrizA[][] = {{4,5,1,4,5,0,0,0},{3,4,0,5,7,0,0,0},{4,2,3,4,5,0,0,0},{1,2,3,4,3,0,0,0},{4,5,4,2,3,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
        int MatrizB[][] = {{2,3,4,5,6,0,0,0},{6,7,5,5,6,0,0,0},{3,4,5,6,5,0,0,0},{1,3,4,5,6,0,0,0},{4,1,1,4,2,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
         
        /*int MatrizA[][] = {{1, 2, 1, 2}, {4, 4, 5, 6}, {6, 5, 3, 7}, {7, 8, 9, 8}};
        int MatrizB[][] = {{2, 3, 4, 7}, {6, 2, 6, 5}, {5, 1, 7, 1}, {4, 3, 8, 9}};*/

        ImprimirMatriz(MatrizA, "Matriz A");
        ImprimirMatriz(MatrizB, "Matriz B");

        int C[][] = Strassen(MatrizA, MatrizB);
        ImprimirMatriz(C, "Matriz C");
    }

    public static void ImprimirMatriz(int[][] MatrizA, String nombre) {
        System.out.println("");
        System.out.println(nombre);
        for (int i = 0; i < MatrizA.length; i++) {
            for (int j = 0; j < MatrizA.length; j++) {
                System.out.print(MatrizA[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static int[][] Strassen(int mat1[][], int mat2[][]) {
        
        int MatrizA11[][] = subdividir(mat1, 1);
        ImprimirMatriz(MatrizA11, "Matriz A11");
        int MatrizA12[][] = subdividir(mat1, 2);
        ImprimirMatriz(MatrizA12, "Matriz A12");
        int MatrizA21[][] = subdividir(mat1, 3);
        ImprimirMatriz(MatrizA21, "Matriz A21");
        int MatrizA22[][] = subdividir(mat1, 4);
        ImprimirMatriz(MatrizA22, "MAtriz A22");

        int MatrizB11[][] = subdividir(mat2, 1);
        ImprimirMatriz(MatrizB11, "Matriz B11");
        int MatrizB12[][] = subdividir(mat2, 2);
        ImprimirMatriz(MatrizB12, "Matriz B12");
        int MatrizB21[][] = subdividir(mat2, 3);
        ImprimirMatriz(MatrizB21, "Matriz B21");
        int MatrizB22[][] = subdividir(mat2, 4);
        ImprimirMatriz(MatrizB22, "Matriz 22");

        int M1[][] = MultiplicarMatrices(SumarMatrices(MatrizA11, MatrizA22), SumarMatrices(MatrizB11, MatrizB22));
        ImprimirMatriz(M1, "M1");
        int M2[][] = MultiplicarMatrices(SumarMatrices(MatrizA21, MatrizA22), MatrizB11);
        ImprimirMatriz(M2, "M2");
        int M3[][] = MultiplicarMatrices(MatrizA11, RestarMatrices(MatrizB12, MatrizB22));
        ImprimirMatriz(M3, "M3");
        int M4[][] = MultiplicarMatrices(MatrizA22, RestarMatrices(MatrizB21, MatrizB11));
        ImprimirMatriz(M4, "M4");
        int M5[][] = MultiplicarMatrices(SumarMatrices(MatrizA11, MatrizA12), MatrizB22);
        ImprimirMatriz(M5, "M5");
        int M6[][] = MultiplicarMatrices(RestarMatrices(MatrizA21, MatrizA11), SumarMatrices(MatrizB11, MatrizB12));
        ImprimirMatriz(M6, "M6");
        int M7[][] = MultiplicarMatrices(RestarMatrices(MatrizA12, MatrizA22), SumarMatrices(MatrizB21, MatrizB22));
        ImprimirMatriz(M7, "M7");

        int MatrizC11[][] = RestarMatrices(SumarMatrices(M1, M4), RestarMatrices(M5, M7));
        int MatrizC12[][] = SumarMatrices(M3, M5);
        int MatrizC21[][] = SumarMatrices(M2, M4);
        int MatrizC22[][] = SumarMatrices(RestarMatrices(M1, M2), SumarMatrices(M3, M6));

        return Unirpartes(MatrizC11, MatrizC12, MatrizC21, MatrizC22);

    }

    private static int[][] subdividir(int[][] mat1, int region) {
        int longitud = mat1.length / 2;
        int Mat[][] = new int[longitud][longitud];

        switch (region) {
            case 1:
                for (int i = 0; i < longitud; i++) {
                    for (int j = 0; j < longitud; j++) {
                        Mat[i][j] = mat1[i][j];
                    }
                }
                break;
            case 2:
                for (int i = 0; i < longitud; i++) {
                    for (int j = longitud; j < mat1.length; j++) {
                        Mat[i][j - longitud] = mat1[i][j];
                    }
                }
                break;
            case 3:
                for (int i = longitud; i < mat1.length; i++) {
                    for (int j = 0; j < longitud; j++) {
                        Mat[i - longitud][j] = mat1[i][j];
                    }
                }
                break;
            case 4:
                for (int i = longitud; i < mat1.length; i++) {
                    for (int j = longitud; j < mat1.length; j++) {
                        Mat[i - longitud][j - longitud] = mat1[i][j];
                    }
                }
                break;
            default:
                break;
        }
        return Mat;
    }

    private static int[][] SumarMatrices(int[][] MatrizA, int[][] MatrizB) {
        int MatrizSuma[][] = new int[MatrizA.length][MatrizA.length];

        for (int i = 0; i < MatrizA.length; i++) {
            for (int j = 0; j < MatrizA.length; j++) {
                MatrizSuma[i][j] = MatrizA[i][j] + MatrizB[i][j];
            }
        }

        return MatrizSuma;
    }

    private static int[][] MultiplicarMatrices(int[][] MatrizA, int[][] MatrizB) {
        int matrizC[][] = new int[MatrizA.length][MatrizB[0].length];
        for (int i = 0; i < matrizC.length; i++) {
            for (int j = 0; j < matrizC[0].length; j++) {
                matrizC[i][j] = 0;
                for (int k = 0; k < MatrizA[0].length; k++) {
                    matrizC[i][j] = matrizC[i][j] + MatrizA[i][k] * MatrizB[k][j];
                }
            }
        }

        return matrizC;
    }

    private static int[][] RestarMatrices(int[][] MatrizA, int[][] MatrizB) {
        int MatrizResta[][] = new int[MatrizA.length][MatrizA.length];

        for (int i = 0; i < MatrizA.length; i++) {
            for (int j = 0; j < MatrizA.length; j++) {
                MatrizResta[i][j] = MatrizA[i][j] - MatrizB[i][j];
            }
        }

        return MatrizResta;
    }

    private static int[][] Unirpartes(int[][] MatrizC11, int[][] MatrizC12, int[][] MatrizC21, int[][] MatrizC22) {
        int MatrizUnida[][] = new int[MatrizC11.length * 2][MatrizC11.length * 2];

        for (int i = 0; i < MatrizC11.length; i++) {
            for (int j = 0; j < MatrizC11.length; j++) {
                MatrizUnida[i][j] = MatrizC11[i][j];
            }
        }
        for (int i = 0; i < MatrizC11.length; i++) {
            for (int j = MatrizC11.length; j < MatrizUnida.length; j++) {
                MatrizUnida[i][j] = MatrizC12[i][j-MatrizC11.length];
            }
        }
        for (int i = MatrizC11.length; i < MatrizUnida.length; i++) {
            for (int j = 0; j < MatrizC11.length; j++) {
                MatrizUnida[i][j] = MatrizC21[i-MatrizC11.length][j];
            }
        }
        for (int i = MatrizC11.length; i < MatrizUnida.length; i++) {
            for (int j = MatrizC11.length; j < MatrizUnida.length; j++) {
                MatrizUnida[i][j] = MatrizC22[i-MatrizC11.length][j-MatrizC11.length];
            }
        }
        return MatrizUnida;
    }
}
