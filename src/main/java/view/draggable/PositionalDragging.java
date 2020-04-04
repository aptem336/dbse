package view.draggable;

import model.Model;
import view.Positional;

public class PositionalDragging {
    public static<T extends Model & Draggable<T> & Positional<T>> void drag(T t) {
    }
}
