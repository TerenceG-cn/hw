package com.tce.IO_Stream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BytetoChar {
    public static void main(String[] args) {
        try {
            //字节流转字符流示例 还有很多
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("example"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
