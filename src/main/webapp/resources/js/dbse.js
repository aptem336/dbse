document.addEventListener(
    //TODO: передача пармаметров не через dragData?
    //TODO: избавиться от дубликатов
    'DOMContentLoaded',
    () => {
        const form = document.getElementById('relations-form');
        //FIXME: return NamedNodeMap!
        form.relations = [...document.getElementsByClassName('relation-block')];
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
            const dragData = JSON.parse(e.dataTransfer.getData('text/plain'));
            const attribute = document.getElementById(dragData.attributeId);
            attribute.remove();
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
            relation.attributes = [...relation.getElementsByTagName('tr')];
            relation.attributeRelationDragOverListener = (e) => {
                e.preventDefault();
                //TODO: ...
            };
            relation.attributeRelationDropListener = (e) => {
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
                attribute.id = 'attribute_' + form.relations.indexOf(relation) + '_' + relation.attributes.indexOf(attribute);
                attribute.draggable = true;
                attribute.ondragstart = (e) => {
                    e.stopPropagation();
                    e.dataTransfer.setData('text/plain', JSON.stringify({
                        attributeId: attribute.id
                    }));
                    e.dataTransfer.effectAllowed = 'all';
                    form.startAttributeFormDragging();
                    form.relations.forEach((relation) => {
                        relation.startAttributeRelationDragging();
                    });
                    form.relations.forEach((relation) => {
                        relation.attributes.filter((target) => {
                            attribute.isSuitableAttribute(target)
                        }).forEach((target) => {
                            target.startAttributeAttributeDragging()
                        })
                    })
                };
                attribute.ondragend = (e) => {
                    form.stopAttributeFormDragging();
                    form.relations.forEach((relation) => {
                        relation.stopAttributeRelationDragging();
                    });
                    form.relations.forEach((relation) => {
                        relation.attributes.filter((target) => {
                            attribute.isSuitableAttribute(target)
                        }).forEach((target) => {
                            target.stopAttributeAttributeDragging();
                        })
                    })
                };
            };
            //FIXME: слишком неточно, нужен выбор по классу
            relation.attributes.forEach((attribute) => {
                relation.animateAttribute(attribute);
            });
            relation.id = 'relation_' + form.relations.indexOf(relation);
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
        form.relations.forEach((relation) => {
            form.animateRelation(relation);
        });
    });