package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: Java_cmr
 * @Date: 2022/12/13 - 16:28
 */
public class Test1 {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(603, 680);
        jFrame.setTitle("事件演示");
        jFrame.setAlwaysOnTop(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLayout(null);

        JButton jbt = new JButton("点我");
        jbt.setBounds(0, 0, 100, 50);
        jbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("点了我一下");
            }
        });

        jFrame.getContentPane().add(jbt);

        jFrame.setVisible(true);
    }
}
