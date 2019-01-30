package us.codecraft.jobhunter.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadUtils {

	/**
	 *
	 * @param folderPath 文件路径
	 * @param fileUrl 资源路径
	 * @throws Exception
	 */
	public static void download(String folderPath, String fileUrl) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            File folder = FileUtils.makeDir(folderPath);

            String fileName = fileUrl.replaceAll(".*/(.*)", "$1");

            File file = new File(folder, fileName);

            URL url = new URL(fileUrl);

            // InputStream inputStream = url.openStream();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            inputStream = connection.getInputStream();

            outputStream = new FileOutputStream(file);

            bis = new BufferedInputStream(inputStream, 1024 * 8);
            bos = new BufferedOutputStream(outputStream);

            int len;
            while ((len = bis.read()) != -1) {
                bos.write(len);
            }
            bos.close();
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

	public static void main(String[] args) {
		String fileUrl="http://sm3.sdflscy.com/templates/24300/img2.png";
        String dest =fileUrl.replaceAll(".*/(.*)", "$1");
        String my =fileUrl.replaceAll(".*/(.*)", "$1");
        System.out.println("my:"+my);
        System.out.println("dest:"+dest);
    }
}
