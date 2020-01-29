//TODO: mojarra.ab || jsf.ajax
const animate = () => {
    animateForm(document.getElementById("relations-form"));
};
const animateForm = (form) => {
    form.add_relation = document.getElementById("relations-form:add_relation");
    // form.ondblclick = () => {
    //     form.add_relation.click();
    // };
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
        attribute.remove.click();
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

    form.relationsList = [...document.getElementsByClassName("relation-block")];
    form.relationsList.forEach((relation, index) => {
        animateRelation(form, relation, index);
    });
};
const animateRelation = (form, relation, index) => {
    // relation.update = document.getElementById("relations-form:relation:" + index + ":update_relation");
    // relation.save =  document.getElementById("relations-form:relation:" + index + ":save_relation");
    // relation.delete = document.getElementById("relations-form:relation:" + index + ":delete_relation");
    // TODO: rename
    // relation.i = document.getElementById("relations-form:relation:" + index + ":relation_id").value;
    // relation.name = document.getElementById("relations-form:relation:" + index + ":relation_name").value;
    relation.x = document.getElementById("relations-form:relation:" + index + ":relation_x");
    relation.y = document.getElementById("relations-form:relation:" + index + ":relation_y");

    relation.shift = (shiftX, shiftY) => {
        relation.x.value = relation.offsetLeft + shiftX;
        relation.y.value = relation.offsetTop + shiftY;
        // Q update || save
    };

    relation.attributeRelationDragOverListener = (e) => {
        e.preventDefault();
    };
    relation.attributeRelationDropListener = (e) => {
        e.stopPropagation();
        // IMPL
        // const dragData = JSON.parse(e.dataTransfer.getData("text/plain"));
        // const attribute = document.getElementById(dragData.attributeId);
    };
    relation.startAttributeRelationDragging = () => {
        relation.addEventListener("dragover", relation.attributeRelationDragOverListener);
        relation.addEventListener("drop", relation.attributeRelationDropListener);
    };
    relation.stopAttributeRelationDragging = () => {
        relation.removeEventListener("dragover", relation.attributeRelationDragOverListener);
        relation.removeEventListener("drop", relation.attributeRelationDropListener);
    };

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

    relation.attributesList = [...relation.getElementsByClassName("attribute-row")];
    relation.attributesList.forEach((attribute, index) => {
        animateAttribute(form, relation, attribute, index);
    });
};
const animateAttribute = (form, relation, attribute, index) => {
    // attribute.update = document.getElementById(attribute.id + ":update_attribute");
    // attribute.save = document.getElementById(attribute.id + ":save_attribute");
    attribute.remove = document.getElementById(attribute.id + ":remove_attribute");

    // attribute.id = attribute.parentElement.parentElement.id + ":" + index;
    // attribute.name = document.getElementById(attribute.id + ":attribute_name");
    // attribute.relation = document.getElementById(attribute.id + ":attribute_relation");

    attribute.isSuitableAttribute = (target) => {
        // IMPL
    };

    attribute.attributeAttributeDragOverListener = (e) => {
        e.preventDefault();
    };
    attribute.attributeAttributeDropListener = (e) => {
        e.stopPropagation();
        // IMPL
    };
    attribute.startAttributeAttributeDragging = () => {
        attribute.addEventListener("dragover", attribute.attributeAttributeDragOverListener);
        attribute.addEventListener("drop", attribute.attributeAttributeDropListener);
    };
    attribute.stopAttributeAttributeDragging = () => {
        attribute.removeEventListener("dragover", attribute.attributeAttributeDragOverListener);
        attribute.removeEventListener("drop", attribute.attributeAttributeDropListener);
    };

    attribute.draggable = true;
    attribute.ondragstart = (e) => {
        e.stopPropagation();
        e.dataTransfer.setData("text/plain", JSON.stringify({
            attributeId: attribute.id,
            // relationId: relation.id
        }));
        e.dataTransfer.effectAllowed = "all";
        form.startAttributeFormDragging();
        form.relationsList.forEach((relation) => {
            relation.startAttributeRelationDragging();
        });
        form.relationsList.forEach((relation) => {
            relation.attributesList.filter((target) => {
                return true //FIXME заменить на фильтр
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
                return true //FIXME заменить на фильтр
            }).forEach((target) => {
                target.stopAttributeAttributeDragging();
            })
        })
    };
};
