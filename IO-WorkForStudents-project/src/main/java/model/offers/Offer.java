package model.offers;

public class Offer {

	private int id_offer, id_person, rating;
	private String title;
	private String content;
	private int salary;
	private String tags;

	public Offer(int id_offer, int id_empl, String title, String content, int salary, String tags) {
		this.id_offer = id_offer;
		this.id_person = id_empl;
		this.title = title;
		this.content = content;
		this.salary = salary;
		this.tags = tags;
	}

	public Offer(int id_stud, String title, String content, int rating, String tags) {
		this.id_person = id_stud;
		this.title = title;
		this.content = content;
		this.rating = rating;
		this.tags = tags;
	}

	public int getIdOffer() {
		return this.id_offer;
	}

	public int getIdPerson() {
		return this.id_person;
	}

	public String getTitle() {
		return this.title;
	}

	public String getContent() {
		return this.content;
	}

	public int getSalary() {
		return this.salary;
	}

	public int getRating() {
		return this.rating;
	}
	
	public String getTags() {
		return this.tags;
	}
}
