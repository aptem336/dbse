let schema_block;
const animateSchema = (schema_block_, create) => {
    schema_block = schema_block_;
    schema_block.ondblclick = (e) => {
        if (e.target === schema_block) {
            create({x: e.pageX, y: e.pageY})
        }
    };
};

const relationSchemaDragOverListener = (e) => {
    e.preventDefault();
};
const relationSchemaDropListener = (e) => {
    const data = JSON.parse(e.dataTransfer.getData("text/plain"));
    const relation_block = document.getElementById(data.relation_block_id);
    relation_block.shift(e.pageX - data.pageX, e.pageY - data.pageY);
};
const startRelationSchemaDragging = () => {
    schema_block.addEventListener("drop", relationSchemaDropListener);
    schema_block.addEventListener("dragover", relationSchemaDragOverListener);
};
const stopRelationSchemaDragging = () => {
    schema_block.removeEventListener("drop", relationSchemaDropListener);
    schema_block.removeEventListener("dragover", relationSchemaDragOverListener);
};

const attributeSchemaDragOverListener = (e) => {
    e.preventDefault();
};
const attributeSchemaDropListener = (e) => {
    const data = JSON.parse(e.dataTransfer.getData("text/plain"));
    const attribute_block = document.getElementById(data.attribute_block_id);
    attribute_block.remove();
};
const startAttributeSchemaDragging = () => {
    schema_block.addEventListener("drop", attributeSchemaDropListener);
    schema_block.addEventListener("dragover", attributeSchemaDragOverListener);
};
const stopAttributeSchemaDragging = () => {
    schema_block.removeEventListener("drop", attributeSchemaDropListener);
    schema_block.removeEventListener("dragover", attributeSchemaDragOverListener);
};