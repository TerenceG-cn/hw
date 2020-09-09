package com.tce.music;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import javax.swing.text.*;
import javax.swing.text.StyleContext.NamedStyle;
import javax.swing.*;
import static javax.swing.JFrame.*; //引入JFrame的静态常量
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.util.*;
import java.util.Timer;


class audioplay{//播放音乐类
    AudioClip adc;// 声音音频剪辑对象
    URL url;
    boolean adcFlag=false;
    boolean playFlag=false;
    void SetPlayAudioPath(String path){
        try{
            url = new URL(path);
            // System.out.println(adc.toString());
            if(adcFlag){adc.stop();playFlag=false;}
            adc = Applet.newAudioClip(url);
            adcFlag=true;
        }
        catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
    }
    void play(){     //开始播放
        adc.play();
        playFlag=true;
    }
    void stop(){     //停止播放
        adc.stop();
        playFlag=false;
    }
}

class music
{
    //LinkedList<String> Lyricslist;

    LinkedList<String> Lyricslist=new LinkedList<String>();//歌曲歌手和歌词信息
    LinkedList<String> Lyrics=new LinkedList<String>();//歌词信息
    LinkedList<Integer> Lyricstime=new LinkedList<Integer>();//歌词时间信息
    String name;//歌曲名称
    String songer;//歌手
    String time;//总时长
    String playFileDirectory;//文件路径
    //String[] Lyrics=new String[100];//歌词信息
    //int[] Lyricstime=new int[((Lyricslist.size()-1)/3)*2];//歌词时间信息
    music(String na,String ti)
    {
        this.name=na;
        this.time=ti;
    }
    void loadlyrics(String path,String name) {
        //参数分别为歌曲的路径和歌曲的名字
        //导入歌手和歌词
        int n=0;
        String Lyricspath=path+name+".txt";//寻找歌词路径
        File filename = new File(Lyricspath);
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(filename));
        } catch (FileNotFoundException e1) {}
        BufferedReader br = new BufferedReader(reader);
        String line="";
        String[] split= {"","",""};

        try {
            while ((line = br.readLine()) != null ) {
                // 一次读入一行数据
                if(n==0) {
                    Lyricslist.add(line);//歌手名
                    //System.out.println(Lyricslist.get(n));
                    n++;
                }
                else {
                    //Lyricslist.add(line);
                    //System.out.println(Lyricslist.get(n));
                    split=line.split(" ");
                    Lyricslist.add(split[0]);//每一行歌词开始时间
                    Lyricslist.add(split[1]);//每一行歌词结束时间
                    Lyricslist.add(split[2]);//每一行歌词
                    n++;
                    //System.out.println(Lyricslist.size());
                }
            }
        } catch (IOException e1) {}

        int x=0;
        for(x=0;x<(Lyricslist.size()-1)/3;x++) {
            //System.out.println(Lyricslist.get(x*3+1));
            //System.out.println(Lyricslist.size());
            Lyricstime.add(Integer.parseInt(Lyricslist.get(x*3+1)));
            Lyricstime.add(Integer.parseInt(Lyricslist.get(x*3+2)));
            //System.out.println(Lyricstime[x*2]+"+"+Lyricstime[x*2+1]);
            Lyrics.add(Lyricslist.get(x*3+3)+"\n");
            //System.out.println(Lyrics.get(x));
        }//每句歌词及它的开始和结束时间
    }
    int timetransform(String s){//将歌曲时间转化为秒数
        String []t= {"",""};
        t=s.split(":");
        return Integer.parseInt(t[0])+Integer.parseInt(t[1]);
    }
}

class MyExtendsJFrame extends JFrame implements ActionListener,MouseListener
{ //窗口类
    JLabel background;//背景控件
    JLabel background1;//背景控件
    JLabel background2;//背景控件
    JLabel picture;//图片
    JButton aaa;
    JButton buttonPlay;//播放 按钮
    JButton buttonStop;//停止播放
    JButton buttonPlay1;//下一首 按钮
    JButton buttonPlay2;//上一首 按钮
    JButton buttonPlay3;//打开 按钮
    JButton buttonPlay4;// 循环 按钮
    JTextPane textLyrics;//歌词控件
    JLabel playTime;//播放进度条控件
    JList listPlayFile;//播放列表控件
    Timer nTimer;//定时器对象
    JTextArea textarea;//歌名控件
    JTextArea runtime;//当前歌曲时间
    JTextArea textarea1;//歌手名
    JLabel gif;//动图
    audioplay audioPlay;
    LinkedList<music>mylist;//播放列表
    Vector<String> vt1=new Vector ();//创建范围Vector对象，用于点击播放列表的索引操作


