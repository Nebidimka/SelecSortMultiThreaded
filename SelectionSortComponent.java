package project;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aleksandr Skidelskiy on 12/08/2015.
 */

public class SelectionSortComponent extends JComponent {

    public SelectionSortComponent() {
        int[] anArray = Operators.randomIntArray(100, 500);
        sorter = new SelectSort(anArray, this);
    }

    public void paintComponent(Graphics g) {
        sorter.draw((Graphics2D)g);
    }

    public void startAnimation() {
        class AnimationRunnable implements Runnable {
            public void run() {
                try {
                    sorter.sort();
                } catch (InterruptedException exception) {
                    System.out.println("Exception encountered.");
                }
            }
        }
        Runnable r = new AnimationRunnable();
        Thread t = new Thread(r);
        t.start();
    }
    private SelectSort sorter;
}
