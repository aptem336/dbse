package view.draggable;

import java.util.function.Consumer;

public interface DragTarget<S extends DragSource> {
    Consumer<S> getDragMethod();
}
