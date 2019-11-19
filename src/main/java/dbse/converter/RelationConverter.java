package dbse.converter;

import dbse.entity.Relation;
import dbse.service.RelationService;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class RelationConverter implements Converter {

    @Inject
    RelationService relationService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return relationService.getById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Relation) value).getId() + "";
    }
}
