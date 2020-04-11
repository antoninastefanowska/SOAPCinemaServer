package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
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
	private static final Comparator<Showing> showingsComparator = new Comparator<Showing>() {
		@Override
		public int compare(Showing showing1, Showing showing2) {
			if (showing1.getDateEpoch() < showing2.getDateEpoch())
				return -1;
			else
				return 1;
		}
		
	};
	
	public Database() {
		this.blockedIpAddresses = new ArrayList<>();
		this.films = new HashMap<>();
		this.people = new HashMap<>();
		this.showings = new HashMap<>();
		this.users = new HashMap<>();
	}
	
	public static Database getInstance() {
		if (instance == null)
			loadData();
		return instance;
	}
	
	public static void loadData() {
		try {
			FileReader reader = new FileReader(FILE_PATH);
			Database loaded = gson.fromJson(reader, Database.class);
			Database.instance = loaded;
		} catch (FileNotFoundException e) {
			Database.instance = DataInitializer.createDatabase();
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
		List<Showing> showingsList = new ArrayList<>(showings.values());
		showingsList.sort(showingsComparator);
		return showingsList;
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
