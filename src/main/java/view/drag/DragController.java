package view.drag;

import model.RelationModel;
import model.SchemaModel;

import javax.inject.Named;
import java.util.function.BiConsumer;

@Named
public class DragController {
    public DragStrategy<SchemaModel, RelationModel> getDragStrategy(SchemaModel schemaTarget, RelationModel relationSource) {
        return (schemaModel, relationModel) -> {

        };
    }

    private interface DragStrategy<T extends DragTarget, S extends DragSource> extends BiConsumer<T, S> {
    }
}
