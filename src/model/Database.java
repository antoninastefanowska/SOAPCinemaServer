package model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Database {
	private static final String FILE_PATH = "./resources/database.json";
	private static Database instance;
	
	private List<String> blockedIpAddresses;
	private Map<Integer, Film> films;
	private Map<Integer, Person> people;
	private Map<Integer, Showing> showings;
	private Map<String, User> users;
	
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public Database() {
		this.blockedIpAddresses = new ArrayList<>();
		this.films = new HashMap<>();
		this.people = new HashMap<>();
		this.showings = new HashMap<>();
		this.users = new HashMap<>();
	}
	
	@SuppressWarnings("unused")
	private void initialize() {
		Person director = new Person("Imaishi", "Hiroyuki");
		addPerson(director);
		Film film = new Film("Promare", director, "A terrorist group calling themselves Mad Burnish has been causing havoc all over the nation. After an encounter with Mad Burnish leader Lio Fotia, Galo sets out on his fated journey to find the truth about these mutants, ultimately leading him to question everything he previously held to be true.");
		film.setCover("promare.jpg");
		
		Person actor = new Person("Taichi", "Saotome");
		addPerson(actor);
		film.addRole(new Role("Lio", "Fotia", actor));
		
		actor = new Person("Kenichi", "Matsuyama");
		addPerson(actor);
		film.addRole(new Role("Galo", "Thymos", actor));
		
		actor = new Person("Masato", "Sakai");
		addPerson(actor);
		film.addRole(new Role("Kray", "Foresight", actor));
		addFilm(film);
		
		Showing showing = new Showing(film, "28-03-2020 17:00");
		showing.takeSeat(new Seat(9, 10));
		showing.takeSeat(new Seat(9, 11));
		showing.takeSeat(new Seat(9, 12));
		showing.takeSeat(new Seat(5, 14));
		addShowing(showing);
		
		showing = new Showing(film, "28-03-2020 20:00");
		showing.takeSeat(new Seat(1, 13));
		showing.takeSeat(new Seat(1, 14));
		addShowing(showing);
		
		showing = new Showing(film, "29-03-2020 10:00");
		showing.takeSeat(new Seat(1, 7));
		addShowing(showing);
		
		director = new Person("Bong", "Joon Ho");
		addPerson(director);
		film = new Film("Parasite", director, "A poor family, the Kims, con their way into becoming the servants of a rich family, the Parks. But their easy life gets complicated when their deception is threatened with exposure.");
		film.setCover("parasite.jpg");
		
		actor = new Person("Song", "Kang-ho");
		addPerson(actor);
		film.addRole(new Role("Taek", "Ki", actor));
		
		actor = new Person("Lee", "Sun-kyun");
		addPerson(actor);
		film.addRole(new Role("Dong", "Ik", actor));
		
		actor = new Person("Jo", "Yeo-jeong");
		addPerson(actor);
		film.addRole(new Role("Kyo", "Yeon", actor));
		
		actor = new Person("Choi", "Woo-sik");
		addPerson(actor);
		film.addRole(new Role("Woo", "Ki", actor));
		
		actor = new Person("Park", "So-dam");
		addPerson(actor);
		film.addRole(new Role("Jung", "Ki", actor));
		addFilm(film);
		
		showing = new Showing(film, "28-03-2020 10:00");
		showing.takeSeat(new Seat(5, 14));
		showing.takeSeat(new Seat(5, 13));
		showing.takeSeat(new Seat(5, 12));
		showing.takeSeat(new Seat(3, 7));
		showing.takeSeat(new Seat(9, 5));
		addShowing(showing);
		
		showing = new Showing(film, "28-03-2020 12:00");
		showing.takeSeat(new Seat(3, 10));
		showing.takeSeat(new Seat(3, 9));
		showing.takeSeat(new Seat(8, 5));
		showing.takeSeat(new Seat(8, 6));
		addShowing(showing);
		
		showing = new Showing(film, "29-03-2020 16:00");
		showing.takeSeat(new Seat(7, 7));
		showing.takeSeat(new Seat(7, 8));
		showing.takeSeat(new Seat(8, 9));
		showing.takeSeat(new Seat(8, 10));
		addShowing(showing);
	}
	
	public static Database getInstance() {
		if (instance == null)
			loadData();
		return instance;
	}
	
	public static void loadData() {
		File file = new File(FILE_PATH);
		if (!file.exists())
			return;
		try {
			FileReader reader = new FileReader(file);
			Database loaded = gson.fromJson(reader, Database.class);
			Database.instance = loaded;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveData() {
		try {
			FileWriter writer = new FileWriter(FILE_PATH);
			gson.toJson(this, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Film> getFilms() {
		return new ArrayList<>(films.values());
	}

	public List<Showing> getShowings() {
		return new ArrayList<>(showings.values());
	}

	public List<User> getUsers() {
		return new ArrayList<>(users.values());
	}

	public List<Person> getPeople() {
		return new ArrayList<>(people.values());
	}
	
	public int addFilm(Film film) {
		int id = film.generateId();
		films.put(id, film);
		return id;
	}
	
	public int addPerson(Person person) {
		int id = person.generateId();
		people.put(id, person);
		return id;
	}
	
	public int addShowing(Showing showing) {
		int id = showing.generateId();
		showings.put(id, showing);
		return id;
	}
	
	public void addUser(User user) {
		users.put(user.getAuthentication().getUsername(), user);
	}
	
	public Film getFilm(int id) {
		return films.get(id);
	}
	
	public Person getPerson(int id) {
		return people.get(id);
	}
	
	public Showing getShowing(int id) {
		return showings.get(id);
	}
	
	public User getUser(String username) {
		return users.get(username);
	}
	
	public Boolean filmExists(int id) {
		return films.containsKey(id);
	}
	
	public boolean personExists(int id) {
		return people.containsKey(id);
	}
	
	public boolean showingExists(int id) {
		return showings.containsKey(id);
	}
	
	public Boolean userExists(String username) {
		return users.containsKey(username);
	}
	
	public void blockIpAddress(String ipAddress) {
		blockedIpAddresses.add(ipAddress);
		System.out.println("Adres IP zosta³ zablokowany.");
		saveData();
	}
	
	public void unblockIpAddress(String ipAddress) {
		blockedIpAddresses.remove(ipAddress);
		System.out.println("Adres IP zosta³ odblokowany.");
		saveData();
	}
	
	public Boolean isIpAddressBlocked(String ipAddress) {
		return blockedIpAddresses.contains(ipAddress);
	}
	
	public void processCommand(String command) {
		String[] words = command.split(" ");
		if (words[0].equals("block"))
			blockIpAddress(words[1]);
		else if (words[0].equals("unblock"))
			unblockIpAddress(words[1]);
	}
}
