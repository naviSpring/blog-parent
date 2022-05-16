package com.ms.blog.test;

/**
 * @PackageName com.ms.blog.test
 * @className MainTest
 * @Author :Wud
 * @CreateDate 2022/5/11 15:00
 * @Desc
 */
public class MainTest {

    public static void main(String[] args) {
        StringBuilder sp = new StringBuilder();
        sp.append("1234.").append("qwerer").append(".");
        System.out.println(sp);
        System.out.println(sp.deleteCharAt(sp.lastIndexOf(".")));


    }
}
