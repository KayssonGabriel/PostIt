package postIt.DAO;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseConnection {

    static DataSource getDataSource() {
        // Carregar as variáveis do arquivo .env
        Dotenv dotenv = Dotenv.load();

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dotenv.get("DB_URL"));
        config.setUsername(dotenv.get("DB_USER"));
        config.setPassword(dotenv.get("DB_PASSWORD"));

        // Configurações opcionais
        config.setMaximumPoolSize(Integer.parseInt(dotenv.get("DB_MAX_POOL_SIZE", "10"))); // Usar 10 como valor padrão

        return new HikariDataSource(config);
    }

    public static void main(String[] args) {
        try (Connection connection = getDataSource().getConnection()) {
            // Use a conexão
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
