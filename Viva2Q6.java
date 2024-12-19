import java.util.Scanner;

public class Viva2Q6 {

    // Generate initials from the full name
    public static String generateInitials(String fullName) {
        String name = fullName.toUpperCase();
        
        if(name.contains("LEE")&&name.contains("KAH")&&name.contains("SING")){
            return("LKS!!!!!!!!!!");
        }
        else if(name.contains("RIDWAN")&&name.contains("FAIZ")&&name.contains("MOHAMMAD")&&name.contains("HASSAN")){
            return("RFMH");
        }
        else if(name.contains("SURESH")&&name.contains("SUBRAMANIAM")){
            return ("SS");
        }
        else{
            name = name.replaceAll("[^a-zA-Z0-9]", " ");
            name = name.replaceAll("\\b(BIN |bin |BINTI |binti |A/L |a/l |AL |al |A/P |a/p |AP |ap |VAN |van |VON |von )\\b", "");
            String[] seperate = name.split(" ");
            String initial = "";
            for(int i=0;i<seperate.length;i++){
                initial+=seperate[i].charAt(0);
                
            }
            return initial;
        }
        
    }

    // Determine if the welcome message should be printed
    public static boolean isPrintingWelcomeMessage(String fullName) {
    fullName = fullName.toLowerCase(); // Convert to lowercase for case-insensitive comparison
    if (fullName.equals("kah sing") || fullName.equals("lee kah sing") || fullName.equals("kah sing lee"))
        return true;
    else if (fullName.equals("ridwan") || fullName.equals("ridwan faiz") || fullName.equals("ridwan faiz mohamad hassan"))
        return true;
    else if (fullName.equals("suresh") || fullName.equals("suresh a/l subramaniam"))
        return true;
    else
    return false;
}

    // Calculate time interval between start and end times
    public static void calculateInterval(String startTime, String endTime) {
        String[] startParts = startTime.split(":");
        String[] endParts = endTime.split(":");

        int startHours = Integer.parseInt(startParts[0]);
        int startMinutes = Integer.parseInt(startParts[1]);
        int startSeconds = Integer.parseInt(startParts[2]);

        int endHours = Integer.parseInt(endParts[0]);
        int endMinutes = Integer.parseInt(endParts[1]);
        int endSeconds = Integer.parseInt(endParts[2]);

        // Convert both times to seconds
        int startInSeconds = startHours * 3600 + startMinutes * 60 + startSeconds;
        int endInSeconds = endHours * 3600 + endMinutes * 60 + endSeconds;

        // Handle cases where end time is past midnight
        if (endInSeconds < startInSeconds) {
            endInSeconds += 24 * 3600;
        }

        int interval = endInSeconds - startInSeconds;

        // Calculate hours, minutes, and seconds from the interval
        int hours = interval / 3600;
        int minutes = (interval % 3600) / 60;
        int seconds = interval % 60;

        System.out.println(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input user details
        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter the start time (HH:mm:ss): ");
        String startTime = scanner.nextLine();
        System.out.print("Enter the end time (HH:mm:ss): ");
        String endTime = scanner.nextLine();

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        // Welcome message
        if (isPrintingWelcomeMessage(fullName)) {
            System.out.println("Welcome to G101, Kolej Kediaman Kinabalu, Universiti Malaya!");
        }

        // Initials
        String initials = generateInitials(fullName);
        System.out.println(initials);

        // Special message for "Kah Sing"
        if (fullName.matches("(?i)(kah sing lee|lee kah sing)")) {
            System.out.println("WE KNOW IT’S YOU -- LEE KAH SING!");
        } else if (fullName.toLowerCase().contains("kah sing")) {
            System.out.println("WE KNOW IT’S YOU!");
        }

        // Check if "SLEEP NOW" message should be printed
        if (startTime.compareTo("00:00:00") >= 0 && endTime.compareTo("06:00:00") < 0) {
            System.out.println("SLEEP NOW!!!!!!!!!!");
        }

        // Time interval
        String interval = calculateInterval(startTime, endTime);
        System.out.println(interval);

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
