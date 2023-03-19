
import java.util.*;

public class PolynomialSolver  {

    public static Node  head,tail,count1,count2;
    public static int size;
    static int c=0;
    String deleted="";

    public boolean check(char poly){
        for(int i=0;i<deleted.length();i++){
            if(poly==deleted.charAt(i)){
                return false;
            }
        }
        return true;
    }

    public  void menu () {
        Scanner cin = new Scanner(System.in);
        while(cin.hasNextLine()){
            String in = cin.nextLine();
            int [][]arr=new int [100][2];
            switch(in){
                case "set" :
                    char poly = cin.next().charAt(0);
                    cin.nextLine();
                    String sin = cin.nextLine().replaceAll("\\[|\\]", "");
                    String[] s = sin.split(",");
                    int[] coeff = new int[s.length];
                    if (s.length == 1 && s[0].isEmpty())
                        coeff = new int[]{};
                    else {
                        for (int i = 0; i < s.length; ++i)
                            coeff[i] = Integer.parseInt(s[i]);
                    }
                    int deg = s.length;
                    int exponent = deg - 1;
                    int [][] terms = new int[coeff.length][2];
                    for (int i = 0; i < deg; i++) {
                        terms[i][0] = coeff[i];
                        terms[i][1] = exponent;
                        exponent--;
                    }
                    setPolynomial(poly,terms,false);
                    break;
                case "print":
                    char p=cin.next().charAt(0);
                    if(check(p)){
                        print(p);
                    }

                    break;
                case "clear":
                    char del=cin.next().charAt(0);
                    if(check(del)){
                        clear(del);
                    }
                    else{
                        System.out.println("Error");
                    }
                    break;
                case "add":
                    char p1=cin.next().charAt(0);
                    char p2=cin.next().charAt(0);
                    if(check(p1)&&check(p2)){
                        arr=sum(p1,p2);
                        setPolynomial('R',arr,true);
                        print('R');
                    }
                    else{
                        System.out.println("Error");
                    }
                    break;
                case "eval":
                    char e=cin.next().charAt(0);
                    float d=cin.nextFloat();
                    if(check(e)){
                        System.out.println( evaluatePolynomial(e,d));
                    }
                    else{
                        System.out.println("Error");
                    }
                    break;

                case "sub":
                    char s1=cin.next().charAt(0);
                    char s2=cin.next().charAt(0);
                    if(check(s1)&&check(s2)){
                        int [][] diff =subtract(s1,s2);
                        setPolynomial('R',diff,true);
                        print('R');
                    }
                    else{
                        System.out.println("Error");
                    }
                    break;

                case "mult":
                    char m1=cin.next().charAt(0);
                    char m2=cin.next().charAt(0);
                    int [][]product=new int[100][2];
                    if(check(m1)&&check(m2)){
                        product=multiply(m1,m2);
                        int exp=multiply(m1,m2).length;
                        int cont=exp-1;
                        for(int i=0;i<exp;i++){
                            product[i][1]=cont;
                            cont--;
                        }
                        setPolynomial('M',product,true);
                        print('M');
                    }
                    else{
                        System.out.println("Error");
                    }
                    break;

                default:
                    if(!(cin.hasNextLine()))
                    {
                        System.out.println("Error");
                        System.exit(0);
                    }
                    break;
            }
        }

    }
    public void  setPolynomial(char poly, int[][] terms,boolean flag){
        for(int i=0;i<terms.length;i++) {
            Node aux=new Node(terms[i][0],terms[i][1],poly,size);
            add(aux);
        }
        if(!flag) menu();
    }
    public void add (Node element){
        Node node = new Node(element.coeff,element.exponent,element.poly,element.size);
        if(size==0){
            head=element;
            tail=element;
        }
        else{
            tail.next=element;
            tail=element;
        }
        size++;
    }
    public int evaluatePolynomial(char poly, float value){
        Node temp = head ;
        int answer=0;
        while(temp.poly!=poly) temp = temp.next;

        while(temp!=null && temp.poly==poly )
        {
            answer+=temp.coeff*Math.pow(value, temp.exponent);
            temp=temp.next;

        }
        return answer;
    }
    public int[][] subtract(char poly1, char poly2){
        Node sub1=head;//global
        Node sub2=head;
        Node sub3=head;
        Node sub4=head;
        int diff=0,c=0;
        char ref;
        int [][] difference=new int[100][2];
        while(sub1!=null&&sub1.poly!=poly1){
            sub1=sub1.next;
        }
        sub3=sub1;
        while(sub3!=null&&sub3.poly==poly1){
            sub3=sub3.next;
        }
        while(sub2!=null&&sub2.poly!=poly2){
            sub2=sub2.next;
        }
        sub4=sub2;
        while(sub4!=null&&sub4.poly==poly2){
            sub4=sub4.next;
        }
        if(sub1.exponent==sub2.exponent){
            while(sub1!=sub3 && sub2!=sub4){
                diff=sub1.coeff-sub2.coeff;
                difference[c][0]=diff;//counter global
                difference[c][1]=sub1.exponent;
                sub1=sub1.next;
                sub2=sub2.next;
                c++;
            }
        }
        return difference;
    }
    public int [][]sum(char poly1,char poly2){
        Node add1=head;//global
        Node add2=head;
        Node add3=head;
        Node add4=head;
        int sum=0;
        char ref;
        int [][] summation=new int[100][2];
        while(add1!=null&&add1.poly!=poly1){
            add1=add1.next;
        }
        add3=add1;
        while(add3!=null&&add3.poly==poly1){
            add3=add3.next;
        }
        while(add2!=null&&add2.poly!=poly2){
            add2=add2.next;
        }
        add4=add2;
        while(add4!=null&&add4.poly==poly2){
            add4=add4.next;
        }
        if(add1.exponent==add2.exponent){
            while(add1!=add3 && add2!=add4){
                sum=add1.coeff+add2.coeff;
                summation[c][0]=sum;//counter global
                summation[c][1]=add1.exponent;
                add1=add1.next;
                add2=add2.next;
                c++;
            }
        }
        return summation;
    }
    public int[][] multiply(char poly1,char poly2){
        Node mult1=head;//global
        Node mult2=head;
        Node mult3=head;
        Node mult4=head;
        int count3=0,count4=0,size1,size2;
        while(mult1!=null&&mult1.poly!=poly1){
            mult1=mult1.next;
        }
        mult3=mult1;
        while(mult3!=null&&mult3.poly==poly1){
            mult3=mult3.next;
            count3++;
        }
        size1=count3;
        while(mult2!=null&&mult2.poly!=poly2){
            mult2=mult2.next;
        }
        mult4=mult2;
        while(mult4!=null&&mult4.poly==poly2){
            mult4=mult4.next;
            count4++;
        }
        size2=count4;
        int arr1[]=new int [size1];
        int arr2[]=new int [size2];
        int z1=0,z2=0;
        while(mult1!=mult3){
            arr1[z1]=mult1.coeff;
            z1++;
            mult1=mult1.next;
        }
        while(mult2!=mult4){
            arr2[z2]=mult2.coeff;
            z2++;
            mult2=mult2.next;
        }
        int [][] pro=new int[size1+size2-1][2];
        for(int i=0;i<size1;i++){
            for(int j=0;j<size2;j++){
                pro[i+j][0]+=arr1[i]*arr2[j];

            }
        }
        return pro;
    }
    public void print(char poly){
        if(size==0) {
            System.out.println("[]");
            menu();
        }
        String function="";
        count1=head;//start
        count2=head;//A
        while(count1!=null&&count1.poly!=poly){//start
            count1=count1.next;
        }
        count2=count1;
        while(count2!=null&&count2.poly==count1.poly){
            count2=count2.next;
        }
        while (count1!=null && count1!=count2){
            if(count1.next!=null) {
                if(count1.coeff==0){
                    System.out.print("");
                }
                else if(count1.coeff==1) {
                    if(count1.exponent!=1 && count1.exponent!=0){
                        function=function.concat("x^" +count1.exponent+"+");
                    }
                    else if(count1.exponent==0){
                        function=function.concat( "1"+"+");
                    }
                    else if (count1.exponent==1){
                        function=function.concat("x"+"+");
                    }
                }

                else {
                    if(count1.exponent!=1 && count1.exponent!=0){
                        function=function.concat(count1.coeff + "x^" + count1.exponent + "+");
                    }
                    else if(count1.exponent==0){
                        function=function.concat(count1.coeff+"+");
                    }
                    else if(count1.exponent==1){
                        function=function.concat(count1.coeff + "x" + "+");
                    }
                }
            }
            else {
                if(count1.coeff==0){
                    System.out.print("");
                }
                else{
                    function+=(count1.coeff);
                }
            }
            count1= count1.next;
        }
        for(int i=0;i<function.length();i++){
            if(function.charAt(i)=='+' && (i==function.length()-1 || (i!=function.length()-1 && function.charAt(i+1)=='-'))){
            }
            else{
                System.out.print(function.charAt(i));
            }
        }
        System.out.println("");

        menu();
    }
    public void clear(char poly){
        Node start=head;
        Node end=head;
        while(start!=null && start.poly!=poly){
            start=start.next;
        }
        end=start;
        while(end!=null && end.poly==poly){
            end=end.next;
        }
        System.out.println("[]");
        deleted+=poly;
        menu();
    }
    public class Node {
        public int coeff;
        public int exponent;
        public Node next;
        public char poly;
        public int size;
        public Node (int coeff,int exponent,char poly,int size) {
            this.coeff = coeff;
            this.exponent=exponent;
            this.poly=poly;
            this.size=size;
        }
    }
}
