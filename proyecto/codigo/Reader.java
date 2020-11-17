import java.io.*;
import java.util.*;
/**
 * The reader and main class of the program.
 * The actual file reader was taken form StackOverflow at https://stackoverflow.com/questions/35791418/java-read-csv-file-as-matrix
 * @authors Santiago Gonzalez Rodriguez, Mariana Vasquez Escobar, Andres Echeverri, Esteban Echeverri
 */
public class Reader {
    Node root;
    /**
     * Main method of the program
     */
    public void main (String args[]) throws FileNotFoundException{
        //Start of the training process
        long startTrain=System.currentTimeMillis();
        List<String[]> rowList = new ArrayList<String[]>();
        try (BufferedReader br = new BufferedReader(new FileReader("4_train_balanced_135000.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineItems = line.split(",");
                rowList.add(lineItems);
            }
            br.close();
        }
        catch(Exception e){
            System.out.print("Error: File Not Found");
        }
        String[][] matrix = new String[rowList.size()][];
        for (int i = 0; i < rowList.size(); i++) {
            String[] row = rowList.get(i);
            matrix[i] = row;
        }
        //Creation of the forest, courtesy of Esteban Echeverri
        ArrayList<Node> forest= new ArrayList<Node>();
        int spacer=500;
        String[][] submatrix;
        for(int i =0; i<matrix.length;i+=700){
            if(i+spacer>matrix.length){
                spacer=matrix.length-i;
            }
            submatrix= new String[spacer][];
            for(int j=0;j<spacer;j++){
                submatrix[j]=matrix[i+j];
            }
            forest.add(new Node(submatrix));
        }
        long endTrain=System.currentTimeMillis();
        //End of the training process
        long totalTrain=endTrain-startTrain;

        //Start if the testing process
        long startTest=System.currentTimeMillis();
        List<String[]> rowList2 = new ArrayList<String[]>();
        try (BufferedReader br2 = new BufferedReader(new FileReader("4_test_balanced_45000.csv"))) {
            String line;
            while ((line = br2.readLine()) != null) {
                String[] lineItems2 = line.split(",");
                rowList2.add(lineItems2);
            }
            br2.close();
        }
        catch(Exception e){
            System.out.println("Error: File Not Found");
        }
        String[][] matrix2 = new String[rowList2.size()][];
        for (int i = 0; i < rowList2.size(); i++) {
            String[] row2 = rowList2.get(i);
            matrix2[i] = row2;
        }    

        int pass=0;
        int didNotPass=0;
        boolean found=true;
        for(int i = 0; i < matrix2.length; i++){
            if(test(forest,matrix2[i])){
                pass++;
            }
            else {
                didNotPass++;
            }
        }
        long endTest=System.currentTimeMillis();
        //End of the ending process
        long totalTest=endTest-startTest;

        System.out.println("Passed: "+pass+"\r\n Didn't pass: "+didNotPass+"\r\n Training time: "+totalTrain+"\r\n Testing time: "+totalTest);
    }

    /**
     * This is a method for testing students success in the testing phase
     * @param forest The forest of decision trees, student The student that is beeing tested
     */
    public boolean test(ArrayList<Node> forest, String[] student){
        int yes = 0;
        int no = 0;
        for(Node tree: forest){
            if(Node.testStudent(tree,student)){
                yes++;
            }
            else {
                no++;
            }
        }
        if(yes > no){
            return true;
        }
        return false;
    }
}