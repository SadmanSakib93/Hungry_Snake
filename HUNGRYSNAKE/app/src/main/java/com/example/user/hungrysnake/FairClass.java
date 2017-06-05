package com.example.user.hungrysnake;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

import static android.graphics.Paint.Style.FILL_AND_STROKE;


public class FairClass extends SurfaceView implements Runnable {

    boolean trueFalse;
    SurfaceHolder holder;
    Canvas canvas;
    Thread thread = null;

    Bitmap wallpaper, Body, Head, back, Food, piontsPic, gameOverPic, impossibleLevelWall;
    Random r = new Random();
    Context context;

    boolean movingLeft, movingRight, movingUp, movingDown;
    static int snakeLenght, snakeBodyWidth, wallpaperWidth, wallpaperHeight, checkedOneTime = 0, snakeMoveDirection = 0, snakeMoveSpeed;
    static int foodXPos, foodYPos, points, numberOfFoodEaten;
    boolean foodDraw, gameOver;
    Paint PiontShow = new Paint();


    SnakeControlClass snakeControlClass;

    public FairClass(Context context) {
        super(context);

        back = BitmapFactory.decodeResource(getResources(), R.drawable.black);
        wallpaper = BitmapFactory.decodeResource(getResources(), R.drawable.grs);
        wallpaperHeight = wallpaper.getHeight();
        wallpaperWidth = wallpaper.getWidth();
        impossibleLevelWall = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
        gameOverPic = BitmapFactory.decodeResource(getResources(), R.drawable.gameover);


        SnakeControlClass.x[0] = wallpaperHeight / 2;
        SnakeControlClass.y[0] = wallpaperWidth / 2;
        snakeLenght = 2;
        if (LevelClass.Level == 1) snakeMoveSpeed = 20;
        if (LevelClass.Level == 2) snakeMoveSpeed = 15;
        if (LevelClass.Level == 3) snakeMoveSpeed = 30;


        movingLeft = true;
        movingDown = movingRight = movingUp = false;
        gameOver = false;
        Head = BitmapFactory.decodeResource(getResources(), R.drawable.head);
        Body = BitmapFactory.decodeResource(getResources(), R.drawable.body);

        Food = BitmapFactory.decodeResource(getResources(), R.drawable.f1);
        foodDraw = false;
        piontsPic = BitmapFactory.decodeResource(getResources(), R.drawable.points);
        points = numberOfFoodEaten = 0;


        snakeBodyWidth = (Body.getWidth());

        for (int i = 0; i <= snakeLenght; i++) {
            SnakeControlClass.x[i + 1] = SnakeControlClass.x[i] + snakeBodyWidth;
            SnakeControlClass.y[i + 1] = SnakeControlClass.y[i];
        }


        holder = getHolder();

    }


