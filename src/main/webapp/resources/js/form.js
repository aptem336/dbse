let form;
const relationFormDragOverListener = (e, relation, x, y) => {
    defaultDragOverListener(e);
};
const relationFormDropListener = (e, relation, x, y) => {
    e.stopPropagation();
    relation.shift(e.pageX - x, e.pageY - y);
};
const startRelationFormDragging = (e, relation, x, y) => {
    form.addEventListener("drop", (e) => relationFormDropListener(e, relation, x, y), {once: true});
    form.addEventListener("dragover", relationFormDragOverListener);//Q?
};
const stopRelationFormDragging = (e, relation, x, y) => {
    form.removeEventListener("dragover", relationFormDragOverListener);//Q?
};

const attributeFormDragOverListener = (e, attribute) => {
    defaultDragOverListener(e);
};
const attributeFormDropListener = (e, attribute) => {
    e.stopPropagation();
    attribute.remove();
};
const startAttributeFormDragging = (e, attribute) => {
    form.addEventListener("drop", (e) => attributeFormDropListener(e, attribute), {once: true});
    form.addEventListener("dragover", attributeFormDragOverListener);//Q
};
const stopAttributeFormDragging = (e, attribute) => {
    form.removeEventListener("dragover", attributeFormDragOverListener);//Q
};