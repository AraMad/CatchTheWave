package Graphics.Models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import Settings.Constants;

import static java.lang.Math.abs;

/**
 * Created by Arina on 09.01.2017.
 */

public class Sprite extends Point{

    private Bitmap bitmap;
    private Matrix matrix = new Matrix();

    private int width_of_sprite_in_pixels;
    private int height_of_sprite_in_pixels;

    private boolean is_animated;
    private int type_of_animation = -1;

    private float coord_X_of_finish;
    private float coord_Y_of_finish;
    private int speed;

    private float greed_of_rotate;

    private float coord_X_of_center_rot;
    private float coord_Y_of_center_rot;

    private float height_of_jump;
    private boolean is_jump;

    public void jump(float height){
        height_of_jump = height;
        is_animated = true;
        type_of_animation = Constants.JUMPING;
        is_jump = false;
    }

    public void moveByStraightLine(float x, float y, int speed){
        coord_X_of_finish = x;
        coord_Y_of_finish = y;
        type_of_animation = Constants.MOVE_BY_STRAIGHT_LINE;
        is_animated = true;
        this.speed = speed;
    }

    public void rotateByOwnSentr(float greed){
        greed_of_rotate = greed;
        is_animated = true;
        type_of_animation = Constants.ROTATION_AROUND_OWN_CENTER;
    }

    public void moveByCirce(float X, float Y, float greed){
        coord_X_of_center_rot = X;
        coord_Y_of_center_rot = Y;
        greed_of_rotate = greed;
        is_animated = true;
        type_of_animation = Constants.MOVE_BY_CIRCLE;
    }

    public Sprite(float x, float y, Bitmap bmp) {
        super(x, y);
        bitmap = bmp;
        type_of_primitive = TYPE_SPRITE;
    }

    public Sprite(String path_to_file_with_sprite) {
        super(0, 0);
        bitmap = BitmapFactory.decodeFile(path_to_file_with_sprite);
        type_of_primitive = TYPE_SPRITE;
    }

    public Sprite(Resources resources, int id) {
        super(0, 0);
        bitmap = BitmapFactory.decodeResource(resources, id);
        type_of_primitive = TYPE_SPRITE;
    }

    public void resize(int new_width_in_pixels, int  new_height_in_pixels) {
        bitmap = Bitmap.createScaledBitmap(bitmap, new_width_in_pixels, new_height_in_pixels, true);
        refreshSpriteAndMatrix();
    }

    public void resize(float multiplier_at_x, float multiplier_at_y) {
        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int)(width_of_sprite_in_pixels *multiplier_at_x),
                (int)(height_of_sprite_in_pixels *multiplier_at_y),
                true);
        refreshSpriteAndMatrix();
    }

    private void refreshSpriteAndMatrix() {
        if (bitmap != null) {
            width_of_sprite_in_pixels = bitmap.getWidth();
            height_of_sprite_in_pixels = bitmap.getHeight();
            matrix.setTranslate(getCoordinate_X(), getCoordinate_Y());
        }
    }

    public void setAnimated(boolean animated) {
        is_animated = animated;
    }

    public float getWidthOfSpriteInPixels() {
        return width_of_sprite_in_pixels;
    }

    public float getHeightOfSpriteInPixels() {
        return height_of_sprite_in_pixels;
    }

    @Override
    public void setCoordinate_X(float coordinate_X) {
        super.setCoordinate_X(coordinate_X);
        refreshSpriteAndMatrix();
    }

    @Override
    public void setCoordinate_Y(float coordinate_Y) {
        super.setCoordinate_Y(coordinate_Y);
        refreshSpriteAndMatrix();
    }

    @Override
    public void setXYCoordinates(float coordinate_X, float coordinate_Y) {
        super.setXYCoordinates(coordinate_X, coordinate_Y);
        refreshSpriteAndMatrix();
    }

    @Override
    public boolean isPointGetIntoPrimitive(float x_of_point, float y_of_point) {
        if ((x_of_point > this.getCoordinate_X() && x_of_point < (this.getCoordinate_X() + width_of_sprite_in_pixels))
                && (y_of_point > this.getCoordinate_Y() && y_of_point < (this.getCoordinate_Y() + height_of_sprite_in_pixels))){
            return true;
        }
        return false;
    }

    public  boolean isCollision(Sprite object, int odds){
        if (abs(coordinate_X - object.getCoordinate_X() - odds) <= (width_of_sprite_in_pixels + object.getWidthOfSpriteInPixels())/2f
                && abs(coordinate_Y - object.getCoordinate_Y() - odds) <= (height_of_sprite_in_pixels + object.getHeightOfSpriteInPixels())/2f){
            return true;
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        if (is_animated){

            switch (type_of_animation){
                case Constants.MOVE_BY_STRAIGHT_LINE:
                    moveByLine();
                    matrix.setTranslate(coordinate_X, coordinate_Y);
                    canvas.drawBitmap(bitmap, matrix, paint);
                    break;
                case Constants.MOVE_BY_CURVE_LINE: break;
                case Constants.ROTATION_AROUND_OWN_CENTER:{
                    matrix.preRotate(greed_of_rotate, bitmap.getWidth()/2, bitmap.getHeight()/2);
                    canvas.drawBitmap(bitmap, matrix, paint);
                    break;
                }
                case Constants.MOVE_BY_CIRCLE:{
                    matrix.preRotate(greed_of_rotate, coord_X_of_center_rot, coord_Y_of_center_rot);
                    canvas.drawBitmap(bitmap, matrix, paint);
                    break;
                }
                case Constants.JUMPING:{
                    jump();
                    matrix.setTranslate(coordinate_X, coordinate_Y);
                    canvas.drawBitmap(bitmap, matrix, paint);
                    break;
                }
                default: canvas.drawBitmap(bitmap, matrix, paint);
            }

        } else {
            canvas.drawBitmap(bitmap, matrix, paint);
        }
    }

    private void moveByLine(){

        if (coord_X_of_finish % speed != 0){
            coordinate_X -= coord_X_of_finish % speed;
        }

        if (coord_Y_of_finish % speed != 0){
            coordinate_Y -= coord_Y_of_finish % speed;
        }

        if(coordinate_X != coord_X_of_finish){
            if (coord_X_of_finish > coordinate_X){
                coordinate_X = coordinate_X + speed;
            } else {
                coordinate_X = coordinate_X - speed;
            }
        }

        if(coordinate_Y != coord_Y_of_finish){
            if (coord_Y_of_finish > coordinate_Y){
                coordinate_Y += speed;
            } else {
                coordinate_Y -= speed;
            }
        }

        if (coordinate_X == coord_X_of_finish && coordinate_Y == coord_Y_of_finish){
            is_animated = true;
        }
    }

    private void jump(){
        if (is_jump){
            coordinate_Y -= height_of_jump;
            is_jump = false;
        } else {
            coordinate_Y += height_of_jump;
            is_jump = true;
        }
    }

    public void rotate(float rot_degree){
        matrix.postRotate(rot_degree, bitmap.getWidth()/2, bitmap.getHeight()/2);
    }
}
