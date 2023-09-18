package com.aluracursos.forum.api.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Boolean existsByIdAndActive(Long idUser, Boolean isUserActive);
	
}
