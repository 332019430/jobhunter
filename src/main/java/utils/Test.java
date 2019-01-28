package utils;

import java.io.*;
import java.util.regex.Pattern;

/**
 * @author 小郑
 * @version 1.0
 * @description utils
 * @date 2019/1/28/0028
 */
public class Test {
    public static void main(String[] args) {


        File folder = new File("resourcea/");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        BufferedReader bufferedReader;
        FileReader reader;
        String line;
        File[] files = folder.listFiles();
        for (File file : files) {
            String pathName = file.getAbsolutePath();
            if (pathName.endsWith("html")){
                System.out.println(pathName);
                try {
                    reader=new FileReader(pathName);
                    bufferedReader=new BufferedReader(reader);
                    while ((line=bufferedReader.readLine())!=null){
                        String pattern=".*\\.html.*";
                        boolean matches = Pattern.matches(pattern, line);
                        if (matches) {
                                //http://sfsm1.chunfund.cn/
                            System.out.println(line);

                        }
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
