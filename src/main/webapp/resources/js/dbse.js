document.addEventListener(
    'DOMContentLoaded',
    () => {
        let relationsCount = 0;
        [].forEach.call(
            document.getElementsByClassName('relation-block'),
            (relationBlock) => {
                relationBlock.id = 'relation_' + relationsCount++;
                relationBlock.draggable = true;
                relationBlock.ondragstart = (e) => {
                    e.dataTransfer.setData('text/plain', JSON.stringify({
                        relationBlockId: relationBlock.id,
                        x: e.pageX,
                        y: e.pageY
                    }));
                    e.dataTransfer.effectAllowed = 'move';
                };
            }
        );
        const relationsForm = document.getElementById('relations-form');
        relationsForm.ondragover = (e) => {
            e.preventDefault();
        };
        relationsForm.ondrop = (e) => {
            const dragData = JSON.parse(e.dataTransfer.getData('text/plain'));
            const relationBlock = document.getElementById(dragData.relationBlockId);
            relationBlock.style.top = relationBlock.offsetTop + (e.pageY - dragData.y) + 'px';
            relationBlock.style.left = relationBlock.offsetLeft + (e.pageX - dragData.x) + 'px';
        }
    });
