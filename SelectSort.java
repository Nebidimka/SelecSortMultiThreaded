package project;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by Aleksandr Skidelskiy on 12/08/2015.
 */
public class SelectSort {
    public SelectSort(int[] a, JComponent aComponent) {
            sortStateLock = new ReentrantLock();
            component = aComponent;
            anArray=a;
        }

    public void sort() throws InterruptedException {
        for (int i = 0; i < anArray.length - 1; i++) {
            int minPos = minimumPosition(i);
            sortStateLock.lock();
            try {
                Operators.swap(anArray, minPos, i);
                alreadySorted = i;
            } finally {
                sortStateLock.unlock();
            }
            pause(1);
        }
    }

    private int minimumPosition(int from) throws InterruptedException {
        int minPos = from;
        for (int i = from + 1; i < anArray.length; i++) {
            sortStateLock.lock();
            try {
                if (anArray[i] < anArray[minPos]) {
                    minPos = i;
                    pause(40);
                }
                markedPosition = i;
            } finally {
                sortStateLock.unlock();
            }
            pause(1);
        }
        return minPos;
    }


    public void draw(Graphics2D g) {
        sortStateLock.lock();
        try {
            int deltaX = component.getWidth() / anArray.length;
            for (int i = 0; i < anArray.length; i++) {
                if (i == markedPosition) {
                    g.setColor(Color.RED);
                } else if (i <= alreadySorted) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.YELLOW);
                }
                g.setStroke(new BasicStroke(2));
                g.drawLine(i * deltaX+40, 0, i * deltaX+40, anArray[i]*3/4);
            }
        } finally {
            sortStateLock.unlock();
        }
    }

    public void pause(int steps) throws InterruptedException {
        component.repaint();
        Thread.sleep(steps * DELAY);
    }

    private static final int DELAY = 10;
    private int[] anArray;
    private int markedPosition = -1;
    private int alreadySorted = -1;
    private Lock sortStateLock;
    private JComponent component;

}
