package com.patek.entities;

import com.patek.main.Game;
import com.patek.world.World;

import java.awt.image.BufferedImage;

public class Enemy extends Entity {

    private int speed = 1;

    public Enemy(int x, int y, int width, int height, BufferedImage spritesheet) {
        super(x, y, width, height, spritesheet);
    }

    public void tick() {
        if (x < Game.player.getX() && World.isFree(this.getX() + speed, this.getY())) {
            x += speed;
        } else if (x > Game.player.getX() && World.isFree(this.getX() - speed, this.getY())) {
            x -= speed;
        }

        if (y < Game.player.getY() && World.isFree(this.getX(), this.getY() + speed)) {
            y += speed;
        } else if (y > Game.player.getY() && World.isFree(this.getX(), this.getY()  - speed)) {
            y -= speed;
        }
    }

}
