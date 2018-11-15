import java.util.*;
import java.io.*;
import java.util.Scanner;

class Shelter implements java.io.Serializable {
    // Instance Variables
    private String name;
    private ShelterAdmin admin;
    private HashMap<String, PetSeeker> petSeekers;
    private ArrayList<Pet> pets;

    // Constructor
    public Shelter (String name) {
        this.name = name;
        this.petSeekers = new HashMap<String, PetSeeker>();
        this.pets = new ArrayList<Pet>();
    }

    // Class functions
    public void addPet(Pet pet) {
        this.pets.add(pet);
    }

    public String getName() {
        return this.name;
    }

    public void addPetSeeker(String email, PetSeeker petseeker) {
        this.petSeekers.put(email, petseeker);
    }

    // Setter functions
    public void setAdmin(ShelterAdmin admin) {
        this.admin = admin;
    }

    // Getter functions
    public ArrayList<Pet> getPets() {
        return this.pets;
    }

    public ShelterAdmin getAdmin() {
        return this.admin;
    }

    public PetSeeker getPetSeeker(String email) {
        return this.petSeekers.get(email);
    }

    public ArrayList<Pet> matchPetsToPetSeeker(PetSeeker petseeker) {
        // return this.pets;
        ArrayList<Pet> pet_list = new ArrayList<Pet>();
        for (int i = 0; i < this.pets.size(); i++) {
            Pet pet = this.pets.get(i);

            Integer matchCriteriaCount = pet.getMatchCriteriaCount();
            Integer matchPercent = petseeker.getMatchPercentPref();
            Integer matchCount = pet.getMatchCount(petseeker);

            if (((matchCount*100)/matchCriteriaCount) >= matchPercent) {
                pet_list.add(pet);
            }
        }

        return pet_list;
    }
}

interface User {
    // Getters
    String getName();
    String getEmail();
    boolean validateLogin(String email, String passwd);
}

class PetSeeker implements User, java.io.Serializable{
    // Instance variables
    private String name;
    private String email;
    private String passwd;
    private String petTypePref;
    private String agePref;
    private String genderPref;
    private String residenceTypePref;
    private String tempermentPref;
    private String breedPref;
    private String sizePref;
    private Integer matchPercentPref;

    // Constructor
    public PetSeeker(String name, String email, String passwd) {
        this.name = name;
        this.email = email;
        this.passwd = passwd;
    }
    // Setter functions
    public void setPetTypePref(String petTypePref) {
        this.petTypePref = petTypePref;
    }
    public String getPetTypePref() {
        return this.petTypePref;
    }

    public void setAgePref(String agepref) {
        this.agePref = agepref;
    }
    public String getAgePref() {
        return this.agePref;
    }

    public void setResidenceTypePref(String residencetype) {
        this.residenceTypePref = residencetype;
    }
    public String getResidenceTypePref() {
        return this.residenceTypePref;
    }

    public void setTempermentPref(String tempermentpref) {
        this.tempermentPref = tempermentpref;
    }
    public String getTempermentPref() {
        return this.tempermentPref;
    }

    public void setBreedPref(String breedpref) {
        this.breedPref = breedpref;
    }
    public String getBreedPref() {
        return this.breedPref;
    }

    public Integer getMatchPercentPref() {
        return this.matchPercentPref;
    }
    public void setMatchPercentPref(Integer matchPercent) {
        this.matchPercentPref = matchPercent;
    }

    public void setGenderPref(String genderPref) {
        this.genderPref = genderPref;
    }
    public String getGenderPref() {
        return this.genderPref;
    }

    public String getSizePref() {
        return this.sizePref;
    }
    public void setSizePref(String sizepref) {
        this.sizePref = sizepref;
    }

    // Interface User
    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean validateLogin(String email, String passwd) {
        return (email.equals(this.email) && passwd.equals(this.passwd));
    }

    public void createProfile() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Excellent! Please tell us more about the " + this.petTypePref + " you want.");

