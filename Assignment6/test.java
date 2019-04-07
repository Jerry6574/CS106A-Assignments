import java.util.Arrays;

public class test {
	
	public static void main(String[] args) {
		String testLine = "Zane 0 0 778 763 867 717 753 752 577 327 250";
		NameSurferEntry surfer = new NameSurferEntry(testLine);
				
		
		System.out.println(surfer.getName());
		
		System.out.println(surfer.getRank(1900));
		
		System.out.println(surfer.toString());
	}

}
