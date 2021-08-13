package com.kaiku.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class PlayerActor extends Actor {
    private Cell player;
    Rectangle rect;

    public PlayerActor(Cell cell, Action action){
        this.player = cell;
        rect = new Rectangle(cell.leftX(), cell.bottomY(), cell.cellSize, cell.cellSize);
        addAction(action);

    }


    public void drawPlayer(ShapeRenderer sr){
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.RED);
        sr.rect(rect.x, rect.y, rect.width, rect.y);
        sr.end();

    }

    public Pixmap getRect(int width, int height, Color color){
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGB888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0 , 0 , pixmap.getWidth(), pixmap.getHeight());
        return pixmap;
    }

    public Texture getPixmapTexture(Color color){
        return new Texture(getRect(Math.round(player.cellSize*0.8f), Math.round(player.cellSize*0.8f), color));
    }




}
