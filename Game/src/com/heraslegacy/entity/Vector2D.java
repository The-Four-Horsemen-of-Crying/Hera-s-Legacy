/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraslegacy.entity;

/**
 *
 * @author HP
 */
public class Vector2D {

    private double x, y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D addVector(Vector2D v) {
        return new Vector2D(x + v.getX(), y + v.getY());
    }

    public Vector2D substracVector(Vector2D v) {
        return new Vector2D(x - v.getX(), y - getY());
    }

    public double getMagnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D scale(Double v) {
        return new Vector2D(x * v, y * v);
    }

    public Vector2D limit(double value) {
        if (getMagnitude() > value) {
            this.normalice().scale(value);
        }
        return this;
    }

    public Vector2D setDirection(double angle) {
        double magnitud = getMagnitude();
        return new Vector2D(Math.cos(angle) * magnitud, Math.sin(angle) * magnitud);
    }

    public Vector2D normalice() {
        double magnitud = getMagnitude();
        return new Vector2D(x / magnitud, y / magnitud);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getAngle() {
        return Math.asin(y / getMagnitude());
    }
}
