package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @author 小郑
 * @version 1.0
 * @description utils
 * @date 2019/1/28/0028
 */
public class Test1 {
    public static void main(String[] args) {
        try {
            File dir = new File("resourcea");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String absolutePath = dir.getAbsolutePath();
            System.out.println(absolutePath+"\\index1.txt");
            String destString=absolutePath+"\\index.html";
            BufferedWriter bw = new BufferedWriter(new FileWriter(destString));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}