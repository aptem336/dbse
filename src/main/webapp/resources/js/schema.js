let schema_form;
const animateSchemaForm = (schema_form_, create) => {
    schema_form = schema_form_;
    schema_form.ondblclick = (e) => {
        if (e.target === schema_form) {
            create({x: e.pageX, y: e.pageY})
        }
    };
};

const relationSchemaFormDragOverListener = (e) => {
    e.preventDefault();
};
const relationSchemaDropListener = (e) => {
    const data = JSON.parse(e.dataTransfer.getData("text/plain"));
    const relation_block = document.getElementById(data.relation_block_id);
    relation_block.shift(e.pageX - data.pageX, e.pageY - data.pageY);
};
const startRelationSchemaFormDragging = () => {
    schema_form.addEventListener("drop", relationSchemaDropListener);
    schema_form.addEventListener("dragover", relationSchemaFormDragOverListener);
};
const stopRelationSchemaFormDragging = () => {
    schema_form.removeEventListener("drop", relationSchemaDropListener);
    schema_form.removeEventListener("dragover", relationSchemaFormDragOverListener);
};

const attributeSchemaFormDragOverListener = (e) => {
    e.preventDefault();
};
const attributeSchemaDropListener = (e) => {
    const data = JSON.parse(e.dataTransfer.getData("text/plain"));
    const attribute_block = document.getElementById(data.attribute_block_id);
    attribute_block.remove();
};
const startAttributeSchemaFormDragging = () => {
    schema_form.addEventListener("drop", attributeSchemaDropListener);
    schema_form.addEventListener("dragover", attributeSchemaFormDragOverListener);
};
const stopAttributeSchemaFormDragging = () => {
    schema_form.removeEventListener("drop", attributeSchemaDropListener);
    schema_form.removeEventListener("dragover", attributeSchemaFormDragOverListener);
};