    @Override
    public void run() {
        while (trueFalse) {
            if (!holder.getSurface().isValid()) continue;
            canvas = holder.lockCanvas();

            if (!gameOver) {

                canvas.drawBitmap(back, 0, 100, null);
                canvas.drawBitmap(wallpaper, 0, 0, null);
                if (LevelClass.Level == 3)
                    canvas.drawBitmap(impossibleLevelWall, (wallpaperWidth / 2) - 150, (wallpaperHeight / 2) - 5, null);

                //Move UP from LEFT (at first)
                if (snakeMoveDirection == 1) {
                    SnakeControlClass.moveUpFromLeft();
                }

                //Move DOWN from LEFT (at first)
                else if (snakeMoveDirection == 2) {
                    SnakeControlClass.moveDownFromLeft();
                }

                //Move LEFT from UP
                else if (snakeMoveDirection == 3) {
                    SnakeControlClass.moveLeftFromUp();
                }

                //Move RIGHT from UP
                else if (snakeMoveDirection == 4) {
                    SnakeControlClass.moveRightFromUp();
                }

                //Move UP from RIGHT
                else if (snakeMoveDirection == 5) {
                    SnakeControlClass.moveUpFromRight();
                }

                //Move DOWN from RIGHT
                else if (snakeMoveDirection == 6) {
                    SnakeControlClass.moveDownFromRight();
                }
                //Move LEFT from DOWN
                else if (snakeMoveDirection == 7) {
                    SnakeControlClass.moveLeftFromDown();
                }

                //Move RIGHT from DOWN
                else if (snakeMoveDirection == 8) {
                    SnakeControlClass.moveRightFromDown();
                }


                //When snake touches the screen sides

                if (LevelClass.Level == 1) {
                    for (int i = 0; i <= snakeLenght; i++) {
                        if (SnakeControlClass.x[i] <= -15) {
                            SnakeControlClass.x[i] = SnakeControlClass.x[i] + wallpaperWidth - 30;
                        }
                    }
                    for (int i = 0; i <= snakeLenght; i++) {
                        if (SnakeControlClass.x[i] >= wallpaperWidth) {
                            SnakeControlClass.x[i] = 0;
                        }
                    }
                    for (int i = 0; i <= snakeLenght; i++) {
                        if (SnakeControlClass.y[i] <= 0) {
                            SnakeControlClass.y[i] = SnakeControlClass.y[i] + wallpaperHeight;
                        }
                    }
                    for (int i = 0; i <= snakeLenght; i++) {
                        if (SnakeControlClass.y[i] >= wallpaperHeight) {
                            SnakeControlClass.y[i] = 0;
                        }
                    }
                }


                //*** For drawing the whole snake ***
                for (int i = 0; i <= snakeLenght; i++) {
                    try {
                        Thread.sleep(snakeMoveSpeed);
                        if (i == 0)
                            canvas.drawBitmap(Head, SnakeControlClass.x[i], SnakeControlClass.y[i], null);
                        else
                            canvas.drawBitmap(Body, SnakeControlClass.x[i], SnakeControlClass.y[i], null);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                //*** For drawing FOOD ***
                if (LevelClass.Level != 3 && foodDraw == false) {

                    foodXPos = r.nextInt(wallpaperWidth - 50);
                    do {
                        foodYPos = r.nextInt(wallpaperHeight - 50);
                    } while (foodYPos <= 50);

                }

                if (LevelClass.Level == 3 && foodDraw == false) {
                    //(SnakeControlClass.x[0]+snakeBodyWidth>=(wallpaperWidth/2)-150 && SnakeControlClass.x[0]<=(wallpaperWidth/2)-150+impossibleLevelWall.getWidth() && SnakeControlClass.y[0]>=(wallpaperHeight/2)-5 && SnakeControlClass.y[0]<=(wallpaperHeight/2)-5)

                    foodXPos = r.nextInt(wallpaperWidth - 50);
                    do {
                        foodYPos = r.nextInt(wallpaperHeight - 50);
                    }
                    while (foodYPos <= 50 && foodXPos >= (wallpaperWidth / 2) - 180 && foodXPos <= (wallpaperWidth / 2) - 150 + impossibleLevelWall.getWidth() + 20 && foodYPos >= (wallpaperHeight / 2) - 30 && foodYPos <= (wallpaperHeight / 2) + 35);

                }
                canvas.drawBitmap(Food, foodXPos, foodYPos, null);
                foodDraw = true;


                //***  Drawing the points  ***
                canvas.drawBitmap(piontsPic, 0, wallpaperHeight + 10, null);
                PiontShow.setTextSize(70);
                PiontShow.setARGB(250, 250, 250, 250);
                PiontShow.setStyle(FILL_AND_STROKE);
                canvas.drawText("" + points, (piontsPic.getWidth() + 15), wallpaperHeight + 70, PiontShow);


                foodCheck();
                gameOver();
                if (LevelClass.Level == 3) impossibleWallTouchgameOverCheck();
                if (LevelClass.Level == 2 || LevelClass.Level == 3) insaneImpossibleGameOverCheck();


            }
            if (gameOver) {
                canvas.drawBitmap(gameOverPic, 0, 0, null);

            }
            holder.unlockCanvasAndPost(canvas);
        }


    }


    // >>>> GAME OVER when snake HEAD touches body <<<<<
    public void gameOver() {
        for (int i = 1; i <= snakeLenght; i++) {
            if (SnakeControlClass.x[0] == SnakeControlClass.x[i] && SnakeControlClass.y[0] == SnakeControlClass.y[i])

            {

                movingUp = movingDown = movingLeft = movingRight = false;
                snakeMoveDirection = -1;
                gameOver = true;

                //Bitmap wallpaper,Body,Head,back,Food,piontsPic,gameOverPic,impossibleLevelWall;

                wallpaper.recycle();
                Body.recycle();
                Head.recycle();
                Food.recycle();
                piontsPic.recycle();
                //   gameOverPic.recycle();
                impossibleLevelWall.recycle();


                if (points > MainActivity.currentHighScore) {
                    context = FairClass.this.getContext();
                    context.startActivity(new Intent(context, IfHighScore.class));
                } else {
                    context = FairClass.this.getContext();
                    context.startActivity(new Intent(context, MainActivity.class));
                }

            }
        }
    }


    //>>>> Checks when snake head touches the obstacle wall <<<<
    public void impossibleWallTouchgameOverCheck() {

        if (SnakeControlClass.x[0] + snakeBodyWidth >= (wallpaperWidth / 2) - 150 && SnakeControlClass.x[0] <= (wallpaperWidth / 2) - 150 + impossibleLevelWall.getWidth() && SnakeControlClass.y[0] >= (wallpaperHeight / 2) - 5 && SnakeControlClass.y[0] <= (wallpaperHeight / 2) - 5) {
            movingUp = movingDown = movingLeft = movingRight = false;
            snakeMoveDirection = -1;
            gameOver = true;

            wallpaper.recycle();
            Body.recycle();
            Head.recycle();
            Food.recycle();
            piontsPic.recycle();
            // gameOverPic.recycle();
            impossibleLevelWall.recycle();

            if (points > MainActivity.currentHighScore) {
                context = FairClass.this.getContext();
                context.startActivity(new Intent(context, IfHighScore.class));
            } else {
                context = FairClass.this.getContext();
                context.startActivity(new Intent(context, MainActivity.class));
            }
        }
    }


    //>>>> GAME OVER check For INSANE & IMPOSSIBLE LEVELS when touches sides  <<<<
    public void insaneImpossibleGameOverCheck() {

        if ((SnakeControlClass.x[0] <= -15) || (SnakeControlClass.x[0] >= wallpaperWidth) || (SnakeControlClass.y[0] <= 0) || (SnakeControlClass.y[0] >= wallpaperHeight)) {
            movingUp = movingDown = movingLeft = movingRight = false;
            snakeMoveDirection = -1;
            gameOver = true;


            wallpaper.recycle();
            Body.recycle();
            Head.recycle();
            Food.recycle();
            piontsPic.recycle();
            // gameOverPic.recycle();
            impossibleLevelWall.recycle();

            if (points > MainActivity.currentHighScore) {
                context = FairClass.this.getContext();
                context.startActivity(new Intent(context, IfHighScore.class));
            } else {
                context = FairClass.this.getContext();
                context.startActivity(new Intent(context, MainActivity.class));
            }

        }
    }


    //>>>> To Check if the Food is eaten or not! <<<<
    public void foodCheck() {
        if (foodDraw && movingLeft) {
            if ((SnakeControlClass.x[0] <= foodXPos + Food.getWidth()) && (SnakeControlClass.x[0] >= foodXPos) && (SnakeControlClass.y[0] >= foodYPos) && (SnakeControlClass.y[0] <= foodYPos + Food.getHeight())) {

                snakeLenght++;
                points++;
                numberOfFoodEaten++;
                foodDraw = false;
            }
        } else if (foodDraw && movingRight) {
            if ((SnakeControlClass.x[0] + Head.getWidth() >= foodXPos) && (SnakeControlClass.x[0] + Head.getWidth() <= foodXPos + Food.getWidth()) && (SnakeControlClass.y[0] >= foodYPos) && (SnakeControlClass.y[0] <= foodYPos + Food.getHeight())) {

                snakeLenght++;
                points++;
                numberOfFoodEaten++;
                foodDraw = false;
            }
        } else if (foodDraw && movingUp) {
            if ((SnakeControlClass.y[0] <= foodYPos + Food.getHeight()) && (SnakeControlClass.y[0] >= foodYPos) && (SnakeControlClass.x[0] <= foodXPos + Food.getWidth()) && (SnakeControlClass.x[0] >= foodXPos - Food.getWidth())) {

                snakeLenght++;
                points++;
                numberOfFoodEaten++;
                foodDraw = false;
            }
        } else if (foodDraw && movingDown) {
            if ((SnakeControlClass.y[0] + Head.getHeight() >= foodYPos) && (SnakeControlClass.y[0] + Head.getHeight() <= foodYPos + Food.getHeight()) && (SnakeControlClass.x[0] <= foodXPos + Food.getWidth()) && (SnakeControlClass.x[0] >= foodXPos - Food.getWidth())) {

                snakeLenght++;
                points++;
                numberOfFoodEaten++;
                foodDraw = false;
            }
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        checkedOneTime = 0;
        if ((checkedOneTime == 0) && movingLeft && (event.getY() < snakeControlClass.y[0])) {
            snakeMoveDirection = 1;  //Move UP from LEFT (at first)
            movingUp = true;
            movingLeft = false;
            checkedOneTime = 1;

        }

        if ((checkedOneTime == 0) && movingLeft && (event.getY() > snakeControlClass.y[0])) {
            snakeMoveDirection = 2;  //Move DOWN from LEFT (at first)
            movingDown = true;
            movingLeft = false;
            checkedOneTime = 1;
        }

        if ((checkedOneTime == 0) && movingUp && (event.getX() < snakeControlClass.x[0])) {
            snakeMoveDirection = 3;  //Move LEFT from UP
            movingLeft = true;
            movingUp = false;
            checkedOneTime = 1;
        }
        if ((checkedOneTime == 0) && movingUp && (event.getX() > snakeControlClass.x[0])) {
            snakeMoveDirection = 4;  //Move RIGHT from UP
            movingRight = true;
            movingUp = false;
            checkedOneTime = 1;
        }

        if ((checkedOneTime == 0) && movingRight && (event.getY() < snakeControlClass.y[0])) {
            snakeMoveDirection = 5;  //Move UP from RIGHT
            movingUp = true;
            movingRight = false;
            checkedOneTime = 1;
        }

        if ((checkedOneTime == 0) && movingRight && (event.getY() > snakeControlClass.y[0])) {
            snakeMoveDirection = 6;  //Move DOWN from RIGHT
            movingDown = true;
            movingRight = false;
            checkedOneTime = 1;
        }

        if ((checkedOneTime == 0) && movingDown && (event.getX() < snakeControlClass.x[0])) {
            snakeMoveDirection = 7;  //Move LEFT from DOWN
            movingLeft = true;
            movingDown = false;
            checkedOneTime = 1;
        }
        if ((checkedOneTime == 0) && movingDown && (event.getX() > snakeControlClass.x[0])) {
            snakeMoveDirection = 8;  //Move RIGHT from DOWN
            movingRight = true;
            movingDown = false;
            checkedOneTime = 1;
        }

        return super.onTouchEvent(event);
    }


    public void pause() {
        trueFalse = false;
        while (true) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
        thread = null;
    }

    public void resume() {
        trueFalse = true;
        thread = new Thread(this);
        thread.start();
    }
}
