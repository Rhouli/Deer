/* Ryan Houlihan
 * 
 * Class that stores two points X and Y and there sigValue
 */
package Deer;

public class PointStorage {
    private int xPos;
    private int yPos;
    private double sigValue;

    public PointStorage(){
        this.xPos = 0;
        this.yPos = 0;
        this.sigValue = Double.MAX_VALUE;
    }
    public PointStorage(int x, int y){
        this.xPos = x;
        this.yPos = y;
        this.sigValue = Double.MAX_VALUE;
    }
    public void setX(int x){
        this.xPos = x;
    }
    public void setY(int y){
        this.yPos = y;
    }
    public void setSigValue(double sigValue){
        this.sigValue = sigValue;
    }
    public int getXPos(){
        return xPos;
    }
    public int getYPos(){
        return yPos;
    }
    public double getSigValue(){
        return sigValue;
    }
    public boolean equals(PointStorage obj){
        if(this.getXPos() == obj.getXPos() && this.getYPos() == obj.getYPos())
            return true;
        return false;
    }
}
