document.addEventListener(
    //TODO: передача пармаметров не через dragData?
    //TODO: избавиться от дубликатов
    'DOMContentLoaded',
    () => {
        const form = document.getElementById('relations-form');
        form.relationsList = [...document.getElementsByClassName('relation-block')];
        form.relationFormDragOverListener = (e) => {
            e.preventDefault();
        };
        form.relationFormDropListener = (e) => {
            const dragData = JSON.parse(e.dataTransfer.getData('text/plain'));
            const relation = document.getElementById(dragData.relationId);
            relation.style.top = relation.offsetTop + (e.pageY - dragData.y) + 'px';
            relation.style.left = relation.offsetLeft + (e.pageX - dragData.x) + 'px';
            //TODO: изменения на бэке
        };
        form.attributeFormDragOverListener = (e) => {
            e.preventDefault();
            //TODO: перетаскивание строки
        };
        form.attributeFormDropListener = (e) => {
            e.stopPropagation();
            const dragData = JSON.parse(e.dataTransfer.getData('text/plain'));
            const attribute = document.getElementById(dragData.attributeId);
            attribute.remove();
            console.log('afd');
            //TODO: удаление с бэка
        };
        form.startRelationFormDragging = () => {
            form.addEventListener('dragover', form.relationFormDragOverListener);
            form.addEventListener('drop', form.relationFormDropListener);
            //TODO: ...
        };
        form.stopRelationFormDragging = () => {
            form.removeEventListener('dragover', form.relationFormDragOverListener);
            form.removeEventListener('drop', form.relationFormDropListener);
            //TODO: ...
        };
        form.startAttributeFormDragging = () => {
            form.addEventListener('dragover', form.attributeFormDragOverListener);
            form.addEventListener('drop', form.attributeFormDropListener);
            //TODO: ...
        };
        form.stopAttributeFormDragging = () => {
            form.removeEventListener('dragover', form.attributeFormDragOverListener);
            form.removeEventListener('drop', form.attributeFormDropListener);
            //TODO: ...
        };
        form.animateRelation = (relation) => {
            //FIXME: слишком неточно, нужен выбор по классу
            relation.attributesList = [...relation.getElementsByTagName('tr')];
            relation.attributeRelationDragOverListener = (e) => {
                e.preventDefault();
                //TODO: ...
            };
            relation.attributeRelationDropListener = (e) => {
                e.stopPropagation();
                //TODO: перетаскивание аттрибута в таблицу
            };
            relation.startAttributeRelationDragging = () => {
                relation.addEventListener('dragover', relation.attributeRelationDragOverListener);
                relation.addEventListener('drop', relation.attributeRelationDropListener);
                //TODO: ...
            };
            relation.stopAttributeRelationDragging = () => {
                relation.removeEventListener('dragover', relation.attributeRelationDragOverListener);
                relation.removeEventListener('drop', relation.attributeRelationDropListener);
                //TODO: ...
            };
            relation.animateAttribute = (attribute) => {
                attribute.attributeAttributeDragOverListener = (e) => {
                    e.preventDefault();
                };
                attribute.attributeAttributeDropListener = (e) => {
                    e.stopPropagation();
                    //TODO: соединение аттрибутов
                };
                attribute.startAttributeAttributeDragging = () => {
                    attribute.addEventListener('dragover', attribute.attributeAttributeDragOverListener);
                    attribute.addEventListener('drop', attribute.attributeAttributeDropListener);
                    //TODO: ...
                };
                attribute.stopAttributeAttributeDragging = () => {
                    attribute.removeEventListener('dragover', attribute.attributeAttributeDragOverListener);
                    attribute.removeEventListener('drop', attribute.attributeAttributeDropListener);
                    //TODO: ...
                };
                attribute.isSuitableAttribute = (target) => {
                    //TODO: фильтрация подходящих аттрибутов
                };
                attribute.id = 'attribute_' + form.relationsList.indexOf(relation) + '_' + relation.attributesList.indexOf(attribute);
                attribute.draggable = true;
                attribute.ondragstart = (e) => {
                    e.stopPropagation();
                    e.dataTransfer.setData('text/plain', JSON.stringify({
                        attributeId: attribute.id
                    }));
                    e.dataTransfer.effectAllowed = 'all';
                    form.startAttributeFormDragging();
                    form.relationsList.forEach((relation) => {
                        relation.startAttributeRelationDragging();
                    });
                    form.relationsList.forEach((relation) => {
                        relation.attributesList.filter((target) => {
                            return true //TODO: заменить на фильтр
                        }).forEach((target) => {
                            target.startAttributeAttributeDragging()
                        })
                    })
                };
                attribute.ondragend = (e) => {
                    e.stopPropagation();
                    form.stopAttributeFormDragging();
                    form.relationsList.forEach((relation) => {
                        relation.stopAttributeRelationDragging();
                    });
                    form.relationsList.forEach((relation) => {
                        relation.attributesList.filter((target) => {
                            return true //TODO: заменить на фильтр
                        }).forEach((target) => {
                            target.stopAttributeAttributeDragging();
                        })
                    })
                };
            };
            //FIXME: слишком неточно, нужен выбор по классу
            relation.attributesList.forEach((attribute) => {
                relation.animateAttribute(attribute);
            });
            relation.id = 'relation_' + form.relationsList.indexOf(relation);
            relation.draggable = true;
            relation.ondragstart = (e) => {
                e.dataTransfer.setData('text/plain', JSON.stringify({
                    relationId: relation.id,
                    x: e.pageX,
                    y: e.pageY
                }));
                e.dataTransfer.effectAllowed = 'copyMove';
                form.startRelationFormDragging();
            };
            relation.ondragend = (e) => {
                form.stopRelationFormDragging();
            };
        };
        form.relationsList.forEach((relation) => {
            form.animateRelation(relation);
        });
    });