package test;

import java.util.Random;

public class Test1 {
    public static void main(String[] args) {
        int[] tempArr={0,1,2,3,4,5,6,7,8};

        Random r =new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index=r.nextInt(tempArr.length);
            int temp=tempArr[index];
            tempArr[index]=tempArr[i];
            tempArr[i]=temp;
        }
//        for (int i = 0; i < tempArr.length; i++) {
//            System.out.println(tempArr[i]);
//        }

        //add numbers into 2d array
        int[][] data=new int[3][3];
        int index=0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {

                data[i][j]=tempArr[index];
                index++;
            }
        }

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                System.out.print(data[i][j]+" ");
            }
            System.out.println();
        }

    }
}