        // if Dog, enter age preference, gender preference, temperment preference, and residence type
        if (this.petTypePref.equals("Dog")) {
            System.out.println("What is your age preference? ");
            System.out.print("Press 'P' for Puppy, 'Y' for Young, 'A' for Adult, or 'S' for Senior: ");
            this.setAgePref(scanner.nextLine());

            System.out.println("What is your gender preference?");
            System.out.print("Press 'M' for Male or 'F' for Female: ");
            this.setGenderPref(scanner.nextLine());

            System.out.println("What is your temperment preference? ");
            System.out.print("Press 'A' for Active, 'L' for Lap Dog, or 'T' for Therapy Dog: ");
            this.setTempermentPref(scanner.nextLine());

            System.out.println("What kind of residence do you live in?");
            System.out.print("Press 'H' for House, 'A' for Apartment, 'C' for Condo, or 'R' for Ranch: ");
            this.setResidenceTypePref(scanner.nextLine());
        }

        // if Snake, enter age preference, breed preference, and residence type
        if (this.petTypePref.equals("Snake")) {
            System.out.println("What is your age preference?");
            System.out.print("Press 'Y' for Young, 'A' for Adult: ");
            this.setAgePref(scanner.nextLine());

            System.out.println("What is your breed preference?");
            System.out.print("Please enter the name of the breed: ");
            this.setBreedPref(scanner.nextLine());

            System.out.println("What is the size preference?");
            System.out.print("Press 'S' for Small(< 1ft), 'M' for Medium(< 3 ft), 'L' for Large(>3 ft): ");
            this.setSizePref(scanner.nextLine());

            System.out.println("What kind of residence do you live in?");
            System.out.print("Press 'H' for House, 'A' for Apartment, 'C' for Condo, or 'R' for Ranch: ");
            this.setResidenceTypePref(scanner.nextLine());
        }
    }
}

class ShelterAdmin implements User, java.io.Serializable{
    // Instance variables
    private String name;
    private String email;
    private String passwd;

    // Constructor
    public ShelterAdmin(String name, String email, String passwd) {
        this.name = name;
        this.email = email;
        this.passwd = passwd;
    }

    // Interface User
    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean validateLogin(String email, String passwd) {
        return (email.equals(this.email) && passwd.equals(this.passwd));
    }
}

abstract class Pet implements java.io.Serializable{
    // Instance Variables
    protected String name;
    protected String age;
    protected String residenceNeed;

    public String getName() {
        return this.name;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public String getAge() {
        return this.age;
    }
    public void setResidenceNeed(String residenceNeed) {
        this.residenceNeed = residenceNeed;
    }
    public String getResidenceNeed() {
        return this.residenceNeed;
    }
    public abstract Integer getMatchCriteriaCount();
    public abstract void createProfile();
    public abstract Integer getMatchCount(PetSeeker p);
}

class Dog extends Pet implements java.io.Serializable{
    // Instance variables
    private String gender;
    private String temperment;

    // Constructor
    public Dog(String name) {
        this.name = name;
    }

    public String getPetType() {
        return "Dog";
    }

    public void setTemperment(String temperment) {
        this.temperment = temperment;
    }
    public String getTemperment() {
        return this.temperment;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return this.gender;
    }

    public Integer getMatchCriteriaCount() {
        /* Count of (age, gender, residenceNeed, temperment) is 4  */
        return 4;
    }
    public String toString() {
        return ("Dog: " + this.getName() + ", Gender: " + this.getGender() + ", Age: " + this.getAge());
    }

    public void createProfile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the age of the dog?");
        System.out.print("Press 'P' for Puppy, 'Y' for Young, 'A' for Adult, or 'S' for Senior: ");
        this.setAge(scanner.nextLine());

        System.out.println("What is the gender of the dog?");
        System.out.print("Press 'M' for Male or 'F' for Female: ");
        this.setGender(scanner.nextLine());

