/* Ryan Houlihan
 * CIS 2168
 * Rolf Lakaemper
 * Lab 4
 *
 * This class creates the image of the object using the points read in by the user
 */

package lab_4_2;
import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    @Override
    // Creates the image of the givin points connected by black lines
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int maxY = getMaxY()/2;

        // Prints out all the lines connecting the points
        for(int i = 0; i <= Slider.sliderChoice.length - 2; i++){
            int xStart = Slider.sliderChoice[i][0];
            int yStart = Slider.sliderChoice[i][1];
            int xEnd = Slider.sliderChoice[i +1][0];
            int yEnd = Slider.sliderChoice[i +1][1];


            int y1 = ((maxY-yStart)*2 + yStart);
            int y2 = ((maxY-yEnd)*2 + yEnd);

            // Prints out the line connecting the first and the last points
            g.setColor(Color.BLACK);
            g.drawLine(xStart+10, y1, xEnd+10, y2);
        }
        // Creates a slider used for added and subtracting points
        int y1 = Slider.sliderChoice[0][1];
        int y2 = Slider.sliderChoice[Slider.sliderChoice.length-1][1];
        g.setColor(Color.BLACK);
        PointStorage Last = (PointStorage) ShapeAbstraction.myList.getLast();
        PointStorage First = (PointStorage) ShapeAbstraction.myList.getFirst();
        g.drawLine(First.getXPos()+10, ((maxY-y1)*2 + y1) ,
                   Last.getXPos()+10, ((maxY-y2)*2 + y2));

    }
    // Gets the maximum Y value in the current array of points
    public int getMaxY(){
        int maxY = Integer.MIN_VALUE;
        Integer[][] initalPoints = ShapeAbstraction.changedPicture.get(0);
        for(int i = 0; i < initalPoints.length -1; i++){
                int y = initalPoints[i][1];
                if(y > maxY){
                    maxY = y;
               }
        }
        return maxY;
   }
}