    public MyExtendsJFrame()
    {
        audioPlay=new audioplay();  //创建播放对象
        mylist=new LinkedList<music>();
        setTitle("播放器");//软件名
        setBounds(100,100,800,537);	//设置窗口大小
        setLayout(null);//空布局
        init();   //添加控件的操作封装成一个函数
        setVisible(true);//放在添加组件后面执行
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    void init()
    {//添加的控件
        //设置背景图片
        Icon img=new ImageIcon(".//backGround1.jpg");     //创建图标对象
        background = new JLabel(img);//设置背景图片
        background.setBounds(0,0,800,500);//设置背景控件大小
        getLayeredPane().add(background, Integer.valueOf(Integer.MIN_VALUE));//背景图片控件置于最底层
        ((JPanel)getContentPane()).setOpaque(false); //控件透明

        Icon img0=new ImageIcon(".//1.jpg");     //创建图标对象
        background1 = new JLabel(img0);//设置背景图片
        background1.setBounds(0,380,800,150);//设置背景控件大小
        getLayeredPane().add(background1, Integer.valueOf(Integer.MIN_VALUE + 1));//背景图片控件置于最底层
        ((JPanel)getContentPane()).setOpaque(false); //控件透明

        Icon img3=new ImageIcon(".//time.jpg");     //创建图标对象
        background2 = new JLabel(img3);//设置背景图片
        background2.setBounds(220,450,300,3);//设置背景控件大小
        getLayeredPane().add(background2, Integer.valueOf(Integer.MIN_VALUE + 2));//背景图片控件置于最底层
        ((JPanel)getContentPane()).setOpaque(false); //控件透明

        Icon img4=new ImageIcon(".//listen.jpeg");     //创建图标对象
        picture = new JLabel(img4);//设置图片
        picture.setBounds(0,405,80,80);//设置图片控件大小
        getLayeredPane().add(picture, Integer.valueOf(Integer.MIN_VALUE + 2));//图片控件置于最底层
        ((JPanel)getContentPane()).setOpaque(false); //控件透明

        //设置动态图
        Icon img1=new ImageIcon(".//y.gif");     //创建图标对象
        gif = new JLabel(img1);//设置背景图片
        gif.setBounds(230,72,240,240);//设置背景控件大小
        //getLayeredPane().add(gif, new Integer(Integer.MIN_VALUE));//背景图片控件置于最底层
        add(gif);

        //当前歌曲播放时间	控件
        runtime=new JTextArea("00 : 00");
        runtime.setBounds(540,440,30,30);
        runtime.setForeground(Color.white);//歌词控件字体颜色
        runtime.setOpaque(false);
        add(runtime);

        //播放		控件button1.setVisible(true);
        buttonPlay=new JButton();//添加     播放   按钮
        buttonPlay.setBounds(350,400,38,38); //设置播放按钮大小
        Icon icon=new ImageIcon(".//3.jpg");//创建播放图标对象
        buttonPlay.setIcon(icon);	      //设置播放图标
        buttonPlay.setBorderPainted(false);
        buttonPlay.addActionListener(this);//添加单机关联事件
        add(buttonPlay);//添加播放按钮至窗口中

        //停止		控件
        buttonStop=new JButton();//添加     播放   按钮
        buttonStop.setBounds(350,400,38,38); //设置播放按钮大小
        Icon iconstop=new ImageIcon(".//4.jpg");//创建播放图标对象
        buttonStop.setIcon(iconstop);	      //设置播放图标
        buttonStop.setBorderPainted(false);
        buttonStop.addActionListener(this);//添加单机关联事件
        add(buttonStop);//添加播放按钮至窗口中

        //下一首	控件
        buttonPlay1=new JButton();//添加    下一首    按钮
        buttonPlay1.setBounds(390,400,38,38); //设置播放按钮大小
        Icon icon1=new ImageIcon(".//5.jpg");//创建播放图标对象
        buttonPlay1.setIcon(icon1);	      //设置播放图标
        buttonPlay1.setBorderPainted(false);
        buttonPlay1.addActionListener(this);
        add(buttonPlay1);//添加播放按钮至窗口中

        //上一首	控件
        buttonPlay2=new JButton();//添加	上一首	按钮
        buttonPlay2.setBounds(308,400,38,38); //设置播放按钮大小
        Icon icon2=new ImageIcon(".//2.jpg");//创建播放图标对象
        buttonPlay2.setIcon(icon2);	      //设置播放图标
        buttonPlay2.setBorderPainted(false);
        buttonPlay2.addActionListener(this);
        add(buttonPlay2);//添加播放按钮至窗口中

        //打开文件		按钮
        buttonPlay3=new JButton();//添加	打开		按钮
        buttonPlay3.setBounds(600,400,30,30); //设置播放按钮大小
        Icon icon3=new ImageIcon(".//6.jpg");//创建播放图标对象
        buttonPlay3.setIcon(icon3);	      //设置播放图标
        buttonPlay3.setBorderPainted(false);
        buttonPlay3.addActionListener(this);
        add(buttonPlay3);//添加播放按钮至窗口中


        //歌曲名	控件
        textarea= new JTextArea("");
        textarea.setBounds(100,405,100,25);
        textarea.setForeground(Color.white);//歌词控件字体颜色
        textarea.setOpaque(false);
        add(textarea);

        //歌手名
        textarea1=new JTextArea("");
        textarea1.setBounds(100,426,50,50);
        textarea1.setForeground(Color.white);//歌词控件字体颜色
        textarea1.setOpaque(false);
        add(textarea1);

        //播放列表		控件
        listPlayFile=new JList();	  //创建播放列表
        listPlayFile.setBounds(500,100,150,150); //设置播放列表大小
        listPlayFile.setOpaque(false);//设置播放列表透明
        listPlayFile.setBackground(new Color(0, 0, 0, 0));//设置播放列表背景颜色
        listPlayFile.setForeground(Color.white);//设置播放列表字体颜色
        add(listPlayFile);       //添加播放列表至窗口中
        listPlayFile.addMouseListener(this);//添加播放列表的双击事件关联

        //歌词		控件
        textLyrics=new JTextPane();   //创建歌词控件
        textLyrics.setBounds(20,20,200,500);//设置歌词控件大小
        textLyrics.setForeground(Color.white);//歌词控件字体颜色
        textLyrics.setOpaque(false);//歌词控件透明
        add(textLyrics);    //添加歌词控件至窗口中
        textLyrics.setText("点击播放列表，选择歌曲进行播放 \n");//歌词控件添加文字

        //进度条	控件
        Icon img2=new ImageIcon(".//time.png");     //创建图标对象
        playTime = new JLabel(img2);	  		//创建播放进度条对象
        playTime.setBounds(70,500,0,3);	  	//设置播放进度条对象大小
        //add(playTime); //添加播放进度条至窗口中
        getLayeredPane().add(playTime, new Integer(Integer.MIN_VALUE+3));
    }
    //时间转换器
    public String timechange(int second)
    {
        int min=second/60;
        int sec=second-min*60;
        String rt1=String.format("%02d",min);
        String rt2=String.format("%02d",sec);
        String rt=rt1+":"+rt2;
        return rt;
    }

    //定时器函数
    public  void timerFun(int nPlayTime,music M)
    {//定时器函数
        if(nTimer!=null){nTimer.cancel();}//已经有定时器则关闭
        nTimer = new Timer();//创建定时器
        nTimer.schedule(new TimerTask(){  //匿名类
            int ttt;
            int eachPlayTime=710/nPlayTime;
            int starttime=0;
            int minute=0;
            int second=0;
            int longth=M.Lyrics.size();//歌词的行数

            public void run() { //定时器函数体
                playTime.setBounds(220, 450, (starttime+=eachPlayTime)/2, 3);
            	/*if(second==60)
            	{
            		minute=minute+1;
            		second=0;
            	}
            	String rt1=String.format("%02d",minute);
            	String rt2=String.format("%02d",second);
            	String rt=rt1+" : "+rt2;*/
                String rt=timechange(second);
                runtime.setText(rt);
                second=second+1;

                //
                int n=0;
                textLyrics.setText("");     //刷新歌词区

                print:for(n=0;n<longth;n++){
                    int m=0;
                    SimpleAttributeSet set1 ,set2;
                    set1=new SimpleAttributeSet();
                    set2=new SimpleAttributeSet();
                    Document doc;
                    doc=textLyrics.getDocument();
                    StyleConstants.setForeground(set1, Color.pink);
                    StyleConstants.setForeground(set2, Color.white);
                    for(m=0;m<longth;m++) {
                        if(ttt>=M.Lyricstime.get(m*2) && ttt<=M.Lyricstime.get(m*2+1) && n==m) {
                            try {
                                doc.insertString(doc.getLength(), M.Lyrics.get(m), set1);
                                continue print;
                            } catch (BadLocationException e) {}
                        }
                    }

                    try {
                        doc.insertString(doc.getLength(), M.Lyrics.get(n), set2);
                    } catch (BadLocationException e) {}
                }
                ttt++;
            }
        },0,1000);
    }
    public  void timerStop()
    {
        if(nTimer!=null){nTimer.cancel();}
        playTime.setBounds(0,324,0,3);
    }
    @SuppressWarnings("unchecked")//忽略警告
    public void actionPerformed(ActionEvent e)
    {

        //如果是播放按钮点击事件
        if(e.getSource()==buttonPlay){
            if(mylist.size()!=0)
            {
                buttonPlay.setVisible(false);
                audioPlay.SetPlayAudioPath("file:"+mylist.get(0).name);
                audioPlay.play();
                music mc=mylist.get(0);
                File file=new File(mc.name+".wav");
                //System.out.println(mc.playFileDirectory);
                //mc.loadlyrics(mc.playFileDirectory, mc.name);

                int iMusicTime=(int)file.length()/1024/173;
                //System.out.println(iMusicTime);
                audioPlay.SetPlayAudioPath("file:"+mylist.get(0).name+".wav");//设置播放文件
                audioPlay.play();//开始播放
                timerFun(iMusicTime,mc);//打开定时器，移动进度条
            }

        }
        //暂停 事件
        if(e.getSource()==buttonStop)
        {
            buttonPlay.setVisible(true);
            audioPlay.stop();
            timerStop();
        }
        //上一首
        if(e.getSource()==buttonPlay2)
        {
            int Index=0;
            int end=mylist.size()-1;
            for(int i=0;i<mylist.size();i++)
            {
                if(mylist.get(i).name.equals(textarea.getText()))
                {
                    Index=i;
                    break;
                }
            }
            if(Index==0)
            {
                Index=end;
            }
            else
                Index=Index-1;
            audioPlay.SetPlayAudioPath("file:"+mylist.get(Index).name+".wav");//设置播放文件
            audioPlay.play();//开始播放
            music mc=mylist.get(Index);
            //String s=mc.name.substring(0,mc.name.lastIndexOf("."));
            textarea.setText(mc.name);//歌曲名更新
            textarea1.setText(mc.Lyricslist.get(0));//歌手更新
            //music mc1=mylist.get(0);
            File file=new File(mc.name+".wav");
            int iMusicTime=(int)file.length()/1024/173;
            timerFun(iMusicTime,mc);//更新计时器
            buttonPlay.setVisible(false);
        }
        //下一首
        if(e.getSource()==buttonPlay1)
        {
            int Index = 0;
            //System.out.println("mylist.size()"+mylist.size());
            //System.out.println("textarea.getText()"+textarea.getText());
            for(int i=0;i<mylist.size();i++)
            {
                //System.out.println(mylist.get(i).name);
                if(mylist.get(i).name.equals(textarea.getText()))
                {
                    Index=i;
                    //System.out.println("Index1"+Index);
                    break;
                }
            }
            int end=mylist.size()-1;
            if(Index==end)
            {
                Index=0;
            }
            else
                Index=Index+1;
            //System.out.println("Index2"+Index);
            audioPlay.SetPlayAudioPath("file:"+mylist.get(Index).name+".wav");//设置播放文件
            audioPlay.play();//开始播放
            music mc=mylist.get(Index);
            //System.out.println(mc.name);
            //String s1=mc.name.substring(0,mc.name.lastIndexOf("."));
            textarea.setText(mc.name);//歌曲名更新
            textarea1.setText(mc.Lyricslist.get(0));//歌手更新
            //textarea.setText(mc.name);//歌曲名更新
            //music mc1=mylist.get(0);
            File file=new File(mc.name+".wav");
            int iMusicTime=(int)file.length()/1024/173;
            timerFun(iMusicTime,mc);//更新计时器
            buttonPlay.setVisible(false);
        }
        //如果是打开文件按钮点击事件
        if(e.getSource()==buttonPlay3){

            FileDialog openFile=new FileDialog(this,"打开文件");//创建打开文件对话框
            openFile.setVisible(true);//对话框可见
            String playFileName=openFile.getFile();//获取打开的文件名包括后缀
            String playName=playFileName.substring(0, playFileName.lastIndexOf("."));//获得要播放的歌曲名
            String playFileDirectory1=openFile.getDirectory();//获取打开的文件路径
            String playFile=playFileDirectory1+playFileName;//完整的路径+文件名

            audioPlay.SetPlayAudioPath("file:"+playFile);//设置播放文件
            audioPlay.play();//开始播放


            File file=new File(playFileName);
            int iMusicTime=(int)file.length()/1024/173;
            //music mc=new music(playName,timechange(iMusicTime));


            buttonPlay.setVisible(false);
            music mc=new music(playName,timechange(iMusicTime));
            mc.playFileDirectory=playFileDirectory1;
            mc.loadlyrics(mc.playFileDirectory, playName);
            mylist.addFirst(mc);
            textarea.setText(playName);//歌曲名更新
            textarea1.setText(mc.Lyricslist.get(0));//歌手更新
            int number0=mylist.size();
            timerFun(iMusicTime,mc);//更新计时器

            int number=mylist.size();
            //System.out.println(number);
            for(int i=0;i<number;i++)
            {
                music mc1=mylist.get(i);
                //System.out.println(mc1.name);
                if(playFileName.equals(mc1.name))
                {
                    mylist.remove(i);
                    break;
                }
            }

            Vector  vt=new Vector ();	//创建Vector对象，通过add方法添加多行
            Vector  vt0=new Vector ();	//创建Vector对象，用于鼠标点击后的歌曲播放
            for(int k=0;k<number0;k++)
            {
                int j=k+1;
                vt.add(j+"、"+mylist.get(k).name+mylist.get(k).time);
                vt0.add(mylist.get(k).name);
            }
            vt1=vt0;
            listPlayFile.setListData(vt);

        }
    }

    public void mousePressed(MouseEvent e){}//按下鼠标
    public void mouseReleased(MouseEvent e){}//释放鼠标
    public void mouseEntered(MouseEvent e){}//鼠标进入
    public void  mouseExited(MouseEvent e){}//鼠标离开
    public void  mouseClicked(MouseEvent e){//点击鼠标
        if (e.getClickCount() == 2) {//如果鼠标连续点击两次
            if(e.getSource()==listPlayFile){//如果事件源是播放列表，即在播放列表控件中双击，则执行。
                //添加双击播放列表中的代码，比如获取歌曲名，并且播放。
                int index=listPlayFile.getSelectedIndex();
                String str=vt1.get(index);
                //System.out.println(str);
                music mc=mylist.get(index);

					 /*int iMusicTime=(int)file.length()/1024/173;
					 //music mc=new music(playName,timechange(iMusicTime));
					 music mc=new music(playName,timechange(iMusicTime));*/

                //String s2=str.substring(0,str.lastIndexOf("."));
                textarea.setText(str);//歌曲名更新
                //textarea.setText(str);//歌曲名更新
                textarea1.setText(mc.Lyricslist.get(0));
                audioPlay.SetPlayAudioPath("file:"+str+".wav");//设置播放文件
                audioPlay.play();//开始播放
                int itime=mc.timetransform(mc.time);
                timerFun(itime,mc);
            }
        }
    }

}


//主函数
public class MusicPlay{
    @SuppressWarnings("unchecked")//忽略警告
    public static void main(String[] args) {
        MyExtendsJFrame frame=new MyExtendsJFrame();//创建聊天程序窗口
    }
}

