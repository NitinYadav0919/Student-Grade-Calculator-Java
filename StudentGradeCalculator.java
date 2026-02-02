import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Student Grade Calculator
 * 
 * Purpose:
 * This program calculates a student's grade based on multiple subject scores.
 * It takes user input for student name and scores, calculates the average,
 * determines the letter grade, and displays all information.
 * 
 * Grade Calculation Logic:
 * - Average >= 90: Grade A
 * - Average >= 80: Grade B
 * - Average >= 70: Grade C
 * - Average >= 60: Grade D
 * - Average < 60: Grade F
 * 
 * Sample Input/Output:
 * Input: Name="John Doe", Scores=[85, 92, 78, 88]
 * Output: Average=85.75, Grade=B
 */

/**
 * Student class represents a student with name, scores, and calculated grade.
 */
class Student {
    // Attributes
    private String name;
    private List<Double> scores;
    private double average;
    private char grade;
    
    /**
     * Constructor to initialize student attributes.
     * @param name The student's name
     */
    public Student(String name) {
        this.name = name;
        this.scores = new ArrayList<>();
        this.average = 0.0;
        this.grade = 'N'; // N for Not calculated yet
    }
    
    /**
     * Adds a score to the student's score list.
     * @param score The score to add
     */
    public void addScore(double score) {
        scores.add(score);
    }
    
    /**
     * Calculates the average score from all entered scores.
     * @return The average score, or -1 if no scores are entered
     */
    public double calculateAverage() {
        if (scores.isEmpty()) {
            System.out.println("Error: No scores entered. Cannot calculate average.");
            return -1;
        }
        
        double sum = 0;
        for (double score : scores) {
            sum += score;
        }
        average = sum / scores.size();
        return average;
    }
    
