const animateAttribute = (attribute_block, remove) => {
    attribute_block.draggable = true;
    attribute_block.ondragstart = (e) => {
        e.stopPropagation();
        e.dataTransfer.setData("text/plain", JSON.stringify({
            attribute_block_id: attribute_block.id
        }));
        startAttributeSchemaFormDragging();
    };
    attribute_block.ondragend = (e) => {
        stopAttributeSchemaFormDragging();
    };
    attribute_block.remove = remove;
};