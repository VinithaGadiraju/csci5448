import java.util.*;
import java.io.*;
import java.util.Scanner;

interface User {
    // Getters 
    String getName();
    String getEmail();
    public boolean validateLogin(String email, String passwd);
}

class Shelter implements java.io.Serializable {
    // Instance Variables 
    String name;
    ShelterAdmin admin;
    HashMap<String, PetSeeker> petSeekers;
    ArrayList<Pet> pets;

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

    /* Matching algorithm 
    public Pet matchPetToPetSeeker(PetSeeker petseeker, Pet pet) {
        // to be implemented 
    }
    */
}

class PetSeeker implements User, java.io.Serializable{
    // Instance variables 
    String name;
    String email;
    String passwd;
    String petPref;
    String agePref;
    String genderPref;
    String residenceType;
    String tempermentPref;
    String breedPref;

    // Constructor 
    public PetSeeker(String name, String email, String passwd) {
        this.name = name;
        this.email = email;
        this.passwd = passwd;
    }
    // Setter functions 
    public void setPetPref(String petpref) {
        this.petPref = petPref;
    }

    public void setAgePref(String agepref) {
        this.agePref = agePref;
    }

    public void setResidenceType(String residencetype) {
        this.residenceType = residenceType;
    }

    public void setTempermentPref(String tempermentpref) {
        this.tempermentPref = tempermentPref;
    }

    public void setBreedPref(String breedpref) {
        this.breedPref = breedPref;
    }

    // Getter functions 
    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPetPref() {
        return this.petPref;
    }

    public String getAgePref() {
        return this.agePref;
    }
    
    public String getResidenceType() {
        return this.residenceType;
    }

    public String getTempermentPref() {
        return this.tempermentPref;
    }

    public String getBreedPref() {
        return this.breedPref;
    }

    public boolean validateLogin(String email, String passwd) {
        return (email.equals(this.email) && passwd.equals(this.passwd));
    }
    /*Profile output function
    public void createProfile() {
        // to be implemented 
    }
    */
}

class ShelterAdmin implements User, java.io.Serializable{
    // Instance variables 
    String name;
    String email;
    String passwd;
    
    // Constructor 
    public ShelterAdmin(String name, String email, String passwd) {
        this.name = name;
        this.email = email;
        this.passwd = passwd;
    }
    
    // Getter functions 
    public String getName() {
        return this.name;
    }
   
    public String getEmail() {
        return this.email;
    }

    /* Profile output function
    public void createProfile() {
        // to be implemented 
    }
    */

    public boolean validateLogin(String email, String passwd) {
        return (email.equals(this.email) && passwd.equals(this.passwd));
    }
}

abstract class Pet implements java.io.Serializable{
    // Instance Variables 
    String name;
    String age;
    String residenceNeed;

    // Setter functions 
    public void setResidenceNeed(String residenceNeed) {
        this.residenceNeed = residenceNeed;
    }
    public void setAge(String age) {
        this.age = age;
    }

    // Getter functions 
    public String getName() {
        return this.name;
    }
    public String getAge() {
        return this.age;
    }
    public String getResidenceNeeds() {
        return this.residenceNeed;
    }
}

class Dog extends Pet implements java.io.Serializable{
    // Instance variables 
    String gender;
    String temperment;

    // Constructor 
    public Dog(String name) {
        this.name = name;
    }

    // Setter functions 
    public void setTemperment(String temperment) {
        this.temperment = temperment;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter functions 
    public String getTemperment() {
        return this.temperment;
    }

    public String toString() {
        return ("Dog - " + this.name);
    }
}

class Snake extends Pet {
    // Instance variables 
    String breed;

    // Constructor 
    public Snake(String name) {
        this.name = name;
    }

    // Setter functions 
    public void setBreed(String breed) {
        this.breed = breed;
    }
 
    // Getter functions 
    public String getBreed() {
        return this.breed;
    }

    public String toString() {
        return ("Snake - " + this.name);
    }
}

public class MyShelterApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome Message 
        System.out.println("Welcome to Pet Matcher! Are you a Pet seeker (Press P) or a Shelter Admin (Press S): ");

        // User selects type (Pet seeker or Shelter admin)
        String userType = scanner.nextLine();

        // Login for User (name, email, password)
        System.out.println("Wonderful! Please enter your name to sign up: ");
        String userName = scanner.nextLine();

        System.out.println("Please enter your email: ");
        String email = scanner.nextLine();

        System.out.println("Please enter your password for this account: ");
        String passwd = scanner.nextLine();

