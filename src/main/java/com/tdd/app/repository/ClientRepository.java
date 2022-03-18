package com.tdd.app.repository;

import com.tdd.app.entity.Client;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{

    @Autowired
    Client client = null;

    @Query(value = "select * from client where email = :email",  nativeQuery = true)
    Client getClientByEmail( @Param("email") String email);

    @Query(value = "select * from client where sex = :sex",  nativeQuery = true)
    List<Client> findBySex(@Param("sex") String sex);
}
