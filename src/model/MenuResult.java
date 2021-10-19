package model;

public class MenuResult {
	private static boolean isAdvance = false;
	private static int role;
	
	
	
	
	
	
	
	public void MenueResult(boolean isAdvanced, int role ) {
		
		this.isAdvance = isAdvanced;
		this.role = role;
		
	}
	
	public void setDifficulty(boolean isAdv) {
		this.isAdvance = isAdv;
	}
	
	public static boolean getDifficulty() {
		return isAdvance;
	}

	
	
	public void setRole(int role) {
		this.role = role;
	}
	
	
	public static int getRole() {
		return role;
	}
	
	
	
	
	
	
	
}