    /**
     * Determines the letter grade based on the average score.
     * @return The letter grade (A, B, C, D, or F)
     */
    public char determineGrade() {
        if (average >= 90) {
            grade = 'A';
        } else if (average >= 80) {
            grade = 'B';
        } else if (average >= 70) {
            grade = 'C';
        } else if (average >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        return grade;
    }
    
    /**
     * Displays the student's name.
     */
    public void displayName() {
        System.out.println("Student Name: " + name);
    }
    
    /**
     * Displays all the student's scores.
     */
    public void displayScores() {
        System.out.print("Scores: ");
        if (scores.isEmpty()) {
            System.out.println("No scores entered.");
        } else {
            for (int i = 0; i < scores.size(); i++) {
                System.out.print(scores.get(i));
                if (i < scores.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
    
    /**
     * Displays the average score.
     */
    public void displayAverage() {
        if (average < 0) {
            System.out.println("Average: Not calculated (no scores entered)");
        } else {
            System.out.printf("Average: %.2f%n", average);
        }
    }
    
    /**
     * Displays the letter grade.
     */
    public void displayGrade() {
        if (grade == 'N') {
            System.out.println("Grade: Not calculated yet");
        } else {
            System.out.println("Grade: " + grade);
        }
    }
    
    /**
     * Displays all student information in a formatted manner.
     */
    public void displayAllInfo() {
        System.out.println("\n========================================");
        System.out.println("         STUDENT GRADE REPORT");
        System.out.println("========================================");
        displayName();
        displayScores();
        displayAverage();
        displayGrade();
        System.out.println("========================================\n");
    }
    
    // Getter methods
    public String getName() {
        return name;
    }
    
    public List<Double> getScores() {
        return scores;
    }
    
    public double getAverage() {
        return average;
    }
    
    public char getGrade() {
        return grade;
    }
}

/**
 * Main class to run the Student Grade Calculator program.
 */
public class StudentGradeCalculator {
    
    /**
     * Validates if a score is within the valid range (0-100).
     * @param score The score to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidScore(double score) {
        return score >= 0 && score <= 100;
    }
    
    /**
     * Main method - entry point of the program.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("========================================");
        System.out.println("    STUDENT GRADE CALCULATOR");
        System.out.println("========================================\n");
        
        // Get student name
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        
        // Validate name
        if (name.isEmpty()) {
            System.out.println("Error: Name cannot be empty. Exiting program.");
            scanner.close();
            return;
        }
        
        // Create Student object
        Student student = new Student(name);
        
        // Get number of subjects/scores
        int numSubjects = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.print("Enter number of subjects: ");
                numSubjects = Integer.parseInt(scanner.nextLine());
                
                if (numSubjects <= 0) {
                    System.out.println("Error: Number of subjects must be greater than 0.");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer for number of subjects.");
            }
        }
        
        // Get scores for each subject
        System.out.println("\nEnter scores for " + numSubjects + " subjects (0-100):");
        
        for (int i = 1; i <= numSubjects; i++) {
            validInput = false;
            
            while (!validInput) {
                try {
                    System.out.print("Subject " + i + " score: ");
                    double score = Double.parseDouble(scanner.nextLine());
                    
                    if (!isValidScore(score)) {
                        System.out.println("Error: Score must be between 0 and 100. Please try again.");
                    } else {
                        student.addScore(score);
                        validInput = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter a valid numeric score.");
                }
            }
        }
        
        // Calculate average and determine grade
        double average = student.calculateAverage();
        
        if (average >= 0) {
            student.determineGrade();
            
            // Display all information
            student.displayAllInfo();
            
            // Additional feedback based on grade
            char grade = student.getGrade();
            System.out.print("Feedback: ");
            switch (grade) {
                case 'A':
                    System.out.println("Excellent work! Keep it up!");
                    break;
                case 'B':
                    System.out.println("Good job! Room for improvement.");
                    break;
                case 'C':
                    System.out.println("Satisfactory. Consider studying more.");
                    break;
                case 'D':
                    System.out.println("Below average. Need significant improvement.");
                    break;
                case 'F':
                    System.out.println("Failed. Please seek help and retake the course.");
                    break;
            }
        } else {
            System.out.println("\nError: Could not calculate grade due to missing scores.");
        }
        
        // Testing section - demonstrate with preset scores
        System.out.println("\n========================================");
        System.out.println("    TESTING WITH SAMPLE DATA");
        System.out.println("========================================");
        
        // Test Case 1: High scores
        System.out.println("\n--- Test Case 1: High Scores ---");
        Student testStudent1 = new Student("Alice (Test)");
        testStudent1.addScore(95);
        testStudent1.addScore(92);
        testStudent1.addScore(88);
        testStudent1.calculateAverage();
        testStudent1.determineGrade();
        testStudent1.displayAllInfo();
        
        // Test Case 2: Borderline scores
        System.out.println("\n--- Test Case 2: Borderline Scores ---");
        Student testStudent2 = new Student("Bob (Test)");
        testStudent2.addScore(79);
        testStudent2.addScore(80);
        testStudent2.addScore(81);
        testStudent2.calculateAverage();
        testStudent2.determineGrade();
        testStudent2.displayAllInfo();
        
        // Test Case 3: Low scores
        System.out.println("\n--- Test Case 3: Low Scores ---");
        Student testStudent3 = new Student("Charlie (Test)");
        testStudent3.addScore(45);
        testStudent3.addScore(52);
        testStudent3.addScore(58);
        testStudent3.calculateAverage();
        testStudent3.determineGrade();
        testStudent3.displayAllInfo();
        
        // Test Case 4: Edge case - exactly 60 (passing threshold)
        System.out.println("\n--- Test Case 4: Exactly 60 (Passing Threshold) ---");
        Student testStudent4 = new Student("David (Test)");
        testStudent4.addScore(60);
        testStudent4.addScore(60);
        testStudent4.addScore(60);
        testStudent4.calculateAverage();
        testStudent4.determineGrade();
        testStudent4.displayAllInfo();
        
        // Test Case 5: Edge case - empty scores (error handling)
        System.out.println("\n--- Test Case 5: No Scores (Error Handling) ---");
        Student testStudent5 = new Student("Eve (Test)");
        testStudent5.calculateAverage();
        testStudent5.displayAllInfo();
        
        System.out.println("\n========================================");
        System.out.println("    PROGRAM COMPLETED SUCCESSFULLY");
        System.out.println("========================================");
        
        scanner.close();
    }
}
