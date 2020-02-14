let form;
const animateForm = (formId) => {
    form = document.getElementById(formId);
};
const relationDropListener = (e, relation, x, y) => {
    e.stopPropagation();
    relation.shift(e.pageX - x, e.pageY - y);
};
const relationDragOverListener = (e, relation, x, y) => {
    defaultDragOverListener(e);
};
const startRelationDragging = (e, relation, x, y) => {
    form.addEventListener("drop", (e) => relationDropListener(e, relation, x, y), {once: true});
    form.addEventListener("dragover", relationDragOverListener);//Q?
};
const stopRelationDragging = (e, relation, x, y) => {
    form.removeEventListener("dragover", relationDragOverListener);//Q?
};
// const attributeFormDropListener = (e) => {
//     e.stopPropagation();
//     const dragData = JSON.parse(e.dataTransfer.getData("text/plain"));
//     const attribute = document.getElementById(dragData.attributeId);
//     attribute.remove();
// };
// const startAttributeFormDragging = (form) => {
//     form.addEventListener("dragover", (e) => e.preventDefault());
//     form.addEventListener("drop", (e) => attributeFormDropListener(e, form));
// };
// const stopAttributeFormDragging = (form) => {
//     form.removeEventListener("dragover", (e) => e.preventDefault());
//     form.removeEventListener("drop", (e) => attributeFormDropListener(e, form));
// };