package postIt.DAO;

import postIt.model.PostIt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostItDAO {
    public void savePostIt(PostIt postIt) throws ExceptionDAO {
        String sql = "INSERT INTO postIt (title, description, deadline) VALUES (?, ?, ?)";
        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = DataBaseConnection.getDataSource().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, postIt.getTitle());
            pStatement.setString(2, postIt.getDescription());
            pStatement.setDate(3, new java.sql.Date(postIt.getDeadline().getTime()));
            pStatement.execute();
        } catch (SQLException e) {
            throw new ExceptionDAO("Erro ao salvar o Post-It: " + e);
        } finally {
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
            } catch (SQLException e) {
                throw new ExceptionDAO("Erro ao fechar o Statement: " + e);
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new ExceptionDAO("Erro ao fechar a conexão: " + e);
            }
        }
    }
}