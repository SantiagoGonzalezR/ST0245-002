/**
 * 
 * @author anietog1, ditrefftzr
 */
public class Recursion1 {
    public int triangle(int rows) {
        if (rows == 0) return 0; //C
        return rows + triangle(rows - 1); //C + T(n-1)
        //MODELO: T(n) =   |C, n=0
        //                 |C + T(n-1), n>0
        //ECUACION DE RECURRENCIA: 
        //          T(n) = C*n + C1
        //CÁLCULO DE COMPLEJIDAD:
        //          O(C*n + C1)
        //          O(C*n)      -> Por regla de la suma
        //          O(n)        -> Por regla de la multiplicación
    }

    public boolean nestParen(String str) {
        if (str.equals("")) return true;
        if (str.charAt(0)=='(' && str.charAt(str.length()-1)==')')
            return nestParen(str.substring(1, str.length()-1));
        return false;
    }

    public int count11(String str) {
        if (str.length()<=1) return 0;
        if (str.substring(0, 2).equals("11")) return 1+count11(str.substring(2));
        return count11(str.substring(1));
    }

    public String endX(String str) {
        if (str.length()==0 || str.length()==1) return str;
        if (str.charAt(0)=='x') return endX(str.substring(1))+'x';
        return str.charAt(0)+endX(str.substring(1));
    }

    private String endXAux(String str, int start) {
        //For the time being, this is just unnecesary
        return endXAux(str, 0);
    }

    public String changePi(String str) {
        if(str.length()<2){
            return str;
        } else if(str.substring(0,2).equals("pi")){
            return "3.14" + changePi(str.substring(2));
        } else return str.charAt(0)+ changePi(str.substring(1));
    }
}