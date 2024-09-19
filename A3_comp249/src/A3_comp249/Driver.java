package A3_comp249;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;






public class Driver {
	static boolean validInputCheck = true;
	private static Vocab vocabList;
	private static Scanner kb;
	static SinglyLinkedList filewordList = new SinglyLinkedList();
	
	public static void main(String[] args) throws FileNotFoundException {

		//example of the file :
		
		
		vocabList = new Vocab();
		kb = new Scanner(System.in);
        
        //Manu loop 
        boolean exit =false;
        while(!exit) {
        	printMenu();
        	System.out.println("Enter Your Choice:");
        	int choice;
        	try {
                choice = kb.nextInt();
                switch (choice) {
                    case 1:
                    	browseTopic(vocabList, kb);

                        break;
                    case 2:
                    	insertNewTopicBefore(vocabList, kb);
                        // insert a new topic before another one
                        break;
                    case 3:
						insertNewTopicAfter(vocabList,kb);
                        // insert a new topic after another one
                        break;
                    case 4:
						removeTopic(vocabList,kb);
                        // remove a topic
                        break;
                    case 5:
						modifyTopic(vocabList,kb);
                        // modify a topic
                        break;
                    case 6:
                    	// search topics for a word
                    	searchTopicsForWord(vocabList, kb);
                        break;
                        
                    case 7:
                    	
                        loadFromFile(vocabList, filewordList );
                        break;
                    case 8:
                        // show all words starting with a given letter
                    	showWordsStartingWith(vocabList, kb);
                        break;
                    case 9:
                        // save to file
                        break;
                    case 0:
                        exit=true;
                        return;
                    default: {
						System.out.println("Invalid choice. Please try again.");
					}
                }
            } catch (InputMismatchException e) {
                System.out.println(" wrong input ,Please try again, input should be number  ");
                kb.nextLine(); // Consume the remaining newline
            }
        }

    }




	public static void printMenu() {
		System.out.println("============================");
        System.out.println("Vocabulary Control Center");
        System.out.println("============================");
        System.out.println("1 Browse a topic");
        System.out.println("2 Insert a new topic before another one");
        System.out.println("3 Insert a new topic after another one");
        System.out.println("4 Remove a topic");
        System.out.println("5 Modify a topic");
        System.out.println("6 Search topics for a word");
        System.out.println("7 Load from a file");
        System.out.println("8 Show all words starting with a given letter  ");
        System.out.println("9 Save to file");
        System.out.println("0 Exit");
        System.out.println("============================");
		
	}
	public static void browseTopic(Vocab vocabList, Scanner scanner) {
		if(vocabList.getSize()==0) {
			System.out.println("there is no topic in the memory ,do you want to add topic ? please answer y/yes or n/no");
			String choice=kb.next();
			if(choice.equalsIgnoreCase("y")||choice.equalsIgnoreCase("yes")) {
				System.out.println("first topic will be added .");
				addFirstTopic();
				System.out.println("first topic is added");
				return;
			}
			else {
				return;
			}
		}
		while(true) {
			int topicBrowser =0;
			topicMenue();
			try {
				topicBrowser = kb.nextInt();
				//check for the input of the scanner

					if (topicBrowser == 0) {
						return;
					}
					if (topicBrowser < 0 || topicBrowser > vocabList.getSize()) {
						System.out.println("Invalid input! try a number between 1 and " + vocabList.getSize() + ":");
					} else {
						vocabList.displayWordsInTopic(topicBrowser);
					}
			}catch(InputMismatchException e){
				System.out.println("Invalid format, please enter an integer:");
				kb.nextLine();
			}
		}
	}
	// Method to insert a new topic before another one
    public static void insertNewTopicBefore(Vocab vocabList, Scanner scanner) {
		if(vocabList.getSize()==0) {
			System.out.println("there is no topic in the memory ,do you want to add topic ? please answer y/yes or n/no");
			String choice=kb.next();
			if(choice.equalsIgnoreCase("y")||choice.equalsIgnoreCase("yes")) {
				System.out.println("first topic will be added .");
				addFirstTopic();
				System.out.println("first topic is added");
				return;
			}
			else {
				return;
			}
		}
		     topicMenue();
			int TopicPosition= 0;
				do {
					//check for the input of the scanner
					System.out.println("Before which Topic you would like to add? "
							+ "Enter Your Choice:  ");
					if (kb.hasNextInt()) {
						TopicPosition = kb.nextInt();

						if (TopicPosition< 0 && TopicPosition > vocabList.getSize()) {
							System.out.println("Invalid input. Enter a number between 1 and " + vocabList.getSize() + ".");
						}
						if(TopicPosition==0){
							return;
						}
					}else  {
						System.out.println("Invalid input. please use numbers .");
						kb.nextLine();
						TopicPosition=-1;
					}
				} while (TopicPosition < 0 || TopicPosition > vocabList.getSize());
				if (TopicPosition == 0) {
					System.exit(0);
				}
				System.out.println("============================");
				System.out.println("Enter a topic name: ");
				String newTopicNameBefore = kb.next();
				// checking if that topic already exists
				if (vocabList.checkTopicExistence(newTopicNameBefore)) {
					System.out.println("The topic already exists. Going back to main menu.");
					return;
				}

				//Create a new WordList
				SinglyLinkedList wordListAddBefore = new SinglyLinkedList();
				vocabList.addBefore(TopicPosition, newTopicNameBefore, wordListAddBefore);
				// user enters the words for the topic
				System.out.println("Enter a word - to quit press Enter.");
				kb.nextLine();
		       	 String inputWord;

			  while (true) {
				 	if (kb.hasNextLine()) {
				do {
					inputWord = kb.nextLine();
					if(!(inputWord.trim().isBlank()))
						wordListAddBefore.addAtEnd(inputWord);
				} while (!(inputWord.isBlank()));
				vocabList.displayFromHeadToTail();
				return;
			}
		}

    }

