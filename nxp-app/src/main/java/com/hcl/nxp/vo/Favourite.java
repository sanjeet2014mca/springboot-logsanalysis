package com.hcl.nxp.vo;

public class Favourite {
	private String favKey;
    private String favValue;
    
	public String getFavKey() {
		return favKey;
	}

	public void setFavKey(String favKey) {
		this.favKey = favKey;
	}

	public String getFavValue() {
		return favValue;
	}

	public void setFavValue(String favValue) {
		this.favValue = favValue;
	}

	@Override
	public String toString() {
		return "Favourite [favKey=" + favKey + ", favValue=" + favValue + "]";
	}
    
}
