package com.module.novel.game.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.base.common.controller.AbstractController;
import com.module.novel.model.Character;
import com.module.novel.service.ICharacterService;
import com.module.novel.model.Family;
import com.module.novel.service.IFamilyService;
import com.module.novel.model.Race;
import com.module.novel.service.IRaceService;
import com.module.system.model.User;

@Controller
@RequestMapping("/game/")
public class GameController {
	
	private Log log = LogFactory.getLog(GameController.class);
	
	@Resource(name="characterService")
	private ICharacterService characterService;
	
	@Resource(name="raceService")
	private IRaceService raceService;
	
	@Resource(name="familyService")
	private IFamilyService familyService;
	
	@RequestMapping(value = "to_select_character.do")
	public ModelAndView toSelectCharacter(){
		ModelAndView mv = new ModelAndView("game/character_select");
		try{
			Character c = new Character();
			c.setUserId(AbstractController.sysUser().getId());
			List<Character> characters = characterService.getList(c);
			mv.addObject("characters", characters);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}
		return mv;
	}

	@RequestMapping(value = "to_create_character.do")
	public ModelAndView toCreateCharacter(){
		ModelAndView mv = new ModelAndView("game/character_create");
		List<Race> races = raceService.getAll(Race.class);
		List<Family> familys = familyService.getAll(Family.class);
		mv.addObject("races", races);
		mv.addObject("familys", familys);
		return mv;
	}

	@RequestMapping(value = "create_character.do")
	public RedirectView createCharacter(Character character){
		RedirectView rv = new RedirectView("to_select_character.do");
		User user = AbstractController.sysUser();
		character.setUserId(user.getId());
		character.setCreateId(user.getId());
		characterService.create(character);
		return rv;
	}
	
	@RequestMapping(value = "select_character.do")
	public ModelAndView selectCharacter(Character character){
		ModelAndView mv = new ModelAndView("game/place/battle_field");
		try{
			User user = AbstractController.sysUser();
			character = (Character) characterService.getObject(Character.class, character.getId());
			character.setRace(raceService.getObject(Race.class, character.getRaceId()));
			character.setFamily(familyService.getObject(Family.class, character.getFamilyId()));
			user.setCurrentCharacter(character);
			List<Character> enemys = characterService.getEnemysByUser(user);
			mv.addObject("enemys", enemys);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}
		return mv;
	}
	
	@RequestMapping(value = "fighting.do")
	public ModelAndView fighting(Long enemyId){
		ModelAndView mv = new ModelAndView("game/place/fighting");
		User user = AbstractController.sysUser();
		Character currentCharater = user.getCurrentCharacter();
		Character enemy = user.getEnemy();
		if(null == enemy){
			enemy = (Character) characterService.getObject(Character.class, enemyId);
			enemy.setRace(raceService.getObject(Race.class, enemy.getRaceId()));
			enemy.setFamily(familyService.getObject(Family.class, enemy.getFamilyId()));
			user.setEnemy(enemy);
		}
		mv.addObject("currentCharater", currentCharater);
		mv.addObject("enemy", enemy);
		return mv;
	}
	
	@RequestMapping(value = "do_action.do")
	public RedirectView doAction(String action){
		RedirectView rv = new RedirectView("fighting.do");
//		rv.addStaticAttribute("id", enemyId);
		User user = AbstractController.sysUser();
		Character enemy = user.getEnemy();
		Character currentCharater = user.getCurrentCharacter();
//		currentCharater.doActionOnLive(enemy,action);
		return rv;
	}

}