        System.out.println("What is the temperment of the dog?");
        System.out.print("Press 'A' for Active, 'L' for Lap Dog, or 'T' for Therapy Dog: ");
        this.setTemperment(scanner.nextLine());

        System.out.println("What kind of residence does the dog need?");
        System.out.print("Press 'H' for House, 'A' for Apartment, 'C' for Condo, or 'R' for Ranch: ");
        this.setResidenceNeed(scanner.nextLine());
    }

    public Integer getMatchCount(PetSeeker petseeker) {
        Integer matchCount = 0;

        if (petseeker.getPetTypePref().equals("Dog")) {
            if (petseeker.getAgePref().equals(this.getAge())) {
                matchCount++;
            }
            if (petseeker.getResidenceTypePref().equals(this.getResidenceNeed())) {
                matchCount++;
            }
            if (petseeker.getGenderPref().equals(this.getGender())) {
                matchCount++;
            }
            if (petseeker.getTempermentPref().equals(this.getTemperment())) {
                matchCount++;
            }
        }

        return matchCount;
    }
}

class Snake extends Pet {
    // Instance variables
    private String breed;
    private String size;

    // Constructor
    public Snake(String name) {
        this.name = name;
    }

    public String getPetType() {
        return "Snake";
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
    public String getBreed() {
        return this.breed;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return this.size;
    }

    public String toString() {
        return ("Snake: " + this.getName() + ", Breed: " + this.getBreed() + ", Age: " + this.getAge());
    }
    public Integer getMatchCriteriaCount() {
        /* Count of (age, size, residenceNeed, breed) is 4 */
        return 4;
    }
    public void createProfile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the age of the snake?");
        System.out.print("Press 'Y' for Young, 'A' for Adult: ");
        this.setAge(scanner.nextLine());

        System.out.println("What is the size of the snake?");
        System.out.print("Press 'S' for Small(< 1ft), 'M' for Medium(< 3 ft), 'L' for Large(>3 ft): ");
        this.setSize(scanner.nextLine());

        System.out.println("What is the breed of the snake?");
        System.out.print("Please type the name of the breed: ");
        this.setBreed(scanner.nextLine());

        System.out.println("What kind of residence does the snake need?");
        System.out.print("Press 'H' for House, 'A' for Apartment, 'C' for Condo, or 'R' for Ranch: ");
        this.setResidenceNeed(scanner.nextLine());
    }

    public Integer getMatchCount(PetSeeker petseeker) {
        Integer matchCount = 0;

        if (petseeker.getPetTypePref().equals("Snake")) {
            if (petseeker.getAgePref().equals(this.getAge())) {
                matchCount++;
            }
            if (petseeker.getResidenceTypePref().equals(this.getResidenceNeed())) {
                matchCount++;
            }
            if (petseeker.getBreedPref().equals(this.getBreed())) {
                matchCount++;
            }
            if (petseeker.getSizePref().equals(this.getSize())) {
                matchCount++;
            }
        }

        return matchCount;
    }
}

