/*
package senla.repository.reposjdbs;

import senla.connectjdbc.ConnectionHolder;
import senla.entities.Session;
import senla.repository.iface.SessionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            statement.setLong(1, session.getPsychologist().getUuid());
            statement.setLong(2, session.getClient().getUuid());
            statement.setObject(3, session.getStartSession());
            statement.setObject(4, session.getFinalSession());
            statement.setLong(5, session.getCourse().getUuid());
            statement.setLong(6, session.getOrder().getUuid());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    session.setUuid(generatedKeys.getLong(1));
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
        // Implement mapping logic from ResultSet to Session object
        return null;
    }
}
*/
