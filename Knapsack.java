import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

class ItemCategory {
	HashMap mapTypes = new HashMap();
	Knapsack sack;
	int arrayItems[];
	String itemCode;

	boolean found;
	String selection = "";
	int aptLevel;
	long iterations;

	public ItemCategory(HashMap mapTypes, int arrayItems[], String itemCode) {
		this.mapTypes = mapTypes;
		this.arrayItems = arrayItems;
		this.itemCode = itemCode;
		Arrays.sort(arrayItems);

	}

	public void process(int weight) {
		DecimalFormat df = new DecimalFormat("0.00");
		processAlgo(weight, arrayItems, 0, "");
		String sol[] = selection.split(":");
		Arrays.sort(sol);
		HashMap mapItemDistribution = new HashMap();

		if (found) {
			// Grouping of solution on basis of bundles
			for (String item : sol) {
				int itemValue = Integer.parseInt(item);
				if (mapItemDistribution.containsKey(itemValue)) {
					mapItemDistribution.put(itemValue, (int) mapItemDistribution.get(itemValue) + 1);
				} else {
					mapItemDistribution.put(itemValue, 1);
				}
			}
			Iterator<Integer> ite = mapItemDistribution.keySet().iterator();
			float total = 0;
			StringBuffer buffer = new StringBuffer("");
			while (ite.hasNext()) {
				int value = ite.next();
				total = total + (int) mapItemDistribution.get(value) * (float) mapTypes.get(value);
				buffer.append(mapItemDistribution.get(value) + " X " + value + " " + mapTypes.get(value) + "\n");
			}

			System.out.print(weight + " " + itemCode + " $" + df.format(total) + "\n" + buffer);
		} else {
			System.out.println(itemCode + " - " + "Error : Impossible to select!");
		}

	}

	public void processAlgo(long sum, int a[], int level, String string) {
		iterations++;
		if (sum == 0) {
			if (!found) {
				aptLevel = level;
				found = true;
				selection = string;
			} else {

				if (level < aptLevel) {
					selection = string;
					aptLevel = level;
				}
			}
			// return;
		} else if (sum < 0) {
			//do nothing and let function return.
			return;
		} else {
			for (int i = a.length - 1; i >= 0; i--) {
				if (sum - a[i] >= 0) {
					String newSelection = string.length() > 0 ? (string + ":" + a[i]) : "" + a[i];

					if (!found) {
						processAlgo(sum - a[i], a, level + 1, newSelection);
					} else {

						if (level < aptLevel - 1) {
							processAlgo(sum - a[i], a, level + 1, newSelection);
						}
					}
				}
			}
		}
	}
}

public class Knapsack {

	public static void main(String args[]) {
		/*
		 * Input format structure: The input format is divided into two parts: 1. This
		 * part takes as input the variety of items available and their configuration.
		 * i.e packing per unit options 2. This part takes as input the actual user
		 * query, on which we want to run the selection algorithm.
		 * 
		 * Structure Example is as under: 
		 * 
		 * no_of_items
		 * item_code count_of_varity
		 * variety_1 price_variety_1
		 * . .
		 * . .
		 * variety_n price_variety_n
		 * number_of_queries
		 * quantity_1 item_code_1
		 * . .
		 * quantity_n item_code_n
		 * 
		 * 
		 * Example Sample input is below: 
		 * 3
		 * VS5 2
		 * 3 6.99
		 * 5 8.99
		 * MB11 3
		 * 2 9.95 
		 * 5 16.95
		 * 8 24.95
		 * CF 3
		 * 3 5.95
		 * 5 9.95
		 * 9 16.99
		 * 3
		 * 10 VS5
		 * 14 MB11
		 * 13 CF
		 * 
		 */

		Scanner scan = new Scanner(System.in);
		
		//Capture number of items
		int numberOfProducts = scan.nextInt();
		
		//Capture Detail of each Item code in hash map 
		HashMap<String, ItemCategory> itemCodeArrayMap = new HashMap<String, ItemCategory>();
		
		//Capture Input
		for (int i = 0; i < numberOfProducts; i++) {
			String itemCode = scan.next();
			int configurations = Integer.parseInt(scan.next());
			int arrayItems[] = new int[configurations];
			HashMap mapTypes = new HashMap();
			for (int j = 0; j < configurations; j++) {
				int variety_j = Integer.parseInt(scan.next());
				float price_variety_j = Float.parseFloat(scan.next());
				arrayItems[j] = variety_j;
				mapTypes.put(variety_j, price_variety_j);
			}
			itemCodeArrayMap.put(itemCode, new ItemCategory(mapTypes, arrayItems, itemCode));

		}
		//Capture Number of inputs
		int number_of_queries = Integer.parseInt(scan.next());
		
		//Process Result
		
		System.out.println(""); //Create a line of gap between input and output. 
		for (int i = 0; i < number_of_queries; i++) {
			int requiredQuantity = Integer.parseInt(scan.next());
			String queryItemCode = scan.next();
			ItemCategory queryObject = itemCodeArrayMap.get(queryItemCode);
			queryObject.process(requiredQuantity);

		}

	}

}
