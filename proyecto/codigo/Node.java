import java.util.*;
import javafx.util.*;
/**
 * This class creates both the nodes and the trees for the forest.
 * It also does most of the calculations that are required for the proyect.
 * @authors: Santiago Gonzalez Rodriguez, Mariana Vasquez Escobar, Andres Echeverri, Esteban Echeverri, Mauricio Toro
 */
public class Node{
    Pair <Integer, String> value;
    Node right;
    Node left;
    
    /**
     * Tests the students for success.
     * @param tree The trees that predict the student success. student The data for each student.
     */
    public static boolean testStudent(Node tree, String[] student){
        Integer key = tree.value.getKey();
        String value = tree.value.getValue();
        if(key == 10){
            if(value.equals(student[key])){
                return true;
            }
            else {
                return false;
            }
        }
        else{
            if(student[key].equals(value)){
                return testStudent(tree.right,student);
            }
            else{
                return testStudent(tree.left,student);
            }
        }
    }

    /**
     * Constructor of the class Node
     * @param m Matrix that possesses all the data
     * Reference for Pair href:https://docs.oracle.com/javase/8/javafx/api/javafx/util/Pair.html
     */
    public Node(String [][] m){
        value=bestVariable(m);
        Pair<String[][],String[][]> matrixPair =  divideMatrix(m, value.getKey(), value.getValue());
        String[][] lft=matrixPair.getValue();
        String[][] rght=matrixPair.getKey();
        if(lft.length!=0&&rght.length!=0){
            if(same(lft)){
                left = new Node(new Pair(10,lft[0][10]));
            }
            else{
                left=new Node(lft);
            }
            if(same(rght)){
                right = new Node(new Pair(10,lft[0][10]));
            }
            else{
                right=new Node(rght);
            }
        }
    }

    /**
     * Verifies if the data is the same
     * @param m Matrix that possesses all the data
     */
    public boolean same(String [][] m){
        String value = m[0][10];
        for(int i = 1; i<m.length;i++){
            if(!m[i][10].equals(value)){
                return false;
            }
        }
        return true;
    }

    /**
     * Constructor of Node
     * @param v Pair of data for the nodes
     */
    public Node(Pair <Integer, String> v){
        value = v;
    }

    /**
     * Uses TreeSet to prune repeated data.
     * @param m The matrix that contains the data, posVariable Position of the variable that is to be pruned
     * Reference for TreeSet href:https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/TreeSet.html
     */
    public TreeSet<String> pruneRepeatedData(String[][] m, int posVariable){
        TreeSet<String> total = new TreeSet();
        for (int fila = 0; fila < m.length; fila++){
            total.add(m[fila][posVariable]);
        }
        return total;
    }

    /**
     * Finds the best posible data for declaring the success of a student
     * @param m The matrix that contains the data
     * Reference for TreeSet href:https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/TreeSet.html
     * Reference for Pair href:https://docs.oracle.com/javase/8/javafx/api/javafx/util/Pair.html
     */
    public  Pair<Integer,String> bestVariable(String[][] m){
        float lowestImpurity = 1;
        String bestValue = "";
        int bestVariablePosition = -1;
        TreeSet<String> values;
        float poweredImpurity;
        for (int column = 0; column < m[0].length - 1; column++){
            values = pruneRepeatedData(m, column);
            for (String unValor : values){
                poweredImpurity = poweredImpurityCalc(m, column, unValor);
                if (poweredImpurity <= lowestImpurity){
                    lowestImpurity = poweredImpurity;
                    bestValue = unValor;
                    bestVariablePosition = column;
                }
            }
        }
        if(bestVariablePosition == -1){
            System.out.print("");
        }
        Pair<Integer,String> answer = new Pair(bestVariablePosition, bestValue);
        return answer;
    }

    /**
     * Caclculates the impurity of the matrix
     * IG = 1 - (p0)^2 - (p1)^2. P0 = exito/total P1 = NoExito/total (Formula given by Mauricio Toro)
     * @param m Matrix that contains the data
     */
    public float matrixImpurity(String[][] m){
        int successfulOnes = 0;
        for (int row = 0; row < m.length; row++)
            if (m[row][m[0].length-1].equals("1"))
                successfulOnes++;
        int nonSuccessfulOnes = m.length - successfulOnes;
        float successfulProportion=0;
        float nonSuccessuflProportion=0;
        if(m.length!=0){
            successfulProportion = successfulOnes/m.length;
            nonSuccessuflProportion = nonSuccessfulOnes/m.length;
        }
        float impurity = 1-successfulProportion*successfulProportion-nonSuccessuflProportion*nonSuccessuflProportion;
        return impurity;        
    }

    /**
     * Calculates the powered impurity of all the data.
     * @param m The matrix that contains the data, posVariable Position of the variables that will be calculated, 
     * value The value that will be used for the calculation
     */
    public  float poweredImpurityCalc(String[][] m, int posVariable, String value){
        int varEqualsVal= 0;
        for (int fila = 0; fila < m.length; fila++)
            if (value.equals(m[fila][posVariable])) 
                varEqualsVal++;
        int varDifVal = m.length - varEqualsVal;
        String[][] matrixVarEqVal = new String[varEqualsVal][m[0].length];
        String[][] matrixVarNonEqVal= new String[varEqualsVal][m[0].length];
        int row = 0;
        int rowN1 = 0;
        int rowN2=0;
        while(row<m.length) {
            if(value.equals(m[row][posVariable])) {
                matrixVarEqVal[rowN1]=m[row];
                row++;rowN1++;
            }else {
                matrixVarNonEqVal[rowN2]=m[row];
                row++;rowN2++;
            }
        }
        float poweredImpurity=((matrixImpurity(matrixVarEqVal)*matrixVarEqVal.length)+(matrixImpurity(matrixVarNonEqVal)*matrixVarNonEqVal.length))/m.length;
        return poweredImpurity;
    }

    /**
     * Divides tha matrix into 2 for the trees.
     * @param m The matrix that possesses the data, varPos The position of the variable to be moved to either matrix
     * value The value of the data to be interchanged
     * Reference for Pair href:https://docs.oracle.com/javase/8/javafx/api/javafx/util/Pair.html
     */
    public  Pair<String[][],String[][]> divideMatrix(String[][] m, int varPos, String value){
        int varEqVal = 0;
        for (int row = 0; row < m.length; row++){
            if(row==-1||varPos==-1){
                System.out.print("error");
            }
            if (value.equals(m[row][varPos]))
                varEqVal++;
        }
        int varNonEqVal= m.length - varEqVal;
        String[][] matrixVarEqVal = new String[varEqVal][m[0].length];
        String[][] matrixVarNonEqVal= new String[varNonEqVal][m[0].length];
        int row = 0;
        int rowN1 = 0;
        int rowN2=0;
        while(row<m.length) {
            if(value.equals(m[row][varPos]))
            {
                matrixVarEqVal[rowN1]=m[row];
                row++;rowN1++;
            }else {
                matrixVarNonEqVal[rowN2]=m[row];
                row++;rowN2++;
            }
        }
        Pair<String[][],String[][]> matrixPair = new Pair(matrixVarEqVal,matrixVarNonEqVal);
        return matrixPair;
    }
}
