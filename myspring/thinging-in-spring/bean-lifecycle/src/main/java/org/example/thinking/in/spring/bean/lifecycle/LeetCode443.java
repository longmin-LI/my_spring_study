package org.example.thinking.in.spring.bean.lifecycle;

public class LeetCode443 {
    public int compress(char[] chars) {
        if(chars == null || chars.length < 1){
            return 0;
        }
        if(chars.length == 1){
            return 1;
        }
        int len = chars.length;
        int l = 0;int r = 1;int p = 0;
        while(r < len){
            while(r < len && chars[r] == chars[r-1]){
                r++;
            }
            int num = r - l;
            if(p < len){
                chars[p++] = chars[l];
                num = reverse(num);
                while(num > 0 && p < len){
                    char c = (char) ((num % 10) + '0');
                    num /= 10;
                    chars[p++] = c;
                }
            }
            l = r;
            r++;
        }
        return p;
    }
    public int reverse(int num){
        int res = 0;
        while(num > 0){
            int value = num % 10;
            res = res * 10 + value;
            num /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode443 test = new LeetCode443();
        System.out.println(test.compress(new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}));
    }
}
