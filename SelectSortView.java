package project; /**
 * Created by Aleksandr Skidelskiy on 12/08/2015.
 */

import javax.swing.*;
import java.awt.*;

public class SelectSortView {
    public static void main(String[] args) {
        JFrame frame = new JFrame(); //We create a frame
        final int FRAME_WIDTH = 500;
        final int FRAME_HEIGHT = 500;

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final SelectionSortComponent component = new SelectionSortComponent();
        frame.add(component, BorderLayout.CENTER);

        frame.setVisible(true);
        component.startAnimation();
    }
}
