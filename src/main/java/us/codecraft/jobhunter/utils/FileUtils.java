package us.codecraft.jobhunter.utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public class FileUtils {
    public static void main(String[] args) {


    }
    /**
     * 递归读取文件夹中的css文件，并把隐藏的图片下载下来
     * 有一种情况是css字符串只有一条，需要递归截取图片url
     * @param file
     * @param urlPrefix  下载地址前缀
     * @param folderPath 文件夹绝对路径
     */
    public static void downloadCssPic(File file, String urlPrefix, String folderPath,BufferedReader br, FileReader r) {
        System.out.println("开始下载CSS中的图片资源");
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
                                r = new FileReader(absolutePath);
                                br = new BufferedReader(r);
                                String line="";
                                while ((line=br.readLine())!=null){
                                    String pattern=".*:url.*";
                                    catchPciUrlAndDownload(urlPrefix, folderPath, line, pattern);
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    if (chilFile.isDirectory()) {
                        downloadCssPic(chilFile, urlPrefix, folderPath,br,r);
                    }
                }
            }
        }
    }

    private static void catchPciUrlAndDownload(String urlPrefix, String folderPath, String line, String pattern) {
        boolean matches = Pattern.matches(pattern, line);
        if (matches) {
            line =line.substring(line.indexOf(":url"));
            int urlStartIndex = line.indexOf('/');
            line = line.substring(urlStartIndex+1);
            //System.out.println("lineSufixx="+line);
            catchPciUrlAndDownload(urlPrefix,folderPath,line,pattern);
            int urlEndIndex = line.indexOf('"');
            //System.out.println("urlEndIndex="+urlEndIndex);
            line = line.substring(0,urlEndIndex);
            System.out.println("line="+line);
            int dirIndex = line.indexOf("/");
            String prefixDir = line.substring(0, dirIndex);
            String fileUrl = urlPrefix + line;
            String destFolderPath=folderPath+prefixDir;
            try {
               DownloadUtils.download(destFolderPath,fileUrl);
                destFolderPath=folderPath;
            } catch (Exception e) {
                e.printStackTrace();
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






}
