package view.draggable;

import model.Model;
import view.Positioned;

public class PositionedDragMethod implements DragMethod {
    public static<T extends Model & DragSource & Positioned> void accept(T t) {
    }
}
