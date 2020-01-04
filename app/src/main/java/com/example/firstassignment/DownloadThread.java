package com.example.firstassignment;

import java.io.FileOutputStream;

public class DownloadThread extends Thread{

    private FileOutputStream out;
    private byte[] b=new byte[1024];
    private int len;
    public DownloadThread(FileOutputStream out,int len){
        this.out=out;
        this.len=len;
    }

    public void run(){
        try {
            if(len != -1){
                out.write(b,0,len);
                out.flush();
            }
        }catch (Exception e){
            System.out.println("DownloadThread"+e.getMessage());
        }
    }
}
