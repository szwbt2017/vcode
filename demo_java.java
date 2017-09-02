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

        //your appcode=自己的appcode
        //appcode = 'APPCODE 自己的appcode'

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
