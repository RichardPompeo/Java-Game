package com.patek.main;

import com.patek.entities.Entity;
import com.patek.entities.Player;
import com.patek.events.KeyListenerEvent;
import com.patek.graphics.Spritesheet;
import com.patek.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 240;
    public static final int HEIGHT = 160;
    public static final int SCALE = 3;
    public static JFrame jFrame;
    public static Spritesheet spritesheet;
    public static World world;
    public static Player player;
    public static List<Entity> entityList;
    private final BufferedImage bufferedImage;
    private boolean isRunning = true;
    private Thread thread;

    public Game() {
        addKeyListener(new KeyListenerEvent());
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        initFrame();
        bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        entityList = new ArrayList<>();
        spritesheet = new Spritesheet("/spritesheet.png");
        player = new Player(0, 0, 16, 16, spritesheet.getSpritesheet(32, 16, 16, 16));
        entityList.add(player);
        world = new World("/map.png");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void initFrame() {
        jFrame = new JFrame("Meu jogo :)");
        jFrame.add(this);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public void tick() {
        for (Entity e : entityList) {
            e.tick();
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bufferedImage.getGraphics();
        g.setColor(new Color(49, 48, 48));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        world.render(g);

        for (Entity e : entityList) {
            e.render(g);
        }

        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(bufferedImage, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        bs.show();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTick = 60.0;
        double ns = 1000000000 / amountOfTick;
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();

        requestFocus();

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                frames++;
                delta--;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }
        }
        stop();
    }

}