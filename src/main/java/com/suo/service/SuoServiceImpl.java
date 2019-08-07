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
 * <h3>SuoServiceImpl  Class</h3>
 *
 * @author : YuXiang
 * @date : 2019-08-07 17:33
 **/
@Service
public class SuoServiceImpl {
    //需要引入：apache的httpClient jar包    fastJson  jar包

    //本人秘钥
    private static final String KEY="5d4a8f8cb1a9c729e634643d@f676968f024af1a916d93f23aeb5d04d";
    //api地址
    private static final String SUO="http://suo.im/api.htm";


    public String suo(String url){
        Map<String, String> param=new HashMap<>();
        param.put("url",url);
        param.put("format","json");
        param.put("key",KEY);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString="";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(SUO);
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
