package com.example.possystem.controller;

import java.util.*;
import java.util.regex.Pattern;

public class ListTest
{
    private static List<String> lst= new ArrayList();
    private static List<Double> priceLst= new ArrayList();
    private static List<String>itemList=new ArrayList<>();
    private static List<String>newItemList=new ArrayList<>();
    private static List<Double>test=new ArrayList<>();
    private double price;
    private String owed;
    private int addProdId;
    private static List<Integer>quantitySize=new ArrayList<>();
    private static List<Double>priceTest=new ArrayList<>();
    private static List<Double>priceTestList=new ArrayList<>();
    private static double subTotal;
    private static double tax;
    private static double grandTotal;
    private static double paySceneGrandTotal;
    private double discountPrice=0;
    private String item;
    private  List<String> newList=new ArrayList<>();
    private  List<String> result = new ArrayList<>();
    private static List<Integer>newProdId=new ArrayList<>();
    private static List<Integer>prodIdAddList=new ArrayList<>();
    private Map<String, Integer> freqMap = new LinkedHashMap<>();
    private  Map<String, Double> freqMap2 = new LinkedHashMap<>();
    private Map<Integer, Integer> freqMap3 = new LinkedHashMap<>();
    private int quantity=1;

    public static List<Double> getTest() {
        return test;
    }
    public List<Integer>getQuantitySize(){return quantitySize;}
    public static void setTest() {
        ListTest.test=priceLst;
    }

    public  List<String> getNewList() {
        return newList;
    }
    public String twoDecimals(double a)
    {
        String numStr= String.valueOf(a);
        String[] test=numStr.split(Pattern.quote("."));
        if(test[1].length()<2)
        {
            numStr+="0";
        }
        return numStr;
    }

    public  double getPaySceneGrandTotal() {
        return paySceneGrandTotal;
    }

    public void setPaySceneGrandTotal(double paySceneGrandTotal) {
        ListTest.paySceneGrandTotal = paySceneGrandTotal;
    }

    public  List<String> getLst(){return lst;}
    public List<Double>getPriceLst(){return priceLst;}
    public double getSubTotal(){return subTotal;}
    public void setSubTotal(double subTotal){
        ListTest.subTotal =subTotal;}
    public List<String>getItemList(){return itemList;}
    public List<String>getNewItemList(){return newItemList;}
    public double getTax(){return tax;}
    public double getGrandTotal(){return grandTotal;}
    public void setPrice(double price){this.price=price;}
    public void setItem(String item){this.item=item;}
    public void setLst(List<String> newItemList)
    {

        lst.add(quantity+" "+item+" : $"+twoDecimals(price));
        this.newItemList=newItemList;
    }

    public void setItemList(){
        itemList.add(item);
    }
    public void setPriceLst(){priceLst.add(price);}
    public void setQuantity(int quantity){this.quantity=quantity;}
    public void calc()
    {
        subTotal=0;
        for(double price:priceLst)
        {
            subTotal+=price;
        }
        subTotal=subTotal-discountPrice;
        discountPrice=0;
        double tempTax;
        tempTax=subTotal*0.06;
        tax =  Math.round(tempTax * 100.0) / 100.0;
        grandTotal=subTotal+tax;
        paySceneGrandTotal=grandTotal;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }
    public Map<String, Integer> getItemsQuantitys(){return freqMap;}
    public Map<String, Double> getItemPrices(){return freqMap2;}
    public List<String>getResult(){return result;}
    public List<Double>getPriceTest(){return priceTest;}
    public List<Integer>getNewProdId(){return newProdId;}
    public void detectDuplicates()
    {
        freqMap2.clear();
        freqMap.clear();

        for (String s: itemList) {
            if(freqMap.containsKey(s)){
                freqMap.put(s, freqMap.get(s)+1);
            } else {
                freqMap.put(s, 1);
            }
        }
        for (int i=0;i< itemList.size();i++) {

                freqMap2.put(itemList.get(i), priceLst.get(i));

        }
        for (int i=0;i< prodIdAddList.size();i++) {
            if(freqMap3.containsKey(i)){
                freqMap3.put(prodIdAddList.get(i), prodIdAddList.get(i));
            } else {
                freqMap3.put(prodIdAddList.get(i), prodIdAddList.get(i));
            }

        }
        for(Map.Entry<String, Integer> entry: freqMap.entrySet()){

                result.add(entry.getKey());
                quantitySize.add(entry.getValue());
        }
        for(Map.Entry<String, Double> entry: freqMap2.entrySet()){

            priceTest.add(entry.getValue());

        }
        for(Map.Entry<Integer, Integer> entry: freqMap3.entrySet()){

            newProdId.add(entry.getKey());

        }
       for (int i=0;i<priceTest.size();i++)
       {
           priceTestList.add(priceTest.get(i)*quantitySize.get(i));
       }
        System.out.println(result);
        System.out.println(freqMap);
        System.out.println(freqMap2);
        System.out.println(quantitySize);
        System.out.println(itemList);
        System.out.println(priceTestList);
        System.out.println(newProdId);

    }
    public List<Double>getPriceTestList(){return  priceTestList;}

    public List<Integer> getProdIdAddList() {
        return prodIdAddList;
    }

    public void setProdIdAddList() {
        prodIdAddList.add(addProdId);
    }

    public Map<Integer, Integer> getFreqMap3() {
        return freqMap3;
    }

    public void setFreqMap3(Map<Integer, Integer> freqMap3) {
        this.freqMap3 = freqMap3;
    }

    public int getAddProdId() {
        return addProdId;
    }

    public void setAddProdId(int addProdId) {
        this.addProdId = addProdId;
    }

    public String getOwed() {
        return owed;
    }

    public void setOwed(String owed) {
        this.owed = owed;
    }
}

