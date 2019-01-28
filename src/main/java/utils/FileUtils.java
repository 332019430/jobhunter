package utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public class FileUtils {

	public static File makeDir(String folderPath){
		File folder = new File(folderPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		return  folder;
	}

	public static void readAndWrite(File file){

	}

	public static void downloadImg(String path, File dir) throws Exception {
		if(!dir.exists()) {
			dir.mkdirs();
		}
		long time=System.currentTimeMillis();
		File f=new File(dir,time+".jpg");
		URL url=new URL(path);
		InputStream is=url.openStream();
		FileOutputStream fos=new FileOutputStream(f);
		int len;
		byte[] arr=new byte[8192];
		while((len=is.read(arr))!=-1) {
			fos.write(arr, 0, len);
		}
		is.close();
		fos.close();
	}

    public static void main(String[] args) {

        try {

            File folder = new File("resourcea/");
            if (!folder.exists()) {
                folder.mkdirs();
            }
            String absolutePath = folder.getAbsolutePath();
            FileWriter w = new FileWriter("index111.html");
            BufferedWriter bw = new BufferedWriter(w);

            String line;
            File[] files = folder.listFiles();
            for (File file : files) {
                String pathName = file.getAbsolutePath();
                if (pathName.endsWith("html")){
                    System.out.println("pathName="+pathName);

                    FileReader r=new FileReader(pathName);
                    BufferedReader br =new BufferedReader(r);
                    while ((line=br.readLine())!=null){
                        String pattern=".*\\.html.*";
                        boolean matches = Pattern.matches(pattern, line);
                        if (matches) {
                            System.out.println(line);
                            line = line.replaceAll("<a href=\"", "<a href=\"" + "http://sfsm1.chunfund.cn/");
                            System.out.println(line);
                        }
                        bw.write(line);
                        bw.newLine();
                    }

                    br.close();
                }else  if (pathName.endsWith("css")){
                    boolean directory = file.isDirectory();
                    if (directory){
                        File[] files1 = file.listFiles();
                        for (File file1 : files1) {
                            String absolutePath1 = file1.getAbsolutePath();
                            System.out.println("css="+absolutePath1);
                            FileReader r=new FileReader(absolutePath1);
                            BufferedReader br =new BufferedReader(r);
                            while ((line=br.readLine())!=null){
                                String pattern=".*:url.*";
                                boolean matches = Pattern.matches(pattern, line);
                                if (matches) {
                                   // System.out.println(line);
                                    line =line.substring(line.indexOf("/"));
                                    //System.out.println(line);
                                    int i = line.indexOf('"');
                                    line = line.substring(0, i);
                                    System.out.println(line);
                                    String fileUrl = "http://sfsm1.chunfund.cn" + line;
                                    try {
                                        DownloadUtils.download("G:\\南京纯熙投资管理有限公司\\resourcea\\images",fileUrl);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            br.close();
                        }
                    }
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
