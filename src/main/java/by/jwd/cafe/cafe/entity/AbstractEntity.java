package by.jwd.cafe.entity;

import java.io.Serializable;

public class AbstractEntity implements Cloneable, Serializable {
    @Override
    public AbstractEntity clone() throws CloneNotSupportedException {
        return (AbstractEntity)super.clone();
    }
}
