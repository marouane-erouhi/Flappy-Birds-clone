package mn.roua.canvas_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;


class Game {
    static final int pipeGap = 500;
    static final float pipeSpeed = 10.0f;
    private final int gapBetweenPipes = 160 * 2 + 700;

    int score = 0;
    private View view;

    private Context context;

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private Player player;

    private ArrayList<Pipe> pipes;

    private Random rand;

    private int returnValue = 2;

    Game(View view, Context context){
        this.view = view;
        this.context = context;

        rand = new Random();

        player = new Player(new Sprite(BitmapFactory.decodeResource(view.getResources(), R.drawable.bird), 200,200));

        pipes = new ArrayList<>();
        startGame();
    }

    private void startGame(){
        resetGame();
    }


    void draw(Canvas canvas){
        player.update();
        player.draw(canvas);

        for (int i = 0; i < pipes.size() ; i++) {
            pipes.get(i).update();
            pipes.get(i).draw(canvas);

            //delete pipe that are out of screen
            if (pipes.get(i).outOfBounds()) {
                score++;
                pipes.remove(i);
            }



            //check collision for the pipes
            if(Collider.pipeColl(player, pipes.get(i))){
                returnValue = 0;
                gameOver();
                GameView.currentScreen = 0;
            }

        }

        //create pipes when there are too little
        if (pipes.size() < 5) {
            generatePipe();
        }



    }

    private void gameOver(){
        //check if the score is higher then the top
        SharedPreferences sharedPref = context.getSharedPreferences("roua.flappy.topScore", Context.MODE_PRIVATE);
        int highScore = sharedPref.getInt("roua.flappy.topScore1", -1);

        if(score > highScore){
            //commit the new on
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("roua.flappy.topScore1", score);
            editor.apply();
        }

        resetGame();
    }
    private void resetGame(){
        pipes.clear();
        score = 0;
        player.y = 100;
        //generate 4 pipes
        for(int i = 0; i < 4; i++){

            if(i == 0) {
                int y = rand.nextInt(43) + 1;
                pipes.add(
                        new Pipe(new Sprite(BitmapFactory.decodeResource(view.getResources(), R.drawable.pipe_up)),
                                1300, -y * 23)
                );
            }
            generatePipe();
        }

        returnValue = 2;
    }

    private void generatePipe(){
        int index = pipes.size() - 1;
        int y = rand.nextInt(43) + 1;
        int x = pipes.get(index).x + gapBetweenPipes;

        pipes.add(
                new Pipe(new Sprite(BitmapFactory.decodeResource(view.getResources(), R.drawable.pipe_up)),
                        x, -y * 23)
        );
    }

    int handleCollisions(){
        player.up();

        return returnValue;
    }
}
