package Graphics.Models;

/**
 * Created by Arina on 10.01.2017.
 */

public class Scene {
    private Layer[] layers_on_scene;
    private int current_layer = 0;

    public static final int ORIENTATION_VER = 1;
    public static final int ORIENTATION_HOR = 0;

    private int orientation;
    private int count_of_layers_on_scene;

    private int width_of_scene_in_pixels;
    private int height_of_scene_in_pixels;

    public Scene(int orientation, int width_in_pixels, int height_in_pixels, int count_of_layers){

        width_of_scene_in_pixels = width_in_pixels;
        height_of_scene_in_pixels = height_in_pixels;
        this.orientation = orientation;
        count_of_layers_on_scene = count_of_layers;

        layers_on_scene = new Layer[count_of_layers_on_scene];
        for (int i = 0; i < count_of_layers_on_scene; i++){
            layers_on_scene[i] = new Layer(i);
        }

        current_layer = count_of_layers-1;
    }

    public void setCurrentLayer(int curr_layer) {
        if (curr_layer < count_of_layers_on_scene && curr_layer >= 0){
            current_layer = curr_layer;
        }
    }

    public void setLayerVisibility(int index_of_layer, boolean visibility){
        layers_on_scene[index_of_layer].setVisibility(visibility);
    }

    public void addObjectOnCurrentLayer(BasicPrimitive object){
        layers_on_scene[current_layer].addObjectOnLayer(object);
    }

    public int getCountOfLayersOnScene() {
        return count_of_layers_on_scene;
    }

    public int getNumberOfCurrentLayer() {
        return current_layer;
    }

    public Layer getCurrantLayer(){
        return layers_on_scene[current_layer];
    }

    public void clearCurrentLayer(){
        layers_on_scene[current_layer].clearLayer();
    }

    public void deleteObjectFromCurrentLayerByIndex(int index){
        layers_on_scene[current_layer].deleteOnjectByIndex(index);
    }

    public Layer getLayerByIndex(int index){
        if (index >= 0 && index < count_of_layers_on_scene){
            return layers_on_scene[index];
        }
        return null;
    }

    public int getWidthOfScene() {
        return width_of_scene_in_pixels;
    }

    public int getHeightOfScene() {
        return height_of_scene_in_pixels;
    }

    public BasicPrimitive getSpriteWhichHavePointFromCurrantLayer(float point_x, float point_y){
        return layers_on_scene[current_layer].getPrimitiveWhichHavePoint(point_x, point_y);
    }

    public void updateAllLayers(){
        for (Layer layer: layers_on_scene) {
            layer.updateObjectsOnLayer();
        }
    }

    public void setSceneWightAndHeight(int weight, int height){
        width_of_scene_in_pixels = weight;
        height_of_scene_in_pixels = height;
    }
}
