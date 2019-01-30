
public class RemoveAllOccurences{
	public static void main(String args[]) {
		String s = "aaabbb";
		
		s = removeAllOccurences(s, 'a');
		System.out.println(s);
	}
	
	private static String removeAllOccurences(String str, char ch) {
		for(int i = 0; i < str.length(); i++) {
			if(ch == str.charAt(i)) {
				str = str.replaceAll(ch + "", "");
			}
		}
		return str;
	}
}
