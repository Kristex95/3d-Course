package main;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ProgramA {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        Slider slider = new Slider();

        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(20);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        panel.add(slider);

        SpinnerModel spinnerModel1 = new SpinnerNumberModel(1, Thread.MIN_PRIORITY, Thread.MAX_PRIORITY, 1);
        SpinnerModel spinnerModel2 = new SpinnerNumberModel(1, Thread.MIN_PRIORITY, Thread.MAX_PRIORITY, 1);
        JSpinner spinner1 = new JSpinner(spinnerModel1);
        JSpinner spinner2 = new JSpinner(spinnerModel2);
        panel.add(spinner1);
        panel.add(spinner2);

        myThread th1 = new myThread(-1, slider, spinner1);
        myThread th2 = new myThread(+1, slider, spinner2);

        JButton btn = new JButton("Start");
        btn.addActionListener(
                (ActionEvent e) -> {
                    synchronized (slider) {
                        th1.start();
                        th2.start();
                    }
                });
        panel.add(btn);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}

class Slider extends JSlider{
    public Slider(){
        super(0, 100, 50);
    }
    public void Increase(int value){
        setValue(getValue() + value);
    }
}

class myThread extends Thread{
    Slider slider;
    private int priority;
    private JSpinner spinner;
    private int increment;
    private int bound = 1000000;
    private int count = 0;

    public myThread(int increment, Slider slider, JSpinner spinner){
        this.increment = increment;
        this.slider = slider;
        this.spinner = spinner;
        priority = (int)spinner.getValue();
        setPriority(priority);
    }

    @Override
    public void run() {
        int val = 0;
        while(!interrupted()) {
            ++count;
            val = slider.getValue();
            if(count > bound && ((val >= 10 && increment < 0) || (val <= 90 && increment>0))){
                priority = (int)spinner.getValue();
                setPriority(priority);
                slider.Increase(increment);
                count = 0;
            }
        }
    }
}
