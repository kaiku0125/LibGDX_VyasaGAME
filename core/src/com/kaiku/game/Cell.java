package com.kaiku.game;

public class Cell {
    public boolean topWall = true;
    public boolean leftWall = true;
    public boolean bottomWall = true;
    public boolean rightWall = true;
    public boolean visited = false;

    public int col, row;
    public float cellSize, vMargin, hMargin;

    public Cell(int col, int row, float cellSize, float hMargin, float vMargin){
        this.col = col;
        this.row = row;
        this.cellSize = cellSize;
        this.vMargin = vMargin;
        this.hMargin = hMargin;
    }

    public float centerX(){
        return hMargin + cellSize * col + cellSize * 0.5f;
    }

    public float centerY(){
        return vMargin + cellSize * row + cellSize * 0.5f;
    }

    public float leftX(){
        return hMargin + cellSize * col;
    }

    public float bottomY(){
        return vMargin + cellSize * row;
    }
}
