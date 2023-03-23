package com.backendnovice.projectportfolioblog.board.service;

import com.backendnovice.projectportfolioblog.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl {
    
    @Autowired
    private BoardRepository boardRepository;
    
}
