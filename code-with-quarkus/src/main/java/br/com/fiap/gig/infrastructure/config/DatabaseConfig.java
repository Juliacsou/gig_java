package br.com.fiap.gig.infrastructure.config;

import br.com.fiap.gig.domain.repository.*;
import br.com.fiap.gig.infrastructure.persistence.*;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DatabaseConfig {

    @ApplicationScoped
    public DatabaseConnection databaseConnection(AgroalDataSource dataSource) {
        return new DatabaseConnectionImpl(dataSource);
    }

    @ApplicationScoped
    public AvaliacaoRepository AvaliacaoRepository(DatabaseConnection databaseConnection) {
        return new JdbcAvaliacaoRepository(databaseConnection);
    }

    @ApplicationScoped
    public CompetenciaRepository compatenciaRepository(DatabaseConnection databaseConnection) {
        return new JdbcCompetenciaRepository(databaseConnection);
    }

    @ApplicationScoped
    public ScoreRepository ScoreRepository(DatabaseConnection databaseConnection) {
        return new JdbcScoreRepository(databaseConnection);
    }

    @ApplicationScoped
    public UsuarioRepository usuarioRepository(DatabaseConnection databaseConnection) {
        return new JdbcUsuarioRepository(databaseConnection);
    }





}