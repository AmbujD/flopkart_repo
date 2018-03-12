package com.iiitb.ooadvoid;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.iiitb.ooadvoid.pojo.FlopkartItem;
import com.iiitb.ooadvoid.pojo.FlopkartListing;

@WebServlet("/UploadImgServlet")
public class UploadImgServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String BASE_URI = "http://localhost:8080/flopkartPrototype/webapi/";
	public static final String PATH_NAME = "listings/create";
	public static final String PATH_NAME2 = "items/create";
	Integer listing_id = 0;
	String listingName = "";
	Integer sub_cat_content = 0;
	String brand = "";
	Integer qty = 0;
	Integer price = 0;
	String dis = "";
	String clr = "";
	String mfd = "";
	String desptn = "";
	String pic_url = "";
	
	@Override
	public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		
		

		try
		{	
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<?> items = null;
			items = upload.parseRequest(request);
			Iterator<?> itr = items.iterator();
			while (itr.hasNext())
			{
				FileItem item = (FileItem) itr.next();
				if (item.isFormField())
				{	
					
					
					String name = item.getFieldName();
					if (name.equals("listingname"))
					{
						listingName = (String) item.getString();
						
					}
					else if (name.equals("listing_id"))
					{
						listing_id =  Integer.parseInt(item.getString());
					}

					else if (name.equals("subcatId"))
					{
						sub_cat_content =  Integer.parseInt(item.getString());
						
					}

					else if (name.equals("brand"))
					{
						brand = (String) item.getString();
						
					}

					else if (name.equals("qty"))
					{
						qty =  Integer.parseInt(item.getString());
						
					}

					else if (name.equals("price"))
					{
						price =  Integer.parseInt(item.getString());
						
					}

				/*	else if (name.equalsIgnoreCase("dis"))
					{
						dis = (String) item.getString();
					
					}

				*/
					else if (name.equals("clr"))
					{
						clr = (String) item.getString();
						
					}

					else if (name.equals("mfd"))
					{
						mfd = (String) item.getString();
						
						
					}

					else if (name.equals("desptn"))
					{
						desptn = (String) item.getString();
						
					}

				}
				else
				{   
					
					pic_url = item.getName();
					AccessProperties ap = new AccessProperties();
					File f = new File(ap.getServerPath() + pic_url);
					item.write(new File(ap.getServerPath() + pic_url));
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URI+PATH_NAME);
		FlopkartListing item = new FlopkartListing();
		
		item.setListingid(listing_id);
		item.setListingName(listingName);
		item.setImgUrl(pic_url);
		item.setSubcategoryId(sub_cat_content);
		item.setBrand(brand);
		item.setQuantity(qty);
		item.setPrice(price);
	//	item.setListingName(dis);
		item.setColour(clr);
		item.setManufacture_Date(mfd);
		item.setDescription(desptn);
		
		System.out.println(item.getListingName());
		System.out.println(item.getImgUrl());
		System.out.println(item.getSubcategoryId());
		System.out.println(item.getBrand());
		System.out.println(item.getQuantity());
		System.out.println(item.getPrice());
		
		System.out.println("After getting");
		target.request(MediaType.APPLICATION_JSON).post(Entity.entity(item, MediaType.APPLICATION_JSON));
		
		
		
		
		
		WebTarget target2 = client.target(BASE_URI+PATH_NAME2);
		FlopkartItem item1 = new FlopkartItem();
		item1.setItemid(listing_id);
		for(int i = 0 ; i < 5; i++) {
			System.out.println(i);
			target2.request(MediaType.APPLICATION_JSON).post(Entity.entity(item1, MediaType.APPLICATION_JSON));
		}
		response.sendRedirect("sellerItemInsert.jsp?success");
		
	}
	
}
