package com.swaroopr.mongo.javers;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Auditable;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document(collection = "test")
public class Issue implements Auditable<User, String>{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private	final String title;
	private final String details;
	private User createdBy;
	private DateTime creationDate;
	private User lastModifiedBy;
	private DateTime lastModifiedDate;
	@Version
	private Long version;
	
	public Issue(String title, String details) {
		this.title = title;
		this.details = details;
	}
	
	public String getId() {
		return this.id;
	}

	public String getTitle() {
		return title;
	}

	public String getDetails() {
		return details;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean isNew() {
		return null == id;
	}

	@Override
	public User getCreatedBy() {
		return this.createdBy;
	}

	@Override
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
		
	}

	@Override
	public DateTime getCreatedDate() {
		return this.creationDate;
	}

	@Override
	public void setCreatedDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public User getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	@Override
	public void setLastModifiedBy(User lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
		
	}

	@Override
	public DateTime getLastModifiedDate() {
		return this.lastModifiedDate;
	}
	
	public Long getVersion() {
		return this.version;
	}

	@Override
	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
	public String toString() {
		return "Issue [id=" + id + ", title=" + title + ", details=" + details
				+ ", version=" + version + "]";
	}
	
	
}
