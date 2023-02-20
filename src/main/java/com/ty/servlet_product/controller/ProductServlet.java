package com.ty.servlet_product.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ty.servlet_product.dao.ProductDAO;
import com.ty.servlet_product.dto.Product;

public class ProductServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String name=req.getParameter("name");
		String brand=req.getParameter("brand");
		String manufacturer=req.getParameter("manufacturer");
		String state=req.getParameter("state");
		double price=Double.parseDouble(req.getParameter("price"));
		
		Product product=new Product();
		product.setName(name);
		product.setBrand(brand);
		product.setManufacturer(manufacturer);
		product.setState(state);
		
		ProductDAO productDAO=new ProductDAO();
		
		ServletContext context=getServletContext();
		double cgst=Double.parseDouble(context.getInitParameter("cgst"));
		
		
		if(state.equals("Karnataka")) {
			
			ServletConfig config=getServletConfig();
			double sgst_kar=Double.parseDouble(config.getInitParameter("sgst_kar"));
			double cgst1=(price*(cgst/100));
			double sgst=( price*(sgst_kar/100));
			
			product.setPrice(cgst1+sgst+price);
			productDAO.saveProduct(product);
			
		}
		else {

			ServletConfig config=getServletConfig();
			double sgst_tn=Double.parseDouble(config.getInitParameter("sgst_tn"));
			
			double cgst1=(price*(cgst/100));
			double sgst=(price*(sgst_tn/100));
			
			product.setPrice(cgst1+sgst+price);
			productDAO.saveProduct(product);
		}
		
	}

}
