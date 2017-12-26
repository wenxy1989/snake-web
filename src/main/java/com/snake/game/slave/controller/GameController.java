package com.snake.game.slave.controller;

import com.snake.system.controller.AbstractController;
import com.snake.novel.model.Character;
import com.snake.novel.model.Family;
import com.snake.novel.model.Race;
import com.snake.novel.service.ICharacterService;
import com.snake.novel.service.IFamilyService;
import com.snake.novel.service.IRaceService;
import com.snake.system.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/game/")
public class GameController {
	
	private Log log = LogFactory.getLog(GameController.class);

}
