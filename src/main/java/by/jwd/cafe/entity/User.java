package by.jwd.cafe.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

public class User extends AbstractEntity {
    private int userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Date createDate;
    private Account account;
    private boolean active;
    private UserRole role;
    private static final long serialVersionUID = 1L;

    public User() {
        account = new Account();
    }

    public User(String email, String password, String firstName, String lastName, String phone) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        account = new Account();
    }

    public class Account extends AbstractEntity {
        private BigDecimal balance;
        private BigDecimal loyaltyPoints;

        public Account() {
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public void setBalance(BigDecimal balance) {
            this.balance = balance;
        }

        public BigDecimal getLoyaltyPoints() {
            return loyaltyPoints;
        }

        public void setLoyaltyPoints(BigDecimal loyaltyPoints) {
            this.loyaltyPoints = loyaltyPoints;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Account account = (Account) o;
            return Objects.equals(balance, account.balance) && Objects.equals(loyaltyPoints, account.loyaltyPoints);
        }

        @Override
        public int hashCode() {
            return Objects.hash(balance, loyaltyPoints);
        }

        @Override
        public String toString() {
            StringJoiner joiner = new StringJoiner(", ", "Account{", "}");
            joiner.add("balance=" + balance);
            joiner.add("loyaltyPoints" + loyaltyPoints);
            return joiner.toString();
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && active == user.active && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(phone, user.phone) && Objects.equals(createDate, user.createDate) && Objects.equals(account, user.account) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password, firstName, lastName, phone, createDate, account, active, role);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ");
        joiner.add("userId=" + userId);
        joiner.add("email=" + email);
        joiner.add("password=" + password);
        joiner.add("firstName=" + firstName );
        joiner.add("lastName=" + lastName);
        joiner.add("phone=" + phone);
        joiner.add("createDate=" + createDate);
        joiner.add("account=" + account);
        joiner.add("active=" + active);
        joiner.add("role=" + role);
        return joiner.toString();
    }
}
