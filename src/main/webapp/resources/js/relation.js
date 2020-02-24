const animateRelation = (relation_block, shiftXY) => {
    relation_block.draggable = true;
    relation_block.ondragstart = (e) => {
        e.dataTransfer.setData("text/plain", JSON.stringify({
            pageX: e.pageX,
            pageY: e.pageY,
            relation_block_id: relation_block.id
        }));
        startRelationSchemaFormDragging();
    };
    relation_block.ondragend = (e) => {
        stopRelationSchemaFormDragging();
    };
    relation_block.shiftXY = (x, y) => shiftXY({x: x, y: y});
};