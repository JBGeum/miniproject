package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stone.springmvc.presentation.BoardControl;
import com.stone.springmvc.service.*;
import com.stone.springmvc.dataservice.*;

@Configuration
public class BeanConfig {
	@Bean
	public BoardControl BoardControl( ) {
		return new BoardControl( );
	}
	@Bean
	public BoardDAO BoardDAO( ) {
		return new BoardDAO( );
	}
	@Bean
	public BoardService BoardService( ) {
		return new BoardService( );
	}
	
	
}
