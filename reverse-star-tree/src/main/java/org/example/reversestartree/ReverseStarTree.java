package org.example.reversestartree;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ReverseStarTree {

    public void drawReverseStarTree() {

        try{

            System.out.print("Enter the step number for your star tree: ");
            Scanner in = new Scanner(System.in);
            int treeLevel = in.nextInt();

            System.out.print(buildAndGetReverseStarTree(treeLevel));

        } catch(InputMismatchException inputMismatchException){
            System.out.println("Please enter a value of type int");
        }


    }

    public String buildAndGetReverseStarTree(int treeLevel) {

        if (treeLevel < 0){
            return "Please give zero or a positive value for tree level";
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = treeLevel; i >= 1; i--){
            int space = treeLevel - i;
            for (int j = 0; j < 2* treeLevel - 1; j++){
                if (j <= space - 1 || j >= 2 * treeLevel - 1 - space)
                    stringBuilder.append(" ");
                else
                    stringBuilder.append("*");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

}

