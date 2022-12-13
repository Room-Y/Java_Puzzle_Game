package cmr.ui;

import javax.swing.*;

/**
 * @author: Java_cmr
 * @Date: 2022/12/10 - 2:32
 */

//LogininJFrame表示注册界面
//跟登陆相关的代码都写在这个类
public class LogininJFrame extends JFrame {

    public LogininJFrame(){
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

        //显示界面
        this.setVisible(true);
    }
}
