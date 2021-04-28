package com.tce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CountName {

    /**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * @param filePath
     * @return
     */
    public static StringBuilder readTxtFile(String filePath){
        StringBuilder result=new StringBuilder();
        try {
            String encoding="UTF-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);

                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    result.append(lineTxt);
                }
                read.close();
                return result;
            }else{
                System.out.println("找不到指定的文件");
                return result;
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        return result;
    }

    public static void countString(String str,String s) {
        int count = 0;
        while(str.indexOf(s) != -1){

            str = str.substring(str.indexOf(s)+1,str.length());
            count++;

        }
        System.out.println(s+"出现的次数为"+count+"次");
    }

    public static void main(String[] args){
        String GreenWoodName="宋江";

        String shuihuTxt=readTxtFile("D:\\E盘\\小说\\corpus-master\\水浒传.txt").toString();
        //System.out.println(shuihuTxt);

        countString(shuihuTxt,GreenWoodName);
        countString(shuihuTxt,"李逵");
        countString(shuihuTxt,"林冲");
        countString(shuihuTxt,"鲁达");
        countString(shuihuTxt,"林冲");
        countString(shuihuTxt,"吴用");
        countString(shuihuTxt,"晁盖");
        countString(shuihuTxt,"公孙胜");
        countString(shuihuTxt,"武松");
        countString(shuihuTxt,"哥哥");
        countString(shuihuTxt,"卢俊义");
        countString(shuihuTxt,"梁山");
        countString(shuihuTxt,"高太尉");
        countString(shuihuTxt,"高俅");
    }

}
