const animateRelation = (x_input_id, y_input_id, relation_block_id) => {
    const x_input = document.getElementById(x_input_id);
    const y_input = document.getElementById(y_input_id);
    const relation_block = document.getElementById(relation_block_id);

    relation_block.shift = (x, y) => {
        x_input.value = relation_block.offsetLeft + x;
        y_input.value = relation_block.offsetTop + y;
        mojarra.ab(this, null, null, x_input_id + ' ' + y_input_id, relation_block_id);
    };

    relation_block.draggable = true;
    relation_block.ondragstart = (e) => {
        e.dataTransfer.setData("text/plain", JSON.stringify({
            pageX: e.pageX,
            pageY: e.pageY,
            relation_block_id: relation_block_id
        }));
        startRelationSchemaFormDragging();
    };
    relation_block.ondragend = (e) => {
        stopRelationSchemaFormDragging();
    };
};