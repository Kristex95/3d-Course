package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class ProgramB {

    public static boolean semaphore = true;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setSize(500, 700);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        Slider slider = new Slider();

        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(20);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        panel.add(slider);

        myThread2[] threads = new myThread2[2];

        //Пуск 1
        JButton start1 = new JButton("Пуск 1");
        JButton start2 = new JButton("Пуск 2");
        JButton stop1 = new JButton("Стоп 1");
        JButton stop2 = new JButton("Стоп 2");
        JLabel label = new JLabel("Семафор: вільний");

        start1.addActionListener(
                (ActionEvent e) -> {
                    if(semaphore == true) {
                        myThread2 th = new myThread2(-1, slider, 1);
                        threads[0] = th;
                        th.start();
                        start1.setEnabled(false);
                        start2.setEnabled(false);
                        stop1.setEnabled(true);
                        stop2.setEnabled(false);
                        label.setText("Семафор: зайнятий");

                        semaphore = false;

                    }
                });
        panel.add(start1);

        //Пуск 2
        start2.addActionListener(
                (ActionEvent e) -> {
                    if(semaphore == true) {
                        myThread2 th = new myThread2(+1, slider, 10);
                        threads[1] = th;
                        th.start();
                        start1.setEnabled(false);
                        start2.setEnabled(false);
                        stop2.setEnabled(true);
                        stop1.setEnabled(false);
                        label.setText("Семафор: зайнятий");

                        semaphore = false;
                    }
                });
        panel.add(start2);

        //Cтоп 1
        stop1.addActionListener(
                (ActionEvent e) -> {
                    if(semaphore == false) {
                        threads[0].interrupt();
                        start1.setEnabled(true);
                        start2.setEnabled(true);
                        stop2.setEnabled(true);
                        label.setText("Семафор: вільний");

                        semaphore = true;
                    }
                });
        panel.add(stop1);

        //Стоп 2
        stop2.addActionListener(
                (ActionEvent e) -> {
                    if(semaphore == false) {
                        threads[1].interrupt();
                        start1.setEnabled(true);
                        start2.setEnabled(true);
                        stop1.setEnabled(true);
                        label.setText("Семафор: вільний");

                        semaphore = true;
                    }
                });
        panel.add(stop2);

        panel.add(label);


        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
class myThread2 extends Thread{
    Slider slider;
    private int priority;
    private int increment;
    private int bound = 1000000;
    private int count = 0;

    public myThread2(int increment, Slider slider, int priority){
        this.increment = increment;
        this.slider = slider;
        this.priority = priority;
        setPriority(priority);
    }

    @Override
    public void run() {
        int val = 0;
        while(!interrupted()) {
            ++count;
            val = slider.getValue();
            if(count > bound && ((val >= 10 && increment < 0) || (val <= 90 && increment>0))){
                setPriority(priority);
                slider.Increase(increment);
                count = 0;
            }
        }
    }
}
