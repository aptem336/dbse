package view.drag;

import model.RelationModel;
import model.SchemaModel;

import javax.inject.Named;
import java.util.function.BiConsumer;

@Named
public class DragController {
    public BiConsumer<SchemaModel, RelationModel> getDragMethod(SchemaModel schemaTarget, RelationModel relationSource) {
        return (schemaModel, relationModel) -> {
        };
    }
}
