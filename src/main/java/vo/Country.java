package vo;

public class Country {
	// private 정보은닉
	private int countryId;
	private String country;
	private String lastUpdate;
	
	
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
	
	
	
	/*
	public Country() {
		super(); // 부모 생성자를 호출
		this.countryId = 0;
		this.country = null;
		this.lastUpdate = null;
	}
	
	public int getCountryId() { // 캡슐화
		return this.countryId;
	}
	
	public void setCountryId(int countryId) {
		if(countryId <0) {
			return;
		}
		this.countryId = countryId;
	}
	
	
	
	public Country() {
		this.countryId = 0;
		this.country = null;
		this.lastUpdate = null;
	}
	*/
}
