package com.example.possystem.controller;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class APICommunication {
    private String id;
    private String password;
    private String tempMessage;
    private static String token;
    private double subtotal;
    private double total;
    private double discount;
    private ListTest listTest=new ListTest();
    private static List<Integer> productIdList=new ArrayList<>();
    private static List<String>itemList=new ArrayList<>();
    private static List<String>onHandList=new ArrayList<>();
    private static List<Double>priceList=new ArrayList<>();

    private static List<String>descriptionList=new ArrayList<>();
    private Map<String, Double> freqMap2 = new TreeMap<>();
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public void login() throws IOException {
        String stringUrl = "https://cis294.hfcc.edu/api/user/login";
        String json = "{\"username\" :\""+id+"\", \"password\" :\""+ password+ "\"}";
        JSONObject obj = new JSONObject();
        URL url = new URL(stringUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        OutputStream os = conn.getOutputStream();
        os.write(json.getBytes("UTF-8"));
        os.close();
        InputStream in = new BufferedInputStream(conn.getInputStream());
        String result = IOUtils.toString(in, "UTF-8");
        System.out.println(result);
        JSONObject myResponse = new JSONObject(result);
        tempMessage=myResponse.getString("message");
        token=myResponse.getString("token");
        System.out.println(tempMessage);
        in.close();
        conn.disconnect();
    }
    public void displayProducts() throws IOException {
        String stringUrl = "https://cis294.hfcc.edu/api/product/get_all";
        String json = "{}";
        URL url = new URL(stringUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Authorization","Bearer "+token);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        OutputStream os = conn.getOutputStream();
        os.write(json.getBytes("UTF-8"));
        os.close();
        InputStream in = new BufferedInputStream(conn.getInputStream());
        String result = IOUtils.toString(in, "UTF-8");
        System.out.println(result);
        JSONObject myResponse = new JSONObject(result);
        JSONObject agac = myResponse.getJSONObject("data");
        System.out.println(agac);
        JSONArray agac2 = agac.getJSONArray("products");
        System.out.println(agac2.length());
        for(int i=0; i<=agac2.length()-1;i++)
        {
            JSONObject agac3 =agac2.getJSONObject(i);
            productIdList.add(Integer.valueOf(String.valueOf(agac3.getInt("id"))));
            itemList.add(agac3.getString("name"));
            priceList.add(agac3.getDouble("price"));
            onHandList.add(String.valueOf(agac3.getInt("quant_in_stock")));
            descriptionList.add(agac3.getString("description"));
            freqMap2.put(agac3.getString("name"),agac3.getDouble("price"));
        }
        for(int i=0;i<priceList.size();i++)
        {
            priceList.set(i, Double.valueOf(df.format(priceList.get(i))));
        }
        System.out.println(productIdList);
        System.out.println(itemList);
        System.out.println(priceList);
        System.out.println(onHandList);
        System.out.println(descriptionList);


        in.close();
        conn.disconnect();
    }

    public void addOrder() throws IOException {
        String stringUrl = "https://cis294.hfcc.edu/api/order/add";
        System.out.println(token);
        CheckoutController checkoutController=new CheckoutController();
        JSONArray orderContents=new JSONArray();
        JSONObject obj = new JSONObject();
        for(int i=0;i<listTest.getQuantitySize().size();i++)
        {
            obj=new JSONObject();
            obj.put("item_quantity", listTest.getQuantitySize().get(i));
            obj.put("prod_id", listTest.getNewProdId().get(i));
            System.out.println(listTest.getNewProdId().get(i));
            orderContents.put(obj);
        }

        System.out.println(orderContents);
        String json = "{\"subtotal\" :"+listTest.getSubTotal()+",\"total\" :"+ listTest.getGrandTotal()+",\"discount\" :"+checkoutController.getDiscount()+",\"order_contents\" :"+orderContents+"}";
        System.out.println(json);
        URL url = new URL(stringUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Authorization","Bearer "+token);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        OutputStream os = conn.getOutputStream();
        os.write(json.getBytes("UTF-8"));
        os.close();
        InputStream in = new BufferedInputStream(conn.getInputStream());
        String result = IOUtils.toString(in, "UTF-8");
        System.out.println(result);
        JSONObject myResponse = new JSONObject(result);
        in.close();
        conn.disconnect();
    }

    public void setId(String id){this.id=id;}
    public void setPassword(String password){this.password=password;}
    public String getTempMessage(){return tempMessage;}
    public List<String>getItemList(){return itemList;}
    public List<Double> getPriceList(){return priceList;}
    public List<String>getOnHandList(){return onHandList;}
    public List<String>getDescriptionList(){return descriptionList;}
    public List<Integer> getProductIdList(){return productIdList;}

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Map<String, Double> getFreqMap2() {
        return freqMap2;
    }
}
