package cmr.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * @author: Java_cmr
 * @Date: 2022/12/10 - 2:31
 */
//GameJFrame表示游戏的主界面
//跟游戏相关的业务逻辑都写在这个类中
public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    //加载图片的时候根据二维数组数据加载
    int[][] data = new int[4][4];

    //空白方块的位置
    int x;
    int y;

    //步数
    int step = 0;

    //当前展示的图片
    String path = "image\\animal\\animal3\\";

    //胜利条件
    int[][] win = new int[][]{
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0},
    };

    //创建选项下的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登陆");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("按A试试看？");

    JMenu changeImage = new JMenu("更换图片");
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");
    JMenuItem sy = new JMenuItem("sy~");

    public GameJFrame(){
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据（根据打乱结果加载数据）1
        initData();

        //初始化图片
        initImage();

        //显示界面
        this.setVisible(true);
    }

    //初始化数据
    private void initData()  {
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8 ,9 ,10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for(int i = 0; i < tempArr.length; i++){
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[index];
            tempArr[index] = tempArr[i];
            tempArr[i] = temp;
        }

        for(int i = 0; i < tempArr.length; i++){
            if(tempArr[i] == 0){
                x = i/4;
                y = i%4;
            }
            data[i/4][i%4] = tempArr[i];
        }
    }

    //初始化图片
    private void initImage() {
        //删除所有图片
        this.getContentPane().removeAll();

        //显示胜利图标
        if(victory()){
            JLabel winLabel = new JLabel(new ImageIcon("image\\win.png"));
            winLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winLabel);
        }

        JLabel stepCount = new JLabel("步数: " + step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        //图片后添加的在下方
        //设置拼图
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                //创建一个JLable对象
                JLabel jLabel = new JLabel(new ImageIcon( path + data[i][j] + ".jpg"));

                //指定图片位置
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);

                //图片添加边框
                jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));

                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);
            }
        }

        //设置背景图片
        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();
    }


    //初始化菜单
    private void initJMenuBar() {
        //初始化菜单栏
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单两个选项的对象
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("想作弊嘛？");

        //将选项的条目添加到对应选项中
        functionJMenu.add(changeImage);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);


        //更换图片功能组件
        changeImage.add(girl);
        changeImage.add(sport);
        changeImage.add(animal);
        changeImage.add(sy);
        //添加监听
        girl.addActionListener(this);
        sport.addActionListener(this);
        animal.addActionListener(this);
        sy.addActionListener(this);

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

        //添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    //按下不松会调用，且一直调用
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 65 && !victory()){
            //删除所有图片
            this.getContentPane().removeAll();

            //加载完整图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);

            //设置背景图片
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);

            this.getContentPane().repaint();
        }
    }

    //松开时调用该方法
    @Override
    public void keyReleased(KeyEvent e) {
        if(victory()){
            return;
        }

        int code = e.getKeyCode();
        if(code == 37){
            System.out.println("向左移动");
            if(y == 3)
                return;
            data[x][y] = data[x][y+1];
            data[x][y+1] = 0;
            y++;
            step++;
            initImage();
        }else if(code == 38){
            System.out.println("向上移动");
            if(x == 3)
                return;
            data[x][y] = data[x+1][y];
            data[x+1][y] = 0;
            x++;
            step++;
            initImage();
        }else if(code == 39){
            System.out.println("向右移动");
            if(y == 0)
                return;
            data[x][y] = data[x][y-1];
            data[x][y-1] = 0;
            y--;
            step++;
            initImage();
        }else if(code == 40){
            System.out.println("向下移动");
            if(x == 0)
                return;
            data[x][y] = data[x-1][y];
            data[x-1][y] = 0;
            x--;
            step++;
            initImage();
        }else if(code == 65){
            initImage();
        }else if(code == 87){
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0},
            };
            x = 3;
            y = 3;
            initImage();
        }
    }

    //判断是否胜利
    public boolean victory(){
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[i].length; j++){
                if(data[i][j] != win[i][j])
                    return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的对象
        Object obj = e.getSource();
        if(obj == replayItem){
            System.out.println("重新游戏");

            //清空当前数据
            step = 0;
            initData();
            initImage();

        }else if(obj == closeItem){
            System.out.println("关闭游戏");

            //直接关闭虚拟机
            System.exit(0);
        }else if(obj == reLoginItem){
            System.out.println("重新登陆");

            //关闭当前游戏界面
            this.setVisible(false);
            new LogininJFrame();
        }else if(obj == accountItem){
            System.out.println("作弊");

            //创建一个弹框对象
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("image\\steal.jpg"));
            jLabel.setBounds(0,0,258,258);

            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344,344);
            //在顶部
            jDialog.setAlwaysOnTop(true);
            //居中
            jDialog.setLocationRelativeTo(null);
            //不关闭弹窗无法继续操作下面的界面
            jDialog.setModal(true);
            //显示弹窗
            jDialog.setVisible(true);
        }else if(obj == girl || obj == sport || obj == animal || obj == sy) {
            Random r = new Random();
            if (obj == girl) {
                path = "image\\girl\\girl" + (r.nextInt(13) + 1) + "\\";
            } else if (obj == sport) {
                path = "image\\sport\\sport" + (r.nextInt(10) + 1) + "\\";
            } else if (obj == animal) {
                path = "image\\animal\\animal" + (r.nextInt(8) + 1) + "\\";
            } else {
                path = "image\\sy\\sy" + (r.nextInt(20) + 1) + "\\";
            }
            this.initData();
            this.initImage();
        }
    }
}
