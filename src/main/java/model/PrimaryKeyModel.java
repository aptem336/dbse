package model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class PrimaryKeyModel extends AttributeContainerModel {
    @OneToOne
    private RelationModel relation;

    public RelationModel getRelation() {
        return relation;
    }

    public void setRelation(RelationModel relationModel) {
        this.relation = relationModel;
    }
}
