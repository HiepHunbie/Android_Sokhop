package com.example.ev.SoKhop.Base;

public class Album_data extends BaseData {
	private String id;

	private String name;

	private String owner_id;

	private String created_at;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "ClassPojo [id = " + id + ", name = " + name + ", owner_id = "
				+ owner_id + ", created_at = " + created_at + "]";
	}
}
