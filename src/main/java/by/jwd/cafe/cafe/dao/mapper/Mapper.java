package by.jwd.cafe.dao.mapper;

import by.jwd.cafe.entity.AbstractEntity;

import java.sql.ResultSet;
import java.util.Optional;

public interface Mapper <T extends AbstractEntity> {
    Optional<T> map(ResultSet resultSet);
}
