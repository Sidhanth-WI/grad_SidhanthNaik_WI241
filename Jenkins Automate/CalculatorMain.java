public class CalculatorMain
{
	public static void main(String args[])
{
	int a=10;
	int b=5;
	Calculator c1=new Calculator();
	System.out.println("Addition:" +c1.add(a,b));
	System.out.println("Subtraction:"+c1.sub(a,b));
	System.out.println("Multiplication:"+c1.mul(a,b));
	System.out.println("Division:"+c1.div(a,b));

}
}