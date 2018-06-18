package com.musatov.waterworld;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Field extends View{

    final int X = 15;
    final int Y = 10;
    float cursorX = 0;
    float cursorY = 0;
    int WIDTH, HEIGHT;
    float segmentX, segmentY;
    private Paint marker;
    Entity [][]mass;
    int amountPenguin = (X*Y)/2;
    int amountGrampus = (int)(((float)(X*Y)/100)*5);
    Bitmap tux, orca;
    Canvas canvas;

    public Field(Context context){
        super(context);
        Resources res = this.getResources();
        tux = BitmapFactory.decodeResource(res, R.drawable.tux);
        orca = BitmapFactory.decodeResource(res, R.drawable.orca);

        mass = new Entity[X][Y];
        init();
    }
    protected void onDraw(Canvas canvas){

        super.onDraw(canvas);
        this.canvas = canvas;

        button();
        newDay();

        if (cursorX<=100 && cursorY<=100){
            fillMass();
            initialState();
            button();
        }

    }
    private void newDay() {

    }
    public void initialState() {
        int x,y;

        canvas.drawColor(Color.WHITE);
        createField();

        for (int i = 0; i < amountPenguin; i++){
            x = (int) (Math.random()*X);
            y = (int) (Math.random()*Y);
            mass[x][y] = new Penguin(0);
            //tux.setWidth(10);
            //tux.setHeight(10);
            canvas.drawBitmap(tux, x*segmentX, y*segmentY, marker);
        }
        for (int i = 0; i < amountGrampus; i++){
            x = (int) (Math.random()*X);
            y = (int) (Math.random()*Y);
            try {
                if (mass[x][y].isLife()) {
                    i--;
                }
            }catch (Exception e){
                mass[x][y] = new Grampus(0);
                canvas.drawBitmap(orca, x * segmentX, y * segmentY, marker);
            }
        }
    }
    private void button() {
        canvas.drawRect(0,0,100,100, marker);
    }
    private void fillMass() {
        for (int i = 0; i < X; i++){
            for (int j = 0; j < Y; j++){
                mass[i][j] = null;
            }
        }
    }
    private void init() {
        marker = new Paint();
        marker.setColor(Color.BLACK);
        marker.setStrokeWidth(1);
    }
    private void createField() {
        WIDTH = canvas.getWidth();
        HEIGHT = canvas.getHeight();
        segmentX = WIDTH/X;
        segmentY = HEIGHT/Y;

        for(int i = 0; i <= WIDTH; i += segmentX)
            canvas.drawLine(i, 0, i, HEIGHT, marker);

        for(int i = 0; i <= HEIGHT; i += segmentY)
            canvas.drawLine(0, i, WIDTH, i, marker);
    }
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            cursorX = event.getX();
            cursorY = event.getY();
            invalidate();
        }
        return true;
    }
}
