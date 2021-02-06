package com.patek.world;

import com.patek.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public static BufferedImage TILE_FLOOR = Game.spritesheet.getSpritesheet(0, 0, 16, 16);
    public static BufferedImage TILE_WALL = Game.spritesheet.getSpritesheet(16, 0, 16, 16);

    private BufferedImage spritesheet;
    private int x, y;

    public Tile(int x, int y, BufferedImage spritesheet) {
        this.x = x;
        this.y = y;
        this.spritesheet = spritesheet;
    }

    public void render(Graphics g) {
        g.drawImage(spritesheet, x - Camera.x, y - Camera.y, null);
    }

}
