package com.patek.entities;

import com.patek.main.Game;
import com.patek.world.Camera;
import com.patek.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    private final BufferedImage[] rightPlayer;
    private final BufferedImage[] leftPlayer;
    public boolean right, left, up, down;
    public int speed = 2;
    private boolean rightDir = true, leftDir = false;
    private int frames = 0;
    private int index = 0;

    public Player(int x, int y, int width, int height, BufferedImage spritesheet) {
        super(x, y, width, height, spritesheet);

        rightPlayer = new BufferedImage[4];
        leftPlayer = new BufferedImage[4];

        for (int i = 0; i < 4; i++) {
            rightPlayer[i] = Game.spritesheet.getSpritesheet(32 + (i * 16), 16, 16, 16);
            leftPlayer[i] = Game.spritesheet.getSpritesheet(32 + (i * 16), 0, 16, 16);
        }
    }

    public void tick() {
        boolean moved = false;

        if (right && World.isFree(this.getX() + speed, this.getY())) {
            moved = true;
            leftDir = false;
            rightDir = true;
            x += speed;
        } else if (left && World.isFree(this.getX() - speed, this.getY())) {
            moved = true;
            rightDir = false;
            leftDir = true;
            x -= speed;
        }

        if (up && World.isFree(this.getX(), this.getY() - speed)) {
            moved = true;
            y -= speed;
        } else if (down && World.isFree(this.getX(), this.getY() + speed)) {
            moved = true;
            y += speed;
        }

        int maxIndex = 3;
        int maxFrames = 5;

        frames++;

        if (moved) {
            if (frames >= maxFrames) {
                frames = 0;
                index++;

                if (index >= maxIndex) {
                    index = 0;
                }
            }
        } else {
            if (frames >= maxFrames * 7) {
                frames = 0;
                index += 3;

                if (index > maxIndex) {
                    index = 0;
                }
            }
        }

        Camera.x = Camera.clamp(this.getX() - (Game.WIDTH / 2), 0, World.WIDTH * 16 - Game.WIDTH);
        Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT / 2), 0, World.HEIGHT * 16 - Game.HEIGHT);
    }

    public void render(Graphics g) {
        if (rightDir) {
            g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
        } else if (leftDir) {
            g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
        }
    }

}
