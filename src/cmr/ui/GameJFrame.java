package cmr.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.xml.crypto.Data;
import java.util.Random;

/**
 * @author: Java_cmr
 * @Date: 2022/12/10 - 2:31
 */
//GameJFrame表示游戏的主界面
//跟游戏相关的业务逻辑都写在这个类中
public class GameJFrame extends JFrame {

    //加载图片的时候根据二维数组数据加载
    int[][] data = new int[4][4];
    public GameJFrame(){
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据（根据打乱结果加载数据）
        initData();

        //初始化图片
        initImage();

        //显示界面
        this.setVisible(true);
    }

    //初始化数据
    private void initData() {
        int[] tempArr = {1, 2, 3, 4, 5, 6, 7, 8 ,9 ,10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for(int i = 0; i < tempArr.length; i++){
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[index];
            tempArr[index] = tempArr[i];
            tempArr[i] = temp;
        }

        for(int i = 0; i < tempArr.length; i++){
            data[i/4][i%4] = tempArr[i];
        }
    }

    //初始化图片
    private void initImage() {
        //设置拼图
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                //创建一个JLable对象
                JLabel jLabel = new JLabel(new ImageIcon("D:\\Java\\Java_Puzzle_Game\\image\\animal\\animal3\\" + data[i][j] + ".jpg"));

                //指定图片位置
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);

                //图片添加边框
                jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));

                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);
            }
        }

        //设置背景图片
        JLabel background = new JLabel(new ImageIcon("D:\\Java\\Java_Puzzle_Game\\image\\background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);
    }


    //初始化菜单
    private void initJMenuBar() {
        //初始化菜单栏
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单两个选项的对象
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        //创建选项下的条目对象
        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reLoginItem = new JMenuItem("重新登陆");
        JMenuItem closeItem = new JMenuItem("关闭游戏");

        JMenuItem accountItem = new JMenuItem("公众号");

        //将选项的条目添加到对应选项中
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //将选项添加到菜单栏中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    //初始化界面
    private void initJFrame() {
        //设置游戏界面的宽 高
        this.setSize(603, 680);

        //设置界面的标题
        this.setTitle("拼拼小沈同学");

        //设置界面置顶
        this.setAlwaysOnTop(true);

        //设置界面居中
        this.setLocationRelativeTo(null);

        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //取消默认的居中方式
        this.setLayout(null);
    }

}
