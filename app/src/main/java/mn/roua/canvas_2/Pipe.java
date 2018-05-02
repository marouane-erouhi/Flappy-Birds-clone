package mn.roua.canvas_2;

import android.graphics.Canvas;

class Pipe {

    private Sprite pipe1, pipe2;
    int x, y;
    int w,h;

    Pipe (Sprite sprite, int x, int y) {
        this.pipe1 = new Sprite(sprite);
        this.pipe1.flipX();
        this.pipe2 = sprite;

        this.x = x;
        this.y = y;
        w = sprite.w;
        h = sprite.h;
    }

    void draw(Canvas canvas) {
        pipe1.draw(canvas, this.x, this.y);
        pipe2.draw(canvas, this.x, this.y + pipe2.h + Game.pipeGap);
    }
    void update() {
        x -= Game.pipeSpeed;
    }

    boolean outOfBounds(){
        return this.x < -this.pipe1.w;
    }

}
