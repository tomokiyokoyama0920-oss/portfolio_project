package com.example.demo.interfaces;
import com.example.demo.models.MenuForm;
import java.util.List;

public interface MenuInterface {
 
	    // 引数や戻り値を統一する
	    public List<MenuForm> findAll();
	    public MenuForm findOne(Long id);
	    public MenuForm save(MenuForm menu); // 戻り値を合わせる
	    public void delete(Long id);
	    
	}


	


