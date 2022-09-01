package org.example.thinking.in.spring.aop.features;

/**
 * @auhtor llm
 * @data 2022/8/25 21:03
 */
public class Test {
    public static int solution(int[] A){
        int res = 0;
        int N = A.length;
        int[] diff = new int[N-1];
        for(int i = 0;i < N - 1;i++){
            diff[i] = A[i+1] - A[i];
        }
        int left = -1;int right = 0;int leftIndex = -1;int rightIndex = 0;
        while(right < diff.length){
            while(right + 1< diff.length && diff[right] == diff[right+1]){
                right++;
            }
            rightIndex = right+1;
            leftIndex = left;
            res += getNum(leftIndex,rightIndex);
            if(res > 1000000000){
                return -1;
            }
            left = right;
            right++;
        }
        return res;
    }
    public static int getNum(int left, int right){
        int len = right - left;
        int res = 0;
        int times = len - 3 + 1;
        if(times < 1){
            return 0;
        }
        for(int i = 0;i < times;i++){
            res += right - 2 - i - left;
        }
        return res;
    }
}
