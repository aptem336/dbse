document.addEventListener(
    //TODO: переделать под новый стиль
    'DOMContentLoaded',
    () => {
        let counter = 0;
        const animateElementToElement = (element, target, ondrop, ondragover, idPrefix, ondragstart, ondragend) => {
            element.draggable = true;
            element.id = (idPrefix || 'draggable_') + counter;
            const dragOverListener = ondragover ||
                ((e) => {
                    e.preventDefault();
                });
            const dragStartListener = ondragstart ||
                ((e) => {
                    e.dataTransfer.setData('text/plain', JSON.stringify({
                        elementId: element.id,
                        eventX: e.pageX,
                        eventY: e.pageY
                    }));
                    target.addEventListener('dragover', dragOverListener);
                    target.addEventListener('drop', ondrop);
                });
            const dragEndListener = ondragend ||
                ((e) => {
                    target.removeEventListener('dragover', dragOverListener);
                    target.removeEventListener('drop', ondrop);
                });
            element.ondragstart = dragStartListener;
            element.ondragend = dragEndListener;
            counter++;
        };
        const animateElementToSelector = (element, targetSelector, ondrop, ondragover, idPrefix, ondragstart, ondragend) => {
            [...document.querySelectorAll(targetSelector)].forEach(target =>
                animateElementToElement(element, target, ondrop, ondragover, idPrefix, ondragstart, ondragend)
            )
        };
        const animateSelectorToSelector = (elementSelector, targetSelector, ondrop, ondragover, idPrefix, ondragstart, ondragend) => {
            [...document.querySelectorAll(elementSelector)].forEach(element =>
                animateElementToSelector(element, targetSelector, ondrop, ondragover, idPrefix, ondragstart, ondragend)
            )
        };
        animateSelectorToSelector(
            '.relation-block',
            '#relations-form',
            (e) => {
                const dataTransferJSON = JSON.parse(e.dataTransfer.getData('text/plain'));
                const relationId = dataTransferJSON.elementId;
                const relation = document.getElementById(relationId);
                relation.style.top = relation.offsetTop + (e.pageY - dataTransferJSON.eventY) + 'px';
                relation.style.left = relation.offsetLeft + (e.pageX - dataTransferJSON.eventX) + 'px';
            },
        );
    });