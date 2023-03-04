package com.alexjoiner.assignment13.repository;

import com.alexjoiner.assignment13.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u"
        + " left join fetch u.accounts"
        + " left join fetch u.address")
    Set<User> findAllUserAccountsAndAddress();

}
