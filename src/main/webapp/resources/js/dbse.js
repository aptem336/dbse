let schema_form;
const animateSchemaForm = (schema_form_id) => {
    schema_form = document.getElementById(schema_form_id);
};
const relationSchemaFormDragOverListener = (e) => {
    e.preventDefault();
};
const relationSchemaDropListener = (e) => {
    const data = JSON.parse(e.dataTransfer.getData("text/plain"));
    const relation = document.getElementById(data.relation_block_id);
    relation.shift(e.pageX - data.pageX, e.pageY - data.pageY);
};
const startRelationSchemaFormDragging = () => {
    schema_form.addEventListener("drop", (e) => relationSchemaDropListener(e));
    schema_form.addEventListener("dragover", relationSchemaFormDragOverListener);
};
const stopRelationSchemaFormDragging = () => {
    schema_form.removeEventListener("drop", (e) => relationSchemaDropListener(e));
    schema_form.removeEventListener("dragover", relationSchemaFormDragOverListener);
};