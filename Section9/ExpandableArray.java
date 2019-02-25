/**
* This class provides methods for working with an array that expands
* to include any positive index value supplied by the caller.
*/
public class ExpandableArray {
	private Object[] array; 
/**
* Creates a new expandable array with no elements.
*/
	public ExpandableArray() {
		array = new Object[0];
	}
/**
* Sets the element at the given index position to the specified.
* value. If the internal array is not large enough to contain that
* element, the implementation expands the array to make room.
*/
	public void set(int index, Object value) {
		try {
			// current array large enough for index
			array[index] = value;
		} catch (ArrayIndexOutOfBoundsException e){
			// current array is not large enough for index
			// create a temporary new array large enough for index
			Object[] newArray = new Object[index + 1];
			
			// copy elements of current array into new array
			for(int i = 0; i < array.length; i++) {
				newArray[i] = array[i];
			}
			array = newArray;
			array[index] = value;
		}
	}
/**
* Returns the element at the specified index position, or null if
* no such element exists. Note that this method never throws an
* out-of-bounds exception; if the index is outside the bounds of
* the array, the return value is simply null.
*/
	public Object get(int index) {
		try {
			return array[index];
		} catch (ArrayIndexOutOfBoundsException e){
			return null;
		}
	}
	
	public int size() {
		return array.length;
	}
	
	public static void main(String[] args) {
		ExpandableArray myList = new ExpandableArray();
		myList.set(14, "Bob");
		myList.set(21, "Sally");
		
		for(int i = 0; i < myList.size(); i++) {
			System.out.println((String)myList.get(i));
		}
	}
}