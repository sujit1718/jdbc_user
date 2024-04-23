package jdbc_user;

import java.util.Scanner;

public class UserMain {
	
   public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		
        while(true) {
		System.out.println("Welcome!!! \n1.SignUp \n2.LogIn \n3.Exit");
		int choise = scanner.nextInt();
		if(choise == 3) {
			System.out.println("Thank for Visit!");
			return;
		}
		UserCRUD crud = new UserCRUD();
		User user = new User();
		switch (choise) {
		case 1: {
			System.out.println("Enter the Id : ");
			int id = scanner.nextInt();
			System.out.println("Enter the Name : ");
			String name = scanner.next();
			System.out.println("Enter the Phone Number : ");
			long phone = scanner.nextLong();
			System.out.println("Enter the Email : ");
			String email = scanner.next();
			System.out.println("Enter the Password : ");
			String password = scanner.next();
			
			
			user.setId(id);
			user.setEmail(email);
			user.setName(name);
			user.setPhone(phone);
			user.setPassword(password);
			user.setInstagram(null);
			user.setFacebook(null);
			user.setTwitter(null);
			user.setSnapchat(null);
			int result = crud.signUpUser(user);
			if (result!=0) {
				System.out.println("SignUp SuccessFul");
			} else {
                 System.out.println("SignUp not SuccessFul");
			}
			break;
        }
		case 2:{
			System.out.println("Enter the email : ");
			String email = scanner.next();
			System.out.println("Enter the password : ");
			String password = scanner.next();
			String dbpassword = crud.loginUser(email);
			if(dbpassword!=null) {
				if(dbpassword.equals(password)) {
					System.out.println("Login success! \n");
					System.out.println("Enter the choise : ");
					System.out.println("1. Display Passwords");
					System.out.println("2. Update Passwords");
					System.out.println("3. Logout");
					System.out.println("Enter your choise");
					int num = scanner.nextInt();
					switch(num) {
					case 1:{
						user = crud.displayPasswords(email);
						System.out.println("Instagram : "+user.getInstagram());
						System.out.println("Fcebook : "+user.getFacebook());
						System.out.println("Twitter : "+user.getTwitter());
						System.out.println("Snapchat : "+user.getSnapchat());
						break;
					}
					case 2:{
						System.out.println("Enter the Instagram password : ");
						String instagram = scanner.next();
						System.out.println("Enter the Facebook password : ");
						String facebook = scanner.next();
						System.out.println("Enter the Twitter password : ");
						String twitter = scanner.next();
						System.out.println("Enter the Snapchat password : ");
						String snapchat = scanner.next();
						
						user = new User();
						user.setInstagram(instagram);
						user.setFacebook(facebook);
						user.setTwitter(twitter);
						user.setSnapchat(snapchat);
						int result = crud.updatePasswords(user, email);
						if(result!=0) {
							System.out.println("Password Updated!");
						}
						else {
							System.out.println("Passwords not Updated!");
						}
						
						break;
					}
					case 3:{
						System.out.println("You have been logged out!");
						break;
					}
					default:{
						System.out.println("Wrong Input!");
						break;
					}
					}
				 }	
				 else {
						System.out.println("Invalid password!");
				 }
				}
				else {
						System.out.println("Person not registered with the given mail : "+email);
				}
				break;
		}
      }
    }
  }
}