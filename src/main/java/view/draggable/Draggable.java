package view.draggable;

import model.Model;

import java.util.Map;
import java.util.function.Consumer;

public interface Draggable<T extends Model> {
    Map<Class<? extends Model>, Consumer<T>> getDragMap();
}
