package senla.repository.reposjdbs;

import org.springframework.stereotype.Repository;
import senla.connectjdbc.ConnectionHolder;
import senla.entities.*;
import senla.enums.RoleName;
import senla.repository.iface.SessionRepository;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Repository
public class JdbcSessionRepositories implements SessionRepository {

    private final ConnectionHolder connectionHolder;

    public JdbcSessionRepositories(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }

    @Override
    public List<Session> findAll() {
        List<Session> sessions = new ArrayList<>();
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM sessions");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                sessions.add(mapSession(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    @Override
    public Session findById(Long id) {
        Session session = null;
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM sessions WHERE id = ?");
        ) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    session = mapSession(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return session;
    }

    @Override
    public void save(Session session) {
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO sessions (psychologist_id, client_id, start_session, final_session, course_id, order_id) VALUES (?, ?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS
             )) {
            statement.setLong(1, session.getPsychologist().getId());
            statement.setLong(2, session.getClient().getId());
            statement.setObject(3, session.getStartSession());
            statement.setObject(4, session.getFinalSession());
            statement.setLong(5, session.getCourse().getId());
            statement.setLong(6, session.getOrder().getId());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    session.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating session failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM sessions WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Session mapSession(ResultSet resultSet) throws SQLException {
        Long psychologistId = resultSet.getLong("psychologist_id");
        Long clientId = resultSet.getLong("client_id");
        LocalDateTime startSession = resultSet.getTimestamp("start_session").toLocalDateTime();
        LocalDateTime finalSession = resultSet.getTimestamp("final_session").toLocalDateTime();
        Long courseId = resultSet.getLong("course_id");
        Long orderId = resultSet.getLong("order_id");

        Psychologist psychologist = loadPsychologist(psychologistId);

        Client client = loadClient(clientId);

        Course course = loadCourse(courseId);

        Order order = loadOrder(orderId);

        return Session.builder()
                .psychologist(psychologist)
                .client(client)
                .startSession(startSession)
                .finalSession(finalSession)
                .course(course)
                .order(order)
                .build();
    }

    private Role loadRole(Long id) throws SQLException {
        Role role = null;
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM roles WHERE id = ?");
        ) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    role = mapRole(resultSet);
                }
            }
        }
        return role;
    }

    private Role mapRole(ResultSet resultSet) throws SQLException {
        String roleNameStr = resultSet.getString("role_name");
        RoleName roleName = RoleName.valueOf(roleNameStr);
        return Role.builder()
                .roleName(roleName)
                .build();
    }
    private User loadUser(Long id) throws SQLException {
        User user = null;
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
        ) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = mapUser(resultSet);
                }
            }
        }
        return user;
    }
    private User mapUser(ResultSet resultSet) throws SQLException {
        String firstname = resultSet.getString("firstname");
        String secondname = resultSet.getString("secondname");
        String userPassword = resultSet.getString("user_password");
        String email = resultSet.getString("email");
        Long roleId = resultSet.getLong("role_id");
        Role role = loadRole(roleId);


        return User.builder()
                .firstname(firstname)
                .secondname(secondname)
                .userPassword(userPassword)
                .email(email)
                .role(role)
                .build();
    }

    private Psychologist loadPsychologist(Long id) throws SQLException {
        Psychologist psychologist = null;
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM psychologists WHERE id = ?");
        ) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    psychologist = mapPsychologist(resultSet);
                }
            }
        }
        return psychologist;
    }

    private Psychologist mapPsychologist(ResultSet resultSet) throws SQLException {
        Long userId = resultSet.getLong("user_id");
        Integer experience = resultSet.getInt("experience");
        BigDecimal hourlyRate = resultSet.getBigDecimal("hourly_rate");
        Integer ratingValue = resultSet.getInt("rating_value");

        User user = loadUser(userId);

        return Psychologist.builder()
                .user(user)
                .ratingValue(ratingValue)
                .hourlyRate(hourlyRate)
                .experience(experience)
                .build();
    }
    private ClientCard loadClientCard(Long id) throws SQLException {
        ClientCard clientCard = null;
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM clients_cards WHERE id = ?");
        ) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    clientCard = mapClientCard(resultSet);
                }
            }
        }
        return clientCard;
    }
    private ClientCard mapClientCard(ResultSet resultSet) throws SQLException {
        Long psychologistId = resultSet.getLong("psychologist_id");
        String diagnos = resultSet.getString("diagnos");
        String recommendations = resultSet.getString("recommendations");
        Psychologist psychologist = loadPsychologist(psychologistId);
        return ClientCard.builder()
                .diagnos(diagnos)
                .psychologist(psychologist)
                .recommendations(recommendations)
                .build();


    }

    private Order loadOrder(Long id) throws SQLException {
        Order order = null;
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");
        ) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    order = mapOrder(resultSet);
                }
            }
        }
        return order;
    }

    private Order mapOrder(ResultSet resultSet) throws SQLException {
        BigDecimal totalPrice = resultSet.getBigDecimal("total_price");
        BigDecimal discounts = resultSet.getBigDecimal("discounts");


        return Order.builder()
                .discounts(discounts)
                .totalPrice(totalPrice)
                .build();
    }

    private Client loadClient(Long id) throws SQLException {
        Client client = null;
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM clients WHERE id = ?");
        ) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    client = mapClient(resultSet);
                }
            }
        }
        return client;
    }

    private Client mapClient(ResultSet resultSet) throws SQLException {
        Long userId = resultSet.getLong("user_id");
        Long clientCardId = resultSet.getLong("client_card_id");

        ClientCard clientCard = loadClientCard(clientCardId);
        User user = loadUser(userId);
        return Client.builder()
                .clientCard(clientCard)
                .user(user)
                .build();
    }

    private Course loadCourse(Long id) throws SQLException {
        Course course = null;
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM courses WHERE id = ?");
        ) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    course = mapCourse(resultSet);
                }
            }
        }
        return course;
    }

    private Course mapCourse(ResultSet resultSet) throws SQLException {
        String courseName = resultSet.getString("course_name");
        BigDecimal coursePrice = resultSet.getBigDecimal("course_price");
        Long orderId = resultSet.getLong("order_id");

        Order order = loadOrder(orderId);
        return Course.builder()
                .coursePrice(coursePrice)
                .courseName(courseName)
                .order(order)
                .build();

    }






}
