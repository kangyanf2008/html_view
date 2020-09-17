package com.ps;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import java.awt.*;
import java.io.IOException;

public class HTMLView extends JFrame implements HyperlinkListener {
    public HTMLView() throws Exception   {
        //setSize(640, 480);
        setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
        setTitle("智慧通行");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JEditorPane editorPane = new JEditorPane();

        //放到滚动窗格中才能滚动查看所有内容
        JScrollPane scrollPane = new JScrollPane(editorPane);
        //设置是显示网页 html 文件,可选项
        //editorPane.setContentType("text/html");
        //设置成只读，如果是可编辑，你会看到显示的样子也是不一样的，true显示界面
        editorPane.setEditable(false);
        //要能响应网页中的链接，则必须加上超链监听器
        editorPane.addHyperlinkListener(this);
        //String path = "http://127.0.0.1:80";
        String path = "http://www.baidu.com";
        //String path = "http://192.168.34.149/#/login";
        try {
            editorPane.setPage(path);
        }  catch (IOException e)
        {
            System.out.println("读取页面 " + path + " 出错. " + e.getMessage());
        }
        Container container = getContentPane();

        //让editorPane总是填满整个窗体
        container.add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
        {
            JEditorPane pane = (JEditorPane) e.getSource();
            if (e instanceof HTMLFrameHyperlinkEvent)
            {
                HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) e;
                HTMLDocument doc = (HTMLDocument) pane.getDocument();
                doc.processHTMLFrameHyperlinkEvent(evt);
            }
            else
            {
                try
                {
                    pane.setPage(e.getURL());
                }
                catch (Throwable t)
                {
                    t.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        JFrame frame = new HTMLView();
        frame.setVisible(true);
    }


}
