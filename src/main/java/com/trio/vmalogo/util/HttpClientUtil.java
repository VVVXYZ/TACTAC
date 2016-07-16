package com.trio.vmalogo.util;



/**
 * @author xiaozhi
 * @ClassName: com.trio.vmalogo.util
 * @Description:
 * @Date 2016/03/23 11:20
 * @History: <author>
 * <time>
 * <desc>
 */

public class HttpClientUtil {

//    static Logger logger = Logger.getLogger(HttpClientUtil.class);
//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
////        // TODO Auto-generated method stub
////        String path = "http://192.168.0.102:8080/myhttp/servlet/LoginAction";
////        Map<String, String> params = new HashMap<String, String>();
////        params.put("username", "admin");
////        params.put("password", "123");
////        String result = HttpUtils.sendHttpClientPost(path, params, "utf-8");
////        System.out.println("-->>"+result);
//
//        try {
//            test();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static String sendHttpClientPost(String path, Map<String, String> map, String encode) {
//        logger.debug("sendHttpClientPost");
//        List<NameValuePair> list = new ArrayList<NameValuePair>();
//        if (map != null && !map.isEmpty()) {
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                list.add(new BasicNameValuePair(entry.getKey(), entry
//                        .getValue()));
//            }
//        }
//        try {
//            // 实现将请求的参数封装到表单中,请求体重
//            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, encode);
//            // 使用Post方式提交数据
//            HttpPost httpPost = new HttpPost(path);
//            httpPost.setEntity(entity);
//            // 指定post请求
//            CloseableHttpClient client = HttpClients.createDefault();
//            HttpResponse httpResponse = client.execute(httpPost);
//            if (httpResponse.getStatusLine().getStatusCode() == 200) {
//                logger.debug("httpResponse.getStatusLine().getStatusCode() == 200");
//                String result = changeInputStream(httpResponse.getEntity().getContent(),
//                        encode);
//
//                return result;
//            }
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return "fsdf";
//    }
//
//    /**
//     * 将一个输入流转换成指定编码的字符串
//     *
//     * @param inputStream
//     * @param encode
//     * @return
//     */
//    public static String changeInputStream(InputStream inputStream,
//                                           String encode) {
//        // TODO Auto-generated method stub
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        byte[] data = new byte[1024];
//        int len = 0;
//        String result = "";
//        if (inputStream != null) {
//            try {
//                while ((len = inputStream.read(data)) != -1) {
//                    outputStream.write(data, 0, len);
//                }
//                result = new String(outputStream.toByteArray(), encode);
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//
//    /**
//     * post方式提交表单（模拟用户登录请求）
//     */
//    public void postForm() {
//        // 创建默认的httpClient实例.
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        // 创建httppost
//        HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceJ.action");
//        // 创建参数队列
//        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//        formparams.add(new BasicNameValuePair("username", "admin"));
//        formparams.add(new BasicNameValuePair("password", "123456"));
//        UrlEncodedFormEntity uefEntity;
//        try {
//            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
//            httppost.setEntity(uefEntity);
//            System.out.println("executing request " + httppost.getURI());
//            CloseableHttpResponse response = httpclient.execute(httppost);
//            try {
//                HttpEntity entity = response.getEntity();
//                if (entity != null) {
//                    System.out.println("--------------------------------------");
//                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
//                    System.out.println("--------------------------------------");
//                }
//            } finally {
//                response.close();
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭连接,释放资源
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
//     */
//    public void post() {
//        // 创建默认的httpClient实例.
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        // 创建httppost
//        HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceJ.action");
//        // 创建参数队列
//        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//        formparams.add(new BasicNameValuePair("type", "house"));
//        UrlEncodedFormEntity uefEntity;
//        try {
//            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
//            httppost.setEntity(uefEntity);
//            System.out.println("executing request " + httppost.getURI());
//            CloseableHttpResponse response = httpclient.execute(httppost);
//            try {
//                HttpEntity entity = response.getEntity();
//                if (entity != null) {
//                    System.out.println("--------------------------------------");
//                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
//                    System.out.println("--------------------------------------");
//                }
//            } finally {
//                response.close();
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭连接,释放资源
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 发送 get请求
//     */
//    public void get() {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        try {
//            // 创建httpget.
//            HttpGet httpget = new HttpGet("http://www.baidu.com/");
//            System.out.println("executing request " + httpget.getURI());
//            // 执行get请求.
//            CloseableHttpResponse response = httpclient.execute(httpget);
//            try {
//                // 获取响应实体
//                HttpEntity entity = response.getEntity();
//                System.out.println("--------------------------------------");
//                // 打印响应状态
//                System.out.println(response.getStatusLine());
//                if (entity != null) {
//                    // 打印响应内容长度
//                    System.out.println("Response content length: " + entity.getContentLength());
//                    // 打印响应内容
//                    System.out.println("Response content: " + EntityUtils.toString(entity));
//                }
//                System.out.println("------------------------------------");
//            } finally {
//                response.close();
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭连接,释放资源
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    public static void test() throws Exception{
//        DefaultHttpClient httpclient = new DefaultHttpClient();
//
////        HttpGet httpget = new HttpGet("https://portal.sun.com/portal/dt");
////
////        HttpResponse response = httpclient.execute(httpget);
//        HttpResponse response ;
////        HttpEntity entity = response.getEntity();
//        HttpEntity entity ;
//        List<Cookie> cookies;
////        System.out.println("Login form get: " + response.getStatusLine());
////        if (entity != null) {
////            entity.consumeContent();
////        }
////        System.out.println("Initial set of cookies:");
////        List<Cookie> cookies = httpclient.getCookieStore().getCookies();
////        if (cookies.isEmpty()) {
////            System.out.println("None");
////        } else {
////            for (int i = 0; i < cookies.size(); i++) {
////                System.out.println("- " + cookies.get(i).toString());
////            }
////        }
//
//        HttpPost httpost = new HttpPost("http://vmalogo.lczyfz.com/vmalogo/client/sendIdentifyingCode");
//
//        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
//        nvps.add(new BasicNameValuePair("phoneNumber", "18650088418"));
//        nvps.add(new BasicNameValuePair("identifyingType", "1"));
//
//        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
//        response = httpclient.execute(httpost);
//        entity = response.getEntity();
//       String result =  changeInputStream(entity.getContent(),"UTF-8");
//        System.out.println("Login form get: " + response.getStatusLine());
//        System.out.println("result(): " + result);
//        if (entity != null) {
//            entity.consumeContent();
//
//        }
//
//        System.out.println("Post logon cookies:");
//        cookies = httpclient.getCookieStore().getCookies();
//        if (cookies.isEmpty()) {
//            System.out.println("None");
//        } else {
//            for (int i = 0; i < cookies.size(); i++) {
//                System.out.println("- " + cookies.get(i).toString());
//            }
//        }
//    }
}
