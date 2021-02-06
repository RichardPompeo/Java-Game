package com.patek.events;

import com.patek.main.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerEvent implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            Game.player.right = true;
        }

        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            Game.player.left = true;
        }

        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            Game.player.down = true;
        }

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            Game.player.up = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            Game.player.right = false;
        }

        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            Game.player.left = false;
        }

        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            Game.player.down = false;
        }

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            Game.player.up = false;
        }
    }

}
