
 public class Array2 {

    public int countEvens(int[] nums) {
        int count = 0;//        C
        for (int n : nums) {//  n
            if (n % 2 == 0) {// n
                count++;    //  n
            }
        }
        return count;       //C
        //Complejidad O(n)
    }

    public int[] post4(int[] nums) {
        int[] array; // c1
        int i=nums.length-1; //c2
        while(nums[i]!=4) i--; //c3*n
        array=new int[nums.length-1-i]; //
        for (int j=i+1; j<nums.length; j++) array[j-i-1]=nums[j]; //c4*n
        return array; //c5
        /*
         * T(n)= c1+c2+(c3+c4)n+c5
         * T(n) es O(c1+c2+(c3+c4)n+c5)
         * T(n) es O((c3+c4)n)
         * T(n) es O(n)
         */
    }

    public boolean only14(int[] nums) {
        boolean var=true; //c1
        for(int i=0; i<nums.length; i++){ //c2*n
            if(nums[i]!=1 && nums[i]!=4){ //c3*n
                var= false; //c4*n
            } 

        }
        return var; //c5
        /*
         * T(n)= c1+(c2+c3+c4)n+c5
         * T(n) es O(c1+(c2+c3+c4)n+c5)
         * T(n) es O((c2+c3+c4)n)
         * T(n) es O(n)
         */
    }

     public String[] fizzArray2(int n) {
        String[] array=new String[n]; //c1
        for(int i=0; i<n; i++){ //c2*n
            array[i]=String.valueOf(i); //c3*n
        }
        return array; //c4
        /* T(n)= c1+(c2+c3)n+c4
         * T(n) es O(c1+(c2+c3)n+c4)
         * T(n) es O((c2+c3)n)
         * T(n) es O(n)
         *
         */
    }

    public static boolean has12(int[] nums) {
        boolean has1=false; //c1
        boolean has2=false; //c2
        for(int i=0; i<nums.length; i++){ //c3*n
         if(nums[i]==1){ //c4*n
            has1=true; //c5*n
         }
         if(has1 && nums[i]==2){ //c6*n
                has2=true; //c7*n
                }
         if(has1 && has2){ //c8*n
             return true; //c9*n
            }
        }
        return false; //c10
        /* T(n)= c1+c2+(c3+c4+c5+c6+c7+c8+c9)n+c4
         * T(n) es O(c1+c2+(c3+c4+c5+c6+c7+c8+c9)n+c4)
         * T(n) es O((c3+c4+c5+c6+c7+c8+c9)n)
         * T(n) es O(n)
         */
    }
}