const animateRelation = (relation_block, shift) => {
    relation_block.draggable = true;
    relation_block.ondragstart = (e) => {
        e.dataTransfer.setData("text/plain", JSON.stringify({
            pageX: e.pageX,
            pageY: e.pageY,
            relation_block_id: relation_block.id
        }));
        startRelationSchemaDragging();
    };
    relation_block.ondragend = () => {
        stopRelationSchemaDragging();
    };
    relation_block.shift = (x, y) => shift({x: x, y: y});
};

const attributeRelationDragOverListener = (e) => {
    e.preventDefault();
};
const attributeRelationDropListener = (e, relation_block_id, relation_index) => {
    e.stopPropagation();
    const data = JSON.parse(e.dataTransfer.getData("text/plain"));
    const attribute_block = document.getElementById(data.attribute_block_id);
    attribute_block.change_relation(relation_block_id, relation_index);
};
const startAttributeRelationDragging = () => {
    [...schema_block.getElementsByClassName('relation-block')].forEach((relation_block, relation_index) => {
        relation_block.addEventListener('dragover', attributeRelationDragOverListener);
        relation_block.addEventListener('drop', e => attributeRelationDropListener(e, relation_block.id, relation_index));
    });
};
const stopAttributeRelationDragging = () => {
    [...schema_block.getElementsByClassName('relation-block')].forEach((relation_block, relation_index) => {
        relation_block.removeEventListener('dragover', attributeRelationDragOverListener);
        relation_block.removeEventListener('drop', e => attributeRelationDropListener(e, relation_block.id, relation_index));
    });
};