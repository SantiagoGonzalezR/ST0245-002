import java.util.*;
public class TecladoRoto{
    //Punto 2.1
    public static void teclado(String a){
        LinkedList<Character>texto = new LinkedList<>();
        for (int i=0; i<a.length(); i++){
            switch (a.charAt(i)) {
                case '[':
                    int j=0;
                    while (a.charAt(i)!=']'){
                        texto.add(j, a.charAt(i));
                        if (a.charAt(i+1)=='[' && i+1<a.length()) break;
                        i++;
                        j++;
                    } 
                    texto.removeFirstOccurrence('[');
                    break;
                case ']':
                    while (a.charAt(i)!='['){
                        texto.addLast(a.charAt(i));
                        break;
                    } texto.removeFirstOccurrence(']');
                     
                    break;
                default:
                    texto.add(a.charAt(i));
                    break;
            }
        }
        System.out.println(texto);
    }
}