package immortal.half.wu;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

//import com.alibaba.fastjson.JSONObject; // 版本用json存的
//import com.your.server.Main; //你的主程序，下面要用来关闭程序实现重启，请读者
//自己实现关闭程序方法，使用思路为主程序开启时调用本类相关方法，完成更新，截图中的
//弹窗根据自己的业务去实现，如监测到新版本，则弹窗提示，点击确定后下载新版本，下载完了提示重启软件


public class Updater {

    private static float currentversion = 1.0f;//当前版本号
    private static float newversion = currentversion; //最新版本号
    private static boolean downloaded = false;//下载完成与否
    private static boolean errored = false;//下载出错与否
    @NotNull
    private static String jarurl = "https://immortalhalfwu.coding.net/p/MallHelper/d/MallHelper/git/archive/master/immortalHalfWu-MallHelper-master.zip"; // jar存放地址
    @NotNull
    private static String string2dowload = "http://your.server/完整版本下载链接用于下载失败时调用浏览器完成下载.exe"; //备用更新方案
    @NotNull
    private static String description = "";//新版本更新信息

    /**
     * 静默下载最新版本
     */
    private static void dowload() {
        try {
            File file = downLoadFromUrl(jarurl, "dowloadtmp.zip", Updater.class.getResource("").getPath() + "tmp");
            String unZipPath = file.getParentFile().getPath();
            ZIPUtil.unZip(file, unZipPath);

            File mdFile = new File(unZipPath + "README.md");
            byte[] bytes = readInputStream(new FileInputStream(mdFile));
            System.out.println(new String(bytes, StandardCharsets.UTF_8));
            downloaded = true;
        } catch (Exception e) {
            downloaded = false;
            errored = true;
            e.printStackTrace();
        }

    }

    /**
     * 重启完成更新
     */
    private static void restart() {
        try {
            Runtime.getRuntime().exec("cmd /k start .\\update.bat");
//            Main.close(); //关闭程序以便重启
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 获取最新版本号
//     */
//    private static void getNewVersion() {
//        String json = sendGetRequest(versinurl);
//
//        JSONObject ob =  JSONObject.parseObject(json);
//        newversion = ob.getFloat("version");
//        description = ob.getString("desc");
//    }

    /**
     * 启动后自动更新
     */
    public static void autoupgrade() {
//        getNewVersion();
        dowload();
//        restart();
    }

    /**
     * 发get请求，获取文本
     * @param getUrl
     * @return 网页context
     */
    private static String sendGetRequest(@NotNull String getUrl) {
        StringBuffer sb = new StringBuffer();
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            URL url = new URL(getUrl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setAllowUserInteraction(false);
            isr = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    /**
     * 从网络Url中下载文件
     *
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    @NotNull
    private static File downLoadFromUrl(@NotNull String urlStr, String fileName, @NotNull String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设置超时间为3秒
        conn.setConnectTimeout(3 * 1000);
        // 防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        // 得到输入流
        InputStream inputStream = conn.getInputStream();
//         获取自己数组
//        byte[] getData = readInputStream(inputStream);

        // 文件保存位置
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File(saveDir + File.separator + fileName);
//        FileOutputStream fos = new FileOutputStream(file);
//        fos.write(getData);
//        fos.close();
        inputStream.close();
        writeIntoOut(inputStream, new FileOutputStream(file));
        System.out.println("info:" + url + " download success");

        return file;
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }


    /**
     * 写文件
     * @param ins
     * @param out
     */
    public static void writeIntoOut(InputStream ins, @NotNull OutputStream out) {
        byte[] bb = new byte[10 * 1024];
        try {
            int cnt = ins.read(bb);
            while (cnt > 0) {
                out.write(bb, 0, cnt);
                cnt = ins.read(bb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                ins.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
