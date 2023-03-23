package com.backendnovice.projectportfolioblog.board.repository;

import com.backendnovice.projectportfolioblog.board.domain.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Long, BoardEntity> {
}
