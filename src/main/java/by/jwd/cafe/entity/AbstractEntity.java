package by.jwd.cafe.entity;

import java.io.Serializable;

public class AbstractEntity implements Cloneable, Serializable {
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
