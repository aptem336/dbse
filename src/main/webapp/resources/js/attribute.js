const animateAttribute = (block, remove) => {
    block.remove = remove;

    block.draggable = true;
    block.ondragstart = (e) => {
        e.stopPropagation();
        startAttributeFormDragging(e, block);
    };
    block.ondragend = (e) => {
        e.stopPropagation();
        stopAttributeFormDragging(e, block);
    };
};
// const attributeAttributeDropListener = (e, attribute) => {
//     e.stopPropagation();
//     // IMPL
// };
// const startAttributeAttributeDragging = (e, attribute) => {
//     attribute.addEventListener("dragover", (e) => e.preventDefault());
//     attribute.addEventListener("drop", attribute.attributeAttributeDropListener);
// };
// const stopAttributeAttributeDragging = (e, attribute) => {
//     attribute.removeEventListener("dragover", );
//     attribute.removeEventListener("drop", attribute.attributeAttributeDropListener);
// };
// const animateAttribute = (attribute) => {
//     attribute.remove = () => document.getElementById(attribute.id + ":remove_attribute").click();
//
//     attribute.isSuitableAttribute = (target) => {
//         // IMPL
//     };
//
//     attribute.draggable = true;
//     attribute.ondragstart = (e) => {
//         e.stopPropagation();
//         e.dataTransfer.setData("text/plain", JSON.stringify({
//             attributeId: attribute.id,
//         }));
//         e.dataTransfer.effectAllowed = "all";
//         form.startAttributeFormDragging();
//         form.relationsList.forEach((relation) => {
//             relation.startAttributeRelationDragging();
//         });
//         form.relationsList.forEach((relation) => {
//             relation.attributesList.filter((target) => {
//                 return true //FIXME заменить на фильтр
//             }).forEach((target) => {
//                 target.startAttributeAttributeDragging()
//             })
//         })
//     };
//     attribute.ondragend = (e) => {
//         e.stopPropagation();
//         stopAttributeFormDragging();
//         relationsList.forEach((relation) => {
//             relation.stopAttributeRelationDragging();
//         });
//         relationsList.forEach((relation) => {
//             relation.attributesList.filter((target) => {
//                 return true //FIXME заменить на фильтр
//             }).forEach((target) => {
//                 target.stopAttributeAttributeDragging();
//             })
//         })
//     };
// };