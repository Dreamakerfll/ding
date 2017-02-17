package com.dreamaker.controller.map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dreamaker.domain.map.Map;

@Controller
@RequestMapping("map")
public class MapController {

	public static void main(String[] args) {
		Map map = new Map();
	}
	
	@RequestMapping("tranToPage")
	public String goToPage(){
		System.out.println("gotopage");
		return "map";
	}

}
