package by.jwd.cafe.dao.mapper.impl;

import by.jwd.cafe.dao.mapper.Mapper;
import by.jwd.cafe.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static by.jwd.cafe.dao.ColumnName.*;

public class UserMapper implements Mapper<User> {
    static Logger logger = LogManager.getLogger();
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
                    .withUserRoleById(resultSet.getInt(ROLE_ID))
                    .build();
            optionalUser = Optional.of(user);
        } catch (SQLException e) {
            logger.error("SQL exception while map User resultSet", e);
            optionalUser = Optional.empty();
        }
        return optionalUser;
    }
}
