/* Ryan Houlihan
 * 
 * Creates the slider used for viewing the steps of the point reduction
 */

package Deer;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class Slider extends JPanel{
    protected static Integer[][] sliderChoice = ShapeAbstraction.changedPicture.get(0);
    private JSlider slider;
    private JLabel buttonCounter;

    // Creates the slider
    public Slider(){
        this.setLayout(new BorderLayout());

        SliderListener s1 = new SliderListener();

        slider = new JSlider(0, ShapeAbstraction.changedPicture.size()-1, 0);
        slider.addChangeListener(s1);
        
        buttonCounter = new JLabel(sliderChoice.length + " total points");

        this.add(slider, BorderLayout.SOUTH);
        this.add(buttonCounter, BorderLayout.CENTER);
        this.setVisible(true);

    }
    // If the slider is moved it repaints the picture
    private class SliderListener implements ChangeListener{
        public void stateChanged(ChangeEvent e){
            change();
        }
        public void change(){
            sliderChoice = ShapeAbstraction.changedPicture.get(slider.getValue());
            buttonCounter.setText(sliderChoice.length + " total points");
            ShapeAbstraction.mainPanel.repaint();
        }
    }
}
