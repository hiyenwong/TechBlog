# Plus One

Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:
```
Input: [1,2,3]
Output: [1,2,4]

Explanation: The array represents the integer 123.
```
Example 2:
```
Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
```

# code
### java
#### version 1:
```java

public class Solution {
       public int[] plusOne(int[] digits) {
             for(int i=digits.length-1;i>=0;i--){
                digits[i] =1+digits[i];
                
                if(digits[i]==10)
                    digits[i]=0;
                 else
                    return digits;
            }
 
            //don't forget over flow case
           int[] newdigit = new int[digits.length+1];
            newdigit[0]=1;
            for(int i=1;i<newdigit.length;i++){
               newdigit[i] = digits[i-1];
            }
           return newdigit;
         }

}
```
#### version 2
```java
class Solution {
    public int[] plusOne(int[] digits) {
        
        boolean marker = false;
        List<Integer> result = new ArrayList();
        
        if(digits[digits.length-1] + 1 <= 9){
            digits[digits.length-1] = digits[digits.length-1] + 1;
            return digits;
        }
        
        else {
            marker = true;
            result.add(0);
            for(int i=digits.length-2;i>=0;i--){
             if(marker){
                 if(digits[i] + 1 > 9){
                    result.add(0);
                 }
                 else {
                     result.add(digits[i] + 1);
                     marker = false;
                 }
             }
                
                else {
                    result.add(digits[i]);
                }
            }
            
            if(marker){
                result.add(1);
            }
            
        }
        
        
        Collections.reverse(result);
        return result.stream().mapToInt(i -> i).toArray();
    }
}
```