package com.alexjoiner.assignment13.repository;

import com.alexjoiner.assignment13.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
