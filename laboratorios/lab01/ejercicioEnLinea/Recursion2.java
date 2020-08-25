/**
 * 
 * @author anietog1, ditrefftzr
 */
public class Recursion2 {
    public boolean groupSum6(int start, int[] nums, int target) {
        if (start >= nums.length) return target == 0; //C

        if (groupSum6(start + 1, nums, target - nums[start])) return true; //C + T(n-1)

        if (nums[start] == 6) target -= 6; //C

        return groupSum6(start + 1, nums, target); //C + T(n+1)
        //MODELO: T(n) =    |C, n=0
        //                  |C + T(n-1) + T(n-1), n>0
        //ECUACION DE RECURRENCIA:
        //              C(2^n - 1) + C1*2^(n-1)
        //COMPLEJIDAD
        //          O(C(2^n - 1) + C1*2^(n-1))
        //          O(C(2^n) + C1*2^(n))        -> Por regla de la suma             (2 veces)
        //          O(2^n + 2^n)                -> Por regla de la multiplicacion   (2 veces)
        //          O(2*2^n)
        //          O(2^n)                      -> Por regla de la multiplicacion
    }

    public boolean groupNoAdj(int start, int[] nums, int target) {
        if (start>=nums.length) return target==0;
        if (groupNoAdj(start+1, nums, target) || groupNoAdj(start+2, nums, target-nums[start])) return true;
        return false;
    }

    public boolean groupSum5(int start, int[] nums, int target) {
        if(start == nums.length)
        {
            if(target == 0)
                return true;
            return false;
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]%5==0){ 
                target-=nums[i]; 
                nums[i]=0; 
                if(i<nums.length-1) 
                    if(nums[i+1]==1)
                        nums[i+1]=0; 
            }
        }
        if(groupSum5(start + 1, nums, target - nums[start]))
            return true;
        return groupSum5(start + 1, nums, target);
    }

    public boolean groupSumClump(int start, int[] nums, int target) {
        if (start>=nums.length) return target==0;
        int counter=1;
        int counterAux=nums[start];
        for (int i=start+1; i<nums.length; i++){
            if (nums[i]==nums[start]){
                counter++;
                counterAux+=nums[i];
            }
        }
        return groupSumClump(start+counter, nums, target-counterAux) || groupSumClump(start+counter, nums, target);
    }

    public boolean splitArray(int[] nums) {
        return helper(0, nums, 0, 0);
    }

    private boolean helper(int start, int[] nums, int sum1, int sum2) {
        if(start==nums.length) return sum1==sum2;
        if(helper(start+1, nums, sum1+nums[start], sum2)){return true;}
        else if(helper(start+1, nums, sum1, sum1+nums[start])){return true;}
        else return false;
    }

    
    //Credit for the next code goes to Andres Echeverri and Sebastian Jacome, since we couldn't get one of the tests in codingbat to work
    public boolean split53(int[] nums) {
        return helper3(0, nums, 0, 0);
    }

    private boolean helper3(int index, int[] nums, int sum5s, int sum3n5) {
        for(int i=0;i<nums.length;i++){
            if(nums[i]%5==0){
                sum5s=sum5s+nums[i];
                nums[i]=0;
            }
            if(nums[i]%3==0){
                sum3n5=sum3n5+nums[i];
                nums[i]=0;
            }
        }
        if(index==nums.length)
            return sum5s==sum3n5;
        if(helper3(index+1, nums, sum5s+nums[index], sum3n5))
            return true;
        else if(helper3(index+1, nums, sum5s, sum3n5+nums[index]))
            return true;
        else return false;
    }
}