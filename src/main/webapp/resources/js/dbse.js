let relations_wrapper;
const relationFormDragOverListener = (e) => {
    defaultDragOverListener(e);
};
const relationFormDropListener = (e) => {
    e.stopPropagation();
    const data = JSON.parse(e.dataTransfer.getData("text/plain"));
    const relation = document.getElementById(data.relation_block_id);
    relation.shift(e.pageX - data.x, e.pageY - data.y);
};
const startRelationFormDragging = () => {
    relations_wrapper.addEventListener("drop", relationFormDropListener);
    relations_wrapper.addEventListener("dragover", relationFormDragOverListener);
};
const stopRelationFormDragging = () => {
    relations_wrapper.removeEventListener("drop", relationFormDropListener);
    relations_wrapper.removeEventListener("dragover", relationFormDragOverListener);
};

const attributeFormDragOverListener = (e) => {
    defaultDragOverListener(e);
};
const attributeFormDropListener = (e) => {
    e.stopPropagation();
    const data = JSON.parse(e.dataTransfer.getData("text/plain"));
    const attribute = document.getElementById(data.attribute_block_id);
    attribute.attribute_remove.click();
};
const startAttributeFormDragging = () => {
    relations_wrapper.addEventListener("drop", attributeFormDropListener);
    relations_wrapper.addEventListener("dragover", attributeFormDragOverListener);
};
const stopAttributeFormDragging = () => {
    relations_wrapper.removeEventListener("drop", attributeFormDropListener);
    relations_wrapper.removeEventListener("dragover", attributeFormDragOverListener);
};

const defaultDragOverListener = (e) => {
    e.preventDefault();
};