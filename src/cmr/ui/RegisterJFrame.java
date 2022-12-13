package cmr.ui;

import javax.swing.*;

/**
 * @author: Java_cmr
 * @Date: 2022/12/10 - 2:36
 */
//RegisterJFrame表示注册界面
//跟注册相关的代码写在该类
public class RegisterJFrame extends JFrame {

    public RegisterJFrame(){
        //设置注册界面的宽 高
        this.setSize(488, 500);

        //设置界面的标题
        this.setTitle("拼图——注册");

        //设置界面置顶
        this.setAlwaysOnTop(true);

        //设置界面居中
        this.setLocationRelativeTo(null);

        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //显示界面
        this.setVisible(true);
    }
}
