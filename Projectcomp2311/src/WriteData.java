import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;


public class WriteData{
 public static void main(String[] args) throws Exception {
	  ArrayList<Family> families = new ArrayList<>();
	 java.io.File file = new File("Filee.txt");
if (file.exists()) {
System.out.println("File already exists");
 }
try (PrintWriter output = new PrintWriter(file); )
{
	 for (Family family : families) {
         output.write("Family Name: " + family.getFamilyName());
         output.println();

         // Writing Members
         output.println("Members:");
         for (Person member : family.getMembers()) {
             output.println("- " + member.toString());
         }

         // Writing Parents
         output.println("Parents:");
         for (Person parent : family.getParents()) {
             output.println("- " + parent.toString());
         }

         output.println(); // Add a blank line between families
     }
}
}
}