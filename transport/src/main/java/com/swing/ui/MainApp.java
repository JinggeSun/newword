package com.swing.ui;

import com.item.MyThriftServer;
import com.test.LogDataTest;

import javax.swing.*;
import java.awt.*;

/**
 * @author zcm
 */
public class MainApp {

    public static void main(String[] args) {

        JFrame jFrame = new JFrame("山狮");
        jFrame.setSize(400,500);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /**
         * 菜单栏
         */
        // 菜单面板
        JMenuBar jMenuBar = new JMenuBar();
        //一级菜单
        JMenu jMenuInfo = new JMenu("信息");
        JMenu jMenuWin = new JMenu("窗口");
        JMenu jMenuHelp = new JMenu("帮助");

        //菜单item
        JMenuItem jMenuItemInfo = new JMenuItem("设置");
        JMenuItem jMenuItemSer = new JMenuItem("服务");
        JMenuItem jMenuItemVer = new JMenuItem("版本");

        JMenuItem jMenuItemMax = new JMenuItem("最大");
        JMenuItem jMenuItemHid = new JMenuItem("隐藏");

        JMenuItem jMenuItemDoc = new JMenuItem("文档");
        JMenuItem jMenuItemAbo = new JMenuItem("联系");
        JMenuItem jMenuItemReg = new JMenuItem("注册");

        jMenuInfo.add(jMenuItemInfo);
        jMenuInfo.add(jMenuItemSer);
        jMenuInfo.add(jMenuItemVer);

        jMenuWin.add(jMenuItemMax);
        jMenuWin.add(jMenuItemHid);

        jMenuHelp.add(jMenuItemDoc);
        jMenuHelp.add(jMenuItemAbo);
        jMenuHelp.add(jMenuItemReg);

        jMenuBar.add(jMenuInfo);
        jMenuBar.add(jMenuWin);
        jMenuBar.add(jMenuHelp);

        /**
         * 使用JTabbedPane作为主界面
         */
        JTabbedPane jTabbedPane = new JTabbedPane();

        jTabbedPane.addTab("服务",serverPanel());
        jTabbedPane.addTab("日志",logTablePanel());

        jFrame.add(jTabbedPane);
        jFrame.setJMenuBar(jMenuBar);
        jFrame.setVisible(true);
    }


    /**
     * 服务面板
     */
    private static Component serverPanel() {

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

        return jPanel;
    }

    /**
     * 日志面板
     * JTable
     * @return
     */
    private static Component logTablePanel() {

        String[] title = LogDataTest.getTitle();

        String[][] content = LogDataTest.getTableContent();

        JTable jTable = new JTable(content,title);
        JScrollPane jp= new JScrollPane(jTable);

        return jp;
    }
}
