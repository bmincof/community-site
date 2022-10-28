package config;

import org.springframework.context.annotation.Bean;

import dao.BoardDao;
import dao.BoardVotesDao;
import dao.ReplyDao;
import service.BoardDetailService;
import service.BoardResponseService;
import service.BoardService;
/**
 * 게시판 관련 기능 구현에 필요한 스프링 빈 클래스들
 * 
 * @author a
 *
 */

public class BoardConfig {

	
	@Bean
	public BoardDao boardDao() {
		return new BoardDao();
	}
	
	@Bean
	public BoardService boardService() {
		return new BoardService();
	}
	
	@Bean
	public BoardDetailService boardDetailService() {
		return new BoardDetailService();
	}
	
	@Bean
	public BoardVotesDao boardVotesDao() {
		return new BoardVotesDao();
	}
	
	@Bean
	public ReplyDao replyDao() {
		return new ReplyDao();
	}
	
	@Bean BoardResponseService boardResponseService() {
		return new BoardResponseService();
	}
	
}
