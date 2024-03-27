package com.board.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.domain.BoardVo;
import com.board.mapper.BoardMapper;
import com.board.menus.domain.MenuVo;
import com.board.menus.mapper.MenuMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/Board")
public class BoardController {

	@Autowired
	private   MenuMapper    menuMapper;
	@Autowired
	private   BoardMapper   boardMapper;

	//  /Board/List?menu_id=MENU01
	@RequestMapping("/List")
	public   ModelAndView   list(MenuVo  menuVo) {

		log.info("menuVo : {}", menuVo );

		// 메뉴 목록
		List<MenuVo>  menuList   =  menuMapper.getMenuList();

		// 게시물 목록
		List<BoardVo> boardList  =  boardMapper.getBoardList( menuVo  ); 
		
		String menu_id = menuVo.getMenu_id();

		ModelAndView  mv         =  new ModelAndView();
		mv.addObject("menu_id",    menu_id);
		mv.addObject("menuList",   menuList );
		mv.addObject("boardList",  boardList );
		mv.setViewName("board/list");
		return   mv;

	}
	
	// /Board/WriteForm?menu_id=MENU01
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm(MenuVo menuVo) {
		//메뉴 목록 조회
		List<MenuVo>  menuList   =  menuMapper.getMenuList();

		
		// 넘어온 menu_id를 처리
		String  menu_id = menuVo.getMenu_id(); 
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList",menuList);
		mv.addObject("menu_id",menu_id);
		mv.setViewName("board/write");
		
		return mv;
	}

	// /Board/Write
	// 넘어오는 menu_id=MENU01, title=aaa,writer=aaa,content=aaa	다 있는게 BoardVo
	@RequestMapping("/Write")
	public ModelAndView write(BoardVo boardVo) {
		
		//넘어온 값 Board 저장
		boardMapper.insertBoard(boardVo);
		
		
		String menu_id = boardVo.getMenu_id();
		
		ModelAndView mv = new ModelAndView();		
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);
		return mv;
	}
	
	// /Board/View?bno=1
	
	@RequestMapping("/View")
	public ModelAndView view(BoardVo boardVo) {
		
		// 메뉴목록 조회
		List<MenuVo>  menuList   =  menuMapper.getMenuList();
		
		//조회수 증가(현재 BNO의 HIT = HIT+1)
		boardMapper.incHit(boardVo);

		// BNO로 조회한 게시글
		BoardVo vo = boardMapper.getBoard(boardVo);
		
		//vo.content 안의 \n(엔터) 을 '<br>' 로 바꾼다. 그래야 엔터가 제대로 먹힘
		String content = vo.getContent(); 
		if(content != null) {
			content = content.replace("\n", "<br>");
			vo.setContent(content);
		}
	
		ModelAndView mv = new ModelAndView();			
		mv.addObject("vo",vo);
		mv.addObject("menuList",menuList);
		
		// DB데이터를 들고 view.jsp로 이동
		mv.setViewName("board/view");
		
		
		return mv;
		
	}

	// /Board/UpdateForm
	@RequestMapping("/UpdateForm")
	public ModelAndView updateForm(BoardVo boardVo) {
		
		HashMap<String,Object> map = boardMapper.boardUpdateForm(boardVo);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("vo",map);
		mv.setViewName("board/updateForm");
		
		return mv;
	}
	// /Board/Update
	@RequestMapping("/Update")
	public ModelAndView update(BoardVo boardVo) {
		
		boardMapper.boardUpdate(boardVo);
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/board/list");
		
		return mv;
	}
	
}