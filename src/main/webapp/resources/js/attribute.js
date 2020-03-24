const animateAttribute = (attribute_block, remove, change_attribute_container) => {
    attribute_block.draggable = true;
    attribute_block.ondragstart = (e) => {
        e.stopPropagation();
        e.dataTransfer.setData("text/plain", JSON.stringify({
            attribute_block_id: attribute_block.id
        }));
        startAttributeSchemaDragging();
        startAttributeRelationDragging();
        startAttributePrimaryKeyDragging();
    };
    attribute_block.ondragend = () => {
        stopAttributeSchemaDragging();
        stopAttributeRelationDragging();
        stopAttributePrimaryKeyDragging();
    };
    attribute_block.remove = remove;
    attribute_block.change_attribute_container = (new_container_block_id, container_index, is_primary_key) =>
        change_attribute_container({container_block_id: attribute_block.parentElement.parentElement.id,
            new_container_block_id: new_container_block_id,
            container_index: container_index,
            is_primary_key: is_primary_key});
};