/******************************************************************************
 * BlogWowBlog.java - created by Sakai App Builder -AZ
 * 
 * Copyright (c) 2006 Sakai Project/Sakai Foundation
 * Licensed under the Educational Community License version 1.0
 * 
 * A copy of the Educational Community License has been included in this 
 * distribution and is available at: http://www.opensource.org/licenses/ecl1.php
 * 
 *****************************************************************************/

package org.sakaiproject.blogwow.model;

import java.util.Date;

/**
 * This is a single users blog in a site
 * 
 * @author Sakai App Builder -AZ
 */
public class BlogWowBlog {

	private Long id;
	private String ownerId; // Sakai userId
	private String location; // Sakai entity reference
	private String profile;
	private String imageUrl;
	private Date dateCreated;

	/**
	 * Default constructor
	 */
	public BlogWowBlog() {
	}

	/**
	 * Minimal constructor
	 */
	public BlogWowBlog(String ownerId, String location) {
		this.ownerId = ownerId;
		this.location = location;
	}

	/**
	 * Full constructor
	 */
	public BlogWowBlog(String ownerId, String location, String profile, String imageUrl, Date dateCreated) {
		this.ownerId = ownerId;
		this.location = location;
		this.profile = profile;
		this.imageUrl = imageUrl;
		this.dateCreated = dateCreated;
	}


	/**
	 * Getters and Setters
	 */

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


}