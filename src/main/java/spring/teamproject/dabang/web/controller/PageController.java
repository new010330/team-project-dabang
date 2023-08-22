package spring.teamproject.dabang.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping({"/", "/index"})
	public String getPage() {
		return "/index";
	}

	@GetMapping("/search/map")
	public String getSearchMap() {
		return "/menu/search-map";
	}

	@GetMapping("/welcome")
	public String loadLoginStart() {
		return "/loginpages/loginStart";
	}
	
	@GetMapping("/welcome/join")
	public String JoinAgree() {
		return "/loginpages/join";
	}
	
	@GetMapping("/welcome/agree/join")
	public String Join() {
		return "/loginpages/joinAgree";
	}
	
	@GetMapping("/welcome/login")
	public String EmailLogin() {
		return "/loginpages/login";
	}
	
	@GetMapping("/board")
	public String board() {
		return "/board";
	}
	@GetMapping("/result_board")
	public String result_board() {
		return "/result_board";
	}
	
	@GetMapping("/account")
	public String account() {
		return "/account";
	}
	
	@GetMapping("/inquery")
	public String inquery() {
		return "/inquery";
	}

	@GetMapping("/manage")
	public String manageRoom() {
		return "/uploadRoom/uploadRoom_insert";
	}
	
	@GetMapping("/manage_result")
	public String manageRoom_result() {
		return "/uploadRoom/uploadRoom_result";
	}
	
	@GetMapping("/roomInfo")
	public String getRoomInfo() {
		return "/menu/room-info";
	}
	
	@GetMapping("/room/interest")
	public String getRoomInterest() {
		return "/list/interest-list";
	}
	
}