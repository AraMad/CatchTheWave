package Graphics.Models;

import android.graphics.Interpolator;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Arina on 10.01.2017.
 */

public class Layer {

    private ArrayList<BasicPrimitive> objects_on_layer = new ArrayList<BasicPrimitive>();
    private Paint paint;
    private int ordinal_number_of_layer;
    private boolean is_visible;

    public Layer(int ordinal_number){
        ordinal_number_of_layer = ordinal_number;
        paint = new Paint();
        is_visible = true;
    }

    public boolean isVisible() {
        return is_visible;
    }

    public void setVisibility(boolean visibility){
        is_visible = visibility;
    }

    public  int getNumberOfObjectsOnLayer(){
        return objects_on_layer.size();
    }

    public BasicPrimitive getObjectByIndex(int index){
        return objects_on_layer.get(index);
    }

    public void addObjectOnLayer(BasicPrimitive object){
        objects_on_layer.add(object);
        objects_on_layer.trimToSize();
    }

    public void deleteOnjectByIndex(int index){
        objects_on_layer.remove(index);
    }

    public void clearLayer(){
        objects_on_layer.clear();
    }

    public void updateObjectsOnLayer(){
        int num_of_obj = objects_on_layer.size();
        for (int i = 0; i < num_of_obj; i++){
            if (objects_on_layer.get(i) != null){
                objects_on_layer.get(i).updateParametersOfPrimitive();
            }
        }
    }

    public void resizeObjectsOnLayer(float weight, float height){
        for (BasicPrimitive primitive: objects_on_layer) {
            if (primitive != null && primitive.type_of_primitive == BasicPrimitive.TYPE_SPRITE){
                ((Sprite)primitive).resize(weight, height);
            }
        }
    }

    public BasicPrimitive getPrimitiveWhichHavePoint(float point_X, float point_Y){
        for (BasicPrimitive primitive: objects_on_layer) {
            if (primitive != null && primitive.isPointGetIntoPrimitive(point_X, point_Y)){
                return primitive;
            }
        }
        return null;
    }

    public int getOrdinalNumberOfLayer() {
        return ordinal_number_of_layer;
    }

    public Paint getPaint(){
        return paint;
    }
}
