let schema_form;
const animateSchemaForm = (schema_form_, createXY) => {
    schema_form = schema_form_;
    schema_form.ondblclick = (e) => {
        if (e.target === schema_form) {
            createXY({x: e.pageX, y: e.pageY})
        }
    };
};
const relationSchemaFormDragOverListener = (e) => {
    e.preventDefault();
};
const relationSchemaDropListener = (e) => {
    const data = JSON.parse(e.dataTransfer.getData("text/plain"));
    const relation_block = document.getElementById(data.relation_block_id);
    relation_block.shiftXY(e.pageX - data.pageX, e.pageY - data.pageY);
};
const startRelationSchemaFormDragging = () => {
    schema_form.addEventListener("drop", relationSchemaDropListener);
    schema_form.addEventListener("dragover", relationSchemaFormDragOverListener);
};
const stopRelationSchemaFormDragging = () => {
    schema_form.removeEventListener("drop", relationSchemaDropListener);
    schema_form.removeEventListener("dragover", relationSchemaFormDragOverListener);
};