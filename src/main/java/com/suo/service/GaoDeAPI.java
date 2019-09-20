package com.suo.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * <h3>GaoDeAPI  Class</h3>
 *
 * @author : YuXiang
 * @date : 2019-09-20 13:58
 **/
@Service
public class GaoDeAPI {
    private static final String KEY="350305c62bb65fa919b72c963f935398";

    private static final String URL="https://restapi.amap.com/v3/place/around?parameters";

    public String gaoDeSou(){
        Map<String, String> param=new HashMap<>();
        param.put("key",KEY);
        param.put("location","112.553657,37.802344");
        param.put("keywords","便利");
        param.put("output","JSON");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString="";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(URL);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.err.println(resultString);
        return resultString;
    }
}
