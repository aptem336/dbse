package view.draggable;

import java.util.function.Consumer;

public interface DragTarget<S extends DragSource<S>> {
    Consumer<S> getDragMethod();
}
