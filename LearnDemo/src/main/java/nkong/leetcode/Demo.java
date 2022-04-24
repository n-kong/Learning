package nkong.leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/9/3 17:28
 */
public class Demo {

    public static void main(String[] args) {
//        twoSum(new int[]{3, 2, 4}, 6).toString();
        System.out.println(lengthOfLongestSubstring("dvdf"));
    }

    public static int lengthOfLongestSubstring(String s) {
        Set<Object> sets = new HashSet<>();
        int fin = 0;
        char[] chars = s.toCharArray();
        int tmp = 0;
        for (int i = 0; i < chars.length; i++) {
            sets.add(chars[i]);
            if (sets.size() != tmp + 1) {
                if (sets.size() > fin) {
                    fin = sets.size();
                }
                sets.clear();
                i = s.indexOf(chars[i]);
                sets.add(chars[i]);
            }
            tmp = sets.size();
        }
        if (sets.size() > fin) {
            fin = sets.size();
        }

        return fin;
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && (nums[i] + nums[j]) == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }


}