public class MyShelterApp {
    public static void main(String[] args) {
        // Read Shelter data from external file if the external file exists
        Shelter shelter = readFrom("shelter_data_file");

        Scanner scanner = new Scanner(System.in);

        // Welcome Message
        System.out.println("Welcome to Pet Matcher! ");
        System.out.print("Are you a Pet Seeker (Press P) or a Shelter Admin (Press S): ");
        // User selects type (Pet seeker or Shelter admin)
        String userType = scanner.nextLine();

        // Get User Credentials
        System.out.print("Please enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Please enter your password for this account: ");
        String passwd = scanner.nextLine();

        // if User is Pet seeker
        if (userType.toUpperCase().equals("P")) {
            if (shelter == null) {
                System.out.println("Shelter has not signed up yet.  Please visit again later.  Thank you!");
                return;
            }
            
            System.out.println("*** Welcome to " + shelter.getName() + " ***");
            // Get PetSeeker object from Database
            PetSeeker p = shelter.getPetSeeker(email);
            if (p != null) {
                if (!p.validateLogin(email, passwd)) {
                    System.out.println("Invalid Password, Bye, Bye !!!");
                    return;
                }
            } else {
                System.out.println("This Pet Seeker is not present in the system, let's Register");
                System.out.print("Enter Your Name: ");
                String name = scanner.nextLine();
                p = new PetSeeker(name, email, passwd);
                shelter.addPetSeeker(email, p);

                // Enter pet preference (dog vs snake)
                System.out.println("What kind of animal would you like to adopt today: ");
                System.out.print("Press 'D' for Dog or 'S' for Snake.");
                String petType = scanner.nextLine();
                if (petType.charAt(0) == 'S' || petType.charAt(0) == 's') {
                    p.setPetTypePref("Snake");
                } else {
                    p.setPetTypePref("Dog"); // Default Pet Type.
                }
                p.createProfile();
            }

            System.out.println("Enter your match percentage of your preferences");
            System.out.print("Enter percentage as Integer (no percent symbol): ");
            p.setMatchPercentPref(Integer.parseInt(scanner.nextLine()));

            ArrayList<Pet> matching_pets = shelter.matchPetsToPetSeeker(p);

            System.out.println("Here are some pets that match your preferences");
            for (int i = 0; i < matching_pets.size(); i++) {
                System.out.println(matching_pets.get(i));
            }
        }

        // if User is Shelter Admin
        if (userType.toUpperCase().equals("S")) {
            ShelterAdmin shelterAdmin = null;
            if (shelter == null) {
                System.out.print("You are not registered yet! Please enter your Name: ");
                String adminName = scanner.nextLine();
                shelterAdmin = new ShelterAdmin(adminName, email, passwd);

                System.out.print("Please enter the name of the Shelter you work for: ");
                String shelterName = scanner.nextLine();
                shelter = new Shelter(shelterName);

                shelter.setAdmin(shelterAdmin);
            } else {
                // At this point we support only 1 Shelter and 1 ShelterAdmin.
                if (!shelterAdmin.validateLogin(email, passwd)) {
                    System.out.println("Invalid Login, Bye, Bye !!!");
                    return;
                }
            }

            while (true) {
                // Enter pet type (dog vs snake)
                System.out.println("What kind of Pet would you like to add today?");
                System.out.print("Press 'D' for Dog or 'S' for Snake: ");
                String petType = scanner.nextLine();

                if (petType.toUpperCase().equals("D")) {
                    System.out.print("Excellent! What is the name of this dog: ");
                    String dogName = scanner.nextLine();

                    Dog dog = new Dog(dogName);
                    dog.createProfile();

                    shelter.addPet(dog);
                }

                if (petType.toUpperCase().equals("S")) {
                    System.out.print("Excellent! What is the name of this snake: ");
                    String snakeName = scanner.nextLine();

                    Snake snake = new Snake(snakeName);
                    snake.createProfile();

                    shelter.addPet(snake);
                }

                System.out.print("Do you have more pets to add? Y/N: ");
                String answer = scanner.nextLine();
                if (answer.charAt(0) == 'n' || answer.charAt(0) == 'N') {
                    break;
                }
            }

            // Save shelter to external file before exiting the program
            System.out.println("Thank you for the information! The pets you entered have been added to the pet pool.");
        }
        writeTo(shelter, "shelter_data_file");
    }

    static Shelter readFrom(String data_file) {
        Shelter shelter = null;
        try {
            FileInputStream fileIn = new FileInputStream(data_file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            shelter = (Shelter) in.readObject();
            in.close();
            fileIn.close();
        }
        catch (IOException e) {
            return null;
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return shelter;
    }

    static void writeTo(Shelter shelter, String data_file) {
        try {
            FileOutputStream fileOut = new FileOutputStream(data_file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(shelter);
            out.close();
            fileOut.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}


