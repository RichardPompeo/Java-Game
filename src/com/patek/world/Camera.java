package com.patek.world;

public class Camera {

    public static int x, y;

    public static int clamp(int axis, int minAxis, int maxAxis) {
        if (axis < minAxis) {
            axis = minAxis;
        }

        if (axis > maxAxis) {
            axis = maxAxis;
        }

        return axis;
    }

}
