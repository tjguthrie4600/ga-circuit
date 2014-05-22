package GeneticAlgorithm.Cell;

// Class to hold the genes of the chromosome
public class Cell
{
    // Class Variables
    private String input01;
    private String input02;
    private int gateFunction;

    // Constructor
    public Cell(String in1, String in2, int gate)
    {
	input01 = in1;
	input02 = in2;
	gateFunction = gate;
    }

    // Gets Input01
    public String getInput01()
    {
	return input01;
    }

    // Gets Input02
    public String getInput02()
    {
	return input02;
    }

    // Get Gate Function
    public int getGateFunction()
    {
	return gateFunction;
    }

    // Set Input01
    public void setInput01(String in1)
    {
	input01 = in1;
    }

    // Set Input02
    public void setInput02(String in2)
    {
	input02 = in2;
    }

    // Set Gate Function
    public void setGateFunction(int gate)
    {
	gateFunction = gate;
    }

    // Prints a cell
    public void printCell()
    {
	System.out.println("----------");
	System.out.println("|" + input01 + "|" + input02 + "|" + gateFunction);
	System.out.println("----------");
    }
}
