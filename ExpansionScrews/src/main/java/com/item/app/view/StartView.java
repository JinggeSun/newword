package com.item.app.view;

import com.item.app.config.ConfigApp;

import javax.swing.*;
import java.awt.*;

/**
 * 欢迎页面
 * @author zcm
 */
public class StartView {

    public void showView() {
        /*
         * 创建一个顶层容器
         */
        JFrame jf = new JFrame("膨胀螺丝");
        /// 设置窗口大小
        jf.setSize(300, 250);
        // 把窗口位置设置到屏幕中心
        jf.setLocationRelativeTo(null);
        // 当点击窗口的关闭按钮时退出程序
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //窗口禁止拉伸
        jf.setResizable(false);
        //设置图标
        jf.setIconImage(new ImageIcon("src\\main\\resources\\image\\screw.png").getImage());


        /*
         * 面板
         * 使用流布局
         */
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.CENTER);

        JPanel panel = new JPanel(null);

        // 功能介绍
        JLabel introduceLabel = new JLabel();
        introduceLabel.setText(ConfigApp.INTRODUCE);
        introduceLabel.setBounds(20,20,250,100);

        // 使用按钮
        JButton btn = new JButton("开始使用");
        btn.setBounds(100,150,100,40);
        btn.addActionListener(event->{
            //打开新的窗口
            new MainView().showView();
            //关闭当前窗口
            jf.dispose();

        });

        // 版本介绍
        JLabel jLabel = new JLabel("github.com/JinggeSun@2020");
        jLabel.setBounds(65,190,200,40);

        panel.add(introduceLabel);
        panel.add(btn);
        panel.add(jLabel);

        // 填充面板
        jf.setContentPane(panel);
        // 显示窗口
        jf.setVisible(true);
    }

}
