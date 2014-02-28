/* Ryan Houlihan
 *
 * Given a list of points this application takes these points and gives them all a sigValue.
 * The sigValue being there importance to the visual depiction of the list of points.
 * The program removes the least important points based on there sigValues and prints out
 * a new object with a total of 38 points
 */

package Deer;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class ShapeAbstraction {
        // Declares the static Panel, ArrayList, and LinkedList
	protected static MyLinkedList myList = new MyLinkedList();
        protected static ArrayList<Integer[][]> changedPicture = new ArrayList<Integer[][]>();
        protected static JPanel mainPanel;

        // Reads the file in with the location of the string
	public static void readFile(String points){
            try{
		FileReader reader = new FileReader(new File(points));
		Scanner in = new Scanner(reader);
		while(in.hasNextLine()){
			String line = in.nextLine();
			Scanner pointReader = new Scanner(line);
                        int x = 0;
                        int y = 0;
			if(pointReader.hasNextInt()){
				x = pointReader.nextInt();
			}
			if(pointReader.hasNextInt()){
				y = pointReader.nextInt();
			}
			myList.addFirst(new PointStorage(x, y));
		}
            }catch(FileNotFoundException e){
                System.out.println(e.getMessage());
            }
            
	}
        // Reduces the list until it has 28 remaning points
        public static void Abstractor(){
		while(myList.size() > 37){
                    double minSigValue = Double.MAX_VALUE;
                    PointStorage minSigPoint = null;
                    int index;
                    ListIterator iterator = myList.listIterator();

                    // Saves the points of the current List
                    getPoints();

                    // Checks to see the list has a next node and if it does checks to see if its sig value
                    // is less then the least sig value
                  while(iterator.hasNext()){
            		PointStorage p = (PointStorage) iterator.next();
			if(p.getSigValue() < minSigValue){
				minSigValue = p.getSigValue();
                                minSigPoint = p;
			}
                    }
                    // Finds the position of the object to be removes
                    index = myList.indexOf(minSigPoint);
                    PointStorage previous = null;
                    PointStorage A = null;
                    PointStorage B = null;
                    PointStorage next = null;
                    // Finds the Nodes around the node to be removed
                    // If the node isnt the second or the second to last node then it
                    // Resets all surrounding points sigValues
                    if(index > 1 && index < (myList.size()-2)){
                        previous = (PointStorage) myList.get(index - 2);
                        A = (PointStorage) myList.get(index - 1);
                        B = (PointStorage) myList.get(index + 1);
                        next = (PointStorage) myList.get(index + 2);
                        // Removes point with the least sig value
                        myList.remove(index);
                        resetSigValues(previous, A, B, next);
                    }
                    // If it is the second value in the list it only resets the next nodes sigValue
                    if(index == 1){
                        A = (PointStorage) myList.get(index + 1);
                        previous = (PointStorage) myList.get(index -1);
                        next = (PointStorage) myList.get(index + 2);
                        // Removes point with the least sig value
                        myList.remove(index);
                        // Resets the sigValue of object A
                        A.setSigValue(findSigValue(previous, A, next));
                    }
                    // If it is the second to last value in the list it only resets the previous nodes sigValue
                    if(index == (myList.size()-2)){
                        A = (PointStorage) myList.get(index - 1);
                        previous = (PointStorage) myList.get(index - 2);
                        next = (PointStorage)myList.get(index + 1);
                        // Removes point with the least sig value
                        myList.remove(index);
                        // Resets the sigValue of object A
                        A.setSigValue(findSigValue(previous, A, next));
                    }
		}
	}
        // Stores the x and y cordinates of all the objects into an arrayList of Integer[][]
        public static void getPoints(){
            Integer[][] listXY = new Integer[myList.size()][2];
                  for(int x = 0; x < myList.size(); x++){
                      PointStorage obj = (PointStorage) myList.get(x);
                      listXY[x][0] = obj.getXPos();
                      listXY[x][1] = obj.getYPos();
                  }
            changedPicture.add(listXY);
        }
        // Sets the SigValues for everypoint in the List
        public static void setSigValues(){
            // Makes the first and last nodes unremovable by setting sigValue to MAX_VALUE
            PointStorage First = (PointStorage) myList.getFirst();
            PointStorage Last = (PointStorage) myList.getLast();
            First.setSigValue(Double.MAX_VALUE);
            Last.setSigValue(Double.MAX_VALUE);
            for(int i = 1; i < myList.size()-1; i++){
                PointStorage previous = (PointStorage) myList.get(i - 1);
                PointStorage current = (PointStorage) myList.get(i);
                PointStorage next = (PointStorage) myList.get(i + 1);
                double sigValue = findSigValue(previous, current, next);
                current.setSigValue(sigValue);
            }
        }
        // Finds the sigValue of a current point
	public static double findSigValue(PointStorage previous, PointStorage current, PointStorage next){
		int previousX = previous.getXPos();
		int previousY = previous.getYPos();
		int currentX = current.getXPos();
		int currentY = current.getYPos();
		int nextX = next.getXPos();
		int nextY = next.getYPos();
		double L1 = Math.sqrt((double)Math.pow((previousX - currentX), 2) + Math.pow((previousY - currentY), 2));
		double L2 = Math.sqrt((double)Math.pow((currentX - nextX), 2) + Math.pow((currentY - nextY), 2));
		double L3 = Math.sqrt((double)Math.pow((previousX - nextX), 2) + Math.pow((previousY - nextY), 2));
		
		return (L1 + L2 - L3);
	}
        // Resets the SigValues of two points A and B
	public static void resetSigValues(PointStorage previous, PointStorage A, PointStorage B, PointStorage next){
		A.setSigValue(findSigValue(previous, A, B));
		B.setSigValue(findSigValue(A, B, next));
	}
        // Creates the Frame the the Frames Panels
        public static void createFrame(){
            JFrame frame = new JFrame("Shape Abstraction Lab 4");
            frame.setPreferredSize(new Dimension(800, 800));
            frame.setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            //Set the drawing panel to Center
            mainPanel = new DrawingPanel();
            mainPanel.setBackground(Color.YELLOW);
            mainPanel.setPreferredSize(new Dimension(530, 750));
            frame.add(mainPanel, BorderLayout.CENTER);

            //Set the North spacing
            JPanel panel1 = new JPanel();
            panel1.setBackground(Color.RED);
            panel1.setPreferredSize(new Dimension(530, 10));
            frame.add(panel1, BorderLayout.NORTH);

            //Set the South spacing
            JPanel panel2 = new JPanel();
            panel2.setBackground(Color.RED);
            panel2.setLayout(new BorderLayout());
            panel2.add(new Slider(), BorderLayout.SOUTH);
            panel2.setPreferredSize(new Dimension(530, 50));
            frame.add(panel2, BorderLayout.SOUTH);

            //Set the West spacing
            JPanel panel3 = new JPanel();
            panel3.setBackground(Color.RED);
            panel3.setPreferredSize(new Dimension(135, 800));
            frame.add(panel3, BorderLayout.WEST);

            //Set the East spacing
            JPanel panel4 = new JPanel();
            panel4.setBackground(Color.RED);
            panel4.setPreferredSize(new Dimension(135, 800));
            frame.add(panel4, BorderLayout.EAST);

            frame.setVisible(true);
            frame.pack();
        }
        // Runs the file after the file name has been read in
        public static void Run(String name){
            readFile(name);
            setSigValues();
            Abstractor();
            createFrame();
        }
        // Reads in the file name
	public static void main(String[] args){
            new FileGetter();
	}

}
