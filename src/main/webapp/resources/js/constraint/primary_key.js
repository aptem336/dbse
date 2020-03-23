attributePrimaryKeyDragOverListener = (e) => {
    e.preventDefault();
};
attributeRelationPrimaryKeyDropListener = (e, primary_block_id, primary_key_index) => {
    e.stopPropagation();
    const data = JSON.parse(e.dataTransfer.getData("text/plain"));
    const attribute_block = document.getElementById(data.attribute_block_id);
    attribute_block.change_attribute_container(primary_block_id, primary_key_index, true);
};
startAttributePrimaryKeyDragging = () => {
    [...schema_block.getElementsByClassName('PrimaryKeyConstraint')]
        .forEach((primary_key_block, primary_key_index) => {
            primary_key_block.addEventListener('dragover', attributePrimaryKeyDragOverListener);
            primary_key_block.addEventListener('drop', e =>
                attributeRelationPrimaryKeyDropListener(e, primary_key_block.id, primary_key_index));
        });
};
stopAttributePrimaryKeyDragging = () => {
    [...schema_block.getElementsByClassName('PrimaryKeyConstraint')]
        .forEach((primary_key_block, primary_key_index) => {
            primary_key_block.removeEventListener('dragover', attributePrimaryKeyDragOverListener);
            //FIXME: not deleted!
            primary_key_block.removeEventListener('drop', e =>
                attributeRelationPrimaryKeyDropListener(e, primary_key_block.id, primary_key_index));
        });
};
