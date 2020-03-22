const animateAttribute = (attribute_block, remove, change_relation, change_primary_key) => {
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
    attribute_block.change_relation = (relation_block_id, new_relation_block_id, relation_index) =>
        change_relation({relation_block_id: relation_block_id, new_relation_block_id: new_relation_block_id, relation_index: relation_index});
    attribute_block.change_primary_key = (relation_block_id, new_relation_block_id, relation_index) =>
        change_primary_key({relation_block_id: relation_block_id, new_relation_block_id: new_relation_block_id, relation_index: relation_index});
};