        // if User is Pet seeker 
        if (userType.equals("P")) {
            Shelter shelter = readFrom("shelter_data_file");
            if (shelter == null) {
                System.out.println("Shelter has not signed up yet.");
                return;
            }
            // Enter pet preference (dog vs snake)
            System.out.println("What kind of animal would you like to adopt today? Press 'D' for Dog or 'S' for Snake.");
            String petType = scanner.nextLine();

            // if Dog, enter age preference, gender preference, temperment preference, and residence type
            if (petType.equals("D")) {
                System.out.println("Excellent! Please tell us more about the dog you want.");
                System.out.println("What is your age preference? Press 'P' for Puppy, 'Y' for Young, 'A' for Adult, or 'S' for Senior: ");
                String agePref = scanner.nextLine();
                
                System.out.println("What is your gender preference? Press 'M' for Male or 'F' for Female: ");
                String genderPref = scanner.nextLine();

                System.out.println("What is your temperment preference? Press 'A' for Active, 'L' for Lap Dog, or 'T' for Therapy Dog: ");
                String tempermentPref = scanner.nextLine();

                System.out.println("What kind of residence do you live in? Press 'H' for House, 'A' for Apartment, 'C' for Condo, or 'R' for Ranch: ");
                String residenceType = scanner.nextLine();
            }
            // if Snake, enter age preference, breed preference, and residence type 
            if (petType.equals("S")) {
                System.out.println("Excellent! Please tell us more about the snake you want.");
                System.out.println("What is your age preference? Press 'Y' for Young, 'A' for Adult: ");
                String agePref = scanner.nextLine();
                
                System.out.println("What is your breed preference? Please type the name of the breed: ");
                String breedPref = scanner.nextLine();

                System.out.println("What kind of residence do you live in? Press 'H' for House, 'A' for Apartment, 'C' for Condo, or 'R' for Ranch: ");
                String residenceType = scanner.nextLine();
            }
            System.out.println("Thank you for signing up! Here are some pets available for adoption");

            // Call matching algorithm

            // For now show all pets, in next milestone we will only show pets that match the profile 
            ArrayList al = shelter.getPets();
            for (int i = 0; i < al.size(); i++) {
                System.out.println(al.get(i));
            }
        }
       
        // if User is Shelter Admin 
        if (userType.equals("S")) {
            System.out.println("Please enter the name of the Shelter you work for: ");
            String shelterName = scanner.nextLine();

            // Create shelter object
            Shelter shelter = readFrom("shelter_data_file");

            if (shelter == null) {
                shelter = new Shelter(shelterName);
                ShelterAdmin shelterAdmin = new ShelterAdmin(userName, email, passwd);
                shelter.setAdmin(shelterAdmin);
            }
            // Enter pet type (dog vs snake)
            System.out.println("What kind of animal would you like to add today? Press 'D' for Dog or 'S' for Snake.");
            String petType = scanner.nextLine();

            // if Dog, enter name, age, gender, temperment, and residence need
            if (petType.equals("D")) {
                System.out.println("Excellent! Please tell us more about this dog.");
                System.out.println("What is the name of the dog: ");
                String dogName = scanner.nextLine();

                Dog dog = new Dog(dogName);

                System.out.println("What is the age of the dog? Press 'P' for Puppy, 'Y' for Young, 'A' for Adult, or 'S' for Senior: ");
                String age = scanner.nextLine();
                dog.setAge(age);

                System.out.println("What is the gender of the dog? Press 'M' for Male or 'F' for Female: ");
                String gender = scanner.nextLine();
                dog.setGender(gender);

                System.out.println("What is the temperment of the dog? Press 'A' for Active, 'L' for Lap Dog, or 'T' for Therapy Dog: ");
                String temperment = scanner.nextLine();
                dog.setTemperment(temperment);

                System.out.println("What kind of residence does the dog need? Press 'H' for House, 'A' for Apartment, 'C' for Condo, or 'R' for Ranch: ");
                String residenceNeed = scanner.nextLine();
                dog.setResidenceNeed(residenceNeed);

                shelter.addPet(dog);
            }
            // if Snake, enter age preference, breed preference, and residence type 
            if (petType.equals("S")) {
                System.out.println("Excellent! Please tell us more about this snake.");
                System.out.println("What is the name of the snake: ");
                String snakeName = scanner.nextLine();

                Snake snake = new Snake(snakeName);

                System.out.println("What is the age of the snake? Press 'Y' for Young, 'A' for Adult: ");
                String age = scanner.nextLine();
                snake.setAge(age);

                System.out.println("What is the breed of the snake? Please type the name of the breed: ");
                String breed = scanner.nextLine();
                snake.setBreed(breed);

                System.out.println("What kind of residence does the snake need? Press 'H' for House, 'A' for Apartment, 'C' for Condo, or 'R' for Ranch: ");
                String residenceNeed = scanner.nextLine();
                snake.setResidenceNeed(residenceNeed);

                shelter.addPet(snake);
            }
            // Save shelter to external file before exiting the program 
            writeTo(shelter, "shelter_data_file");
            System.out.println("Thank you for the information! This pet has been added to the pet pool.");
        }

        // Still need to implement: creating objects using collected data, showing pets to users, matching, and messaging

        

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



