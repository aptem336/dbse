const animateAttribute = (attribute_block, attribute_remove) => {
    attribute_block.attribute_remove = attribute_remove;

    attribute_block.draggable = true;
    attribute_block.ondragstart = (e) => {
        e.stopPropagation();
        e.dataTransfer.setData("text/plain", JSON.stringify({
            attribute_block_id: attribute_block.id
        }));
        startAttributeFormDragging();
        startAttributeRelationDragging();
        //startAttributeAttributeDragging(attribute_block);
    };
    attribute_block.ondragend = (e) => {
        e.stopPropagation();
        stopAttributeFormDragging();
        stopAttributeRelationDragging();
        //stopAttributeAttributeDragging(attribute_block);
    };
    attribute_block.attributeAttributeDragOverListener = (e) => {
        defaultDragOverListener(e);
    };
    attribute_block.attributeAttributeDropListener = (e) => {
        e.stopPropagation();
        //IMPL
    };
};
const startAttributeAttributeDragging = (attribute_block) => {
    [...relations_wrapper.getElementsByClassName('attribute-block')]
        .filter((t_attribute_block) => attribute_block.id !== t_attribute_block.id)
        .forEach((t_attribute_block) => {
            t_attribute_block.addEventListener("drop", t_attribute_block.attributeAttributeDropListener);
            t_attribute_block.addEventListener("dragover", t_attribute_block.attributeAttributeDragOverListener);
        });
};
const stopAttributeAttributeDragging = (attribute_block) => {
    [...relations_wrapper.getElementsByClassName('attribute-block')]
        .filter((t_attribute_block) => attribute_block.id !== t_attribute_block.id)
        .forEach((t_attribute_block) => {
            t_attribute_block.removeEventListener("drop", t_attribute_block.attributeAttributeDropListener);
            t_attribute_block.removeEventListener("dragover", t_attribute_block.attributeAttributeDragOverListener);
        })
};