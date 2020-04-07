package view.draggable;

import model.Model;
import view.Positioned;

public class PositionedDragMethod {
    public static<T extends Model & DragSource<T> & Positioned> void accept(T t) {
    }
}
