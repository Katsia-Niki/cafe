package by.jwd.cafe.dao.mapper.impl;

import by.jwd.cafe.dao.mapper.Mapper;
import by.jwd.cafe.entity.User;
import by.jwd.cafe.entity.UserRole;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static by.jwd.cafe.dao.ColumnName.*;
public class UserMapper implements Mapper<User> {
    private static final UserMapper instance = new UserMapper();
    private UserMapper() {

    }

    public static UserMapper getInstance() {
        return instance;
    }


    @Override
    public Optional<User> map(ResultSet resultSet) {
        Optional<User> optionalUser;
        try {
            User user = new User.UserBuilder()
                    .withUserId(resultSet.getInt(USER_ID))
                    .withLogin(resultSet.getString(LOGIN))
                    .withPassword(resultSet.getString(PASSWORD))
                    .withFirstName(resultSet.getString(FIRST_NAME))
                    .withLastName(resultSet.getString(LAST_NAME))
                    .withEmail(resultSet.getString(EMAIL))
                    .withBalance(resultSet.getBigDecimal(BALANCE))
                    .withLoyaltyPoints(resultSet.getBigDecimal(LOYALTY_POINTS))
                    .withIsActive(resultSet.getBoolean(IS_ACTIVE))
                    .withUserRole(resultSet.getInt(ROLE_ID))
                    .build();
            optionalUser = Optional.of(user);
        } catch (SQLException e) {
            optionalUser = Optional.empty();
        }
        return optionalUser;
    }
}
