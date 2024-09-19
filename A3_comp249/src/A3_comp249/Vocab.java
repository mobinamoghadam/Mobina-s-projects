package A3_comp249;


public class Vocab {
	
	private int size;
	private DNode head;
	private DNode tail;

	public Vocab () {
		setHead(null);
		tail = null;
		size = 0;
	}

//add at head
	public void addAtHead(String newTopic,SinglyLinkedList wordsList) {
		DNode oldHead = getHead();
		setHead(new DNode(newTopic, wordsList, null, getHead()));
		
		if(tail == null) {
			tail =getHead();
		} else {
			oldHead.previous = getHead();
		}
		size++;
	}

//add at tail
	public void addAtEnd(String newTopic, SinglyLinkedList wordsList) {
        if (tail == null) {
            setHead(new DNode(newTopic, wordsList, null, getHead()));
            tail = getHead();
        } else {
            DNode oldTail = tail;
            tail = new DNode(newTopic, wordsList, tail, null);
            oldTail.next = tail;
        }
        size++;
    }

//add after element
	public void addAfter(int referenceTopic, String newTopic, SinglyLinkedList wordsList) {
    	if (getHead() == null) {
    		return;
    	} else if (size == 1) {
    		addAtEnd(newTopic, wordsList);
    	} else {
    		DNode position = getHead();
    		int i = 1;
    		while (position.next != null && i != referenceTopic) {
    			position = position.next;
    			i++;
    		}
    		// reference found
    		if (position != null) {
    			DNode n = new DNode(newTopic, wordsList, position, position.next);
    			if (position.next != null) {
    				position.next.previous = n;
    			}
    			position.next = n;
    			size++;
    		}
    	}
    }
	public void addBefore(int referenceTopicPosition, String newTopic, SinglyLinkedList wordsList) {
        // if there are no elements in the list
    	if (getHead() == null) {
            return;
        // if there is only one element in the list
    	} else if (size == 1 || referenceTopicPosition == 1) {
            addAtHead(newTopic, wordsList);
        } else {
        	// looping through to find position where to add
            DNode position = getHead();
            int i = 1;
            while (position != null && i != referenceTopicPosition) {
                position = position.next;
                i++;
            }
            // position found
            if (position != null) {
            	// adding the element
                DNode n = new DNode(newTopic, wordsList, position.previous, position);
                // updating the links
                position.previous.next = n;
                position.previous = n;

                size++;
            }
        }
    }

//remove head
	public String removeHead() {
		if (getHead() == null) {
			return null;
		} else if (getHead() == tail) {
			DNode temp = getHead();
			setHead(null);
			tail = null;
			size--;
			return temp.topic;
		} else {
			DNode temp = getHead();
			setHead(getHead().next);
			getHead().previous = null;
			size--;
			return temp.topic;
		}
	}

// Remove tail
	public String removeTail() {
		if (tail == null) {
			return null;
		} else if (getHead() == tail) {
			DNode temp = getHead();
			setHead(null);
			tail = null;
			size--;
			return temp.topic;
		} else {
			DNode temp = tail;
			tail = tail.previous;
			tail.next = null;
			size--;
			return temp.topic;
		}
	}

	public int getSize() {
		return size;
	}

//display from head to tail

//display from tail to head
	public void displayFromTailToHead() {
		DNode position = tail;
		if (tail == null) {
			System.out.println("the list is empty");
		} else {
			System.out.println("displaying from tail to head with " + size + " word");
			while (position != null) {
				System.out.println(position.topic);
				position = position.previous;
			}
		}
	}
	//display from head to tail
	public void displayFromHeadToTail() {
		if (size == 0) {
			System.out.println("The topic  list is empty");
		} else {
			System.out.println("You have  "+size+" topic(s)");
			DNode position = head;
			while (position != null) {
				System.out.println("Topic: " + position.topic);
				position.wordsList.display();
				position = position.next;

				System.out.println();
			}
		}
	}
	
	
	public void displayTopics() {
    	if (size == 0) {
    		System.out.println("You have no topics.");
    	}
    	DNode position = getHead();
    	for (int i = 0; i < size; i++) {
    		System.out.println(i+1 + " " + position.topic);
    		position = position.next;
    	}
    }
    
    //words in topic
    public void displayWordsInTopic(int userChoice) {
    	DNode position = getHead();
    	for (int i = 1; i < userChoice; i++) {
    		position = position.next;
    	}
    	System.out.println("Topic: " + position.topic);
    	position.wordsList.displayWordsForTopicBrowse();
    }
    
    // method to check if a topic already exists
    public boolean checkTopicExistence(String findTopic) {
    	DNode position = getHead();
    	while (position != null && !(position.topic.equals(findTopic))) {
    		position = position.next;
    	}
    	// if position is null then the topic was not in the list already
    	if (position != null && position.topic.equals(findTopic)) {
    		return true; // topic exists in the list
    	} else {
    		return false; // topic does not exists
    	}
    }
   
