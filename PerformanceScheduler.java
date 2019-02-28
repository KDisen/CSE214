/** This class is the manager of the program. It's what the user uses to put information
 * into the nodes and uses the list class to work with the performance schedule
 * 
 * @author Keven Disen <br>
 * email: keven.disen@stonybrook.edu <br>
 * ID: 111433335 <br>
 *  Recitation: 06
 *
 */
import java.util.*;
public class PerformanceScheduler {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String userSelection = "";
		PerformanceList list = new PerformanceList();
		PerformanceNode node = new PerformanceNode();
		
		//Main menu
		System.out.println(" A) Add to end \n B) Move current node backward \n"
				+ " C) Display current node \n D) Display all nodes \n F) Move current node forward"
				+ " \n I) Insert after current node \n J) Jump to position \n R) Remove current node \n"
				+ " Q) Exit");
		
		do {
			try {
				System.out.print("\nSelect an operation: \n");
				userSelection = input.nextLine().toUpperCase();
			
				if(userSelection.equals("A")) {						//add a node at the end
					System.out.print("Enter name of performance: ");
					String performance = input.nextLine();
					System.out.print("Enter name of lead performer: ");
					String lead = input.nextLine();
					System.out.print("Enter the total participants: ");
					int participants = input.nextInt();
					System.out.print("Enter the duration of the performance (in mins): ");
					int duration = input.nextInt();
					list.addToEnd(new PerformanceNode(performance, lead, participants, duration, node.getStartTime()));
	
					System.out.print("New performance " + performance + " is added to the end of the list");
					input.nextLine();
				}
				if(userSelection.equals("I")) {					//add a node after cursor
					System.out.print("Enter name of performance: ");
					String performance = input.nextLine();
					System.out.print("Enter name of lead performer: ");
					String lead = input.nextLine();
					System.out.print("Enter the total participants: ");
					int participants = input.nextInt();
					System.out.print("Enter the duration of the performance (in mins): ");
					int duration = input.nextInt();
				
					list.addAfterCurrent(new PerformanceNode(performance,lead,participants,duration, node.getStartTime()));
					System.out.print("New performance " + performance + " is added after the current performance");
					input.nextLine();
				}
				if(userSelection.equals("R")) {					//remove the node
					if(list.removeCurrentNode()) {
						System.out.print("Performance has been removed");
						
					}
				}
				if(userSelection.equals("D")) {					//print the list of nodes
					list.toString();
				}
				if(userSelection.equals("C")) {					//print the node the cursor was pointing to
					list.displayCurrentPerfomance();
				}
				if(userSelection.equals("B")) {					//move cursor backwards
					if(list.moveCursorBackward())
						System.out.println("Cursor moved back");
					else
						System.out.println("Cursor can't be moved backwards");
				}
				if(userSelection.equals("F")) {					//move cursor forward
					if(list.moveCursorForward())
						System.out.println("Cursor moved forward");
					else
						System.out.println("Cursor can't move any further");
				}
				if(userSelection.equals("J")) {					//move cursor to which ever position u want
					System.out.println("Enter a position: ");
					int position = input.nextInt();
					if(list.jumpToPosition(position)) {
						System.out.println("Cursor has been moved to position " + position);
					}
					input.nextLine();
				}
			}catch(InputMismatchException ex) {
				System.out.println("Wrong input \ntry again");
				input.nextLine();
			}catch(IllegalArgumentException ia) {
				System.out.println("Wrong input \ntry again");
				input.nextLine();
			}catch(NullPointerException np) {
				System.out.println("There's nothing there");
				input.nextLine();
			}
		}while(!userSelection.equals("Q"));					//quit
		System.out.println("Program terminating.......");
		
	}

}
