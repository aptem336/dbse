//TODO: rework
const animateForm = () => {
    //TODO:
    // избавиться от дубликатов
    // вынести методы
    //Q:
    // получение по id?
    // через input?
    const form = document.getElementById("relations-form");
    form.relationsList = [...document.getElementsByClassName("relation-block")];
    form.relationFormDragOverListener = (e) => {
        e.preventDefault();
    };
    form.relationFormDropListener = (e) => {
        const dragData = JSON.parse(e.dataTransfer.getData("text/plain"));
        const relation = document.getElementById(dragData.relationId);
        const x = relation.offsetLeft + (e.pageX - dragData.x);
        const y = relation.offsetTop + (e.pageY - dragData.y);

        const index = form.relationsList.indexOf(relation);
        document.getElementById("relations-form:relation:" + index + ":relation_x").value = x;
        document.getElementById("relations-form:relation:" + index + ":relation_y").value = y;

        relation.style.left = x + "px";
        relation.style.top = y + "px";
    };
    form.attributeFormDragOverListener = (e) => {
        e.preventDefault();
    };
    form.attributeFormDropListener = (e) => {
        e.stopPropagation();
        const dragData = JSON.parse(e.dataTransfer.getData("text/plain"));
        const attribute = document.getElementById(dragData.attributeId);
        document.getElementById(attribute.id + "remove_attribute").click();
        attribute.remove();
    };
    form.startRelationFormDragging = () => {
        form.addEventListener("dragover", form.relationFormDragOverListener);
        form.addEventListener("drop", form.relationFormDropListener);
    };
    form.stopRelationFormDragging = () => {
        form.removeEventListener("dragover", form.relationFormDragOverListener);
        form.removeEventListener("drop", form.relationFormDropListener);
    };
    form.startAttributeFormDragging = () => {
        form.addEventListener("dragover", form.attributeFormDragOverListener);
        form.addEventListener("drop", form.attributeFormDropListener);
    };
    form.stopAttributeFormDragging = () => {
        form.removeEventListener("dragover", form.attributeFormDragOverListener);
        form.removeEventListener("drop", form.attributeFormDropListener);
    };
    form.animateRelation = (relation) => {
        //FIXME: слишком неточно, нужен выбор по классу
        relation.attributesList = [...relation.getElementsByTagName("tr")];
        relation.attributeRelationDragOverListener = (e) => {
            e.preventDefault();
        };
        relation.attributeRelationDropListener = (e) => {
            e.stopPropagation();
            // FIXME:
            //  перемещение из списка в список аттрибутов
            //  обновление - возврат
            const dragData = JSON.parse(e.dataTransfer.getData("text/plain"));
            const attribute = document.getElementById(dragData.attributeId);
            attribute.remove();
            relation.getElementsByTagName("table")[0].appendChild(attribute);
            const index = form.relationsList.indexOf(relation);
            document.getElementById(attribute.id + "attribute_relation")
                .setAttribute("value", document.getElementById("relations-form:relation:" + index + ":relation_id").value);
        };
        relation.startAttributeRelationDragging = () => {
            relation.addEventListener("dragover", relation.attributeRelationDragOverListener);
            relation.addEventListener("drop", relation.attributeRelationDropListener);
        };
        relation.stopAttributeRelationDragging = () => {
            relation.removeEventListener("dragover", relation.attributeRelationDragOverListener);
            relation.removeEventListener("drop", relation.attributeRelationDropListener);
        };
        relation.animateAttribute = (attribute) => {
            attribute.attributeAttributeDragOverListener = (e) => {
                e.preventDefault();
            };
            attribute.attributeAttributeDropListener = (e) => {
                e.stopPropagation();
                //IMPL: соединение аттрибутов
            };
            attribute.startAttributeAttributeDragging = () => {
                attribute.addEventListener("dragover", attribute.attributeAttributeDragOverListener);
                attribute.addEventListener("drop", attribute.attributeAttributeDropListener);
            };
            attribute.stopAttributeAttributeDragging = () => {
                attribute.removeEventListener("dragover", attribute.attributeAttributeDragOverListener);
                attribute.removeEventListener("drop", attribute.attributeAttributeDropListener);
            };
            attribute.isSuitableAttribute = (target) => {
                //IMPL: фильтрация подходящих аттрибутов
            };
            attribute.id = attribute.parentElement.parentElement.id + ":" + relation.attributesList.indexOf(attribute) + ":";
            attribute.draggable = true;
            attribute.ondragstart = (e) => {
                e.stopPropagation();
                e.dataTransfer.setData("text/plain", JSON.stringify({
                    attributeId: attribute.id
                }));
                e.dataTransfer.effectAllowed = "all";
                form.startAttributeFormDragging();
                form.relationsList.forEach((relation) => {
                    relation.startAttributeRelationDragging();
                });
                form.relationsList.forEach((relation) => {
                    relation.attributesList.filter((target) => {
                        return true //FIXME: заменить на фильтр
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
                        return true //FIXME: заменить на фильтр
                    }).forEach((target) => {
                        target.stopAttributeAttributeDragging();
                    })
                })
            };
        };
        relation.attributesList.forEach((attribute) => {
            relation.animateAttribute(attribute);
        });
        relation.draggable = true;
        relation.ondragstart = (e) => {
            e.dataTransfer.setData("text/plain", JSON.stringify({
                relationId: relation.id,
                x: e.pageX,
                y: e.pageY
            }));
            e.dataTransfer.effectAllowed = "copyMove";
            form.startRelationFormDragging();
        };
        relation.ondragend = (e) => {
            form.stopRelationFormDragging();
        };
    };
    form.relationsList.forEach((relation) => {
        form.animateRelation(relation);
    });
};