package com.patek.world;

import com.patek.entities.*;
import com.patek.main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class World {

    public static final int TILE_SIZE = 16;
    public static int WIDTH, HEIGHT;
    public static Tile[] tiles;

    public World(String path) {
        try {
            BufferedImage map = ImageIO.read(getClass().getResource(path));
            WIDTH = map.getWidth();
            HEIGHT = map.getHeight();
            int[] pixels = new int[WIDTH * HEIGHT];
            tiles = new Tile[WIDTH * HEIGHT];
            map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);

            for (int xx = 0; xx < WIDTH; xx++) {
                for (int yy = 0; yy < HEIGHT; yy++) {
                    switch (pixels[xx + (yy * WIDTH)]) {
                        case 0xFFFFFCFD:
                            // Floor
                            tiles[xx + (yy * WIDTH)] = new TileWall(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_WALL);
                            break;
                        case 0xFF4CFF00:
                            // Wall
                            tiles[xx + (yy * WIDTH)] = new TileFloor(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_FLOOR);
                            break;
                        case 0xFFFF00DC:
                            // Weapon
                            Game.entityList.add(new Weapon(xx * TILE_SIZE, yy * TILE_SIZE, TILE_SIZE, TILE_SIZE, Entity.WEAPON));
                            tiles[xx + (yy * WIDTH)] = new TileFloor(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_FLOOR);
                            break;
                        case 0xFFFF0000:
                            // Enemy
                            Game.entityList.add(new Enemy(xx * TILE_SIZE, yy * TILE_SIZE, TILE_SIZE, TILE_SIZE, Entity.ENEMY));
                            tiles[xx + (yy * WIDTH)] = new TileFloor(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_FLOOR);
                            break;
                        case 0xFFFFFF00:
                            // Bullet
                            Game.entityList.add(new Bullet(xx * TILE_SIZE, yy * TILE_SIZE, TILE_SIZE, TILE_SIZE, Entity.BULLET));
                            tiles[xx + (yy * WIDTH)] = new TileFloor(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_FLOOR);
                            break;
                        case 0xFF0026FF:
                            // Player
                            Game.player.setX(xx * TILE_SIZE);
                            Game.player.setY(yy * TILE_SIZE);
                            tiles[xx + (yy * WIDTH)] = new TileFloor(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_FLOOR);
                            break;
                        case 0xFFB200FF:
                            // Life Pack
                            Game.entityList.add(new LifePack(xx * TILE_SIZE, yy * TILE_SIZE, TILE_SIZE, TILE_SIZE, Entity.LIFE_PACK));
                            tiles[xx + (yy * WIDTH)] = new TileFloor(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_FLOOR);
                            break;
                        default:
                            // Floor
                            tiles[xx + (yy * WIDTH)] = new TileFloor(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_FLOOR);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isFree(int nextX, int nextY) {
        int x1 = nextX / TILE_SIZE;
        int y1 = nextY / TILE_SIZE;

        int x2 = (nextX + TILE_SIZE - 1) / TILE_SIZE;
        int y2 = nextY / TILE_SIZE;;

        int x3 = nextX / TILE_SIZE;
        int y3 = (nextY + TILE_SIZE - 1) / TILE_SIZE;

        int x4 = (nextX + TILE_SIZE - 1) / TILE_SIZE;
        int y4 = (nextY + TILE_SIZE - 1) / TILE_SIZE;

        return !(tiles[x1 + (y1 * World.WIDTH)] instanceof TileWall ||
                tiles[x2 + (y2 * World.WIDTH)] instanceof TileWall ||
                tiles[x3 + (y3 * World.WIDTH)] instanceof TileWall ||
                tiles[x4 + (y4 * World.WIDTH)] instanceof TileWall);
    }

    public void render(Graphics g) {
        int xStart = Camera.x / TILE_SIZE;
        int yStart = Camera.y / TILE_SIZE;

        int xFinal = xStart + (Game.WIDTH / TILE_SIZE);
        int yFinal = yStart + (Game.HEIGHT / TILE_SIZE);

        for (int xx = xStart; xx <= xFinal; xx++) {
            for (int yy = yStart; yy <= yFinal; yy++) {
                if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT) continue;
                Tile tile = tiles[xx + (yy * WIDTH)];
                tile.render(g);
            }
        }
    }

}
