public class Main {
    public static void main(String[] args) {
        PolynomialSolver myPoly=new PolynomialSolver();
        try {
            myPoly.menu();
        }
        catch(Exception e) {
            System.out.println("Error");
        }
    }
}