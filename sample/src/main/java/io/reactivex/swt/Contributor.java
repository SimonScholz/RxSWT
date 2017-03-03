package io.reactivex.swt;

public class Contributor {
	private final String login;
	private final int contributions;

	public Contributor(String login, int contributions) {
		this.login = login;
		this.contributions = contributions;
	}

	public String getLogin() {
		return login;
	}

	public int getContributions() {
		return contributions;
	}
}