package model;

public class DataInitializer {
	
	public static Database createDatabase() {
		Database db = new Database();
		
		Person director = new Person("Imaishi", "Hiroyuki");
		db.addPerson(director);
		Film film = new Film("Promare", director, "A terrorist group calling themselves Mad Burnish has been causing havoc all over the nation. After an encounter with Mad Burnish leader Lio Fotia, Galo sets out on his fated journey to find the truth about these mutants, ultimately leading him to question everything he previously held to be true.");
		film.setCover("promare.jpg");
		
		Person actor = new Person("Taichi", "Saotome");
		db.addPerson(actor);
		film.addRole(new Role("Lio", "Fotia", actor));
		
		actor = new Person("Kenichi", "Matsuyama");
		db.addPerson(actor);
		film.addRole(new Role("Galo", "Thymos", actor));
		
		actor = new Person("Masato", "Sakai");
		db.addPerson(actor);
		film.addRole(new Role("Kray", "Foresight", actor));
		db.addFilm(film);
		
		Showing showing = new Showing(film, "18-04-2020 17:00");
		showing.takeSeat(new Seat(9, 10));
		showing.takeSeat(new Seat(9, 11));
		showing.takeSeat(new Seat(9, 12));
		showing.takeSeat(new Seat(5, 14));
		db.addShowing(showing);
		
		showing = new Showing(film, "18-04-2020 20:00");
		showing.takeSeat(new Seat(1, 13));
		showing.takeSeat(new Seat(1, 14));
		db.addShowing(showing);
		
		showing = new Showing(film, "19-04-2020 10:00");
		showing.takeSeat(new Seat(1, 7));
		db.addShowing(showing);
		
		director = new Person("Bong", "Joon Ho");
		db.addPerson(director);
		film = new Film("Parasite", director, "A poor family, the Kims, con their way into becoming the servants of a rich family, the Parks. But their easy life gets complicated when their deception is threatened with exposure.");
		film.setCover("parasite.jpg");
		
		actor = new Person("Song", "Kang-ho");
		db.addPerson(actor);
		film.addRole(new Role("Taek", "Ki", actor));
		
		actor = new Person("Lee", "Sun-kyun");
		db.addPerson(actor);
		film.addRole(new Role("Dong", "Ik", actor));
		
		actor = new Person("Jo", "Yeo-jeong");
		db.addPerson(actor);
		film.addRole(new Role("Kyo", "Yeon", actor));
		
		actor = new Person("Choi", "Woo-sik");
		db.addPerson(actor);
		film.addRole(new Role("Woo", "Ki", actor));
		
		actor = new Person("Park", "So-dam");
		db.addPerson(actor);
		film.addRole(new Role("Jung", "Ki", actor));
		db.addFilm(film);
		
		showing = new Showing(film, "18-04-2020 10:00");
		showing.takeSeat(new Seat(5, 14));
		showing.takeSeat(new Seat(5, 13));
		showing.takeSeat(new Seat(5, 12));
		showing.takeSeat(new Seat(3, 7));
		showing.takeSeat(new Seat(9, 5));
		db.addShowing(showing);
		
		showing = new Showing(film, "18-04-2020 12:00");
		showing.takeSeat(new Seat(3, 10));
		showing.takeSeat(new Seat(3, 9));
		showing.takeSeat(new Seat(8, 5));
		showing.takeSeat(new Seat(8, 6));
		db.addShowing(showing);
		
		showing = new Showing(film, "19-04-2020 16:00");
		showing.takeSeat(new Seat(7, 7));
		showing.takeSeat(new Seat(7, 8));
		showing.takeSeat(new Seat(8, 9));
		showing.takeSeat(new Seat(8, 10));
		db.addShowing(showing);
		
		director = new Person("Lulu", "Wang");
		db.addPerson(director);
		film = new Film("The Farewell", director, "A Chinese family discovers their grandmother has only a short while left to live and decide to keep her in the dark, scheduling a wedding to gather before she dies.");
		film.setCover("thefarewell.jpg");
		
		actor = new Person("Shuzhen", "Zhao");
		db.addPerson(actor);
		film.addRole(new Role("Nai", "Nai", actor));
		
		actor = new Person("Awkwafina", "");
		db.addPerson(actor);
		film.addRole(new Role("Billi", "", actor));
		
		actor = new Person("Tzi", "Ma");
		db.addPerson(actor);
		film.addRole(new Role("Haiyan", "", actor));
		
		actor = new Person("Diana", "Lin");
		db.addPerson(actor);
		film.addRole(new Role("Lu", "Jian", actor));
		db.addFilm(film);
		
		showing = new Showing(film, "17-04-2020 12:00");
		showing.takeSeat(new Seat(7, 13));
		showing.takeSeat(new Seat(6, 12));
		showing.takeSeat(new Seat(6, 11));
		showing.takeSeat(new Seat(3, 7));
		showing.takeSeat(new Seat(4, 5));
		db.addShowing(showing);
		
		showing = new Showing(film, "17-04-2020 14:00");
		showing.takeSeat(new Seat(9, 0));
		showing.takeSeat(new Seat(9, 1));
		showing.takeSeat(new Seat(3, 2));
		showing.takeSeat(new Seat(3, 7));
		showing.takeSeat(new Seat(3, 5));
		db.addShowing(showing);
		
		showing = new Showing(film, "18-04-2020 14:00");
		showing.takeSeat(new Seat(2, 10));
		showing.takeSeat(new Seat(2, 11));
		showing.takeSeat(new Seat(2, 12));
		db.addShowing(showing);
		
		director = new Person("Jan", "Komasa");
		db.addPerson(director);
		film = new Film("Bo¿e Cia³o", director, "Dwudziestoletni Daniel zostaje warunkowo zwolniony z poprawczaka. Wyje¿d¿a na drugi koniec Polski, ¿eby pracowaæ w stolarni, ale zamiast tego zaczyna udawaæ ksiêdza.");
		film.setCover("bozecialo.jpg");
		
		actor = new Person("Bartosz", "Bielenia");
		db.addPerson(actor);
		film.addRole(new Role("Daniel", "", actor));
		
		actor = new Person("Aleksandra", "Konieczna");
		db.addPerson(actor);
		film.addRole(new Role("Koœcielna", "", actor));
		
		actor = new Person("Eliza", "Rycembel");
		db.addPerson(actor);
		film.addRole(new Role("Eliza", "", actor));
		
		actor = new Person("£ukasz", "Simlat");
		db.addPerson(actor);
		film.addRole(new Role("Ksi¹dz Tomasz", "", actor));
		db.addFilm(film);
		
		showing = new Showing(film, "17-04-2020 10:00");
		showing.takeSeat(new Seat(0, 0));
		showing.takeSeat(new Seat(0, 1));
		showing.takeSeat(new Seat(0, 2));
		showing.takeSeat(new Seat(9, 14));
		showing.takeSeat(new Seat(9, 13));
		db.addShowing(showing);
		
		showing = new Showing(film, "17-04-2020 16:00");
		showing.takeSeat(new Seat(7, 3));
		showing.takeSeat(new Seat(7, 4));
		showing.takeSeat(new Seat(7, 5));
		showing.takeSeat(new Seat(7, 6));
		showing.takeSeat(new Seat(7, 7));
		db.addShowing(showing);
		
		director = new Person("Bob", "Persichetti");
		db.addPerson(director);
		film = new Film("Spider-Man: Into the Spider-Verse", director, "Teen Miles Morales becomes Spider-Man of his reality, crossing his path with five counterparts from other dimensions to stop a threat for all realities.");
		film.setCover("intothespiderverse.jpg");
		
		actor = new Person("Shameik", "Moore");
		db.addPerson(actor);
		film.addRole(new Role("Miles", "Morales", actor));
		
		actor = new Person("Jake", "Johnson");
		db.addPerson(actor);
		film.addRole(new Role("Peter B.", "Parker", actor));
		
		actor = new Person("Hailee", "Steinfeld");
		db.addPerson(actor);
		film.addRole(new Role("Gwen", "Stacy", actor));
		db.addFilm(film);
		
		showing = new Showing(film, "19-04-2020 12:00");
		showing.takeSeat(new Seat(9, 7));
		showing.takeSeat(new Seat(9, 8));
		showing.takeSeat(new Seat(9, 9));
		showing.takeSeat(new Seat(3, 5));
		showing.takeSeat(new Seat(4, 5));
		db.addShowing(showing);
		
		showing = new Showing(film, "19-04-2020 14:00");
		showing.takeSeat(new Seat(3, 8));
		showing.takeSeat(new Seat(3, 9));
		showing.takeSeat(new Seat(3, 10));
		db.addShowing(showing);
		
		return db;
	}
}
