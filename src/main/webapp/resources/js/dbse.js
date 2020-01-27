const animateForm = (form) => {
    form = form || document.getElementById("relations-form");
    form.relationsList = [...document.getElementsByClassName("relation-block")];
    const add_relation = document.getElementById('relations-form:add_relation');
    form.ondblclick = () => {
        add_relation.click();
    };
    form.relationFormDragOverListener = (e) => {
        e.preventDefault();
    };
    form.relationFormDropListener = (e) => {
        e.stopPropagation();
        const dragData = JSON.parse(e.dataTransfer.getData("text/plain"));
        const relation = document.getElementById(dragData.relationId);
        relation.shift(e.pageX - dragData.x, e.pageY - dragData.y);
    };
    form.attributeFormDragOverListener = (e) => {
        e.preventDefault();
    };
    form.attributeFormDropListener = (e) => {
        e.stopPropagation();
        const dragData = JSON.parse(e.dataTransfer.getData("text/plain"));
        const attribute = document.getElementById(dragData.attributeId);
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
    form.relationsList.forEach((relation) => {
        animateRelation(form, relation);
    });
};
const animateRelation = (form, relation) => {
    form = form || document.getElementById("relations-form");
    relation = relation || '/*get*/';
    relation.attributesList = [...relation.getElementsByTagName("tr")];
    const index = form.relationsList.indexOf(relation);
    relation.update = () => document.getElementById("relations-form:relation:" + index + ":update_relation").click();
    relation.save = () => document.getElementById("relations-form:relation:" + index + ":save_relation").click();
    relation.delete = () => document.getElementById("relations-form:relation:" + index + ":delete_relation").click();
    const x = document.getElementById("relations-form:relation:" + index + ":x");
    const y = document.getElementById("relations-form:relation:" + index + ":y");
    relation.shift = (shiftX, shiftY) => {
        x.value = relation.offsetLeft + shiftX;
        y.value = relation.offsetTop + shiftY;
        relation.save();
    };
    relation.i = document.getElementById("relations-form:relation:" + index + ":id").value;
    relation.attributeRelationDragOverListener = (e) => {
        e.preventDefault();
    };
    relation.attributeRelationDropListener = (e) => {
        e.stopPropagation();
        const dragData = JSON.parse(e.dataTransfer.getData("text/plain"));
        const attribute = document.getElementById(dragData.attributeId);
        attribute.relation.value = relation.i;
        const previousRelation = document.getElementById(dragData.relationId);
        previousRelation.update();
        attribute.save();
        relation.update();
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
        attribute.id = attribute.parentElement.parentElement.id + ":" + relation.attributesList.indexOf(attribute) + ":";
        attribute.relation = document.getElementById(attribute.id + "relation");
        attribute.update = () => document.getElementById(attribute.id + "update_attribute").click();
        attribute.save = () => document.getElementById(attribute.id + "save_attribute").click();
        attribute.remove = () => document.getElementById(attribute.id + "remove_attribute").click();
        attribute.attributeAttributeDragOverListener = (e) => {
            e.preventDefault();
        };
        attribute.attributeAttributeDropListener = (e) => {
            e.stopPropagation();
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
        };
        attribute.draggable = true;
        attribute.ondragstart = (e) => {
            e.stopPropagation();
            e.dataTransfer.setData("text/plain", JSON.stringify({
                attributeId: attribute.id,
                relationId: relation.id
            }));
            e.dataTransfer.effectAllowed = "all";
            form.startAttributeFormDragging();
            form.relationsList.forEach((relation) => {
                relation.startAttributeRelationDragging();
            });
            form.relationsList.forEach((relation) => {
                relation.attributesList.filter((target) => {
                    return true
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
                    return true
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