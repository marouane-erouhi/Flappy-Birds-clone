package mn.roua.canvas_2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

/**
 * Created by marou on 4/10/2018.
 */

public class Sprite implements Cloneable {

    private Bitmap img;
    int w, h;

    private int screenWidth, screenHeight;

    Sprite(Bitmap img){
        this.img = img;
        this.w = img.getWidth();
        this.h = img.getHeight();
    }
    Sprite(Sprite a){
        this.img = a.img;
        this.w = a.w;
        this.h = a.h;
    }
    Sprite(Bitmap img, int w, int h){
        this.img = img;
        this.w = w; this.h = h;

        //resize img
        int width = img.getWidth();
        int height = img.getHeight();

        float scaleWidth = ((float) w) / width;
        float scaleHeight = ((float) h) / height;

        //manipulation matrix
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        this.img = Bitmap.createBitmap(img, 0, 0, width, height, matrix, false);


        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    void draw(Canvas canvas, int x, int y){
        canvas.drawBitmap(this.img, x,y,null);
    }

    void flipX(){
        Matrix m = new Matrix();
        m.preScale(1, -1);
        Bitmap src = img;
        img = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
    }

}
