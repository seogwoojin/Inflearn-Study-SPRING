package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int sum=0;

        String str = br.readLine();
        String[] s = str.split("");

        for(int i=0; i<n; i++){
            sum+=Integer.parseInt(s[i]);
        }

        System.out.println(sum);
    }
}