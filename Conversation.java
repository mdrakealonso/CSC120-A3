import java.util.Scanner;
import java.util.Random;

/**
 * Class constructor for a simple chat bot conversation. 
 */
class Conversation {

  public static void main(String[] arguments) {

    // Canned responses to user input
    String[] cannedResponses = {
      "Fascinating.",
      "I see.",
      "Hmmm... Tell me more.",
      "Okay...",
      "That’s a new perspective."
    };

    // Create scanner
    Scanner conversationStart = new Scanner(System.in);

    // Ask user for desired number of rounds
    System.out.println("How many rounds? ");
    String numRounds = conversationStart.nextLine();

    // Store desired number of rounds
    int rounds = Integer.parseInt(numRounds);
    
    // Begin conversation
    String[] transcript = new String[2 * rounds + 1];
    transcript[0] = "Okie dokey! What's going on today, you?";
    System.out.println(transcript[0]);

    // Loop for desired number of rounds
    for(int i = 0; i < Integer.parseInt(numRounds); i++){
      String userInput = conversationStart.nextLine();
      String botResponse;

      // Track whether string has been edited
      boolean edited = false;

      // Split user input
      String[] splitResponse = userInput.split(" ");
      for(int j = 0; j < splitResponse.length; j++){
        String word = splitResponse[j].toLowerCase();
        String punctuation = "";
        if (word.matches(".*[.,!?]$")){
          punctuation = word.substring(word.length() - 1);
          word = word.substring(0, word.length() - 1);
        }
        // Mirror words
        if (word.equals("i")) {
          word = "you";
          edited = true;
        } else if (word.equals("me")) {
          word = "you";
          edited = true;
        } else if (word.equals("am")) {
          word = "are";
          edited = true;
        } else if (word.equals("your")) {
          word = "my";
          edited = true;
        } else if (word.equals("my")) {
          word = "your";
          edited = true;
        } else if (word.equals("you")) {
          word = "I";
          edited = true;
        } else if (word.equals("are")) {
          word = "am";
          edited = true;
        }
        splitResponse[j] = word + punctuation;
      }
      String joinResponse = String.join(" ", splitResponse);
      joinResponse = joinResponse.replace(".", "?");
      String response = joinResponse.substring(0,1).toUpperCase() 
                        + joinResponse.substring(1);
      if (edited) {
        botResponse = response;
      } else {
        int randomIndex = new Random().nextInt(cannedResponses.length);
        botResponse = cannedResponses[randomIndex];
      }
      System.out.println(botResponse);
      transcript[2 * i + 1] = userInput;
      transcript[2 * i + 2] = botResponse;
    }

    System.out.println("This has been fun! Let's chat again soon.");
    System.out.println("\n");

    // Create transcript
    System.out.println("TRANSCRIPT:");
    for(int i = 0; i < transcript.length; i++){
      System.out.println(transcript[i]);
    }
    conversationStart.close();
  }
}