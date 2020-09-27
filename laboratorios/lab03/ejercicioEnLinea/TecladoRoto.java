import java.util.*;
/**
 * Write a description of class mjhg here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

   
public class TecladoRoto{
    //Punto 2.1
    public static void teclado(String a){
        LinkedList<Character>texto = new LinkedList<>(); //c1
        for (int i=0; i<a.length(); i++){ //c2*n +c3 (n=longitud del String)
            switch (a.charAt(i)) { //c4*n
                case '[': //c5*n
                    int j=0; //c6*n
                    while (a.charAt(i)!=']'){//c7*n*n
                        texto.add(j, a.charAt(i));//c8*n*n
                        if (a.charAt(i+1)=='[' && i+1<a.length())//c9*n*n
                        break;//c10*n*n
                        i++;//c11*n*n
                        j++;//c12*n*n
                    } 
                    texto.removeFirstOccurrence('['); //c13*n
                    break;//c14*n
                case ']': //c15*n
                    while (a.charAt(i)!='['){ //c16*n*n
                        texto.addLast(a.charAt(i)); //c17*n*n
                        break;//c18*n*n
                    } texto.removeFirstOccurrence(']');//c19*n

                    break;//c20*n
                default://c21*n
                    texto.add(a.charAt(i));//c22*n
                    break;//c23*n
            }
        }
        System.out.println(texto);//c24
        /*
         * T(n)= c1+c3+(c2+c4+c5+c6+c13+c14+c15+c20+c21+c22+c23)n+(c8+c9+c10+c11+c12+c16+c17+c18)n^2
         * T(n) es O(c1+c3+(c2+c4+c5+c6+c13+c14+c15+c20+c21+c22+c23)n+(c8+c9+c10+c11+c12+c16+c17+c18)n^2)
         * T(n) es O((c8+c9+c10+c11+c12+c16+c17+c18)n^2)
         * T(n) es O(n^2)
         */
    }
}

