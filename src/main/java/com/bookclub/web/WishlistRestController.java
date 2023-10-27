package com.bookclub.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.MongoWishlistDao;
import com.bookclub.service.dao.WishlistDao;

@RestController
@RequestMapping(path="/api/wishlist", produces="application/json")
@CrossOrigin(origins = "*")
public class WishlistRestController {


	WishlistDao wishlistDao = new MongoWishlistDao();

	@Autowired
	public void setWishlistDao(WishlistDao wishlistDao) {
		this.wishlistDao = wishlistDao;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<WishlistItem> showWishlist(Authentication authentication) {
		String username = authentication.getName();
		return wishlistDao.list(username);
	}
	@RequestMapping(path="/{id}", method = RequestMethod.GET)
	public WishlistItem findById(@PathVariable String id) {
		return wishlistDao.find(id);
	}

}