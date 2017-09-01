/*
maven jsoup依赖
      <dependency>
              <groupId>org.jsoup</groupId>
              <artifactId>jsoup</artifactId>
              <version>1.9.2</version>
              <scope>test</scope>
      </dependency>
*/


public class Demo {



    public static void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[] { new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            } }, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {

        //your appcode=4bXXXXXXXXXXXXXXXXXXXXX83
        //appcode = 'APPCODE 4bXXXXXXXXXXXXXXXXXXXXX83'

        //String imgInBase64Fromat="/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAYADwDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD35twU7QC2OATgE1xPiTx3Jp+r6fpmg6dLrOqT273T2kciRxJACQXeY/KhDKQOSOoPJU12kTO8SNImxyoLJnO09xnvXmXiHQ/EGj+MrzxN4c0641NtVjjiu9Pa4W3KOihUkEo/g2ggpuB3EMcjAW4LXUmUZPZnUeFvGDeIHudOv9Nm0XXrYM0un3R3Ex7ioljbAEsZIxuXjPHQqTHB43hbx+/hKa1KzrFkXAfKu+wPtC44Gwk5J6jHPWuCi8SaxpnxRv8AXfFOl2+nzw+Fi0VlDP5pA+0qqq7qCNzSZ5GQFZc8g1QufEuiWXg6yvrDV0l8U297/akxNrIpmlk/1sZYYCrggNtIDCPpzVwhfoc9erySVnbr6o9O8OeMrTxnYS3eizraiCXypYr2EM+SBtOFk4ByQCepB9Kj8UeMItChGkLJFdeI7pEFpaqjxLKZH2Kd2SFwc9XBO3qMg1yHw6sbbwtrem2KPvtfEmjRXJMxDsbpBvaMBfup5chPzDnjB7VzWpJrPirxvYazZi2kOoXsr6PJJJIoMVpkgYz8queSDg7gT8gOTbir2W39f8EmVSShdfE/6/yPVPAt9d3GmNbXuqR3Wo2dzPbXybB99HIBXheCCpJwc57HNbf/AAkWjrxLqVvbv3iuX8mRfqj4YevI6c1wfwytdWnutd/tKCx+zDUbjzijOZVugUzg5xtAJwfvA55559IMDgARyYUDHzlmP57qwbVy4TqygmieuR1vw34gutQSXQvG91pR8oJJBNaxXalVJ2lQ2Cp+ZgWJJb5cn5aKKm9jptcytN+G0Jna7udc1G+kvLmK51c3kO37a8Q+RApACRAk/JhhjAyNoI7LX7CXVfDmqadAyLNdWksCM5IUMyFQTjPGTRRTUnoJwim/M4SX4Y6g/gfTNKTVbWPU9PjuFinWBipWdCJIjluhLEb9uQAMKDnO7qHh2ePxb4Ml061xpmkR3UUh8wfulaFUjHzHc3THf3ooq/aye/n+JCpRW3l+Bp2j6L4fvr+13QWLXM325zLccTPJkM3zHg5Q8DgDHrgbRcA4Ib8FJoop1YqMYyXUzozbco9n/mf/2Q==";
        String imgInBase64Fromat=fmImage("./2079569834.jpg");

        String url="https://vcode.weibits.com/vcode";

        String json=dovcode(url,appcode,imgInBase64Fromat);

    }


    public static String fmImage(String filePath) throws Exception{
        File file=new File(filePath);
        if(!file.exists())return null;
        return fmImage(file);
    }

    //import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
    public static  String fmImage(File file) throws Exception{

        FileInputStream stream=null;//new FileInputStream(file);

        try{
            stream=new FileInputStream(file);
            byte[] src=new byte[(int)file.length()];

            int i=stream.read(src);

            String base64= Base64.encode(src);

            return base64;

        }catch(Exception e){
            e.getStackTrace();
        }finally{
            stream.close();
        }

        return null;
    }



    private static String  dovcode(String url,String appcode,String imgInBase64Fromat) throws Exception{

        trustEveryone();

        Connection conn = Jsoup.connect(url).ignoreContentType(true).timeout(50000);

        conn.header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                .header("Authorization", appcode);

        conn.data("convert_to_jpg","0");
        conn.data("img_base64",imgInBase64Fromat);
        conn.data("typeId","34");

        long t1=System.currentTimeMillis();
        try {
            Document doc=conn.post();
            int status=conn.response().statusCode();
            System.out.println(String.format("Status====%s",status));

            String json=doc.body().text();
            System.out.println(json);



            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            long t2 = System.currentTimeMillis();
            System.out.println("\nt2-t1=" + (t2 - t1));
        }
        return null;

    }


}
