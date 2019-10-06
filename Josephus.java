/*
      THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
      CODE WRITTEN BY OTHER STUDENTS. Deidra Papakostas
*/
public class Josephus{
	
        public static void main(String[] args){
                //Example 1 with 9-0 numbers and 6 remains
                CircularList cl = new CircularList();
                for(int i=0; i<10; i++){
                        cl.insert(new ListElem(i));
                }
                int m =3; 
                cl.printList();
                System.out.println("result: " + solve(cl,3));

                //Example 2 with 6-0 numbers and 3 remains
                System.out.println("CircularList 2: ");

                CircularList cl2 = new CircularList();
                for(int i=0; i<7; i++){
                        cl2.insert(new ListElem(i));
                }
                int n =3; 
                cl2.printList();
                System.out.println("size: " + cl2.getSize());
                System.out.println("result: " + solve(cl2,3));

                //Example 3 with 3-0 numbers and 3 remains
                System.out.println("CircularList 3: ");

                CircularList cl3 = new CircularList();
                for(int i=0; i<4; i++){
                        cl3.insert(new ListElem(i));
                }
                int o =2; 
                cl3.printList();
                System.out.println("result: " + solve(cl3,2));

                //Example 4 with 1-15 numbers and 15 remains
                System.out.println("CircularList 4: ");

                CircularList cl4 = new CircularList();
                for(int i=15; i>0; i--){
                        cl4.insert(new ListElem(i));
                }
                int p =2; 
                cl4.printList();
                System.out.println("size: " + cl4.getSize());
                System.out.println("result: " + solve(cl4,2));



        }

	public static int solve(CircularList cl, int m){
                while(cl.getSize() > 1){ //ensures only 1 element will remain
                        for(int i = 0; i < m - 1; i++){
                                cl.rotate(); //rotates first element while < m-1

                        }
                        cl.remove(); //removes element on m
                }
                return cl.remove(); //will return final element remaining
	}

}
