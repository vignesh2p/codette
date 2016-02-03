/**
 * 
 */
package com.codette.apps.frontend.translator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.codette.apps.frontend.model.Repository;

/**
 * @author ramarla
 *
 */
@Component
public class DashBoardTranslator extends BaseTranslator{

	/**
	 * Setting Repository Detail
	 * @param name
	 * @param url
	 * @return
	 */
	public Repository setRepository(String name,String url){
		Repository repository = new Repository();
		repository.setName(name);
		repository.setUrl(url);
		return repository;
	}

	/**
	 * Creating Repository List
	 * @return
	 */
	public List<Repository> createRepositoryList(){
		List<Repository> repositories = new ArrayList<Repository>();
		repositories.add(setRepository("Angular 1", "https://github.com/angular/angular1.js"));
		repositories.add(setRepository("Angular 2", "https://github.com/angular/angular2.js"));
		repositories.add(setRepository("Angular 3", "https://github.com/angular/angular3.js"));
		repositories.add(setRepository("Angular 4", "https://github.com/angular/angular4.js"));
		return repositories;
	}
	
}
