package by.jwd.cafe.entity;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.CommandType;
import by.jwd.cafe.command.impl.DefaultCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum UserRole {
     UNSUPPORTED,
     CUSTOMER,
     ADMIN,
     GUEST
}
