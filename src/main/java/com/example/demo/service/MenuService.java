package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.interfaces.MenuInterface;
import com.example.demo.models.MenuForm;
import com.example.demo.repositries.MenuRepository3;
import org.springframework.transaction.annotation.Transactional; 

@Service("menuService")
@Transactional
public class MenuService implements MenuInterface {
	
	@Autowired
     private MenuRepository3 menuRepository3;
	
//menuRepository3の処理
	@Override
	 public List<MenuForm> findAll() {
	        return menuRepository3.findAll();
	    }
	 
//menuRepository3の検索
	 @Override
	 public MenuForm findOne(Long id) {
	        return menuRepository3.findById(id)
	        		.orElseThrow(() -> new RuntimeException("指定されたメニュー(ID:" + id + ")は見つかりません。"));
	 }
	 
//menuRepository3の保存
	 @Override
	 public MenuForm save(MenuForm menu) {
	        return menuRepository3.save(menu);
	    }
	 
//menuRepository3の削除
	 @Override
	  public void delete(Long id) {
		    menuRepository3.findById(id)
	                .orElseThrow(() -> new RuntimeException("削除対象(ID:" + id + ")が見つかりません"));
	        menuRepository3.deleteById(id);
	    }
}
