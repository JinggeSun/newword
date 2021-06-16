package com.item.app.view;

import com.item.app.config.ConfigApp;
import com.item.app.model.DbModel;
import com.item.app.model.ScrewModel;
import com.item.app.util.DataCheckUtil;
import com.item.app.util.DbUtil;
import com.item.app.util.ScrewUtil;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Arrays;

/**
 * 主界面
 * @author zcm
 */
public class MainView {

    private static JComboBox<String> dbComboBox;
    private static JTextField ipField;
    public static JTextField portField;
    public static JTextField tableField;
    public static JTextField usernameField;
    public static JPasswordField passwordField;
    public static JTextField exportField;
    public static JLabel tableLabel;
    public static JButton testButton;
    private static JComboBox<String> typeComboBox;


    public void showView() {
        /*
         * 创建一个顶层容器
         */
        JFrame jFrame = new JFrame("膨胀螺丝" + ConfigApp.getVersion());
        /// 设置窗口大小
        jFrame.setSize(500, 650);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);




        /**
         * 菜单栏
         */
        // 菜单面板
        JMenuBar jMenuBar = new JMenuBar();
        //一级菜单
        JMenu jMenuInfo = new JMenu("信息");
        JMenu jMenuWin = new JMenu("高级");
        JMenu jMenuHelp = new JMenu("帮助");

        //菜单item
        JMenuItem jMenuItemInfo = new JMenuItem("设置");
        JMenuItem jMenuItemSer = new JMenuItem("服务");
        JMenuItem jMenuItemVer = new JMenuItem("版本");

        JMenuItem jMenuItemMax = new JMenuItem("查询");
        JMenuItem jMenuItemHid = new JMenuItem("分析");

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
       // jTabbedPane.addTab("日志",logTablePanel());

