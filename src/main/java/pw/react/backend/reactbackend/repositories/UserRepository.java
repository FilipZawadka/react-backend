package pw.react.backend.reactbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pw.react.backend.reactbackend.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}