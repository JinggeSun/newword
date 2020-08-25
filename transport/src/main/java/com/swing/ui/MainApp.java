package com.swing.ui;

import com.item.MyThriftServer;

import javax.swing.*;

/**
 * @author zcm
 */
public class MainApp {

    public static void main(String[] args) {

        JFrame jFrame = new JFrame("山狮");
        jFrame.setSize(400,500);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();

        JButton jButton = new JButton("开始");



        jButton.addActionListener(e -> {
            String text = jButton.getText();
            String flag = "开始";
            if (flag.equals(text)) {
                jButton.setText("关闭");
                
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MyThriftServer myThriftServer = new MyThriftServer();
                        myThriftServer.startThriftServer();
                    }
                }).start();
            }else{
                MyThriftServer myThriftServer = new MyThriftServer();
                myThriftServer.stopThriftServer();
                jButton.setText("开始");
            }

        });

        jPanel.add(jButton);
        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }
}
