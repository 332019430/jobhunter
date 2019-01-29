package utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public class FileUtils {
    public static void main(String[] args) {
        String folderPath = "D:\\resourcea\\";
        File file = new File(folderPath);
        String urlPrefix="http://sfsm1.chunfund.cn/";
        downloadCssPic(file,urlPrefix,folderPath);
    }
    /**
     * 递归读取文件夹中的css文件，并把隐藏的图片下载下来
     *
     * @param file
     * @param urlPrefix  下载地址前缀
     * @param folderPath 文件夹绝对路径
     */
    public static void downloadCssPic(File file, String urlPrefix, String folderPath) {
        //如果当前文件夹不为空
        if (file != null) {
            //如果是文件夹
            if (file.isDirectory()) {
                //获得文件夹集合
                File[] files = file.listFiles();
                for (File chilFile : files) {
                    if (chilFile.isFile()) {
                        String fileName = chilFile.getName();
                        if (fileName.endsWith(".css")){
                            String absolutePath = chilFile.getAbsolutePath();
                            try {
                                FileReader reader = new FileReader(absolutePath);
                                BufferedReader br = new BufferedReader(reader);
                                String line="";
                                while ((line=br.readLine())!=null){
                                    String pattern=".*:url.*";
                                    boolean matches = Pattern.matches(pattern, line);
                                    if (matches) {
                                        line =line.substring(line.indexOf(":url"));

                                        int i = line.indexOf('"');
                                        line = line.substring(i+4);
                                        int i1 = line.indexOf('"');
                                        line = line.substring(0,i1);
                                        int i2 = line.indexOf("/");
                                        String prefixDir = line.substring(0, i2);
                                        String fileUrl = urlPrefix + line;
                                        String folderPath1=folderPath+prefixDir;
                                        try {
                                           DownloadUtils.download(folderPath1,fileUrl);
                                            folderPath1=folderPath;
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    if (chilFile.isDirectory()) {
                        downloadCssPic(chilFile, urlPrefix, folderPath);
                    }
                }
            }
        }
    }


    public static File makeDir(String folderPath) {
        File folder = new File(folderPath);
        if (folder != null) {
            if (!folder.exists()) {
                folder.mkdirs();
            }
        }
        return folder;
    }

    public static void readAndWrite(File file) {

    }

    public static void downloadImg(String path, File dir) throws Exception {
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long time = System.currentTimeMillis();
        File f = new File(dir, time + ".jpg");
        URL url = new URL(path);
        InputStream is = url.openStream();
        FileOutputStream fos = new FileOutputStream(f);
        int len;
        byte[] arr = new byte[8192];
        while ((len = is.read(arr)) != -1) {
            fos.write(arr, 0, len);
        }
        is.close();
        fos.close();
    }


}