    // get topic name from input number
    public String getTopicName(int topicNumber) {
    	if (size == 0) {
    		return "No topics";
    	} else if (size == 1) {
    		return getHead().topic;
    	} else {
    		DNode position = getHead();
    		int i = 1;
    		while (position != null && i != topicNumber) {
    			position = position.next;
    			i++;
    		}
    		// position found
    		if (position != null) {
    			return position.topic;
    		// does not happen
    		} else {
    			return "Topic not found.";
    		}
    	}
    }
    
    // method to add word to a topic - OPTION 5
    public void addWordToTopic(String topicName, String wordToAdd) {
    	DNode position = getHead();
    	// if statement if there's no topics?
    	while (position != null && !(position.topic.equals(topicName))) {
    		position = position.next;
    	}
    	if (wordExistenceCheck(topicName, wordToAdd)) {
    		position.wordsList.addAtHead(wordToAdd);
    	} else {
    		System.out.println("Sorry, the word '" + wordToAdd + "' already exists.");
    	}
    }
    
    // method to check if a word already exists in a topic word-list - OPTION 5
    public boolean wordExistenceCheck(String topicName, String wordToAdd) {
    	DNode position = getHead();
    	while (position != null && !(position.topic.equals(topicName))) {
    		position = position.next;
    	}
    	// position found (always is)
    	if (position.wordsList.checkWord(wordToAdd)) { // if word exists already
    		return false;
    	} else { // if word doesn't exit yet
    		return true;
    	}
    }
    
    
    // method to check if a word exists in ALL topics - only OPTION 6
    public String wordExistenceCheckAllTopics(String wordSearch) {
    	int checking = 0;
    	String print = "";
    	DNode position = getHead();
    	String topicNameSearch = "";
    	// looping through all topics
    	while (position != null) {
    		// checking if the word is in the list of words
    		if (position.wordsList.checkWord(wordSearch)) {
    			// looping through all values of the inner list
    			for (int i = 0; i < position.wordsList.getSize(); i++) {
    				// storing the word in string for each repetition
    				String word = position.wordsList.getWordFromIndex(i);
    				// checking if the word equals the given word
    				if (word.equals(wordSearch)) {
    					// adding it to the output
    					print = print + "The word '" + wordSearch + "' is in topic '" + position.topic +
    	    					"' at position " + i + ".\n";
    					checking++;
    				}
    			}
    		}
    		position = position.next;
    	}
    	if (checking == 0) {
    		print = "The word '" + wordSearch + "' does not exist in the topics.";
    	}
    	return print;
    }
    
    
    // method to remove word from topic
	public void removeElement(int remove) {
		if (size == 0) {
			return;
		} else if (size == 1 || remove == 1) {
			removeHead();
		} else if (remove == size) {
			removeTail();
		} else {
			DNode position = head;
			int i = 1;
			while (position != null && i != remove) {
				position = position.next;
			}
		}
	}
	public void removeWordFromTopic(String topicName, String wordRemove) {
		DNode position = head;
		while (position != null && !(position.topic.equals(topicName))) {
			position = position.next;
		}
		if (position != null) {
			// check if word exists
			if (position.wordsList.checkWord(wordRemove)) {
				position.wordsList.removeWord(wordRemove);
			} else {
				System.out.println("Sorry, there exists no word: " + wordRemove);
			}
		}
	}

				// method to change a word in a topic
    public void changeWordInTopic(String topicName, String wordChange, String newWord) {
    	DNode position = getHead();
    	while (position != null && !(position.topic.equals(topicName))) {
    		position = position.next;
    	}
    	if (position != null) {
    		// check if word even exists
    		if (position.wordsList.checkWord(wordChange)) {
    			// method to change word to new one
    			position.wordsList.changeWord(wordChange, newWord);
    		} else {
    			System.out.println("Sorry, there exists no word: '" + wordChange + "' to change.");
    		}
    	}
    }
    
    
    public SinglyLinkedList getWordsInTopic(int i) {
    	DNode position = getHead();
    	for (int j = 1; j < i; j++) {
    		position = position.next;
    	}
    	return position.wordsList;
    }
    
    
    
    
	/*public void display() {
        if (size == 0) {
            System.out.println("Your list is empty.");
        } else {
            System.out.println("Your list has " + size + " element(s): ");
            System.out.println(head.value);

            Node position = head.next;
            while (position != head) {
                System.out.println(position.value);
                position = position.next;
            }
        }
    }

*/
	
	
public DNode getHead() {
		return head;
	}

	public void setHead(DNode head) {
		this.head = head;
	}

	//inner class Node 
	class DNode {
		private String topic;
		private SinglyLinkedList wordsList;
		private DNode previous;
		private DNode next;

		public DNode(String topic, SinglyLinkedList wordsList,DNode previous, DNode next) {
            this.topic = topic;
            this.wordsList = wordsList;
            this.next = next;
            this.previous = previous;
		
	}
	
}
}