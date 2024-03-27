package com.board.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.BoardVo;
import com.board.menus.domain.MenuVo;

@Mapper
public interface BoardMapper {

	List<BoardVo> getBoardList(MenuVo menuVo);

	void insertBoard(BoardVo boardVo);

	HashMap<String, Object> boardUpdateForm(BoardVo boardVo);

	void boardUpdate(BoardVo boardVo);

	BoardVo getBoard(BoardVo boardVo);

	void incHit(BoardVo boardVo);





}