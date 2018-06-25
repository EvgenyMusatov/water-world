package com.musatov.waterworld;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Field extends View {

    final int X = 10;
    final int Y = 15;
    float cursorX = 0;
    float cursorY = 0;
    int WIDTH, HEIGHT;
    float day;
    float segmentX, segmentY;
    private Paint marker;
    Entity[][] mass;
    int amountPenguin = (X * Y) / 2;
    int amountGrampus = (int) (((float) (X * Y) / 100) * 5);
    Bitmap tux, orca;
    Canvas canvas;
    Rect rect = new Rect();

    public Field(Context context) {
        super(context);
        Resources res = this.getResources();
        tux = BitmapFactory.decodeResource(res, R.drawable.tux);
        orca = BitmapFactory.decodeResource(res, R.drawable.orca);
        mass = new Entity[X][Y];
        initMarker();
    }

    private void bitmapSize() {
        if (segmentX > segmentY) {
            tux = Bitmap.createScaledBitmap(tux, (int) segmentY, (int) segmentY, true);
            orca = Bitmap.createScaledBitmap(orca, (int) segmentY, (int) segmentY, true);
        } else {
            tux = Bitmap.createScaledBitmap(tux, (int) segmentX, (int) segmentX, true);
            orca = Bitmap.createScaledBitmap(orca, (int) segmentX, (int) segmentX, true);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        newDay();
        button();
        if (cursorX >= 0 && cursorX <= canvas.getWidth() &&
                cursorY >= (int) (segmentY * (Y - 1)) && cursorY <= canvas.getHeight()) {
            day = 0;
            fillMass();
            initialState();
            button();
        }
    }

    private void newDay() {
        int vector;
        int nextDay;
        day++;
        Log.v("Day ", String.valueOf(day));
        canvas.drawColor(Color.WHITE);
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                vector = (int) (Math.random() * 5);
                switch (vector) {
                    case (0):
                        toLeftPenguin(i, j);
                    case (1):
                        toLeftUpPenguin(i, j);
                    case (2):
                        toUpPenguin(i, j);
                    case (3):
                      toRightUpPenguin(i,j);
                    case (4):
                        toRightPenguin(i, j);
                }
            }
            createField();
        }
    }

    private void toRightUpPenguin(int i, int j) {
        if (isPenguin(mass[i][j])) {
            if (mass[i][j].getToday() < day) {
                if (upRightElementExist(i,j)) {
                    if (ElementIsEmpty(mass[i + 1][j - 1])) {
                        mass[i + 1][j - 1] = new Penguin(mass[i][j].getLifetime() + 1, day);
                        canvas.drawBitmap(tux, (i + 1) * segmentX, (j - 1) * segmentY, marker);
                        mass[i][j] = new Entity();
                    } else {
                        mass[i][j].setLifetime(mass[i][j].getLifetime() + 1);
                        mass[i][j].setToday(day);
                        canvas.drawBitmap(tux, (i) * segmentX, (j) * segmentY, marker);
                    }
                } else {
                    mass[i][j].setLifetime(mass[i][j].getLifetime() + 1);
                    mass[i][j].setToday(day);
                    canvas.drawBitmap(tux, (i) * segmentX, (j) * segmentY, marker);
                }
            }
        }
    }

    private void toRightPenguin(int i, int j) {
        if (isPenguin(mass[i][j])) {
            Log.v("Today", String.valueOf(mass[i][j].getToday()));
            if (mass[i][j].getToday() < day) {
                if (rightElementExist(i)) {
                    if (ElementIsEmpty(mass[i + 1][j])) {
                        mass[i + 1][j] = new Penguin(mass[i][j].getLifetime() + 1, day + 1);
                        Log.v("NEW Today", String.valueOf(mass[i][j].getToday()+1));
                        canvas.drawBitmap(tux, (i + 1) * segmentX, (j) * segmentY, marker);
                        mass[i][j] = new Entity();
                        Log.v(String.valueOf(i), String.valueOf(i+1));
                    } else {
                        mass[i][j].setLifetime(mass[i][j].getLifetime() + 1);
                        mass[i][j].setToday(day);
                        canvas.drawBitmap(tux, (i) * segmentX, (j) * segmentY, marker);
                    }
                } else {
                    mass[i][j].setLifetime(mass[i][j].getLifetime() + 1);
                    mass[i][j].setToday(day);
                    canvas.drawBitmap(tux, (i) * segmentX, (j) * segmentY, marker);
                }
            }
        }
    }

    private void toLeftUpPenguin(int i, int j) {
        if (isPenguin(mass[i][j])) {
            if (mass[i][j].getToday() < day) {
                if (upLeftElementExist(i, j)) {
                    if (ElementIsEmpty(mass[i - 1][j - 1])) {
                        mass[i - 1][j - 1] = new Penguin(mass[i][j].getLifetime() + 1, day);
                        canvas.drawBitmap(tux, (i - 1) * segmentX, (j - 1) * segmentY, marker);
                        mass[i][j] = new Entity();
                    } else {
                        mass[i][j].setLifetime(mass[i][j].getLifetime() + 1);
                        mass[i][j].setToday(day);
                        canvas.drawBitmap(tux, (i) * segmentX, (j) * segmentY, marker);
                    }
                } else {
                    mass[i][j].setLifetime(mass[i][j].getLifetime() + 1);
                    mass[i][j].setToday(day);
                    canvas.drawBitmap(tux, (i) * segmentX, (j) * segmentY, marker);
                }
            }
        }
    }

    private void toUpPenguin(int i, int j) {
        if (isPenguin(mass[i][j])) {
            if (mass[i][j].getToday() < day) {
                if (upElementExist(j)) {
                    if (ElementIsEmpty(mass[i][j - 1])) {
                        mass[i][j - 1] = new Penguin(mass[i][j].getLifetime() + 1, day);
                        canvas.drawBitmap(tux, (i) * segmentX, (j - 1) * segmentY, marker);
                        mass[i][j] = new Entity();
                    } else {
                        mass[i][j].setLifetime(mass[i][j].getLifetime() + 1);
                        mass[i][j].setToday(day);
                        canvas.drawBitmap(tux, (i) * segmentX, (j) * segmentY, marker);
                    }
                } else {
                    mass[i][j].setLifetime(mass[i][j].getLifetime() + 1);
                    mass[i][j].setToday(day);
                    canvas.drawBitmap(tux, (i) * segmentX, (j) * segmentY, marker);
                }
            }
        }
    }

    private void toLeftPenguin(int i, int j) {
        if (isPenguin(mass[i][j])) {
                if (mass[i][j].getToday() < day) {
            if (leftElementExist(i)) {
                if (ElementIsEmpty(mass[i - 1][j])) {
                    mass[i - 1][j] = new Penguin(mass[i][j].getLifetime() + 1, day);
                    canvas.drawBitmap(tux, (i - 1) * segmentX, (j) * segmentY, marker);
                    mass[i][j] = new Entity();
                    Log.v(String.valueOf(i), String.valueOf(i-1));
                } else {
                    mass[i][j].setLifetime(mass[i][j].getLifetime() + 1);
                    mass[i][j].setToday(day);
                    canvas.drawBitmap(tux, (i) * segmentX, (j) * segmentY, marker);
                }
            } else {
                mass[i][j].setLifetime(mass[i][j].getLifetime() + 1);
                mass[i][j].setToday(day);
                canvas.drawBitmap(tux, (i) * segmentX, (j) * segmentY, marker);
            }
        }
        }
    }

    private boolean upLeftElementExist(int i, int j) {
        if (i > 0 && j > 0) return true;
        return false;
    }
    private boolean upRightElementExist(int i, int j) {
        if (i < X - 1 && j > 0) return true;
        return false;
    }

    private boolean upElementExist(int j) {
        if (j > 0) return true;
        return false;
    }


    private boolean rightElementExist(int i) {
        if (i < X - 1) return true;
        return false;
    }

    private boolean ElementIsEmpty(Entity entity) {
        if (entity.getName().equals("")) return true;
        return false;
    }

    private boolean isPenguin(Entity entity) {
        try {
            if (entity.getName().equals("Penguin")) return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private boolean leftElementExist(int i) {
        if (i > 0) return true;
        return false;
    }

    public void initialState() {
        int x, y;
        canvas.drawColor(Color.WHITE);
        createField();
        bitmapSize();
        for (int i = 0; i < amountPenguin; i++) {
            x = (int) (Math.random() * X);
            y = (int) (Math.random() * Y);
                if (mass[x][y].getName().equals("")) {
                    mass[x][y] = new Penguin(0, day);
                    canvas.drawBitmap(tux, x * segmentX, (y * segmentY), marker);
                }
                else i--;
        }
        for (int i = 0; i < amountGrampus; i++) {
            x = (int) (Math.random() * X);
            y = (int) (Math.random() * Y);
                if (mass[x][y].getName().equals("")) {
                    mass[x][y] = new Grampus(0, day);
                    canvas.drawBitmap(orca, x * segmentX, y * segmentY, marker);
                }
                else i--;
        }
    }

    private void button() {
        rect.set(0, (int) (segmentY * (Y - 1)), canvas.getWidth(), canvas.getHeight());
        Paint blue = new Paint();
        blue.setColor(Color.BLUE);
        blue.setStyle(Paint.Style.FILL);
        canvas.drawRect(rect, blue);
    }

    private void fillMass() {
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                mass[i][j] = new Entity(0, day);
            }
        }
    }

    private void initMarker() {
        marker = new Paint();
        marker.setColor(Color.BLACK);
        marker.setStrokeWidth(1);
    }

    private void createField() {
        WIDTH = canvas.getWidth();
        HEIGHT = canvas.getHeight();
        segmentX = WIDTH / X;
        segmentY = HEIGHT / Y;

        for (int i = 0; i <= WIDTH; i += segmentX)
            canvas.drawLine(i, 0, i, HEIGHT, marker);

        for (int i = 0; i <= HEIGHT; i += segmentY)
            canvas.drawLine(0, i, WIDTH, i, marker);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            cursorX = event.getX();
            cursorY = event.getY();
            invalidate();
        }
        return true;
    }
}

