package com.patek.entities;

import com.patek.main.Game;
import com.patek.world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public static BufferedImage LIFE_PACK = Game.spritesheet.getSpritesheet(96, 0, 16, 16);
    public static BufferedImage WEAPON = Game.spritesheet.getSpritesheet(112, 0, 16, 16);
    public static BufferedImage BULLET = Game.spritesheet.getSpritesheet(128, 0, 16, 16);
    public static BufferedImage ENEMY = Game.spritesheet.getSpritesheet(144, 0, 16, 16);

    private final int width;
    private final int height;
    private final BufferedImage spritesheet;
    protected int x;
    protected int y;

    public Entity(int x, int y, int width, int height, BufferedImage spritesheet) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.spritesheet = spritesheet;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(spritesheet, this.getX() - Camera.x, this.getY() - Camera.y, null);
    }

}
