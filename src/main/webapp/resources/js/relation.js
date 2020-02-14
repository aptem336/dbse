const relationsList = [];
const animateRelation = (relationId) => {
    const relation = document.getElementById(relationId);

    let index = relationsList.indexOf(relationId);
    index = index !== -1 ? index : relationsList.length;

    //Q best way? ids?
    relation.x = document.getElementById("relations-form:relation:" + index + ":relation_x");
    relation.y = document.getElementById("relations-form:relation:" + index + ":relation_y");

    relation.shift = (shiftX, shiftY) => {
        relation.x.value = relation.offsetLeft + shiftX;
        relation.y.value = relation.offsetTop + shiftY;
        mojarra.ab(relation, null, 'action', null, relationId);//Q resolve!
    };

    relation.draggable = true;
    relation.ondragstart = (e) => {
        startRelationDragging(e, relation, e.pageX, e.pageY);
    };
    relation.ondragend = (e) => {
        stopRelationDragging(e, relation, e.pageX, e.pageY);
    };

    relationsList.push(relationId);
};
// const attributeRelationDropListener = (e, relation) => {
//     e.stopPropagation();
//     // IMPL
// };
// const startAttributeRelationDragging = () => {
//     relationsList.forEach((relation) => {
//         relation.addEventListener("dragover", (e) => e.preventDefault());
//         relation.addEventListener("drop", (e) => attributeRelationDropListener(e, relation));
//     });
// };
// const stopAttributeRelationDragging = () => {
//     relationsList.forEach((relation) => {
//         relation.removeEventListener("dragover", (e) => e.preventDefault());
//         relation.removeEventListener("drop", (e) => attributeRelationDropListener(e, relation));
//     })
// };