	private static void insertNewTopicAfter(Vocab vocabList, Scanner kb) {
		if(vocabList.getSize()==0) {
			System.out.println("there is no topic in the memory ,do you want to add topic ? please answer y/yes or n/no");
			String choice=kb.next();
			if(choice.equalsIgnoreCase("y")||choice.equalsIgnoreCase("yes")) {
				System.out.println("first topic will be added .");
				addFirstTopic();
				System.out.println("first topic is added");
				return;
			}
			else {
				return;
			}
		}
		topicMenue();
		int TopicPosition= 0;
		do {
			System.out.print("After which Topic you would like to add? "
					+ "Enter Your Choice:  ");
			if (kb.hasNextInt()) {
				TopicPosition = kb.nextInt();

				if (TopicPosition< 0 || TopicPosition > vocabList.getSize()) {
					System.out.println("Invalid input. Enter a number between 1 and " + vocabList.getSize() + ".");
				}
				if(TopicPosition==0){
					return;
				}
			}else {
				System.out.println("Invalid input. please use numbers .");
				kb.nextLine();
				TopicPosition=-1;
			}

		}while(TopicPosition<0||TopicPosition>vocabList.getSize());
		if (TopicPosition == 0) {
			System.exit(0);
		}
		System.out.println("============================");
		System.out.println("Enter a topic name: ");
		String newTopicNameAfter = kb.next();
		// checking if that topic already exists
		if (vocabList.checkTopicExistence(newTopicNameAfter)) {
			System.out.println("The topic already exists. Going back to main menu.");
			return;
		}

		//Create a new WordList
		SinglyLinkedList wordListAddAfter = new SinglyLinkedList();
		vocabList.addAfter(TopicPosition, newTopicNameAfter, wordListAddAfter);
		// user enters the words for the topic
		System.out.println("Enter a word - to quit press Enter.");
		kb.nextLine();
		String inputWord;

		while (true) {
			if (kb.hasNextLine()) {
				do {
					inputWord = kb.nextLine();
					if(!(inputWord.trim().isBlank()))
						wordListAddAfter.addAtEnd(inputWord);
				} while (!(inputWord.isBlank()));
				vocabList.displayFromHeadToTail();
				return;
			}
			}

	}
	private static void removeTopic(Vocab vocabList, Scanner kb) {
		if(vocabList.getSize()==0){
			System.out.println("No topic is added,please first add topic ");
			return;
		}
		int TopicPosition;
		do {
			topicMenue();
			System.out.println("-------------------");
			System.out.println("Enter your choice ");
			System.out.println("-------------------");
			if (kb.hasNextInt()) {
				TopicPosition = kb.nextInt();

				if (TopicPosition< 0 || TopicPosition > vocabList.getSize()) {
					System.out.println("Invalid input. Enter a number between 1 and " + vocabList.getSize() + ".");
				}
				if(TopicPosition==0){
					return;
				}
			}else {
				System.out.println("Invalid input. please use numbers .");
				kb.nextLine();
				TopicPosition=-1;
			}
		}while(TopicPosition< 0 || TopicPosition > vocabList.getSize());
		vocabList.removeElement(TopicPosition);
	}
	private static void modifyTopic(Vocab vocabList, Scanner kb) {
		if(vocabList.getSize()==0){
			System.out.println("No topic is added,please first add topic ");
			return;
		}
		int TopicPosition;
		do {
			topicMenue();
			System.out.println("-------------------");
			System.out.print("Enter your choice ");

			if (kb.hasNextInt()) {
				TopicPosition = kb.nextInt();

				if (TopicPosition< 0 || TopicPosition > vocabList.getSize()) {
					System.out.println("Invalid input. Enter a number between 1 and " + vocabList.getSize() + ".");
				}
				if(TopicPosition==0){
					return;
				}
			}else {
				System.out.println("Invalid input. please use numbers .");
				kb.nextLine();
				TopicPosition=-1;
			}
		}while(TopicPosition< 0 || TopicPosition > vocabList.getSize());
		char choice;
		String word;
		do {
			System.out.println("-------------------");
			System.out.println("Modify Topics Menu");
			System.out.println("-------------------");
			System.out.println("a. Add a word");
			System.out.println("r. Remove a word");
			System.out.println("c. Change the word");
			System.out.println("0. Exit");
			System.out.print("Enter your choice: ");
			choice = kb.next().charAt(0);
			String topic;
			String word1;
			switch (choice) {
				case 'a','A': {
					try {
					System.out.println("You selected to add a word,please enter the word you want to add");
						word1 = kb.next();
						topic = vocabList.getTopicName(TopicPosition);
						vocabList.addWordToTopic(topic, word1);
					} catch (InputMismatchException e) {
						System.out.println("invalid input .");
					}
					break;
				}
				case 'r','R': {
					System.out.println("You selected to remove a word,plese enter the word you want to remove ");
					try {
						topic = vocabList.getTopicName(TopicPosition);
						word1 = kb.next();
						vocabList.removeWordFromTopic(topic, word1);
					} catch (InputMismatchException e) {
						System.out.println("invalid input .");
					}
					break;
				}
				case 'c','C': {
					System.out.println("You selected to change a word");
					topic = vocabList.getTopicName(TopicPosition);
					vocabList.displayWordsInTopic(TopicPosition);
					System.out.println("Enter word would you like to change (enter the word): ");
					String wordChange = kb.next();
					System.out.println("Enter the new word: ");
					String newWord = kb.next();
					// method to change a word
					vocabList.changeWordInTopic(topic, wordChange, newWord);
					break;
				}
				case '0': {
					System.out.println("go back to the previous menu");
					break;
				}
				default: {
					System.out.println("Invalid choice. Please try again.");
					break;
				}
			}
		} while (choice != '0');

	}


