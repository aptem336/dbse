attributePrimaryKeyDragOverListener = (e) => {
    e.preventDefault();
};
attributeRelationPrimaryKeyDropListener = (e, relation_block_id, relation_index) => {
    e.stopPropagation();
    const data = JSON.parse(e.dataTransfer.getData("text/plain"));
    const attribute_block = document.getElementById(data.attribute_block_id);
    attribute_block.change_primary_key(relation_block_id, relation_index);
};
startAttributePrimaryKeyDragging = () => {
    [...schema_block.getElementsByClassName('Relation')]
        .forEach((relation_block, relation_index) => {
            relation_block.getElementsByClassName('PrimaryKeyConstraint')[0]
                .addEventListener('dragover', attributePrimaryKeyDragOverListener);
            //FIXME: not deleted!
            relation_block.getElementsByClassName('PrimaryKeyConstraint')[0]
                .addEventListener('drop', e =>
                    attributeRelationPrimaryKeyDropListener(e, relation_block.id, relation_index));
        });
};
stopAttributePrimaryKeyDragging = () => {
    [...schema_block.getElementsByClassName('Relation')]
        .forEach((relation_block, relation_index) => {
            relation_block.getElementsByClassName('PrimaryKeyConstraint')[0]
                .removeEventListener('dragover', attributePrimaryKeyDragOverListener);
            //FIXME: not deleted!
            relation_block.getElementsByClassName('PrimaryKeyConstraint')[0]
                .removeEventListener('drop', e =>
                    attributeRelationPrimaryKeyDropListener(e, relation_block.id, relation_index));
        });
};
