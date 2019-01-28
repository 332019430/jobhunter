package utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DownloadUtils {


	public static void download(String folderPath, String fileUrl) throws Exception {
		File folder = FileUtils.makeDir(folderPath);

		String picName = fileUrl.replaceAll(".*/(.*)", "$1");

		File file = new File(folder, picName);

		URL url = new URL(fileUrl);

		// InputStream inputStream = url.openStream();

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		InputStream inputStream = connection.getInputStream();

		OutputStream outputStream = new FileOutputStream(file);

		BufferedInputStream bis = new BufferedInputStream(inputStream, 1024 * 8);
		BufferedOutputStream bos = new BufferedOutputStream(outputStream);

		int len;
		while ((len = bis.read()) != -1) {
			bos.write(len);
		}

		bos.close();
		bis.close();

	}

	public static void main(String[] args) {
		String a="";
	}
}
