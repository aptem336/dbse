package dbse.controller;

import dbse.model.AbstractEntity;
import dbse.persist.AbstractPersistService;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractController<T extends AbstractEntity> implements Converter<T>, Serializable {

    abstract T getEntity();

    abstract AbstractPersistService<T> getService();

    private List<T> list;

    public T create() {
        T t = getEntity();
        add(t);
        return t;
    }

    public void add(T t) {
        list.add(t);
    }

    public void delete(T t) {
        t.setPresent(false);
    }

    @PostConstruct
    private void readAll() {
        list = getService().selectAll();
    }

    private void writeAll() {
        list.forEach(t -> {
            if (t.isPresent()) {
                getService().merge(t);
            } else {
                getService().remove(t);
            }
        });
    }

    public List<T> getList() {
        return list/*.stream().filter(AbstractEntity::isPresent).collect(Collectors.toList())*/;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


    @Override
    public String getAsString(FacesContext context, UIComponent component, T t) {
        return t.toString();
    }

    @Override
    public T getAsObject(FacesContext context, UIComponent component, String id) {
        return getService().getById(id);
    }
}