import Albert.CfgFileReader.CfgFileReader;
import com.sun.deploy.config.Config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by liuchuan on 2017/6/30.
 */
public class UpLoad {



    public String fastDFSUploadFile(String filename) throws Exception {


     System.out.println("文件上传函数");
     //访问路径
     String path = new CfgFileReader("config.ini").getString("UpLoad.address","http://120.25.88.24:10005/upload/egoo");
       //String path = "http://120.25.88.24:10005/upload/egoo";
        //文件路径
        String filePath = filename;
        String CRLF = "\r\n";
        String CHARSET = "utf-8";
        int count;
        byte[] buf = new byte[4096];
        URL url = new URL(path);
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection con = (HttpURLConnection)urlConnection;



        String boundary = Long.toHexString(System.currentTimeMillis());
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("connection", "Keep-Alive");
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        OutputStream outputStream = con.getOutputStream();

        //获取文件输入流
        FileInputStream fileInputStream = new FileInputStream(filePath);

        // Send file.
        outputStream.write(("--" + boundary + CRLF).getBytes());
        outputStream.write(("Content-Disposition: form-data; name=\"file\"; filename=\"" + filePath + "\"" + CRLF).getBytes());
        outputStream.write(("Content-Type: application/octet-stream; charset=" + CHARSET + CRLF).getBytes());
        outputStream.write(CRLF.getBytes());

        while ((count = fileInputStream.read(buf)) != -1) {
            outputStream.write(buf, 0, count);
        }

        outputStream.write(CRLF.getBytes());
        outputStream.write(("--" + boundary + "--" + CRLF).getBytes());

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
        String stringBuilder = new String();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder = line + stringBuilder;
        }
     //System.out.println(stringBuilder.toString());
     return stringBuilder;
    }

   /* public static void main(String[] args){
       UpLoad up = new UpLoad();
        try {
            String str = up.fastDFSUploadFile("pom.xml");
            System.out.println("+++++++++++++++"+str);


        }catch (Exception ex ){

        }

    }*/
}
