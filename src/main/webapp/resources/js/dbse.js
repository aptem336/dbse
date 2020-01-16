document.addEventListener(
    //TODO: передача пармаметров не через dragData?
    //TODO: избавиться от дубликатов (описание JSONом)
    'DOMContentLoaded',
    () => {
        const animateElementToElement = (element, targetSelector, ondrop, ondragstart, ondragover, ondragend) => {

        };
        const animateElementToSelector = (element, targetSelector, ondrop, ondragstart, ondragover, ondragend) => {

        };
        const animateSelectorToSelector = (elementSelector, targetSelector, ondrop, ondragstart, ondragover, ondragend) => {
            [...document.querySelectorAll(elementSelector)].forEach(element =>
                [...document.querySelectorAll(targetSelector)].forEach(target =>
                    DnD(element, target, ondrop, ondragstart, ondragover, ondragend)
                )
            )
        };
        animateSelectorToSelector(
            'relation',
            'relations-form',
            () => {
            }
        );
        // const form = document.getElementById('relations-form');
        // form.relationsArray = [...document.getElementsByClassName('relation-block')];
        // form.relationFormDragOverListener = (e) => {
        //     e.preventDefault();
        // };
        // form.relationFormDropListener = (e) => {
        //     const dragData = JSON.parse(e.dataTransfer.getData('text/plain'));
        //     const relation = document.getElementById(dragData.relationId);
        //     relation.style.top = relation.offsetTop + (e.pageY - dragData.y) + 'px';
        //     relation.style.left = relation.offsetLeft + (e.pageX - dragData.x) + 'px';
        //     //TODO: изменения на бэке
        // };
        // form.attributeFormDragOverListener = (e) => {
        //     e.preventDefault();
        //     //TODO: перетаскивание аттрибута
        // };
        // form.attributeFormDropListener = (e) => {
        //     const dragData = JSON.parse(e.dataTransfer.getData('text/plain'));
        //     const attribute = document.getElementById(dragData.attributeId);
        //     attribute.remove();
        //     //TODO: удаление с бэка
        // };
        // form.startRelationFormDragging = () => {
        //     form.addEventListener('dragover', form.relationFormDragOverListener);
        //     form.addEventListener('drop', form.relationFormDropListener);
        // };
        // form.stopRelationFormDragging = () => {
        //     form.removeEventListener('dragover', form.relationFormDragOverListener);
        //     form.removeEventListener('drop', form.relationFormDropListener);
        // };
        // form.startAttributeFormDragging = () => {
        //     form.addEventListener('dragover', form.attributeFormDragOverListener);
        //     form.addEventListener('drop', form.attributeFormDropListener);
        // };
        // form.stopAttributeFormDragging = () => {
        //     form.removeEventListener('dragover', form.attributeFormDragOverListener);
        //     form.removeEventListener('drop', form.attributeFormDropListener);
        // };
        // form.animateRelation = (relation) => {
        //     //FIXME: слишком неточно, нужен выбор по классу
        //     relation.attributesArray = [...relation.getElementsByTagName('tr')];
        //     relation.attributeRelationDragOverListener = (e) => {
        //         e.preventDefault();
        //     };
        //     relation.attributeRelationDropListener = (e) => {
        //         //TODO: перетаскивание аттрибута в таблицу
        //     };
        //     relation.startAttributeRelationDragging = () => {
        //         relation.addEventListener('dragover', relation.attributeRelationDragOverListener);
        //         relation.addEventListener('drop', relation.attributeRelationDropListener);
        //     };
        //     relation.stopAttributeRelationDragging = () => {
        //         relation.removeEventListener('dragover', relation.attributeRelationDragOverListener);
        //         relation.removeEventListener('drop', relation.attributeRelationDropListener);
        //     };
        //     relation.animateAttribute = (attribute) => {
        //         attribute.attributeAttributeDragOverListener = (e) => {
        //             e.preventDefault();
        //         };
        //         attribute.attributeAttributeDropListener = (e) => {
        //             //TODO: соединение аттрибутов
        //         };
        //         attribute.startAttributeAttributeDragging = () => {
        //             attribute.addEventListener('dragover', attribute.attributeAttributeDragOverListener);
        //             attribute.addEventListener('drop', attribute.attributeAttributeDropListener);
        //         };
        //         attribute.stopAttributeAttributeDragging = () => {
        //             attribute.removeEventListener('dragover', attribute.attributeAttributeDragOverListener);
        //             attribute.removeEventListener('drop', attribute.attributeAttributeDropListener);
        //         };
        //         attribute.isSuitableAttribute = (target) => {
        //             //TODO: фильтрация подходящих аттрибутов
        //         };
        //         attribute.id = 'attribute_' + form.relationsArray.indexOf(relation) + '_' + relation.attributesArray.indexOf(attribute);
        //         attribute.draggable = true;
        //         attribute.ondragstart = (e) => {
        //             e.stopPropagation();
        //             e.dataTransfer.setData('text/plain', JSON.stringify({
        //                 attributeId: attribute.id
        //             }));
        //             e.dataTransfer.effectAllowed = 'all';
        //             form.startAttributeFormDragging();
        //             form.relationsArray.forEach((relation) => {
        //                 relation.startAttributeRelationDragging();
        //             });
        //             form.relationsArray.forEach((relation) => {
        //                 relation.attributesArray.filter((target) => {
        //                     attribute.isSuitableAttribute(target);
        //                 }).forEach((target) => {
        //                     target.startAttributeAttributeDragging();
        //                 })
        //             })
        //         };
        //         attribute.ondragend = (e) => {
        //             form.stopAttributeFormDragging();
        //             form.relationsArray.forEach((relation) => {
        //                 relation.stopAttributeRelationDragging();
        //             });
        //             form.relationsArray.forEach((relation) => {
        //                 relation.attributesArray.filter((target) => {
        //                     attribute.isSuitableAttribute(target);
        //                 }).forEach((target) => {
        //                     target.stopAttributeAttributeDragging();
        //                 })
        //             })
        //         };
        //     };
        //     relation.attributesArray.forEach((attribute) => {
        //         relation.animateAttribute(attribute);
        //     });
        //     relation.id = 'relation_' + form.relationsArray.indexOf(relation);
        //     relation.draggable = true;
        //     relation.ondragstart = (e) => {
        //         e.dataTransfer.setData('text/plain', JSON.stringify({
        //             relationId: relation.id,
        //             x: e.pageX,
        //             y: e.pageY
        //         }));
        //         e.dataTransfer.effectAllowed = 'copyMove';
        //         form.startRelationFormDragging();
        //     };
        //     relation.ondragend = (e) => {
        //         form.stopRelationFormDragging();
        //     };
        // };
        // form.relationsArray.forEach((relation) => {
        //     form.animateRelation(relation);
        // });
    });