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
        int[] array;
        int i=nums.length-1;
        while(nums[i]!=4) i--;
        array=new int[nums.length-1-i];
        for (int j=i+1; j<nums.length; j++) array[j-i-1]=nums[j];
        return array;
    }

    public boolean only14(int[] nums) {
        boolean var=true;
        for(int i=0; i<nums.length; i++){
            if(nums[i]!=1 && nums[i]!=4){
                var= false;
            } 

        }
        return var;
    }

    public String[] fizzArray2(int n) {
        String[] array=new String[n];
        for(int i=0; i<n; i++){
            array[i]=String.valueOf(i);
        }
        return array;
    }

    public boolean has12(int[] nums) {
        boolean aux=true;
        for (int i=0; i<nums.length; i++){
            if (nums[i]==1 || nums[i]==2) return true;
            else return false;
        } return aux;
    }
}