        jFrame.add(jTabbedPane);
        jFrame.setJMenuBar(jMenuBar);
        jFrame.setVisible(true);

    }

    /**
     * 配置主页面，常规设置
     * 服务面板
     */
    private static Component serverPanel() {

        JPanel jPanel = new JPanel(null);

        //1. 下拉框，选择数据库
        JLabel dbLabel = new JLabel("选择数据库");
        dbLabel.setBounds(70,30,100,30);

        // 创建一个下拉列表框
        dbComboBox = new JComboBox<String>(ConfigApp.getSupportDb());
        dbComboBox.setSelectedIndex(0);
        dbComboBox.setBounds(175,30,200,30);

        //2. IP 地址
        JLabel ipLabel = new JLabel("IP地址");
        ipLabel.setBounds(70,60,100,30);

        ipField = new JTextField(8);
        ipField.setBounds(175,60,200,30);
        ipField.setFont(new Font(null, Font.PLAIN, 16));
        //3. 端口
        JLabel portLabel = new JLabel("端口号");
        portLabel.setBounds(70,90,100,30);
        portField = new JTextField(8);
        portField.setBounds(175,90,200,30);
        portField.setFont(new Font(null, Font.PLAIN, 16));
        //4. 表名/实例
        tableLabel = new JLabel("表名");
        tableLabel.setBounds(70,120,100,30);
        tableField = new JTextField(8);
        tableField.setBounds(175,120,200,30);
        tableField.setFont(new Font(null, Font.PLAIN, 16));
        //5. 用户名
        JLabel usernameLabel = new JLabel("登陆名");
        usernameLabel.setBounds(70,150,100,30);
        usernameField = new JTextField(8);
        usernameField.setBounds(175,150,200,30);
        usernameField.setFont(new Font(null, Font.PLAIN, 16));
        //6. 密码
        JLabel passwordLabel = new JLabel("密码");
        passwordLabel.setBounds(70,180,100,30);
        passwordField = new JPasswordField(8);
        passwordField.setBounds(175,180,200,30);
        passwordField.setFont(new Font(null, Font.PLAIN, 16));
        //7. 导出位置
        JLabel exportLabel = new JLabel("位置");
        exportLabel.setBounds(70,210,100,30);
        exportField = new JTextField(8);
        exportField.setBounds(175,210,160,30);
        exportField.setFont(new Font(null, Font.PLAIN, 16));
        exportField.setEditable(false);
        JButton exportButton = new JButton("选择");
        exportButton.setBounds(345,215,30,20);

        //8。导出类型
        JLabel typeLabel = new JLabel("导出类型");
        typeLabel.setBounds(70,240,160,30);
        // 创建一个下拉列表框
        typeComboBox = new JComboBox<String>(ConfigApp.getExportType());
        typeComboBox.setSelectedIndex(0);
        typeComboBox.setBounds(175,240,200,30);

        //9. 测试连接
        testButton = new JButton("测试连接");
        testButton.setBounds(175,290,80,30);
        //10. 导出
        JButton saveButton = new JButton("导出文档");
        saveButton.setBounds(275,290,80,30);


        /**
         * 下拉监听事件
         */
        dbComboBox.addItemListener(item->{
            DbModel dbModel = ConfigApp.getDbModelByDb(item.getItem().toString());
            initViewBySelect(dbModel);
        });

        /**
         * 选择文件
         */
        exportButton.addActionListener(action->{
            JFileChooser jf = new JFileChooser();
            jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jf.showOpenDialog(jPanel);//显示打开的文件对话框
            File f =  jf.getSelectedFile();//使用文件类获取选择器选择的文件
            String s = f.getAbsolutePath();//返回路径名
            //赋值exportField
            exportField.setText(s);
        });

        /**
         * 测试链接
         */
        testButton.addActionListener(action->{
            //1. 验证输入信息
            ScrewModel model = checkInputParam();

            if (model == null){
                JOptionPane.showMessageDialog(null, "请填写完整信息!");
                return;
            }
            //将实体发送给jdbc工具类
            String connectFlag = DbUtil.connectTest(model);
            if (connectFlag != null && connectFlag.length() > 0){
                JOptionPane.showMessageDialog(null, "数据库连接失败，请检查!");
                return ;
            }
            JOptionPane.showMessageDialog(null, "数据库连接成功!");
        });

        /**
         * 导出文档
         */
        saveButton.addActionListener(action->{
            //1. 验证输入信息
            ScrewModel model = checkInputParam();

            if (model == null){
                JOptionPane.showMessageDialog(null, "请填写完整信息!");
                return;
            }
            //将实体发送给jdbc工具类
            String connectFlag = DbUtil.connectTest(model);
            if (connectFlag == null  || connectFlag.length() > 0){
                JOptionPane.showMessageDialog(null, "数据库连接失败，请检查,"+connectFlag);
                return ;
            }
            //数据库验证完毕，开始使用screw导出
            DataSource dataSource = DbUtil.getDataSource(model);
            System.err.println("------->" + model.getFileOutputDir());
            String msg = ScrewUtil.exportDoc(model.getFileOutputDir(),dataSource);

            JOptionPane.showMessageDialog(null, msg);

        });

        //加载
        jPanel.add(dbLabel);
        jPanel.add(dbComboBox);
        jPanel.add(ipLabel);
        jPanel.add(ipField);
        jPanel.add(portLabel);
        jPanel.add(portField);
        jPanel.add(ipField);
        jPanel.add(tableLabel);
        jPanel.add(tableField);
        jPanel.add(usernameLabel);
        jPanel.add(usernameField);
        jPanel.add(passwordLabel);
        jPanel.add(passwordField);
        jPanel.add(exportLabel);
        jPanel.add(exportField);
        jPanel.add(exportButton);
        jPanel.add(typeLabel);
        jPanel.add(typeComboBox);
        jPanel.add(testButton);
        jPanel.add(saveButton);


        return jPanel;
    }

    private static ScrewModel checkInputParam() {

        ScrewModel screwModel = new ScrewModel();

        //下拉框对应的数据库
        String dbComboParam = (String) dbComboBox.getSelectedItem();
        String ipFieldParam = ipField.getText();
        String portFieldParam = portField.getText();
        String tableFieldParam = tableField.getText();
        String usernameFieldParam = usernameField.getText();
        String passwordFieldParam = String.valueOf(passwordField.getPassword());
        String exportFieldParam = exportField.getText();
        String typeComboParam = (String) typeComboBox.getSelectedItem();


        boolean flag = DataCheckUtil.checkStringEmpty(dbComboParam,ipFieldParam,portFieldParam,tableFieldParam,usernameFieldParam,passwordFieldParam,exportFieldParam);

        if (!flag){
            return null;
        }

        //填写的数据赋值给实体
        //用户名
        screwModel.setUserName(usernameFieldParam);
        //密码
        screwModel.setPassword(passwordFieldParam);
        //驱动
        screwModel.setDriverClassName(ConfigApp.getDriverByKey(dbComboParam));
        //连接
        String url = ConfigApp.getJdbcUrl(dbComboParam,ipFieldParam,portFieldParam,tableFieldParam);
        screwModel.setJdbcUrl(url);
        //导出路径
        screwModel.setFileOutputDir(exportFieldParam);
        //导出类型

        return screwModel;
    }

    /**
     * 日志面板
     * JTable
     * @return
     */
    private static Component logTablePanel() {

      return null;
    }

    private static void initViewBySelect(DbModel dbModel){
        ipField.setText(dbModel.getIpAddr());
        portField.setText(dbModel.getPort()+"");
        usernameField.setText(dbModel.getUsername());
        tableLabel.setText(dbModel.getTableLabel());
    }

}
