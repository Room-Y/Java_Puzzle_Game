package cmr.ui;

import cmrUtil.CodeUtil;
import domian.User;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * @author: Java_cmr
 * @Date: 2022/12/10 - 2:32
 */

//LogininJFrame表示注册界面
//跟登陆相关的代码都写在这个类
public class LogininJFrame extends JFrame implements MouseListener {
    static ArrayList<User> allUsers = new ArrayList<>();
    static {
        allUsers.add(new User("admin", "1"));
        allUsers.add(new User("sy", "520"));
        allUsers.add(new User("beibei", "1"));
    }

    String path = "image\\login\\";

    //登陆注册按键
    JButton login = new JButton();
    JButton register = new JButton();

    //用户名，密码，验证码输入框
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JTextField code = new JTextField();

    //登录/注册 按下/松开
    ImageIcon loginPressed = new ImageIcon(path + "loginPressed" + ".png");
    ImageIcon loginReleased = new ImageIcon(path + "loginReleased" + ".png");
    ImageIcon registerPressed = new ImageIcon(path + "registerPressed" + ".png");
    ImageIcon registerReleased = new ImageIcon(path + "registerReleased" + ".png");


    //正确的验证码
    JLabel rightCode = new JLabel();

    public LogininJFrame(){
        //初始化界面
        initFrame();

        initDesktop();

        //显示界面
        this.setVisible(true);
    }

    private void initDesktop() {
        //用户名 + 输入框
        JLabel usernamePNG = new JLabel(new ImageIcon(path + "username" + ".png"));
        usernamePNG.setBounds(100,150,59,22);
        this.getContentPane().add(usernamePNG);

        username.setBounds(180,150,150,30);
        this.getContentPane().add(username);

        //密码 + 输入框
        JLabel passwordPNG = new JLabel(new ImageIcon(path + "password" + ".png"));
        passwordPNG.setBounds(100, 200, 59, 22);
        this.getContentPane().add(passwordPNG);

        password.setBounds(180, 200, 150, 30);
        this.getContentPane().add(password);

        //验证码 + 输入框
        JLabel codePNG = new JLabel(new ImageIcon(path + "verification" + ".png"));
        codePNG.setBounds(100, 250, 59, 22);
        this.getContentPane().add(codePNG);

        code.setBounds(180, 250, 80, 30);
//        code.addMouseListener(this);
        this.getContentPane().add(code);

        //添加验证码
        String codStr = CodeUtil.getCode();
        rightCode.setText(codStr);
        rightCode.addMouseListener(this);
        rightCode.setBounds(280, 250, 50, 30);
        this.getContentPane().add(rightCode);

        //添加登录键
        login.setBounds(100, 300, 128, 47);
        login.setIcon(loginReleased);
        //去除默认边框 背景
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        login.addMouseListener(this);
        this.getContentPane().add(login);

        //添加注册键
        register.setBounds(250, 300, 128, 47);
        register.setIcon(registerReleased);
        //去除默认边框 背景
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        register.addMouseListener(this);
        this.getContentPane().add(register);

        //添加背景图
        JLabel background = new JLabel(new ImageIcon(path + "background" + ".png"));
        background.setBounds(0,0,470,390);
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();
    }

    private void initFrame() {
        //设置登录界面的宽 高
        this.setSize(488, 430);

        //设置界面的标题
        this.setTitle("拼图——登陆");

        //设置界面置顶
        this.setAlwaysOnTop(true);

        //设置界面居中
        this.setLocationRelativeTo(null);

        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //取消默认的居中方式
        this.setLayout(null);

        //显示界面
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == login){
            System.out.println("点击了登录按钮");

            //获取三个文本框的内容
            String usernameInput = username.getText();
            String passwordInput = new String(password.getPassword());
            String codeInput = code.getText();

            User userInput = new User(usernameInput, passwordInput);
            System.out.println("用户输入的用户名为" + usernameInput);
            System.out.println("用户输入的密码为" + passwordInput);

            if(codeInput.length() == 0){
                showJDialog("验证码不能为空");
            } else if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                showJDialog("用户名或密码不能为空");
            } else if(!codeInput.equalsIgnoreCase(rightCode.getText())){
                showJDialog("验证码输入错误");
            } else if(contains(userInput)){
                System.out.println("用户名和密码正确可以开始玩游戏了");
                this.setVisible(false);
                new GameJFrame();
            } else {
                showJDialog("用户名或密码输入错误");
            }

        }else if(e.getSource() == register){
            System.out.println("点击了注册按钮");
            showJDialog("暂无法注册，使用admin/1或者隐藏用户名登录");



        }else if(e.getSource() == rightCode){
            System.out.println("更换验证码");
            rightCode.setText(CodeUtil.getCode());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() == register){
            register.setIcon(registerPressed);
        }else if(e.getSource() == login){
            login.setIcon(loginPressed);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource() == register){
            register.setIcon(registerReleased);
        }else if(e.getSource() == login){
            login.setIcon(loginReleased);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void showJDialog(String content){
        JDialog jDialog = new JDialog();

        JLabel warning = new JLabel(content);
        warning.setBounds(0,0,300,150);
        jDialog.getContentPane().add(warning);

        jDialog.setSize(300, 150);
        //在顶部
        jDialog.setAlwaysOnTop(true);
        //居中
        jDialog.setLocationRelativeTo(null);
        //不关闭弹窗无法继续操作下面的界面
        jDialog.setModal(true);
        //显示弹窗
        jDialog.setVisible(true);
    }

    public boolean contains(User user){
        for(int i = 0; i < allUsers.size(); i++){
            if(allUsers.get(i).getUsername().equals(user.getUsername())
                    && allUsers.get(i).getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }
}
