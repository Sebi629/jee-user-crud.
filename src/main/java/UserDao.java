import org.mindrot.jbcrypt.BCrypt;
import utils.DbUtil;

import java.sql.*;
import java.util.Arrays;

class UserDao {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO workshop2.users(email, username, password) VALUES (?, ?, ?)";

    private static final String UPDATE_USER_QUERY =
            "UPDATE workshop2.users SET" +
                    "    email = ?," +
                    "    username = ?," +
                    "    password = ?" +
                    "WHERE id = ?;";

    private static final String DELETE_USER_QUERY =
            "DELETE FROM workshop2.users WHERE id = ?;";

    private static final String FIND_ALL_USER_QUERY =
            "SELECT * FROM workshop2.users";

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int userId) {
        try (Connection conn  = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(String.format("SELECT * FROM users WHERE id = %d",userId));
            ResultSet resultSet = statement.executeQuery();

            User user = new User();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
            if (user.getAllInfo().contains("null")) {
                return null;
            } else {
                return user;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    public void update(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(DELETE_USER_QUERY);
            statement.setString(1, String.valueOf(userId));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String[] findAllString() {
        String[] arrayUser = new String[0];
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USER_QUERY);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User user = new User();
                user.setId(result.getInt("id"));
                user.setUserName(result.getString("username"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                int oldLength = arrayUser.length;
                arrayUser = Arrays.copyOf(arrayUser, oldLength + 1);
                arrayUser[oldLength] = user.getAllInfo();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arrayUser;
    }

    public User[] findAll() {
        User[] arrayUser = new User[0];
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USER_QUERY);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User user = new User();
                user.setId(result.getInt("id"));
                user.setUserName(result.getString("username"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                int oldLength = arrayUser.length;
                arrayUser = Arrays.copyOf(arrayUser, oldLength + 1);
                arrayUser[oldLength] = user;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arrayUser;
    }
}