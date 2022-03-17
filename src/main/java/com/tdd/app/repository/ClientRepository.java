package com.tdd.app.repository;

import com.tdd.app.entity.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{

    @Query(value = "select * from Client where email = :email",  nativeQuery = true)
    Client getClientByEmail( @Param("email") String email);

    @Query(value = "select * from Client where sex = :sex",  nativeQuery = true)
    List<Client> findBySex(@Param("sex") String sex);
}
