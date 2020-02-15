const animateRelation = (block, x, y) => {
    block.shift = (shiftX, shiftY) => {
        x.value = block.offsetLeft + shiftX;
        y.value = block.offsetTop + shiftY;
        mojarra.ab(block, null, 'action', null, block.id);
    };

    block.draggable = true;
    block.ondragstart = (e) => {
        startRelationFormDragging(e, block, e.pageX, e.pageY);
    };
    block.ondragend = (e) => {
        stopRelationFormDragging(e, block, e.pageX, e.pageY);
    };
};
// const attributeRelationDropListener = (e, relation) => {
//     e.stopPropagation();
//     // IMPL
// };
// const startAttributeRelationDragging = () => {
//     relationsList.forEach((relation) => {
//         relation.addEventListener("dragover", (e) => e.preventDefault());
//         relation.addEventListener("drop", (e) => attributeRelationDropListener(e, relation));
//     });
// };
// const stopAttributeRelationDragging = () => {
//     relationsList.forEach((relation) => {
//         relation.removeEventListener("dragover", (e) => e.preventDefault());
//         relation.removeEventListener("drop", (e) => attributeRelationDropListener(e, relation));
//     })
// };