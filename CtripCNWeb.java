package com.eking.crawl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CtripCNWeb {
    final static  String source_code = "ctrip_cn_web";


    public static String getUrl(CrawlJob job) {

        String url = "";
        String dep_code="";
        String arr_code="";

        if(job.getDep_code()!=null&&job.getDep_code().toLowerCase().equals("pek")){
            dep_code="BJS";
        }else
        {
            dep_code=job.getDep_code();
        }
        if(job.getArr_code()!=null&&job.getArr_code().toLowerCase().equals("pek")){
            arr_code="BJS";
        }else
        {
            arr_code=job.getArr_code();
        }

        if (job.getRoute_type().trim().equals("0")) {

            url = "https://m.ctrip.com/html5/flight/swift/domestic/" + dep_code + "/" + arr_code + "/" + job.getDep_date();

        } else {
            url = "https://flights.ctrip.com/domestic/booking/" + dep_code + "-" + arr_code +
                    "---D-adu-1/?dayoffset=32&ddate1=" + job.getDep_date() + "&ddate2=" + job.getDep_date1();
        }

        return url;
    }

    ///html/body/div[4]/div[3]/div[8]/div[1]/div[2]/div[2]/div[2]/table
    static String cmd = "driver.get(url_param);p_value=driver.page_source;";


    public static String toJson(CrawlJob job, String user_agent, String agent_code,
                                String revice_server_ip, String revice_server_ports, String source_code, String route_type, String dep_code, String arr_code,
                                String dep_date, String dep_date1) {

        SubJobValue a = new SubJobValue();

        a.setUrl(getUrl(job));
        a.setCmd(cmd);
        a.setUser_agent(user_agent);
        a.setCreate_time(String.valueOf(System.currentTimeMillis()));
        a.setAgent_code(agent_code);
        a.setJob_id(job.getJob_id());
        a.setReceive_msg_server_ip(revice_server_ip);
        a.setReceive_msg_server_port(revice_server_ports);
        a.setSource_code(source_code);
        /**
         *    String source_code;
         String route_type;
         String dep_code;
         String arr_code;
         String dep_date;
         String dep_date1;
         */
        a.setRoute_type(route_type);

        if(job.getDep_code()!=null&&job.getDep_code().toLowerCase().equals("pek"))dep_code="BJS";
        if(job.getArr_code()!=null&&job.getArr_code().toLowerCase().equals("pek"))arr_code="BJS";
        a.setDep_code(dep_code);
        a.setArr_code(arr_code);


        a.setDep_date(dep_date);
        a.setDep_date1(dep_date1);


        Gson b = new GsonBuilder().disableHtmlEscaping().create();
        String tmp = b.toJson(a);
        return tmp;
    }

    public static List<PlaneData> paramHtml(String html) {
        List datas = new ArrayList<PlaneData>();

        try {
            Document doc = Jsoup.parse(html);
            Elements elements = doc.select(".card-item-content");

            for (Element obj : elements) {


                PlaneData data = new PlaneData();
                Elements subs_flight_depart = obj.getElementsByClass("flight-depart");
                for (Element subs_obj_flight_depart : subs_flight_depart) {

                    String flight_dep_time = subs_obj_flight_depart.getElementsByClass("flight-time").text().replaceAll("[^0-9a-zA-Z+: ]", "");;
                    data.setFlight_dep_time(flight_dep_time);
//                    String flight_dep_airport = subs_obj_flight_depart.getElementsByClass("flight-airport").text();
//                    data.setFlight_dep_airport(flight_dep_airport);flight_dep_airport
                }


                Elements subs_flight_dest = obj.getElementsByClass("flight-dest");

                for (Element subs_obj_flight_dest : subs_flight_dest) {

                    String flight_arr_time = subs_obj_flight_dest.getElementsByClass("flight-time").text().replaceAll("[^0-9a-zA-Z+: ]", "");;
                    data.setFlight_arr_time(flight_arr_time);
//                    String flight_arr_airport = subs_obj_flight_dest.getElementsByClass("flight-airport").text();
//                    data.setFlight_arr_airport(flight_arr_airport);
                }

                Elements subs_flight_price = obj.getElementsByClass("flight-price");



                for (Element subs_obj_flight_price : subs_flight_price) {
                    String flight_price = "";
                    Elements subs_ojb_price_1 = subs_obj_flight_price.getElementsByClass("flight-number");
                    Element subs_ojb_price_1_1 = subs_ojb_price_1.first();
                    Element subs_ojb_price_1_1_1 = subs_ojb_price_1_1.getElementsByTag("strong").first();
                    flight_price = subs_ojb_price_1_1_1.text();

                    data.setFlight_price(flight_price);


                    String flight_price_num = subs_obj_flight_price.getElementsByClass("ticket-left").text();



                    flight_price_num = flight_price_num.replaceAll("[^0-9a-zA-Z]", "");
                    if(flight_price_num.equals(""))
                    {
                        flight_price_num="A";
                    }

                    data.setFlight_price_num(flight_price_num);
                }


                Elements subs_flight_plane = obj.getElementsByClass("flight-plane");


                String f_plane=subs_flight_plane.first().getElementsByTag("span").first().text();
                if(f_plane.lastIndexOf("(")!=-1)
                {
                    //data.setFlight_plane_type(f_plane.substring(f_plane.lastIndexOf("(")+1,f_plane.lastIndexOf(")")));
                }




                String regex ="[\u4e00-\u9fa5]";
                Pattern pat = Pattern.compile(regex);
                Matcher mat = pat.matcher(f_plane);
                String repickStr = mat.replaceAll("");
                repickStr=repickStr.replace("(","");
                repickStr=repickStr.replace(")","");
                data.setFlight_plane(repickStr.replaceAll("[^0-9a-zA-Z]", ""));
                data.setSource_code(CtripCNWeb.source_code);

                datas.add(data);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }


}
