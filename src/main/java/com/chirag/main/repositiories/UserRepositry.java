package com.chirag.main.repositiories;

import com.chirag.main.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepositry extends JpaRepository<User, Integer> {
}
