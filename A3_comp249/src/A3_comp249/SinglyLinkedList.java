package A3_comp249;

import java.util.ArrayList;



public class SinglyLinkedList {


	private SNode head;
	private int size;
	
	public SinglyLinkedList() {
		setHead(null);
		size=0;
	}
	
	//add at head 
	public void addAtHead (String word ) {
		setHead(new SNode (word, getHead()));
		size++;
	}
	
	//add at End
	public void addAtEnd (String word) {
		if(getHead()==null) {
			addAtHead(word);
		}else {
			SNode position = getHead();
			while(position.next != null) {
				position = position.next;
			}
			position.next = new SNode (word, null);
			size++;
		}
		
	}
	//add at end 
	public void addAtend (String newWord) {
		if(getHead() == null) {
			addAtHead(newWord);
		}else { 
			SNode position = getHead();
			while (position.next !=null) {
				position= position.next;
			}
			position.next= new SNode (newWord, null);
			size ++;
		}
	}
	//add after 
	public void addafter (String word , String newWord) {
		SNode position = getHead();
		while(position !=null && position.word != word) {
			position = position.next;
		}
		if(position !=null) {
			position.next= new SNode (newWord, position.next);
			size++;
		}
	}
	//add before 
	public void addbefore(String word, String newWord) {
		if(getHead() != null){
			if(getHead().word == word) {
				addAtHead(newWord);
			}else {
				SNode position = getHead();
				while (position.next != null && position.next.word != word) {
					position = position.next;
				}
				
				if(position.next != null) {
					addafter(position.word , newWord);
				}
			}
			}
		}
	//remove head 
	public String removeHead() {
		if(getHead()==null) {
			return null;
		}else {
			SNode temp = getHead();
			setHead(getHead().next);
			size--;
			return temp.word;
		}
	}
	//remove end 
	public String removeEnd() {
		if(getHead()==null) {
				return null;
		}else if(size ==1) {
			String word = getHead().word;
			setHead(null);
			size--;
			return word;
		}else {
			SNode position =getHead();
			while(position.next.next !=null) {
				position = position.next;
			}
			String word = position.next.word;
			position.next = null;
			size--;
			return word;
		}
			
	}

	
	public String removeWord(String word) {
		if(getHead()==null) {
			return null;
		}else if(getHead().word == word) {
			return removeHead();
		}else {
			SNode position = getHead();
			while(position.next != null && position.next.word!= word) {
				position= position.next;
			}
			
			if(position.next != null) {
				size--;
				SNode temp = position.next;
				position.next=position.next.next;
				return temp.word;
			}
			return null;
		}
	}
	public String removeAfter (String word) {
		 if (getHead() == null) {
	            return null;
	        } else if (size == 1) {
	            return null;
	        } else {
	            SNode position = getHead();
	            while (position != null && position.word != word) {
	                position = position.next;
	            }
	            if (position == null || position.next == null) {
	                return null;
	            } else {
	                String tempword = position.next.word;
	                position.next = position.next.next;
	                return tempword;
	            }
	        }
	    }

	    // Get size (number of nodes in the linked list)
	    public int getSize() {
	        return size;
	    }

	    // Display content of the linked list
	    public void display() {
	        if (size == 0) {
	            System.out.println("Your list is empty.");
	        } else {
	            System.out.println("Your list has " + size + " element(s): ");
	            SNode position = getHead();
	            while (position != null) {
	                System.out.println(position.word);
	                position = position.next;
	            }
	        }
	    }
	 // Method to add a word to the list in sorted order
	    public void addWord(String word) {
	        SNode newNode = new SNode(word);
	        if (head == null || word.compareTo(head.word) <= 0) {
	            newNode.next = head;
	            head = newNode;
	        } else {
	            SNode current = head;
	            while (current.next != null && word.compareTo(current.next.word) > 0) {
	                current = current.next;
	            }
	            newNode.next = current.next;
	            current.next = newNode;
	        }
	        size++;
	    }
	    //Method to print all words with a given letter 
	    public ArrayList<String> getWordsStartingWith(char letter){
	    	ArrayList<String> result = new ArrayList<>();
	    	SNode position = head;
	    	while(position != null) {
	    		if(position.word.charAt(0)== letter) {
	    			result.add(position.word);
	    		}
	    		position = position.next;
	    	}
	    	return result;
	    }
	    
	    public void changeWord(String wordChange, String newWord) {
	    	SNode position = head;
	    	for (int i = 0; i < size; i++) {
		    	while (position != null && !(position.word.equals(wordChange))) {
		    		position = position.next;
		    	}
		    	// position found (always because we already checked if the word even exists)
		    	if (position != null) {
		    		position.word = newWord;
		    	}
	    	}
	    }
	    
	    public void displayWordsForTopicBrowse() {
	    	SNode position = head;
	    	int i = 1;
	    	while (position != null) {
	    		System.out.print(i + ": " + position.word + "\t\t");
	    		position = position.next;
	    		System.out.println();
	    		i++;
	    	}
	    	System.out.println();
	    }
	    
	    // boolean method to see if the word already exists 
	    public boolean checkWord(String wordToAdd) {
	    	SNode position = head;
	    	while (position != null && !(position.word.equals(wordToAdd))) {
	    		position = position.next;
	    	}
	    	if (position != null) {
	    		// word already exists
	    		return true;
	    	} else {
	    		// word doesn't exist
	    		return false;
	    	}
	    }
	    
	    // method that checks if a word exists in an inner list WITH returning the index - OPTION 6
	    public int checkWordWithIndex(String wordSearch) {
	    	SNode position = head;
	    	int counter = 1;
	    	while (position != null && !(position.word.equals(wordSearch))) {
	    		position = position.next;
	    		counter++;
	    	}
	    	if (position != null) {
	    		// word exists
	    		return counter;
	    	} else {
	    		// word doesn't exist
	    		return 0;
	    	}
	    }
	    public String getWordFromIndex(int i) {
	    	SNode position = head;
	    	for (int j = 1; j <= i; j++) {
	    		position = position.next;
	    	}
	    	String wordAtIndex = position.word;
	    	return wordAtIndex;
	    }
	    
	
	public SNode getHead() {
			return head;
		}

		public void setHead(SNode head) {
			this.head = head;
		}

	//inner class 
	class SNode {
		private String word;
		private SNode next;
		public SNode() {
			// TODO Auto-generated constructor stub
			word=null;
			next=null;
		}
		public SNode(String word) {
			this.word=word;
			next=null;
		}
		public SNode (String word, SNode next) {
			this.word= word;
			this.next=next;
		}
		public String getWord() {
			return word;
		}
		public SNode getNext() {
			return next;
		}
	}
}
