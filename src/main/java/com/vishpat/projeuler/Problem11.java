package com.vishpat.projeuler;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by vpati011 on 11/29/16.
 */
public class Problem11 {
    private final static ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
    private final static String dataFile = "/home/vpati011/tmp/problem11.dat";

    private final static int ROWS = 20;
    private final static int COLUMNS = 20;

    public Problem11() {

    }

    public static void fillArray(ArrayList<ArrayList<Integer>> matrix) {
        try (
                InputStream fis = new FileInputStream(dataFile);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<Integer> lineArray = new ArrayList<>();
                Arrays.asList(line.split(" ")).forEach(x -> {
                    lineArray.add(Integer.parseInt(x));
                });
                matrix.add(lineArray);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getCellValue(int row, int column) {
        if (row < 0 || row >= ROWS || column < 0 || column >= COLUMNS) {
            return 0;
        }

        ArrayList<Integer> rowMat = matrix.get(row);
        return rowMat.get(column);
    }


    public static void solve() {

            fillArray(matrix);

            int maxProduct = -1;
            for (int m = 0; m < ROWS; m++ ){
                for (int n = 0; n < COLUMNS; n++) {
                    int cellVal = getCellValue(m, n);
                    if (cellVal == 0) {
                        continue;
                    }

                    int product = getCellValue(m, n) * getCellValue(m - 1, n) * getCellValue(m - 2, n) * getCellValue(m - 3, n);
                    if (product > maxProduct) {
                        maxProduct = product;
                    }

                    product = getCellValue(m, n) * getCellValue(m + 1, n) * getCellValue(m + 2, n) * getCellValue(m + 3, n);
                    if (product > maxProduct) {
                        maxProduct = product;
                    }

                    product = getCellValue(m, n) * getCellValue(m, n - 1) * getCellValue(m, n - 2) * getCellValue(m, n - 3);
                    if (product > maxProduct) {
                        maxProduct = product;
                    }

                    product = getCellValue(m, n) * getCellValue(m, n + 1) * getCellValue(m, n + 2) * getCellValue(m, n + 3);
                    if (product > maxProduct) {
                        maxProduct = product;
                    }

                    product = getCellValue(m, n) * getCellValue(m + 1, n - 1) * getCellValue(m + 2, n - 2) * getCellValue(m + 3, n - 3);
                    if (product > maxProduct) {
                        maxProduct = product;
                    }

                    product = getCellValue(m, n) * getCellValue(m - 1, n + 1) * getCellValue(m - 2, n + 2) * getCellValue(m - 3 , n + 3);
                    if (product > maxProduct) {
                        maxProduct = product;
                    }

                }
            }
        System.out.println(maxProduct);
    }
}