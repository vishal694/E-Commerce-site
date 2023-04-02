package com.sheryians.major.pub;

import java.util.ArrayList;
import java.util.List;

import com.sheryians.major.model.ProductModel;

public class GlobleData {

	public static List<ProductModel> cart;
	
	static {
		cart = new ArrayList<ProductModel>();
	}
}