	// Method to load topics and words from a file
	public static void loadFromFile (Vocab vocabList, SinglyLinkedList filewordList) throws FileNotFoundException {
		System.out.println("Please enter the name of the file you want to input: ");
		String inputFile = kb.next();
		
		if(filewordList.checkWord(inputFile)) {
			System.out.println("The file already exists!");
			return;
		}
		try {
		BufferedReader reader = new BufferedReader (new FileReader(inputFile));
		String line;
		String topic = "";
		SinglyLinkedList wordsList = new SinglyLinkedList();
			while ((line = reader.readLine()) != null) {
				if (line.trim().isEmpty()) {
					if(!topic.isEmpty()) {
						vocabList.addAtEnd(topic, wordsList);
					}
					wordsList= new SinglyLinkedList();
				}else if (line.charAt(0)=='#') {
					topic=line.substring(1).trim();
				}else {
					wordsList.addAtend(line.trim());
				}
			}
			// Add the last topic and innerList to vocab
            if (!topic.isEmpty()) {
                vocabList.addAtEnd(topic, wordsList);
            }

            // Close the reader
            reader.close();

            // Add the input file to the fileList
            filewordList.addAtEnd(inputFile);
            System.out.println("Done loading");
        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*public static void loadFromFile(Vocab vocabList, String fileName) {
		try {
			SinglyLinkedList wordsList = new SinglyLinkedList();
			Scanner fileScanner = new Scanner(new File(fileName));
			String currentTopic = "";
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine().trim();
				if (line.startsWith("#")) {
					currentTopic = line.substring(2);
					vocabList.addAtEnd(currentTopic, wordsList);
				} else if (!line.isEmpty()) {
					wordsList.addAtEnd(line);
				}
			}
			fileScanner.close();
			System.out.println("Done loading.");
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}*/
	

	//Method for searching for a word in a topic
	public static void searchTopicsForWord(Vocab vocabList, Scanner scanner) {
		if(vocabList.getSize() ==0) {
			System.out.println("There are no topics avaiable.");
			return;
		}
        System.out.print("Enter the word to search for: ");
        String word = kb.nextLine();
        vocabList.wordExistenceCheckAllTopics(word);
    }
	
	//Show words starting with a letter in a topic
	    public static void showWordsStartingWith(Vocab vocabList, Scanner scanner) {
	    	 // Check if there are any topics in the vocabList
	        if (vocabList.getSize() == 0) {
	            System.out.println("You have no topics.");
	            return;
	        }

	        // Prompt the user to enter the letter to filter words
	        System.out.println("Enter the letter which the words should start with: ");
	        char letter = scanner.next().charAt(0); // Read the letter from the user

	        // Create an ArrayList to store words starting with the specified letter
	        ArrayList<String> words = new ArrayList<>();

	        // Display the header
	        System.out.println("Words starting with the letter '" + letter + "' are:");

	        // Loop through each topic in the vocabList
	        for (int i = 1; i <= vocabList.getSize(); i++) {
	            String topicName = vocabList.getTopicName(i); // Get the name of the topic
	            SinglyLinkedList wordList = vocabList.getWordsInTopic(i); // Get the word list for the topic

	            // Loop through each word in the wordList
	            for (int j = 0; j < wordList.getSize(); j++) {
	                String word = wordList.getWordFromIndex(j); // Get the word at index j

	                // Check if the word starts with the specified letter
	                if (word.charAt(0) == letter) {
	                    words.add(word); // Add the word to the ArrayList
	                    System.out.print(word ); // Print the word
	                    System.out.println();
	                }
	            }
	        }
	        
	    }	    	
	    /*  public static void showWordsStartingWith(Vocab vocabList, Scanner scanner) {
        if (vocabList.getSize() == 0) {
            System.out.println("There are no topics available.");
            return;
        }
        System.out.print("Enter the letter: ");
        char letter = scanner.nextLine().charAt(0);
        ArrayList<String> words = new ArrayList<>();
        words.addAll(vocabList.getWordsStartingWith(letter));
        words.sort(String::compareToIgnoreCase);
        System.out.println("Words starting with '" + letter + "': ");
        for (String word : words) {
            System.out.println(word);
        }
    } */
		
	
public static  void topicMenue(){
	System.out.println("============================");
	System.out.println("        pick a Topic        ");
	System.out.println("============================");
	vocabList.displayTopics();
	System.out.println("0 to Exit");
}
	private static void addFirstTopic() {
		System.out.println("============================");
		System.out.println("Enter a topic name: ");
		String newTopic = kb.next();
		SinglyLinkedList wordList = new SinglyLinkedList();
		vocabList.addAtHead(newTopic, wordList);
		// user enters the words for the topic
		System.out.println("Enter a word - to quit press Enter.");
		kb.nextLine();
		String inputWord;
		while (true) {
			if (kb.hasNextLine()) {
				do {
					inputWord = kb.nextLine();
					if(!(inputWord.trim().isBlank()))
						wordList.addAtEnd(inputWord);
				} while (!(inputWord.isBlank()));
				vocabList.displayFromHeadToTail();
				return;
			}
		}
	}
}
