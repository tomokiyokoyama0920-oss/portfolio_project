package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.interfaces.MenuInterface;

import com.example.demo.models.MenuForm;
import java.util.List;

@Controller
@RequestMapping("/menulist")
public class MenuController {
	private final MenuInterface menuService;

	// コンストラクタで受け取る

	public MenuController(@Qualifier("menuService") MenuInterface menuService) {
		this.menuService = menuService;
	}

	// 商品登録画面
	@GetMapping("/menu")

	public String menu(MenuForm menuForm) {
		return "menulist/menu";

	}

	// 編集

	public String menulist(Model model) {
		List<MenuForm> list = menuService.findAll();
		model.addAttribute("menus", list);
		return "menulist/index";
	}

	// 検索
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		try {
			MenuForm menu = menuService.findOne(id);
			model.addAttribute("menu", menu);
			return "menulist/edit";

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "対象の商品が見つかりませんでした");
		}
		return "redirect:/menulist";
	}

	
	
//　練習用
	/*PostMapping("/menu")
public String menu(@Validated MenuForm menu, BindingResult bindingResult, Model model){
       if(bingingResult.hasErrors()){
            return "menulist/menu";
            }
            
            try {
                 menuService.save(menu)
                 menu.clear();
                 model.addAttribute("message","商品を登録しました");
                 
                 } catch (Exception e){
                 e.printStackTrace();
                 model.addAttribute("message","商品を登録できませんでした。もう一度登録し直してください")
                 */
	
	
	
	
	
	
	// 登録
	@PostMapping("/menu")
	public String menu(@Validated MenuForm menu, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "menulist/menu";
		}
		try {
			menuService.save(menu);
			menu.clear();
			model.addAttribute("message", "商品を登録しました");

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "商品を登録できませんでした。もう一度登録し直してください");

		}
		return "menulist/menu";

	}

	// 更新
	@PostMapping("/{id}/edit")
	public String update(@PathVariable Long id, @ModelAttribute MenuForm menu, RedirectAttributes redirectAttributes) {
		try {
			menu.setId(id);
			menuService.save(menu);
			redirectAttributes.addFlashAttribute("message", "更新完了");

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "更新できませんでした。もう一度更新を行ってください");

		}
		return "redirect:/menulist";
	}

	// 削除
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		try {
			menuService.delete(id);
			redirectAttributes.addFlashAttribute("message", "削除しました");

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "削除に失敗しました。もう一度やり直してください");

		}
		return "redirect:/menulist";
	}
}