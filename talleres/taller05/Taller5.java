/**
 * La clase Taller5 tiene la intención de resolver el taller número 5.
 *
 * @author Mauricio Toro, Andres Paez
 * @version 1
 * 
 * NOTA: La complejidad de cada ejercicio está especificada en los comentarios
 */
public class Taller5 {  

    /**
     * @param array es una arreglo de numeros enteros.
     * El método suma tiene la intención de hacer el proceso de suma
     * mediante una funcion cíclica (while/for/...)
     * @return la suma de todos los numeros sumados.
     */
    public static int suma (int[]array){
        int acum = 0; // T2(n)=c1
        for (int i = 0; i < array.length; i++) // T3(n)=c2+c3*n
            acum = acum + array[i]; // T4(n)=c4*n
        return acum; // T5(n)=c5
        /* T(n)= c1 + c2+c3*n + c4*n + c5
         * Suma --> O(c4*n)
         * Producto --> T(n) es O(n)
         */
    }

    /**
     * @param num es el numero el cual se utiliza para ser multiplicado.
     * El método mul tiene la intención de hacer la multiplicación
     * de 1 a n por el numero mul
     * mediante una funcion cíclica (while/for/...)
     * 
     */
    public static void mul (int num){
        for(int i=1;i<=10;i++) // T2(n)=c1(n+1)+c2
            System.out.println(num+" x "+i+" = "+(num*i)); //T3(n)=c3(n+1)
            /*
             * T(n)= c1(n+1)+c2+c3(n+1)
             * Suma --> O(c3(n+1))
             *  Producto --> T(n) es O(n+1)
             */
    }

    /**
     * @param array es un arreglo de números desordenados
     * El método insertionSort tiene la intención ordenar los números
     * del arreglo array por el método insertion:
     * @see <a href="https://www.youtube.com/watch?v=OGzPmgsI-pQ"> Insertion Sort <a/>
     * mediante la anidación de funciones cíclicas (while/for/...)
     * 
     */
    public static int[] insertionSort (int[] array){
        for(int i=0; i<array.length; i++){ // T2(n)= c1+c2*n
            for(int j=i; j>0; j--){ // T3(n)= c3+c4*n
                if (array[j]<array[j-1]){ //T4(n)=c5*n*n
                    int temp=array[j]; // T4(n)=c6*n*n
                    array[j]=array[j-1]; //T5(n)=c7*n*n
                    array[j-1]=temp; //T6(n)=c8*n*n
                }
            }
        }
        return array; //T7(n)=c9
        /* T(n)= c1+c2*n + c3+c4*n+ c5*n*n+ c5*n*n+ c6*n*n+ c7*n*n+ c8*n*n
         * Suma --> O(c8*n*n)
         * Producto --> T(n) es O(n^2)
         */
    }    
}
