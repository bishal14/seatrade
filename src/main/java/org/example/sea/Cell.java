package org.example.sea;

public class Cell {
    private  int firstPoint;
    private int secondPoint;
    private  int thirdPoint;
    private int fourthPoint;

    public Cell(int firstPoint, int secondPoint, int thirdPoint, int fourthPoint) {
        if(1==Math.abs(firstPoint-secondPoint)&& 1==Math.abs(secondPoint-thirdPoint)&&1==Math.abs(thirdPoint-fourthPoint)&&Math.abs(fourthPoint-firstPoint)==1){
            this.firstPoint = firstPoint;
            this.secondPoint = secondPoint;
            this.thirdPoint = thirdPoint;
            this.fourthPoint = fourthPoint;
        } else{
            throw  new RuntimeException();
        }

    }


}
