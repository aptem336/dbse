const animateRelation = (relation_block, relation_x_input, relation_y_input/*, relation_id, relation_attributes_wrapper*/) => {
    relation_block.shift = (shiftX, shiftY) => {
        relation_x_input.value = relation_block.offsetLeft + shiftX;
        relation_y_input.value = relation_block.offsetTop + shiftY;
        //Q unresolved in idea :(
        mojarra.ab(relation_block, null, 'action', null, relation_block.id);
    };

    relation_block.draggable = true;
    relation_block.ondragstart = (e) => {
        e.stopPropagation();
        e.dataTransfer.setData("text/plain", JSON.stringify({
            relation_block_id: relation_block.id,
            x: e.pageX,
            y: e.pageY
        }));
        startRelationFormDragging();
    };
    relation_block.ondragend = (e) => {
        e.stopPropagation();
        stopRelationFormDragging();
    };
    relation_block.attributeRelationDragOverListener = (e) => {
        defaultDragOverListener(e);
    };
    relation_block.attributeRelationDropListener = (e) => {
        e.stopPropagation();
        //IMPL
    };
};

const startAttributeRelationDragging = () => {
    [...relations_wrapper.getElementsByClassName('attributes-wrapper')]
        .forEach((attribute_wrapper) => {
            attribute_wrapper.addEventListener("drop", attribute_wrapper.parentElement.attributeRelationDropListener);
            attribute_wrapper.addEventListener("dragover", attribute_wrapper.parentElement.attributeRelationDragOverListener);
        });
};
const stopAttributeRelationDragging = () => {
    [...relations_wrapper.getElementsByClassName('attributes-wrapper')]
        .forEach((attribute_wrapper) => {
            attribute_wrapper.removeEventListener("drop", attribute_wrapper.parentElement.attributeRelationDropListener);
            attribute_wrapper.removeEventListener("dragover", attribute_wrapper.parentElement.attributeRelationDragOverListener);
        })
};