package com.demo.Filter;

import com.demo.utils.PropertiesUtil;

import javax.servlet.http.HttpServletRequest;

public class FilterUtil {

    private static String DO_MAIN_URL = PropertiesUtil.getProperties("/db.properties").getProperty("domain.url.deploy");

    public static String getSchoolPrefix(HttpServletRequest request) {

        String url = null;
        String prefix = "";
        try {
            StringBuffer urlString = request.getRequestURL();

            //截取url
            int start = 0;
            if (urlString.indexOf("http://") >= 0) {
                start = "http://".length();
            } else if (urlString.indexOf("https://") >= 0){
                start = "https://".length();
            }

            String domain = DO_MAIN_URL;

            if (urlString.toString().contains(domain)) {
                prefix = urlString.toString().substring(start, urlString.indexOf("."));
                if (prefix.length() > 0) {
                    url = prefix;
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return url;
    }
}
