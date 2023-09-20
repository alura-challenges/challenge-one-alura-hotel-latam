package model;

public enum Nationality {

	AFGAN ("AFGAN","afgano-afgana"),
	GERMAN ("GERMAN","alem\u00e1n-alemana"),
	ARGENTIN ("ARGENTIN","argentino-argentina");
	
	private final String name;
	private final String spanishNationality;
	
	private Nationality(String name, String spanishNationality) {
		this.name=name;
		this.spanishNationality=spanishNationality;
	}

	public String getName(){
		return name;
	}
}
